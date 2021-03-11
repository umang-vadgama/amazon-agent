package com.smartwebtech.scraper.service;

import com.smartwebtech.scraper.exception.PropertiesFileNotFoundException;
import com.smartwebtech.scraper.helper.AppProperties;
import com.smartwebtech.scraper.helper.ChromeInstance;
import com.smartwebtech.scraper.helper.DriverOps;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ExtractProductInfo {

    AppProperties appProperties;
    DriverOps driverOps;
    WebDriver driver;

    public void getProductDetails(){

        try {
            appProperties = new AppProperties();
        } catch (PropertiesFileNotFoundException e) {
            e.printStackTrace();
        }


        driver = new ChromeInstance().getChromeDriver(appProperties.getChromeDriverPath(),appProperties.getDownloadFolderPath(),appProperties.isHeadless());
        driverOps = new DriverOps(driver);

        driver.get("https://www.amazon.in/");

        driverOps.waitForPageLoad(90);

        driverOps.waitForElementVisibility(By.name("field-keywords"),30);

        driver.findElement(By.name("field-keywords")).sendKeys(appProperties.getAmazonSearchProductName());

        driverOps.waitForElementClickable(By.id("nav-search-submit-button"),40);

        driver.findElement(By.id("nav-search-submit-button")).click();

        int index =1 ;
        boolean hasNext = true;
        List<WebElement> rows ;

        rows = driver.findElements(By.xpath("//span[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS-')]"));


        rows.stream().forEach( e -> {

            String tag = e.getAttribute("cel_widget_id");

            String productName = e.findElement(By.xpath("//span[@cel_widget_id='"+e.getAttribute("cel_widget_id")+"']//span[@class='a-size-medium a-color-base a-text-normal']")).getText();
            String productRating = null;

            boolean hasRating = e.findElements(By.xpath("//span[@cel_widget_id='"+e.getAttribute("cel_widget_id")+"']//span[@class='a-icon-alt']")).isEmpty();

            if(!hasRating){
                productRating = (String) ((JavascriptExecutor) driver).executeScript("return document.evaluate( \"//span[@cel_widget_id='"+e.getAttribute("cel_widget_id")+"']//span[@class='a-icon-alt']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue.textContent; ");
            }else{
                productRating = "N/A";
            }

            String productPrize = e.findElement(By.xpath("//span[@cel_widget_id='"+e.getAttribute("cel_widget_id")+"']//span[@class='a-price-whole']")).getText();

            boolean hasActualPrize = e.findElements(By.xpath("//span[@cel_widget_id='"+e.getAttribute("cel_widget_id")+"']//span[@class='a-price a-text-price']/span")).isEmpty();

            String productActualPrize = null ;
            if(!hasActualPrize){
                //span[@class='a-price a-text-price']/span[1]
                productActualPrize = (String) ((JavascriptExecutor) driver).executeScript("return document.evaluate( \"//span[@cel_widget_id='"+e.getAttribute("cel_widget_id")+"']//span[@class='a-price a-text-price']/span[1]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue.textContent;");
            }else{
                productActualPrize = "N/A";
            }

            String saveRs = e.findElement(By.xpath("//span[@cel_widget_id='"+e.getAttribute("cel_widget_id")+"']//span[contains(text(),'Save ')]")).getText();

            System.out.printf("%25s : %s\n","TAG",tag);
            System.out.printf("%25s : %s\n","Product name",productName);
            System.out.printf("%25s : %s\n","Product rating",productRating);
            System.out.printf("%25s : %s %s\n","Product prize",productPrize,"Rs/-");
            System.out.printf("%25s : %s %s\n","Product actualPrize",productActualPrize,"Rs/-");
            System.out.printf("%25s : %s %s\n\n","You have save",saveRs,"RS/-");

        });

        driver.quit();


    }
}
