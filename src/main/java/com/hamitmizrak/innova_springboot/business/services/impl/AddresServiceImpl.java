package com.hamitmizrak.innova_springboot.business.services.impl;

import com.hamitmizrak.innova_springboot.bean.ModelMapperBean;
import com.hamitmizrak.innova_springboot.business.dto.AddressDto;
import com.hamitmizrak.innova_springboot.business.services.interfaces.IAddressService;
import com.hamitmizrak.innova_springboot.data.embedded.AddresseEntityDetailsEmbedable;
import com.hamitmizrak.innova_springboot.data.entity.AddressEntity;
import com.hamitmizrak.innova_springboot.data.mapper.AddressMapper;
import com.hamitmizrak.innova_springboot.data.repository.IAddressRepository;
import com.hamitmizrak.innova_springboot.exception._404_NotFoundException;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

// LOMBOK
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@RequiredArgsConstructor
@Log4j2

//Service:  Asıl İş Yükünü yapan bean
@Service
public class AddresServiceImpl implements IAddressService<AddressDto, AddressEntity> {

    // INJECTION
    private final IAddressRepository iAddressRepository;
    private final ModelMapperBean modelMapperBean;


    // Model Mapper
    @Override
    public AddressDto entityToDto(AddressEntity addressEntity) {
        // 1.YOL (ModelMapper)
        /*return modelMapperBean.getModelMapperBeanMethod().map(addressEntity, AddressDto.class);*/

        // 2.YOL (Mapper Special)
        return AddressMapper.AddressEntityToAddressDto(addressEntity);
    }

    @Override
    public AddressEntity dtoToEntity(AddressDto addressDto) {
        // 1.YOL (ModelMapper)
        // return modelMapperBean.getModelMapperBeanMethod().map(addressDto, AddressEntity.class);

        // 2.YOL (Mapper Special)
        return AddressMapper.AddressDtoToAddressEntity(addressDto);
    }

    ////////////////////////////////////////////////////////////////
    // CRUD

    // CREATE (Address)
    // org.springframework.transaction
    @Transactional //(propagation = ) // () // create, delete, update yani manipulation işlemlerin
    @Override
    public AddressDto objectServiceCreate(AddressDto addressDto) {
        AddressEntity addressEntityCreate =dtoToEntity(addressDto);
        // Not: Kayıt veya güncellemede ID içini set eder
        addressEntityCreate=iAddressRepository.save(addressEntityCreate);
        return entityToDto(addressEntityCreate);
    }

    // LIST (Address)
    @Override
    public List<AddressDto> objectServiceList() {
        List<AddressEntity> addressEntities = iAddressRepository.findAll();
        return addressEntities.stream()
                //.sorted(Comparator.comparing((temp)-> temp.getAddressDetailsEmbedable().getCity()))
                //.map((temp)->AddressMapper.AddressEntityToAddressDto(temp)) // 1.YOL (Lambda Expression)
                .map(AddressMapper::AddressEntityToAddressDto)                // 2.YOL (Method Referances)
                .collect(Collectors.toList());
    }

    // FIND BY ID (Address)
    // REDIS : için aşağıdaki linktten çalışıp çalışmadığını bu linkten anlayabiliriz
    // http://localhost:4444/api/address/v1.0.0/find/1
    @Cacheable(value = "addressFindByIdCache", key = "#id")
    @Override
    public AddressDto objectServiceFindById(Long id) {
        //REDIS
        System.err.println("Redis başlamadan önce ilk burası çalışacak ancak redis çalışıyorsa bunu cache dakikası bitene kadar veya flush yapana kadar görmeyeceksiniz "+id);
        return iAddressRepository.findById(id)
                .map(AddressMapper::AddressEntityToAddressDto)
                .orElseThrow(()-> new _404_NotFoundException(id+" nolu veri yoktur"));
    }

    // UPDATE
    @Transactional // create, delete, update yani manipulation işlemlerin
    @Override
    public AddressDto objectServiceUpdate(Long id, AddressDto addressDto) {
        // Öncelikle ilgili Adresi bulalım
        AddressEntity addressEntityFindByUpdate= dtoToEntity(objectServiceFindById(id));

        // Embeddable Set
        AddresseEntityDetailsEmbedable addressDetailsEmbedableUpdate= new AddresseEntityDetailsEmbedable();
        addressDetailsEmbedableUpdate.setAddressQrCode(addressDto.getAddressQrCode());
        addressDetailsEmbedableUpdate.setCity(addressDto.getCity());
        addressDetailsEmbedableUpdate.setState(addressDto.getState());
        addressDetailsEmbedableUpdate.setZipCode(addressDto.getZipCode());
        addressDetailsEmbedableUpdate.setAvenue(addressDto.getAvenue());
        addressDetailsEmbedableUpdate.setDoorNumber(addressDto.getDoorNumber());
        addressDetailsEmbedableUpdate.setStreet(addressDto.getStreet());
        addressDetailsEmbedableUpdate.setDescription(addressDto.getDescription());

        // Embeddable Set
        addressEntityFindByUpdate.setAddresseEntityDetailsEmbedable(addressDetailsEmbedableUpdate);

        /*
        save: Veritabanına yazma işlemlerinin toplu olarak yapılmasını tercih ediyorsanız kullanın.
        saveAndFlush: Veritabanına hemen yazma ihtiyacı olan durumlarda (örneğin, başka bir işlem kaydedilen veriyi hemen kullanacaksa) kullanın.
        */
        //addressEntityFindByUpdate=iAddressRepository.save(addressEntityFindByUpdate);       // 1.YOL
        addressEntityFindByUpdate=iAddressRepository.saveAndFlush(addressEntityFindByUpdate); // 2.YOL
        return entityToDto(addressEntityFindByUpdate);   // 2.YOL
    }

    // DELETE
    @Transactional // create, delete, update yani manipulation işlemlerin
    @Override
    public AddressDto objectServiceDelete(Long id) {
        // Öncelikle ilgili Adresi bulalım
        AddressEntity addressEntityFindByDelete= dtoToEntity(objectServiceFindById(id));
        iAddressRepository.delete(addressEntityFindByDelete);
        return entityToDto(addressEntityFindByDelete);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ROOT (Spring MVC)
    // http://localhost:4444/api/address
    // http://localhost:4444/api/address/index
    @GetMapping("/index")
    public ResponseEntity<?> getRoot() {
        return ResponseEntity.ok("index");
    }

    ////////////////////////////////////////////////////////////////
    // PAGE & SORT


    // PAGE
    // import org.springframework.cache.annotation.Cacheable;
    // REDIS :
    @Cacheable(value = "addressPaginationCache", key = "#currentPage + '-' + #pageSize")
    @Override
    public Page<AddressDto> objectServicePagination(int currentPage, int pageSize) {

        System.out.println("Redis Data ");
        // import org.springframework.data.domain.Pageable;
        // Pageable nesnesini oluştur
        Pageable pageable= PageRequest.of(currentPage, pageSize);

        Page<AddressEntity> addressEntityPage = iAddressRepository.findAll(pageable);

        /*Page<AddressDto> addressDtoPage*/
        List<AddressDto> addressDtoPage =  iAddressRepository.findAll(pageable)
                .stream()
                .map(AddressMapper::AddressEntityToAddressDto)
                .collect(Collectors.toList());
        // DTO'ları içeren bir PageImpl oluştur
        // Bu yapı, List türündeki DTO nesnelerini PageImpl kullanarak Page nesnesine dönüştürür.
        // Böylece Spring Data JPA ile uyumlu bir sayfalama yapısı elde edilir.
        // PageImpl, toplam öğe sayısını ve sayfalama bilgilerini koruyarak hem performanslı hem de düzenli bir dönüşüm sağlar.
        return new PageImpl<>(addressDtoPage, pageable, addressEntityPage.getTotalElements());
    }
    /*
    PageImpl Kullanımı
    PageImpl, bir List türündeki veriyi Page nesnesine dönüştürmek için Spring Data JPA tarafından sağlanan bir sınıftır. PageImpl aşağıdaki durumlarda kullanılır:

    Stream veya List'ten Page oluşturmak istediğinizde.
    Orijinal Pageable bilgilerini ve toplam öğe sayısını (totalElements) korumak istediğinizde.
    */


    // SORTING (Herhangi bir kolon)
    // REDIS :
    @Cacheable(value = "addressSortedByCache", key = "#sortedBy")
    @Override
    public List<AddressDto> objectServiceListSortedBy(String sortedBy) {
        return iAddressRepository.findAll(Sort.by(Sort.Direction.ASC,sortedBy))
                .stream().map(AddressMapper::AddressEntityToAddressDto)
                .collect(Collectors.toList());
    }

    // SORTING (CITY ASC)
    // REDIS :
    @Cacheable(value = "addressSortedByCityAscCache")
    @Override
    public List<AddressDto> objectServiceListSortedByAsc() {
        // Dikkat: Eğer Embedable olmasaydı `city` yazsak yeterdi ancak `addresseEntityDetailsEmbedable.city` yazmalıyız
        return iAddressRepository.findAll(Sort.by(Sort.Direction.ASC,"addresseEntityDetailsEmbedable.city"))
                .stream()
                .map(AddressMapper::AddressEntityToAddressDto)
                .collect(Collectors.toList());
    }

    // SORTING (CITY DESC)
    // REDIS :
    @Cacheable(value = "addressSortedByCityDescCache")
    @Override
    public List<AddressDto> objectServiceListSortedByDesc() {
        // Dikkat: Eğer Embedable olmasaydı `city` yazsak yeterdi ancak `addresseEntityDetailsEmbedable.city` yazmalıyız
        return iAddressRepository.findAll(Sort.by(Sort.Direction.DESC,"addresseEntityDetailsEmbedable.city"))
                .stream()
                .map(AddressMapper::AddressEntityToAddressDto)
                .collect(Collectors.toList());
    }
} //end AddresServiceImpl
