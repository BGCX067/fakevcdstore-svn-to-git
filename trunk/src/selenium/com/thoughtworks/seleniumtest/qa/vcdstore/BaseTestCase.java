package com.thoughtworks.seleniumtest.qa.vcdstore;

import com.thoughtworks.seleniumtest.testhelper.SeleniumTestCase;

public class BaseTestCase extends SeleniumTestCase {
	Integer defaultTimeOut = 20000;
    public void openAndWaiting(String url,int timeout) {
        user.open(url);
        user.waitForPageToLoad(new Integer(timeout).toString());
    }
    public void openAndWaiting(String url) {
        user.open(url);
        user.waitForPageToLoad(defaultTimeOut.toString());
    }

}
