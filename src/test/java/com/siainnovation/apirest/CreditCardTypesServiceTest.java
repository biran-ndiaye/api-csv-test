package com.siainnovation.apirest;

import com.siainnovation.apirest.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreditCardTypesServiceTest {

    @Autowired
    CreditCardService creditCardService;

    @Test
    public void creditCardServiceTest(){
       // creditCardService.getAllCreditCardType();
    }
}
