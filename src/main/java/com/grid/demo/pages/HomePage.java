
package com.grid.demo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.grid.demo.DriverManager;

public class HomePage {

    private By searchBox = By.id("small-searchterms");
    private By searchBtn = By.cssSelector("input[value='Search']");


    
    public void searchProduct(String product) {

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(), Duration.ofSeconds(10));

        WebElement search =
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));

        search.clear();
        search.sendKeys(product);

        DriverManager.getDriver()
                .findElement(searchBtn)
                .click();
    }
}