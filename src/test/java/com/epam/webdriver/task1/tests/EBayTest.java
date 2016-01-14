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

    Driver driver = new Driver();

    @Test(priority = 0)
    public void openEBayHomepageTest(){
        System.out.println("Open eBay page test");
        Driver.get().get("http://ebay.com");
        Assert.assertTrue(Driver.get().getTitle().contains("eBay"), "eBay page is not opened");
    }

    @Test(priority = 1)
    public void signInOutEBayTest(){
        System.out.println("SignIn and SignOut eBay test");

        System.out.println("Step1: Open eBay page");
        Driver.get().get("http://ebay.com");
        Assert.assertTrue(Driver.get().getTitle().contains("eBay"), "eBay page is not opened");

        System.out.println("Step2: Navigate to sighIn eBay page");
        Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().getCurrentUrl().contains("signin.ebay"), "Sighin eBay page is not opened");

        System.out.println("Step3: sighIn to eBay");
        Driver.get().findElement(By.xpath("//input[@id='userid'][@class='fld'][@type='text']")).sendKeys(USERNAME);
        Driver.get().findElement(By.xpath("//input[@id='pass'][@class='fld'][@type='password']")).sendKeys(PASSWORD);
        Driver.get().findElement(By.xpath("//input[@id='sgnBt'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[@id='gh-ug']/b")).getText().contains("Siarhei")
                , "User is not logined on eBay");

        System.out.println("Step4: sighOut from eBay");
        Driver.get().findElement(By.xpath("//a[@id='gh-ug'][@class='gh-ua gh-control'][@role='button']")).click();
        Driver.get().findElement(By.xpath("//li[@id='gh-uo']/a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).isDisplayed()
                , "User is not logouted from eBay");
    }

    @Test(priority = 2)
    public void findAndOpenProductTest(){
        System.out.println("Find and open product on eBay");

        System.out.println("Step1: Open eBay page");
        Driver.get().get("http://ebay.com");
        Assert.assertTrue(Driver.get().getTitle().contains("eBay"), "eBay page is not opened");

        System.out.println("Step2: Search product on eBay");
        Driver.get().findElement(By.xpath("//input[@id='gh-ac'][@type='text']")).sendKeys("knives");
        Driver.get().findElement(By.xpath("//input[@id='gh-btn'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElements(By.id("ResultSetItems")).size() > 0
                , "Search Result List does not present on eBay");
        Driver.get().findElement(By.partialLinkText("Tac Force ")).click();
        Assert.assertTrue(Driver.get()
                .findElement(By.xpath("//h1[@id='itemTitle'][@itemprop='name']")).getText().toUpperCase().contains("TAC FORCE ")
                , "The product page is not opened");
    }

    @Test(priority = 3)
    public void addProductToBasketTest(){

        System.out.println("Find, open and send to Cart product on eBay Test");

        System.out.println("Step1: Open eBay page");
        Driver.get().get("http://ebay.com");
        Assert.assertTrue(Driver.get().getTitle().contains("eBay"), "eBay page is not opened");

        System.out.println("Step2: Navigate to sighIn eBay page");
        Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().getCurrentUrl().contains("signin.ebay"), "Sighin eBay page is not opened");

        System.out.println("Step3: sighIn to eBay");
        Driver.get().findElement(By.xpath("//input[@id='userid'][@class='fld'][@type='text']")).sendKeys(USERNAME);
        Driver.get().findElement(By.xpath("//input[@id='pass'][@class='fld'][@type='password']")).sendKeys(PASSWORD);
        Driver.get().findElement(By.xpath("//input[@id='sgnBt'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[@id='gh-ug']/b")).getText().contains("Siarhei")
                , "User is not logined on eBay");

        System.out.println("Step4: Search product on eBay");
        Driver.get().findElement(By.xpath("//input[@id='gh-ac'][@type='text']")).sendKeys("knives");
        Driver.get().findElement(By.xpath("//input[@id='gh-btn'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElements(By.id("ResultSetItems")).size() > 0
                , "Search Result List does not present on eBay");
        Driver.get().findElement(By.partialLinkText("Tac Force ")).click();
        Assert.assertTrue(Driver.get()
                .findElement(By.xpath("//h1[@id='itemTitle'][@itemprop='name']")).getText().toUpperCase().contains("TAC FORCE ")
                , "The product page is not opened");

        System.out.println("Step5: Send product to Cart");
        if (driver.isElementPresent(By.xpath("//a[@id='isCartBtn_btn'][@role='button']")))
            Driver.get().findElement(By.xpath("//a[@id='isCartBtn_btn'][@role='button']")).click();
        else if (driver.isElementPresent(By.xpath("//span[@id='atcLnk']/a")))
            Driver.get().findElement(By.xpath("//span[@id='atcLnk']/a")).click();
        Assert.assertTrue(Driver.get().findElements(By.xpath("//i[@id='gh-cart-n']")).size() > 0
                , "The product does not exist in basket");

        System.out.println("Step6: sighOut from eBay");
        Driver.get().findElement(By.xpath("//a[@id='gh-ug'][@class='gh-ua gh-control'][@role='button']")).click();
        Driver.get().findElement(By.xpath("//li[@id='gh-uo']/a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).isDisplayed()
                , "User is not logouted from eBay");

    }

    @Test(priority = 4/*, dependsOnMethods = "addProductToBasketTest"*/)
    public void removeAssignedProductFromBasketTest(){

        System.out.println("Remove product from Cart Test");

        System.out.println("Step1: Open eBay page");
        Driver.get().get("http://ebay.com");
        Assert.assertTrue(Driver.get().getTitle().contains("eBay"), "eBay page is not opened");

        System.out.println("Step2: Navigate to sighIn eBay page");
        Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().getCurrentUrl().contains("signin.ebay"), "Sighin eBay page is not opened");

        System.out.println("Step3: sighIn to eBay");
        Driver.get().findElement(By.xpath("//input[@id='userid'][@class='fld'][@type='text']")).sendKeys(USERNAME);
        Driver.get().findElement(By.xpath("//input[@id='pass'][@class='fld'][@type='password']")).sendKeys(PASSWORD);
        Driver.get().findElement(By.xpath("//input[@id='sgnBt'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[@id='gh-ug']/b")).getText().contains("Siarhei")
                , "User is not logined on eBay");

        System.out.println("Step4: Navigate to Cart page on eBay");
        Driver.get().findElement(By.xpath("//i[@id='gh-cart-i']")).click();
        Assert.assertTrue(Driver.get().getCurrentUrl().contains("cart.payments.ebay.com")
                , "The shopping cart  page is not opened");

        System.out.println("Step5: Remove product from Cart");
        int tempCount = Driver.get().findElements(By.xpath("//div[contains(@id,'sellerBucket_')]")).size();
        if (driver.isElementPresent(By.xpath("//a[@class='action actionLink'][@aria-label='Remove']")))
            Driver.get().findElement(By.xpath("//a[@class='action actionLink'][@aria-label='Remove']")).click();
        else if(driver.isElementPresent(By.xpath("//a[@class='action actionLink'][@aria-label='Удалить']")))
            Driver.get().findElement(By.xpath("//a[@class='action actionLink'][@aria-label='Удалить']")).click();
        Assert.assertTrue(Driver.get().findElements(By.xpath("//div[contains(@id,'sellerBucket_')]")).size() < tempCount
                , "The product is not removed from basket");

        System.out.println("Step6: sighOut from eBay");
        Driver.get().findElement(By.xpath("//a[@id='gh-ug'][@class='gh-ua gh-control'][@role='button']")).click();
        Driver.get().findElement(By.xpath("//li[@id='gh-uo']/a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).isDisplayed()
                , "User does not logout from eBay");

    }

    @Test(priority = 5/*, dependsOnMethods = "removeAssignedProductFromBasketTest"*/)
    public void smokeEBayTest(){

        System.out.println("Smoke eBay Test");

        System.out.println("Step1: Open eBay page");
        Driver.get().get("http://ebay.com");
        Assert.assertTrue(Driver.get().getTitle().contains("eBay"), "eBay page is not opened");

        System.out.println("Step2: Navigate to sighIn eBay page");
        Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().getCurrentUrl().contains("signin.ebay"), "Sighin eBay page is not opened");

        System.out.println("Step3: sighIn to eBay");
        Driver.get().findElement(By.xpath("//input[@id='userid'][@class='fld'][@type='text']")).sendKeys(USERNAME);
        Driver.get().findElement(By.xpath("//input[@id='pass'][@class='fld'][@type='password']")).sendKeys(PASSWORD);
        Driver.get().findElement(By.xpath("//input[@id='sgnBt'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[@id='gh-ug']/b")).getText().contains("Siarhei")
                , "User is not logined on eBay");

        System.out.println("Step4: Search product on eBay");
        Driver.get().findElement(By.xpath("//input[@id='gh-ac'][@type='text']")).sendKeys("knives");
        Driver.get().findElement(By.xpath("//input[@id='gh-btn'][@type='submit']")).click();
        Assert.assertTrue(Driver.get().findElements(By.id("ResultSetItems")).size() > 0
                , "Search Result List does not present on eBay");
        Driver.get().findElement(By.partialLinkText("Tac Force ")).click();
        Assert.assertTrue(Driver.get()
                .findElement(By.xpath("//h1[@id='itemTitle'][@itemprop='name']")).getText().toUpperCase().contains("TAC FORCE ")
                , "The product page is not opened");

        System.out.println("Step5: Send product to Cart");
        if (driver.isElementPresent(By.xpath("//a[@id='isCartBtn_btn'][@role='button']")))
            Driver.get().findElement(By.xpath("//a[@id='isCartBtn_btn'][@role='button']")).click();
        else if (driver.isElementPresent(By.xpath("//span[@id='atcLnk']/a")))
            Driver.get().findElement(By.xpath("//span[@id='atcLnk']/a")).click();
        Assert.assertTrue(Driver.get().findElements(By.xpath("//i[@id='gh-cart-n']")).size() > 0
                , "The product does not exist in basket");

        System.out.println("Step6: Navigate to Cart page on eBay");
        Driver.get().findElement(By.xpath("//i[@id='gh-cart-i']")).click();
        Assert.assertTrue(Driver.get().getCurrentUrl().contains("cart.payments.ebay.com")
                , "The shopping cart  page is not opened");

        System.out.println("Step7: Remove product from Cart");
        int tempCount = Driver.get().findElements(By.xpath("//div[contains(@id,'sellerBucket_')]")).size();
        if (driver.isElementPresent(By.xpath("//a[@class='action actionLink'][@aria-label='Remove']")))
            Driver.get().findElement(By.xpath("//a[@class='action actionLink'][@aria-label='Remove']")).click();
        else if(driver.isElementPresent(By.xpath("//a[@class='action actionLink'][@aria-label='Удалить']")))
            Driver.get().findElement(By.xpath("//a[@class='action actionLink'][@aria-label='Удалить']")).click();
        Assert.assertTrue(Driver.get().findElements(By.xpath("//div[contains(@id,'sellerBucket_')]")).size() < tempCount
                , "The product is not removed from basket");

        System.out.println("Step8: sighOut from eBay");
        Driver.get().findElement(By.xpath("//a[@id='gh-ug'][@class='gh-ua gh-control'][@role='button']")).click();
        Driver.get().findElement(By.xpath("//li[@id='gh-uo']/a[contains(@href,'signin.ebay.com')]")).click();
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[contains(@href,'signin.ebay.com')]")).isDisplayed()
                , "User does not logout from eBay");

    }

}
