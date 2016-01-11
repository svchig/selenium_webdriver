package com.epam.webdriver.task1.core;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by Siarhei_Chyhir on 12/29/2015.
 */
public class BaseTests {
    @BeforeClass
    public void init(){
        Driver.init();
    }

    @AfterClass
    public void cleanup(){
        Driver.tearDown();
    }
}
