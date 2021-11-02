package com.siainnovation.apirest.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.siainnovation.apirest.exception.ExceptionType;
import com.siainnovation.apirest.exception.Exceptions;
import com.siainnovation.apirest.model.CreditCardType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class CreditCardService {

    public List<CreditCardType> getAllCreditCardType() throws Exceptions {
        List<CreditCardType> records = new ArrayList<>();
        String fileLocation = "/credit-cards.csv";

        String fileFormat = fileLocation.substring(fileLocation.length()-3,fileLocation.length());
        if(!fileFormat.equals("csv")){
            throw new Exceptions(ExceptionType.WRONG_FORMAT_EXCEPTION);
        }


        try (CSVReader csvReader = new CSVReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(fileLocation))))) {
            String[] values = null;
            // Read All Records
            List<String> columns = Arrays.asList(csvReader.readNext());
            if(columns.size() == 0){
                throw new Exceptions(ExceptionType.WRONG_FORMAT_EXCEPTION);
            }
            while ((values = csvReader.readNext()) != null) {
                CreditCardType creditCardType = new CreditCardType();
                creditCardType.setId(Long.valueOf(values[0].trim()));
                creditCardType.setType(values[1]);
                records.add(creditCardType);
            }

        } catch (  IOException | CsvValidationException | NumberFormatException e) {
            throw new Exceptions(ExceptionType.WRONG_FORMAT_EXCEPTION);
        }catch (NullPointerException nullPointerException){
            throw new Exceptions(ExceptionType.FILE_NOT_PRESENT_EXCEPTION);
        }
        return records;
    }

    public  Optional<CreditCardType> getCreditCardTypeById(Long idCreditCard) throws Exceptions {
        List<CreditCardType> creditCardTypeList = getAllCreditCardType();
        return creditCardTypeList.stream().filter(creditCard-> Objects.equals(creditCard.getId(), idCreditCard)).findFirst();

    }

    //get credit card type by type
    public Optional<CreditCardType> getCreditCardTypeByType(String type) throws Exceptions {
        List<CreditCardType> creditCardTypeList = getAllCreditCardType();
        return creditCardTypeList.stream().filter(creditCard-> creditCard.getType().equals(type)).findFirst();
    }

    public int getDifferenceDays(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000));
    }

}
