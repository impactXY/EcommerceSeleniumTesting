package com.example.pages;

import com.example.setup.LaptopFilter;
import com.example.setup.filtersArea;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class searchPage {
    private WebDriver driver;

    // Define locators
    private By searchbar = By.id("searchboxTrigger");
    private By productList = By.cssSelector("#card_grid .card-item");

    // Constructor to initialize WebDriver
    public searchPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to perform search
    public void searchForProduct(String productName) {
        WebElement searchbarElement = new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.elementToBeClickable(searchbar));
        searchbarElement.click();
        searchbarElement.sendKeys(productName);
        searchbarElement.sendKeys(Keys.RETURN);

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(productList));
    }

    // Method to get list of product elements
    public List<WebElement> getProductList() {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
    }

    public void scrollToMiddle(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "var yOffset = window.pageYOffset;" +
                        "window.scrollTo({ top: rect.top + yOffset - (window.innerHeight / 2), behavior: 'smooth' });",
                element
        );
    }

    // Method to apply a specific filter using LaptopFilter enum
    public void applyFilter(LaptopFilter filter) {
        filtersArea filterArea = filtersArea.fromDisplayName(filter.getFilterType());

        WebElement filterAreaElement = driver.findElement(By.xpath(filterArea.getXpath()));
        scrollToMiddle(filterAreaElement);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(filterAreaElement));

        // Step 4: Find the specific filter within the area using its filter name
        WebElement filterElement = driver.findElement(By.xpath(filter.getXPath()));

        // Step 5: Scroll to the specific filter element
        scrollToMiddle(filterElement);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(filterElement));

        WebElement filterElement1 = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(filter.getXPath())));
        filterElement1.click();
    }

    // Method to verify if a specific filter is applied
    public boolean isFilterApplied(LaptopFilter filter) {
        try {
            // Verify if the filter fragment is present in the URL
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Constructed URL: " + filter.getUrlFragment());
            if (currentUrl.contains(filter.getUrlFragment())) {
                return true; // Filter is applied as indicated by the URL
            }

            // Fallback: Verify if the filter element is visually marked as active
            WebElement filterElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(filter.getXPath())));
            String classAttribute = filterElement.getAttribute("class");
            return classAttribute != null && classAttribute.contains("active");

        } catch (TimeoutException e) {
            // If the filter element is not found or visible within the timeout, assume the filter is not applied
            return false;
        }
    }

}