package com.siainnovation.apirest.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CreditCardType {
    private Long id;
    private String type;
}
