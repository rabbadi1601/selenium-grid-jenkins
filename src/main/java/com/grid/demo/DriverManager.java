package com.grid.demo;



import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void unload() {
        driver.remove();
    }
}

/***
 * Why ThreadLocal Is Critical

If your DriverManager is:

private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

Each thread stores its own driver.

Without ThreadLocal:

One driver variable

Threads overwrite each other

Chaos

Only 1 browser visible
 */
