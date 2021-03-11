package com.smartwebtech.scraper.helper;

import com.smartwebtech.scraper.exception.PropertiesFileNotFoundException;


import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    String PROPERTIES_FILE_PATH = "\\application.properties";

    public Properties properties;

    String chromeDriverPath,downloadFolderPath,amazonSearchProductName,amazonProductGroup;

    boolean isHeadless;


    public AppProperties() throws PropertiesFileNotFoundException {


        init();

        this.chromeDriverPath = properties.getProperty("chrome-driver-path");
        this.downloadFolderPath = properties.getProperty("download-folder-path");
        this.isHeadless = Boolean.parseBoolean(properties.getProperty("headless-mode"));
        this.amazonSearchProductName = properties.getProperty("amazon-search-product-name");
        this.amazonProductGroup = properties.getProperty("amazon-product-group");

    }

    public void init() throws PropertiesFileNotFoundException{
        try{
            properties = new Properties();
            properties.load(AppProperties.class.getResourceAsStream("/application.properties"));
        }catch (IOException e){
            throw new PropertiesFileNotFoundException(PropertiesFileNotFoundException.PropertiesFileNotFoundException(PROPERTIES_FILE_PATH,e.getMessage()));
        }
    }




    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public void setChromeDriverPath(String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    public String getDownloadFolderPath() {
        return downloadFolderPath;
    }

    public void setDownloadFolderPath(String downloadFolderPath) {
        this.downloadFolderPath = downloadFolderPath;
    }

    public String getAmazonSearchProductName() {
        return amazonSearchProductName;
    }

    public void setAmazonSearchProductName(String amazonSearchProductName) {
        this.amazonSearchProductName = amazonSearchProductName;
    }

    public String getAmazonProductGroup() {
        return amazonProductGroup;
    }

    public void setAmazonProductGroup(String amazonProductGroup) {
        this.amazonProductGroup = amazonProductGroup;
    }

    public boolean isHeadless() {
        return isHeadless;
    }

    public void setHeadless(boolean headless) {
        isHeadless = headless;
    }
}
