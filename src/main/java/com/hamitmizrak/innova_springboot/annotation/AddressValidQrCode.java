package com.hamitmizrak.innova_springboot.annotation;

import com.hamitmizrak.innova_springboot.data.repository.IAddressRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Address tablosundaki qr code için benzersiz bir içerik olması gerekiyor
// Eğer aynı isimde bir veri varsa ekleme ve hata ver yoksa databse eklesin
public class AddressValidQrCode implements ConstraintValidator<AddressUniqueQrCode, String> {

    // Injection
    private final IAddressRepository iAddressRepository;
    private final MessageSource messageSource;


    @Override
    public boolean isValid(String addressQrCode, ConstraintValidatorContext constraintValidatorContext) {
       //String findQrCode =addressQrCode+" böyle bir Qr Code yoktur ekleyebilirsin.";
       String findQrCode= messageSource.getMessage("address.qr_code.unique.validation.constraints.NotNull.message",null, LocaleContextHolder.getLocale());
       // 1.YOL
        /*
                 AddressEntity addressEntityFind1=iAddressRepository
                .findAdressEntitiesByAddresseEntityDetailsEmbedable_AddressQrCode(addressQrCode)
                .orElseThrow(()-> new _404_NotFoundException(findQrCode));
        */

        // 2.YOL
      boolean isResult=  iAddressRepository
              .findAdressEntitiesByAddresseEntityDetailsEmbedable_AddressQrCode(addressQrCode)
              .isPresent();
        log.info("isValid: "+isResult);
        System.out.println("isValid: "+isResult);

        // Conditional
        // Eğer böyle bir kayıt varsa
        if(isResult){
            /*System.out.println(addressQrCode+" Aynı isimde başka bir Qr code vardır");*/
            System.out.println(findQrCode);
            return false;
        }else {
            System.out.println(addressQrCode+" Harika Farklı  isimde başka bir Qr code yazabilirsiniz");
        }
        return true;
    } // end isValid
} // end AddressValidQrCode
