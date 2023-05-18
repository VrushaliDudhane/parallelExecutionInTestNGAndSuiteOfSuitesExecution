/**
 * 
 */
package com.tripadvisor.webapp.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tripadvisor.webapp.base.BaseTest;
import com.tripadvisor.webapp.pages.HomePage;



/**
 * This class demonstrate the unit and functional test cases on Home page 
 * @author Vrushali
 *
 */
public class HomePageTest extends BaseTest{
	
	
	Logger logger=Logger.getLogger(HomePageTest.class.getName());
	/**
	 * The objective of this test case is "To assert the title of apllication's home page"
	 * This test method uses hard assert concept from TestNG
	 */
	@Test(enabled=true)
	public void TC_TripAdvisor_HomePage_001()
	{
		HomePage objHomePage=new HomePage(driver);
		String expectedTitle= "Tripadvisor: Over a billion reviews & contributions for Hotels, Attractions, Restaurants, and more";
		String actualTitle=objHomePage.getTheHomePageTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Wrong Page Opened");
		logger.trace("TripAdvisor HomePage opened");
	}
	
	/**
	 * The objective of this case is "To verify the apllication's logo has been displayed"
	 * this method used Soft assert or verify concept from TestNG
	 */
	@Test(enabled=true,groups={"regression"})
	void TC_TripAdvisor_HomePage_002()
	{
		HomePage objHomePage=new HomePage(driver);
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(objHomePage.isLogoDisplayed(), true);
		sa.assertAll();
		if(objHomePage.isLogoDisplayed())
		{
			logger.trace("TripAdvisor logo has been displayed");
		}
		else
		{
			logger.error("TripAdvisor logo haven't been displayed");
		}
	}
	
	/**
	 * The objective of this case is "To assert the apllication's Sign In Link Displayed"
	 * This method assert that login link has been displayed
	 */
	@Test(enabled=true)
	void TC_TripAdvisor_HomePage_003()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isSignInDisplayed(), true);
		logger.trace("Sign In Link has been displayed and clickable");
	}
	
}
