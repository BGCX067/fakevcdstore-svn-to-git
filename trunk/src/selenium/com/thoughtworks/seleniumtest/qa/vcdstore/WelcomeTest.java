package com.thoughtworks.seleniumtest.qa.vcdstore;


public class WelcomeTest extends BaseTestCase {

	public void testFirstPage() throws InterruptedException{
		openAndWaiting("/vcdstore");
		user.type("userName","user1");
		user.click("commit");
		user.waitForPageToLoad("2000");
		//assertEquals("Welcome, user1",user.getTitle());
	}
}
