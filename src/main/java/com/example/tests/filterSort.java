package com.example.tests;

import com.example.pages.searchPage;
import com.example.setup.LaptopFilter;
import com.example.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class filterSort extends Setup {

    @Test
    public void testSearchAndApplySingleFilter() {
        searchPage searchPage = new searchPage(getDriver());
        // Step 1: Perform a search
        searchPage.searchForProduct("laptop");

        // Step 2: Apply a single filter (Operating System: Windows 10 Pro)
        searchPage.applyFilter(LaptopFilter.OS_WINDOWS_10_PRO);

        // Step 3: Verify that the filter is applied
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(LaptopFilter.OS_WINDOWS_10_PRO.getUrlFragment()));

        // Step 3: Verify that the filter is applied
        boolean isFilterApplied = searchPage.isFilterApplied(LaptopFilter.OS_WINDOWS_10_PRO);
        Assert.assertTrue(isFilterApplied, "The Windows 10 Pro filter should be applied.");
    }

    @Test
    public void testSearchAndApplyMultipleFilters() {
        // Step 1: Perform a search
        searchPage searchPage = new searchPage(getDriver());
        searchPage.searchForProduct("laptop");

        // Step 2: Apply multiple filters
        searchPage.applyFilter(LaptopFilter.OS_WINDOWS_10_PRO);

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(LaptopFilter.OS_WINDOWS_10_PRO.getUrlFragment()));

        searchPage.applyFilter(LaptopFilter.BRAND_DELL);

        // Step 3: Verify that each filter is applied
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(LaptopFilter.BRAND_DELL.getUrlFragment()));

        Assert.assertTrue(searchPage.isFilterApplied(LaptopFilter.OS_WINDOWS_10_PRO), "The Windows 10 Pro filter should be applied.");
        Assert.assertTrue(searchPage.isFilterApplied(LaptopFilter.BRAND_DELL), "The Apple brand filter should be applied.");
    }

    @Test
    public void testSearchAndApplyRamFilter() {
        // Step 1: Perform a search
        searchPage searchPage = new searchPage(getDriver());
        searchPage.searchForProduct("laptop");

        // Step 2: Apply a RAM filter (e.g., 16GB)
        searchPage.applyFilter(LaptopFilter.RAM_16GB);

        // Step 3: Verify that the RAM filter is applied
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(LaptopFilter.RAM_16GB.getUrlFragment()));

        boolean isRamFilterApplied = searchPage.isFilterApplied(LaptopFilter.RAM_16GB);
        Assert.assertTrue(isRamFilterApplied, "The 16GB RAM filter should be applied.");
    }
}