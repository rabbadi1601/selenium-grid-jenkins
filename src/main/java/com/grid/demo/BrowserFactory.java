package com.grid.demo;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserFactory {

    public static WebDriver getDriver(String browser) throws Exception {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            return new RemoteWebDriver(new URL(ConfigReader.get("grid.url")), options);
        }

        else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            return new RemoteWebDriver(new URL(ConfigReader.get("grid.url")), options);
        }

        else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            return new RemoteWebDriver(new URL(ConfigReader.get("grid.url")), options);
        }

        else {
            throw new RuntimeException("Invalid browser");
        }
    }
}