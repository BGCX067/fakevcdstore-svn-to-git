package com.thoughtworks.seleniumtest.testhelper;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SeleniumTestCase extends TestCase {

    private String originalContent;

    protected Selenium user;

    private String browser = "";

    public SeleniumTestCase() {
        String browserPath = new OSEnvironment().getVariable("BROWSER_PATH");
        if (StringUtils.isEmpty(browserPath)) {
            throw new RuntimeException("You must define browser " + "path using env variable BROWSER_PATH");
        }
        if (StringUtils.containsIgnoreCase(browserPath, "firefox")) {
            browser = "*firefox";
        } else if (StringUtils.containsIgnoreCase(browserPath, "iexplore")) {
            browser = "*iexplore";
        } else {
            throw new RuntimeException("BROWSER_PATH should either point to firefox or IE");
        }
    }

    public final void setUp() throws Exception {
        user = new DefaultSelenium("localhost", 4444, browser, "http://localhost:9090");
        user.start();
        doSetUp();
    }

    public final void tearDown() throws Exception {
        doTearDown();
        user.stop();
    }

    protected void doSetUp() throws Exception {
    }

    protected void doTearDown() throws Exception {
    }

}
