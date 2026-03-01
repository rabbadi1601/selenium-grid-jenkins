package com.grid.demo.pages;


import org.openqa.selenium.By;
import com.grid.demo.DriverManager;

public class ProductPage {

    private By firstProduct = By.cssSelector(".product-title a");

    private By addToCartBtn = By.cssSelector("input[value='Add to cart']");
    public void openFirstProduct() {
        DriverManager.getDriver().findElement(firstProduct).click();
    }

    public void addToCart() {
        DriverManager.getDriver().findElement(addToCartBtn).click();
    }
}