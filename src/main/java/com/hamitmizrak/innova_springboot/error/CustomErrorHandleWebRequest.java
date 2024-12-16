package com.hamitmizrak.innova_springboot.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

// LOMBOK
@Log4j2
@RequiredArgsConstructor

// ErrorController (1)
// ErrorAttributes (2)

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


}
