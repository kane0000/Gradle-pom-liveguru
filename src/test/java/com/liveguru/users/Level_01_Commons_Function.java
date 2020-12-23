package com.liveguru.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class Level_01_Commons_Function extends AbstractPage {
	WebDriver driver;
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		openPageUrl(driver, "http://live.demoguru99.com/");
		setImplicitWait(driver, 30);
	 }
		@BeforeMethod
		  public void beforeMethod() {
		clickToElement(driver, "//div[@class='footer']//a[text()='My Account']");
		clickToElement(driver, "//a[@title='Create an Account']");
		}
	@Test
	public void Register_01_Empty_Data() {
		clickToElement(driver, "//div[@class='buttons-set']//button");
		
		Assert.assertEquals(getTextElement(driver, "//div[@id='advice-required-entry-firstname']"), "This is a required field.");
		Assert.assertEquals(getTextElement(driver, "//div[@id='advice-required-entry-lastname']"), "This is a required field.");
		Assert.assertEquals(getTextElement(driver, "//div[@id='advice-required-entry-email_address']"), "This is a required field.");
		Assert.assertEquals(getTextElement(driver, "//div[@id='advice-required-entry-password']"), "This is a required field.");
		Assert.assertEquals(getTextElement(driver, "//div[@id='advice-required-entry-confirmation']"), "This is a required field.");
	}
	@Test
	public void Register_02_Invalid_Email() {
		sendkeyToElement(driver, "//input[@id='email_address']", "123@123.123");
		clickToElement(driver, "//div[@class='buttons-set']//button");
		
		Assert.assertEquals(getTextElement(driver, "//div[@id='advice-validate-email-email_address']"), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	@Test
	public void Register_03_Password_Less_6_Chars() {
		sendkeyToElement(driver, "//input[@id='password']", "1234");
		clickToElement(driver, "//div[@class='buttons-set']//button");
		
		Assert.assertEquals(getTextElement(driver, "//div[@id='advice-validate-password-password']"), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void Register_04_Confirm_Password_Notmatching() {
		sendkeyToElement(driver, "//input[@id='password']", "123456");
		sendkeyToElement(driver, "//input[@id='confirmation']", "654321");
		clickToElement(driver, "//div[@class='buttons-set']//button");
		
		Assert.assertEquals(getTextElement(driver, "//div[@id='advice-validate-cpassword-confirmation']"), "Please make sure your passwords match.");
		
	}
	@Test
	public void Register_05_Valid_Data() {
		sendkeyToElement(driver, "//input[@id='firstname']", "Kane");
		sendkeyToElement(driver, "//input[@id='lastname']", "Lee");
		sendkeyToElement(driver, "//input[@id='email_address']", "kanelee"+randomNumber()+"@gmai.com");
		sendkeyToElement(driver, "//input[@id='password']", "123456");
		sendkeyToElement(driver, "//input[@id='confirmation']", "123456");
		clickToElement(driver, "//div[@class='buttons-set']//button");
		
		Assert.assertEquals(getTextElement(driver, "//li[@class='success-msg']//span"), "Thank you for registering with Main Website Store.");
	}
 

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
