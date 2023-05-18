/**
 * 
 */
package com.tripadvisor.webapp.tests;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tripadvisor.webapp.base.BaseTest;
import com.tripadvisor.webapp.commons.DataProviderSignIn;
import com.tripadvisor.webapp.pages.HomePage;
import com.tripadvisor.webapp.pages.SignInPage;
import com.tripadvisor.webapp.pages.UserHomePage;
import com.tripadvisor.webapp.utilities.PropertyFileReader;
import com.tripadvisor.webapp.utilities.ReadingXlsxSheet;

/**
 * This class demonstrate the unit and functional test cases on Sign in frame  
 * @author Vrushali
 *
 */
public class SignInPageTest extends BaseTest {
	
	Logger logger=Logger.getLogger(HomePageTest.class.getName());
	
	/**
	 * The objective of this test case is "To assert the Header of the frame"
	 * This test method uses hard assert concept from TestNG
	 */
	@Test(enabled=true,groups="regression")
	public void TC_TripAdvisor_SignInPage_001()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		String expected="Sign in to unlock the best of Tripadvisor.";
		Assert.assertEquals(objSignInPage.getSignInPageHeader(),expected);
		logger.trace(expected+ "header displayed on login Frame");
		//Assert.assertTrue(false);
	}
	
	/**
	 * The objective of this test case is "To verify Continue With Google option has been displayed"
	 */
	@Test(enabled=true)
	public void TC_TripAdvisor_SignInPage_002()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		String expected="Continue with Google";
		Assert.assertEquals(objSignInPage.textOnContinueWithGoogle(),expected);
		logger.trace(expected+ " Option displayed on login Frame");
	}
	
	/**
	 * The objective of this test case is "To verify Continue With Facebook option has been displayed"
	 */
	@Test(enabled=true)
	public void TC_TripAdvisor_SignInPage_003()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		String expected="Continue with Facebook";
		Assert.assertEquals(objSignInPage.textOnContinueWithFacebook(),expected);
		logger.trace(expected+ " Option displayed on login Frame");
	}
	
	
	/**
	 * The objective of this test case is "To verify Continue With email option has been displayed"
	 */
	@Test(enabled=true)
	public void TC_TripAdvisor_SignInPage_004()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		String expected="Continue with email";
		Assert.assertEquals(objSignInPage.textOnContinueWithEmail(),expected);
		logger.trace(expected+ " Option displayed on login Frame");
	}
	
	/**
	 * The objective of this test case is "To verify after clicking "Continue With email" indented pop up frame gets open"
	 */
	@Test(enabled=true)
	public void TC_TripAdvisor_SignInPage_005()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		objSignInPage.clickOnContinueWithEmail();
		String expected1="Welcome back.";
		Assert.assertEquals(objSignInPage.getEmailHeader(),expected1);
		logger.trace(expected1+ "header displayed on login Frame");
	}
	

	/**
	 * The objective of this test case is "To verify that user can login with valid credentials"
	 * This takes test data i.e valid credentials to sign in from config.properties file
	 */
	@Test(enabled=true)
	public void TC_TripAdvisor_SignInPage_006()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		objSignInPage.clickOnContinueWithEmail();
		UserHomePage objUserSignInPage=objSignInPage.signInWithCredentials(PropertyFileReader.readConfigFile().getProperty("emailAddress"),PropertyFileReader.readConfigFile().getProperty("password"));
		String expectedTitle= "Tripadvisor: Over a billion reviews & contributions for Hotels, Attractions, Restaurants, and more";
		String actualTitle=objUserSignInPage.getTheUserHomePageTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Wrong Page Opened");
		logger.trace("User logged in with valid credentials");	
	}
	
	/*
	 *  The objective of this test case is "To verify that user can not login with invalid credentials"
	 *  this test method demonstrate data driven testing 
	 *  this is negative test case
	 *  the data provider reads test data from Excel file and run the test cases
	 */
	@Test(dataProvider="dataProviderForSignIn",dataProviderClass=DataProviderSignIn.class,enabled=true)
	public void TC_TripAdvisor_SignInPage_Negative_007(Row rows)
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		objSignInPage.clickOnContinueWithEmail();
		String testData[]=ReadingXlsxSheet.readingXlsSheetRowWise(rows);
		objSignInPage.signInWithCredentials(testData[0],testData[1]);
		String expectedMessage= "Either your email or password was incorrect. Please try again or click the \"Forgot password?\" link below.";
		String actualMessage=objSignInPage.getTheErrorMessageOnInvalidCredetials();
		Assert.assertEquals(actualMessage, expectedMessage, "Some error in login page");
		logger.trace("User cannot logged in with invalidvalid credentials");	
	}
	
	/*
	 * The objective of this test case is "To verify that user can not login with Blank Email address"
	 * This is negative test case
	 */
	@Test(enabled=true)
	public void TC_TripAdvisor_SignInPage_Negative_008()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		objSignInPage.clickOnContinueWithEmail();
		objSignInPage.signInWithCredentials("TripAdvisorLogin",'P');
		String expectedMessage= "E-mail address is required";
		String actualMessage=objSignInPage.getTheErrorMessageOnBlankEmailAddress();
		Assert.assertEquals(actualMessage, expectedMessage, "Some error in login page");
		logger.trace("User cannot logged in with blank Email address");	
	}
	
	
	/*
	 * The objective of this test case is "To verify that user can not login with Blank Password"
	 *  This is negative test case
	 */
	@Test(enabled=true,groups="regression")
	public void TC_TripAdvisor_SignInPage_Negative_009()
	{
		HomePage objHomePage=new HomePage(driver);
		Assert.assertEquals(objHomePage.isLogoDisplayed(), true);
		SignInPage objSignInPage=objHomePage.clickOnSignIn();
		objSignInPage.clickOnContinueWithEmail();
		objSignInPage.signInWithCredentials("testingpurposesel@gmail.com",'E');
		String expectedMessage= "Your password should be at least 6 characters long.";
		String actualMessage=objSignInPage.getTheErrorMessageOnBlankPassword();
		Assert.assertEquals(actualMessage, expectedMessage, "Some error in login page");
		logger.trace("User cannot logged in with blank Password");	
	}

}
