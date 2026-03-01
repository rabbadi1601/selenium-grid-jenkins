package com.grid.demo;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.grid.demo.pages.HomePage;
import com.grid.demo.pages.ProductPage;

public class E2EGridTest {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) throws Exception {

        DriverManager.setDriver(BrowserFactory.getDriver(browser));

        DriverManager.getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void addProductToCartTest() {

       // DriverManager.getDriver().get("https://demowebshop.tricentis.com/");
        HomePage home = new HomePage();
        ProductPage product = new ProductPage();

        DriverManager.getDriver().get(ConfigReader.get("base.url"));

        home.searchProduct("computer");
        product.openFirstProduct();
        product.addToCart();

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(), Duration.ofSeconds(10));

        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".content")));

        Assert.assertTrue(successMsg.getText()
                .contains("The product has been added"));

        String cartQty = DriverManager.getDriver()
                .findElement(By.cssSelector(".cart-qty"))
                .getText();

        Assert.assertTrue(cartQty.contains("1"));

        System.out.println("Thread: " + Thread.currentThread().getId()
                + " | Cart quantity: " + cartQty);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.getDriver().quit();
        DriverManager.unload();
    }
}