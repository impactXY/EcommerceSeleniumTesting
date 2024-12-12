package com.example.tests;

import com.example.pages.searchPage;
import com.example.setup.Setup;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Searching extends Setup {

    @Test
    public void completeSearchTest() {
        searchPage searchPage = new searchPage(getDriver());

        //Search for "Laptop"
        searchPage.searchForProduct("Laptop");

        //Verify products are displayed
        List<WebElement> productElements = searchPage.getProductList();
        Assert.assertFalse(productElements.isEmpty(), "No products found for the search term");

        //Verify the relevancy of products
        for (WebElement product : productElements) {
            String productTitle = product.getAttribute("data-name");
            Assert.assertNotNull(productTitle, "Product title is missing.");
            Assert.assertTrue(productTitle.toLowerCase().contains("laptop"),
                    "Irrelevant product found: " + productTitle);
        }
    }
}
