package com.smartwebtech.scraper.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverOps {

    WebDriver driver;

    public DriverOps(WebDriver driver){
        this.driver=driver;
    }

    public void waitForElementVisibility(By selector,int timeout){
        new WebDriverWait(driver ,timeout).until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public void waitForElementClickable(By selector,int timeout){

        new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(selector));
    }

    public void waitForPageLoad(int timeout){
        /*
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete") ;
            }
        };

        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(pageLoadCondition);
        */

        new WebDriverWait(driver,timeout).until(
                new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete") ;
            }
        });
    }


}
