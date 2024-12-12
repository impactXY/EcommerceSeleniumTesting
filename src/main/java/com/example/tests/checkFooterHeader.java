package com.example.tests;

import com.example.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class checkFooterHeader extends Setup {

    @BeforeMethod
    public void navigateToHomePage() {
        // Navigate to the homepage before each test
        getDriver().get("https://www.emag.ro/homepage");
    }

    @Test
    public void headerVisibilityAndFunctionality() {
        // Verify that the header is displayed
        WebElement header = getDriver().findElement(By.id("masthead"));
        assert header.isDisplayed() : "Header is not displayed";

        // Verify that the search bar is displayed and functional
        WebElement searchbar = getDriver().findElement(By.id("searchboxTrigger"));
        assert searchbar.isDisplayed() : "Search bar is not displayed in the header";
        searchbar.sendKeys("Laptop");
        searchbar.sendKeys(Keys.RETURN);

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains("Laptop"));
        assert getDriver().getTitle().contains("Laptop") : "Search did not return correct results";

        // Verify navigation link in the header
        WebElement categoryLink = getDriver().findElement(By.linkText("Laptopuri"));
        assert categoryLink.isDisplayed() : "Category link 'Laptopuri' is not displayed in the header";
        categoryLink.click();
        assert getDriver().getCurrentUrl().contains("laptopuri") : "Category link 'Laptopuri' did not navigate correctly";
    }

    @Test
    public void footerContactLinkTest() {
        // Verify that the footer is displayed
        WebElement footer = getDriver().findElement(By.xpath("//footer[@class='footer footer-default']"));
        assert footer.isDisplayed() : "Footer is not displayed";

        // Verify contact link in the footer
        WebElement contactLink = getDriver().findElement(By.linkText("Contact"));
        assert contactLink.isDisplayed() : "Contact link is not displayed in the footer";
        contactLink.click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("contact"));
        assert getDriver().getCurrentUrl().contains("contact") : "Contact link did not navigate correctly";
    }

    @Test
    public void footerSocialMediaLinksTest() {
        // Verify that the footer is displayed
        WebElement footer = getDriver().findElement(By.xpath("//footer[@class='footer footer-default']"));
        assert footer.isDisplayed() : "Footer is not displayed";

        // Verify the Facebook link is displayed and functional
        WebElement facebookLink = getDriver().findElement(By.cssSelector("a.btn.btn-link.font-size-lg.text-primary[href*='facebook.com']"));
        assert facebookLink.isDisplayed() : "Facebook link is not displayed in the footer";
        facebookLink.click();

        // Switch to the new tab (if the link opens a new tab)
        String originalWindow = getDriver().getWindowHandle();
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        // Verify if the correct Facebook page is opened
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("facebook.com/eMAG.ro"));
        assert getDriver().getCurrentUrl().contains("facebook.com/eMAG.ro") : "Facebook link did not navigate correctly";

        // Close the new tab and switch back to the original
        getDriver().close();
        getDriver().switchTo().window(originalWindow);
    }

    @Test(dataProvider = "pagesProvider")
    public void headerAndFooterMultiPageTest(String pageUrl) {
        // Navigate to the page URL
        getDriver().get(pageUrl);

        // Verify header is displayed
        WebElement header = getDriver().findElement(By.id("masthead"));
        assert header.isDisplayed() : "Header is not displayed on page: " + pageUrl;

        // Verify footer is displayed
        WebElement footer = getDriver().findElement(By.xpath("//footer[@class='footer footer-default']"));
        assert footer.isDisplayed() : "Footer is not displayed on page: " + pageUrl;
    }

    @DataProvider(name = "pagesProvider")
    public Object[] pagesProvider() {
        return new Object[]{
                "https://www.emag.ro/laptopuri",
                "https://www.emag.ro/telefoane",
                "https://www.emag.ro/cart/products?ref=cart"
        };
    }
}
