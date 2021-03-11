package com.smartwebtech.scraper;

import com.smartwebtech.scraper.exception.PropertiesFileNotFoundException;
import com.smartwebtech.scraper.helper.AppProperties;
import com.smartwebtech.scraper.service.ExtractProductInfo;

public class AmazonScraper {

    public static void main(String[] args) {


        ExtractProductInfo productInfo = new ExtractProductInfo();

        productInfo.getProductDetails();


    }
}
