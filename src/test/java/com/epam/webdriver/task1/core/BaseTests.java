package com.epam.webdriver.task1.core;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Siarhei_Chyhir on 12/29/2015.
 */
public class BaseTests {
    @BeforeMethod
    public void init(){
        Driver.init();
    }

    @AfterMethod
    public void cleanup(){
        Driver.tearDown();
    }
}
