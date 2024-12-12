package com.example.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Setup {
    public ChromeOptions options = new ChromeOptions();
    public JavascriptExecutor js;
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeClass
    public void setUpOnce() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserName", "chrome");

        try {
            driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
            js = (JavascriptExecutor) getDriver();
            driver.get().manage().window().maximize();
            driver.get().get("https://www.emag.ro");

            dismissCookiesAndPopups();
        } catch (MalformedURLException e) {
            System.err.println("Invalid Grid URL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error setting up WebDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void dismissCookiesAndPopups() {
        for (popupLocators popup : popupLocators.values()) {
            clickElementWithRetry(popup.getLocator(), 3, 20, "Popup not found after multiple attempts.");
        }
    }

    private void clickElementWithRetry(String xpath, int retryCount, int waitSeconds, String notFoundMessage) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitSeconds));
        for (int attempts = 0; attempts < retryCount; attempts++) {
            try {
                boolean isPresent = getDriver().findElements(By.xpath(xpath)).size() > 0;
                if (isPresent) {
                    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                    js.executeScript("arguments[0].scrollIntoView(true);", element);
                    Thread.sleep(500);
                    js.executeScript("arguments[0].click();", element);
                    return;
                } else {
                    System.out.println("Element not found. Retrying...");
                }
            } catch (Exception e) {
                System.out.println("Attempt failed: " + e.getMessage());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(notFoundMessage);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    @AfterClass
    public void tearDownOnce() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
