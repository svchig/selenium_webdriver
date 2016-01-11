package com.epam.webdriver.task1.tests;

import com.epam.webdriver.task1.core.BaseTests;
import com.epam.webdriver.task1.core.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Siarhei_Chyhir on 12/29/2015.
 */
public class EBayTest extends BaseTests {
    public static final String  USERNAME = "siarhei_chyhir@epam.com";
    public static final String PASSWORD = "Sergh13";

    @Test(priority = 0)
    public void openEBayHomepageTest(){
        Driver.get().get("http://ebay.com");
        Assert.assertTrue(Driver.get().getTitle().contains("eBay"), "eBay page is not opened");
    }

//    @Test(priority = 1, dependsOnMethods = {"openEBayHomepageTest"})
    @Test(priority = 1)
    public void navigateToSignInPageTest(){
        Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().getCurrentUrl().contains("signin.ebay"), "Sighin eBay page is not opened");
    }

//    @Test(priority = 2, dependsOnMethods = {"navigateToSignInPageTest"})
    @Test(priority = 2)
    public void signInToEBayTest(){
        Driver.get().findElement(By.xpath("//input[@id='userid'][@class='fld'][@type='text']")).sendKeys(USERNAME);
        Driver.get().findElement(By.xpath("//input[@id='pass'][@class='fld'][@type='password']")).sendKeys(PASSWORD);
        Driver.get().findElement(By.xpath("//input[@id='sgnBt'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[@id='gh-ug']/b")).getText().contains("Siarhei"), "User is not logined on eBay");
    }

//    @Test(priority = 3, dependsOnMethods = {"signInToEBayTest", "openEBayHomepageTest"})
    @Test(priority = 3)
    public void findAndOpenProductTest(){
        Driver.get().findElement(By.xpath("//input[@id='gh-ac'][@type='text']")).sendKeys("knives");
        Driver.get().findElement(By.xpath("//input[@id='gh-btn'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElements(By.id("ResultSetItems")).size() > 0, "Search Result List does not present on eBay");
        Driver.get().findElement(By.partialLinkText("TAC FORCE BLACK ")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//h1[@id='itemTitle'][@itemprop='name']")).getText().contains("TAC FORCE BLACK "), "The product page is not opened");
    }

//    @Test(priority = 4, dependsOnMethods = {"signInToEBayTest", "findAndOpenProductTest"})
    @Test(priority = 4)
    public void addProductToBasketTest(){
        Driver.get().findElement(By.xpath("//a[@id='isCartBtn_btn'][@role='button']")).click();
        Assert.assertTrue(Driver.get().findElements(By.xpath("//i[@id='gh-cart-n']")).size() > 0, "The product does not exist in basket");
    }

//    @Test(priority = 5, dependsOnMethods = {"signInToEBayTest", "addProductToBasketTest"})
    @Test(priority = 5)
    public void navigateToBasketTest(){
        Driver.get().findElement(By.xpath("//i[@id='gh-cart-i']")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//h1[@class='mb15 nowrap']")).getText().contains("Your eBay Shopping Cart"), "The shopping cart  page is not opened");
    }

//    @Test(priority = 6, dependsOnMethods = {"signInToEBayTest", "navigateToBasketTest"})
    @Test(priority = 6)
    public void removeAssignedProductFromBasketTest(){
        Driver.get().findElement(By.xpath("//a[@class='action actionLink'][@aria-label='Remove']")).click();
        Assert.assertTrue(Driver.get().findElements(By.xpath("//i[@id='gh-cart-n']")).size() == 0, "The product is not removed from basket");
    }

//    @Test(priority = 7, dependsOnMethods = {"signInToEBayTest"})
    @Test(priority = 7)
    public void signOutFromEBayTest(){
        Driver.get().findElement(By.xpath("//a[@id='gh-ug']/b")).click();
        Driver.get().findElement(By.xpath("//li[@id='gh-uo']/a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).isDisplayed(), "User is not logouted from eBay");
    }

}
