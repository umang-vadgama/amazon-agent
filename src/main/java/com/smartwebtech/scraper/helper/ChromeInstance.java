package com.smartwebtech.scraper.helper;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.HashMap;


public class ChromeInstance {


    public WebDriver getChromeDriver(String chromeDriverPath, String downloadFolderPath, boolean isHeadless) {

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();


        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("download.default_directory", downloadFolderPath);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        chromePrefs.put("download.prompt_for_download", false);

        options.setExperimentalOption("prefs", chromePrefs);

        if (isHeadless) {
            options.addArguments("--headless");
        }

        options.addArguments("--window-size=1980,700");

        return new ChromeDriver(options);

    }


}
