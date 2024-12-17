package com.hamitmizrak.innova_springboot.controller.api.impl;

import com.hamitmizrak.innova_springboot.business.dto.AddressDto;
import com.hamitmizrak.innova_springboot.business.services.interfaces.IAddressService;
import com.hamitmizrak.innova_springboot.controller.api.interfaces.IAddressApi;
import com.hamitmizrak.innova_springboot.error.ApiResult;
import com.hamitmizrak.innova_springboot.exception._400_BadRequestException;
import com.hamitmizrak.innova_springboot.utils.FrontEnd;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Api: Dış dünyaya açılan kapı
@RestController
@RequestMapping("/api/address/v1.0.0")
// @CrossOrigin
// @CrossOrigin(origins = "http://localhost:4000")
@CrossOrigin(origins = {FrontEnd.REACT_URL, FrontEnd.ANGULAR_URL})
public class AddressApiImpl implements IAddressApi<AddressDto> {

    // Injection
    private final IAddressService iAddressService;
    private final MessageSource messageSource;

    // ApiResult Instance
    private ApiResult apiResult;

    /////////////////////////////////////////////////////////////////////////////////////////////////
    // CRUD

    // CREATE
    // http://localhost:4444/api/address/v1.0.0/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<?> objectApiCreate(@Valid @RequestBody AddressDto addressDto) {
        AddressDto addressDtoCreate= (AddressDto) iAddressService.objectServiceCreate(addressDto);
        // return ResponseEntity.status(201).body(addressDtoCreate); //1.YOL
        // return ResponseEntity.status(HttpStatus.CREATED).body(addressDtoCreate); //2.YOL
        // return new ResponseEntity<>(addressDtoCreate,HttpStatus.CREATED); //3.YOL
        // return  ResponseEntity.ok().body(addressDtoCreate); //4.YOL
        return  ResponseEntity.ok(addressDtoCreate); //5.YOL
    }

    // LIST
    // http://localhost:4444/api/address/v1.0.0/list
    @GetMapping(value = "/list")
    @Override
    public ResponseEntity<List<AddressDto>> objectApiList() {
        List<AddressDto> addressDtoList = iAddressService.objectServiceList();
        // Stream Value
        return ResponseEntity.ok(addressDtoList);
    }

// FIND BY ID
    // http://localhost:4444/api/address/v1.0.0/find
    // http://localhost:4444/api/address/v1.0.0/find/0
    // http://localhost:4444/api/address/v1.0.0/find/-1
    // http://localhost:4444/api/address/v1.0.0/find/%20%    boşluk:%20%
    // http://localhost:4444/api/address/v1.0.0/find/1
    @GetMapping({"/find/","/find/{id}"})
    @Override
    public ResponseEntity<?> objectApiFindById(@PathVariable(name="id",required = false) Long id) { //NOT: @PathVariable sadece yazabiliriz
        String message="";
        if(id ==null){
            throw new NullPointerException("Null Pointer Exception: Null değer");
        }else if(id==0){
            throw new _400_BadRequestException("Bad Request Exception: Kötü istek");
        } else if(id<0){
            // Config ApiResultValidationMessage
            // resource/ValidationMessages/ValidationMessages.properties => error.unauthorized
            message= messageSource.getMessage("error.unauthorized",null, LocaleContextHolder.getLocale());
            apiResult= new ApiResult();
            apiResult.setError("unAuthorized: Yetkisiz Giriş");
            apiResult.setPath("/api/address/v1.0.0/find");
            apiResult.setStatus(HttpStatus.UNAUTHORIZED.value());
            apiResult.setMessage(message);
            return ResponseEntity.ok(apiResult);
        }
        // Address Find By Id
        AddressDto addressDtoFind= (AddressDto) iAddressService.objectServiceFindById(id);
        return ResponseEntity.ok(addressDtoFind);
    }

    // UPDATE
    // http://localhost:4444/api/address/v1.0.0/find/1
    @PutMapping({"/update/","/update/id"})
    @Override
    public ResponseEntity<?> objectApiUpdate(@PathVariable(name = "id",required = false)  Long id, @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(iAddressService.objectServiceUpdate(id, addressDto));
    }

    // DELETE
    // http://localhost:4444/api/address/v1.0.0/delete/1
    @DeleteMapping({"/delete/","/delete/id"})
    @Override
    public ResponseEntity<?> objectApiDelete(@PathVariable(name = "id",required = false) Long id) {
        return ResponseEntity.ok(iAddressService.objectServiceDelete(id));
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // PAGING
    @Override
    public ResponseEntity<Page<?>> objectServicePagination(int currentPage, int pageSize) {
        return ResponseEntity.ok(iAddressService.objectServicePagination(currentPage, pageSize));
    }

    // SORTING (Belli kolona göre)
    @Override
    public ResponseEntity<List<AddressDto>> objectServiceListSortedBy(String sortedBy) {
        return ResponseEntity.ok(iAddressService.objectServiceListSortedBy(sortedBy));
    }

    // SORTING ASC
    @Override
    public ResponseEntity<List<AddressDto>> objectServiceListSortedByAsc() {
        return ResponseEntity.ok(iAddressService.objectServiceListSortedByAsc());
    }

    // SORTING DESC
    @Override
    public ResponseEntity<List<AddressDto>> objectServiceListSortedByDesc() {
        return ResponseEntity.ok(iAddressService.objectServiceListSortedByDesc());
    }

} //end AddressApiImpl
