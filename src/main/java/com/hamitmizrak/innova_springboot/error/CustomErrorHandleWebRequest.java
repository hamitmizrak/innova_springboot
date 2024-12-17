package com.hamitmizrak.innova_springboot.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LOMBOK
@Log4j2
@RequiredArgsConstructor

// ErrorController (1)
// ErrorAttributes (2)  =>  errorAttributes.getErrorAttributes(webRequest,ErrorAttributeOptions.defaults())
// WebRequest

// http://localhost:4444/error
@RestController
@CrossOrigin
public class CustomErrorHandleWebRequest implements ErrorController {

    // Injection
    // 1.YOL (Field Injection)
    // @Autowired
    // private ErrorAttributes errorAttributes;

    // 2.YOL (Constructor Injection)
    /*
    private final ErrorAttributes errorAttributes;
    @Autowired
    public CustomErrorHandleWebRequest(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }
    */

    // 3.YOL (Lombok Injection)
    private final ErrorAttributes errorAttributes;

    // Field
    // sm pv (ApiResult)
    private int status;
    private String message;
    private String path;
    private Map<String,Object> validationErrors;

    // ApiResult
    private ApiResult apiResult;

    // http://localhost:4444/error
    // Spring Framework'un hataları çok karmaşıktır ancak biz burada daha sade bir yapı yapmak için işlem yapıyoruz.
    @RequestMapping("/error")
    public ApiResult handleErrorMethod(WebRequest webRequest) {
        // Spring>=2.3
        Map<String,Object> attributes = errorAttributes.getErrorAttributes(
                webRequest,
                /*ErrorAttributeOptions.defaults()*/
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE,ErrorAttributeOptions.Include.BINDING_ERRORS)
        ); //end attributes

        // Spring'ten gelen verileri almak
        status=attributes.get("status")==null?0:Integer.parseInt(attributes.get("status").toString());
        message=(String) attributes.get("message");/*message=attributes.get("message").toString();*/
        path=(String) attributes.get("path");
        // ApiResult olarak set etmek
        apiResult=new ApiResult(path,message,status);

        // attributes içinde `errors` hatalar varsa;
        if(attributes.containsKey("errors")){
            List<FieldError> fieldErrors=(List<FieldError>) attributes.get("errors");
            validationErrors=new HashMap<>();

            // For döngüsünde hataları validationErrors set etmek
            for(FieldError fieldError : fieldErrors){
                validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            apiResult.setValidationErrors(validationErrors);
        }
        return apiResult;
    } //end handleErrorMethod
} // end CustomErrorHandleWebRequest

