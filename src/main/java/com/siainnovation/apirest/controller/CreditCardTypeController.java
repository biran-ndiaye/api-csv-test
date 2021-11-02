package com.siainnovation.apirest.controller;

import com.siainnovation.apirest.exception.ExceptionType;
import com.siainnovation.apirest.exception.Exceptions;
import com.siainnovation.apirest.model.CreditCardType;
import com.siainnovation.apirest.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class CreditCardTypeController {

    @Autowired
    CreditCardService creditCardService;

    @GetMapping( "/**")
    public ResponseEntity handleBadEndpoint(final HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionType.getMessageException(ExceptionType.ENDPOINT_NOT_EXIST_EXCEPTION));
    }


    @GetMapping("/csv-api/credit-card-types")
    public ResponseEntity getAllCreditCardType() {

        try {
            return  ResponseEntity.status(HttpStatus.OK).body( creditCardService.getAllCreditCardType());
        } catch (Exceptions  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/csv-api/credit-card-types/{creditCardTypeID}")
    public ResponseEntity getCreditCardById(@PathVariable String creditCardTypeID)  {
        try {
            Optional<CreditCardType> creditCardTypeOptional = creditCardService.getCreditCardTypeById(Long.parseLong(creditCardTypeID));
           if(creditCardTypeOptional.isPresent()){
               return ResponseEntity.status(HttpStatus.OK).body(creditCardTypeOptional);
           }else{
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionType.getMessageException(ExceptionType.WRONG_ID_PASSED_EXCEPTION));
           }

        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionType.getMessageException(ExceptionType.WRONG_ID_PASSED_EXCEPTION));
        }catch (Exceptions exceptions) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptions.getMessage());
        }


    }


}
