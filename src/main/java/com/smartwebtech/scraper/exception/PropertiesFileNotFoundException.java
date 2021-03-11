package com.smartwebtech.scraper.exception;

import java.io.IOException;

public class PropertiesFileNotFoundException extends Exception{

    public static String PropertiesFileNotFoundException(String propertiesFilePath,String errorMessage){

        return String.format("Properties file not found at location {}",propertiesFilePath,errorMessage);
    }

    public PropertiesFileNotFoundException(String message){
        super(message);
    }
}
