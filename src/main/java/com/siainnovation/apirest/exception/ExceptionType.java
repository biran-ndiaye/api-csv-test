package com.siainnovation.apirest.exception;

public enum ExceptionType {
    WRONG_FORMAT_EXCEPTION,
    FILE_NOT_PRESENT_EXCEPTION,
    WRONG_ID_PASSED_EXCEPTION,
    ENDPOINT_NOT_EXIST_EXCEPTION;

    public static String getMessageException(ExceptionType type){
        switch (type){
            case WRONG_FORMAT_EXCEPTION:
                return "CSV file corrupted or wrong format";
            case WRONG_ID_PASSED_EXCEPTION:
                return "Wrong ID passed";
            case FILE_NOT_PRESENT_EXCEPTION:
                return "CSV file not present at location";
            case ENDPOINT_NOT_EXIST_EXCEPTION:
                return "Endpoint not Exist";
            default:
                return "";
        }
    }
}
