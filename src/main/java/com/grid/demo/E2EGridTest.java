package com.grid.demo;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class E2EGridTest {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws Exception {
        driver = BrowserFactory.getDriver(browser);
    }

    @Test
    public void addProductToCartTest() throws InterruptedException {

        driver.get("https://demowebshop.tricentis.com/");

        // Search product
        driver.findElement(By.id("small-searchterms")).sendKeys("computer");
        driver.findElement(By.cssSelector("input[value='Search']")).click();

        // Click first product
        driver.findElement(By.cssSelector(".product-title a")).click();

        // Add to cart
        driver.findElement(By.id("add-to-cart-button-16")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement successMsg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".content"))
        );

        Assert.assertTrue(successMsg.getText().contains("The product has been added"));

        // Validate cart quantity
        String cartQty = driver.findElement(By.cssSelector(".cart-qty")).getText();
        Assert.assertTrue(cartQty.contains("1"));

        System.out.println("Product successfully added. Cart quantity: " + cartQty);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}