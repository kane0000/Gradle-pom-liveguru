package com.nopcommerce.enduser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.EndUser;

import commons.AbstractTest;

public class Register_02_Data_Out_Class extends AbstractTest {
	// cách này phù hợp cho các dự án có nhiều dữ liệu hơn.
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUlr) {
		log.info("Pre-Condition - Open browser and application");
		driver = getBrowserDriver(browserName, appUlr);

		email = EndUser.Register.EMAIL + randomNumber()+ "@hotmail.com";
	}

	@Test
	public void Register_To_System() {
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(EndUser.Register.FIRST_NAME);
		driver.findElement(By.id("LastName")).sendKeys(EndUser.Register.LAST_NAME);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(EndUser.Register.COMPANY_NAME);
		driver.findElement(By.name("Password")).sendKeys(EndUser.Register.PASSWORD);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(EndUser.Register.PASSWORD);
		driver.findElement(By.id("register-button")).click();
		
	}

	@Test
	public void Verify_My_Account() {
		driver.findElement(By.className("ico-account")).click();
		verifyEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), EndUser.Register.FIRST_NAME);
		verifyEquals(driver.findElement(By.id("LastName")).getAttribute("value"), EndUser.Register.LAST_NAME);
		verifyEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		verifyEquals(driver.findElement(By.id("Company")).getAttribute("value"), EndUser.Register.COMPANY_NAME);
	}

	WebDriver driver;
	String email;
	
	@AfterClass
	public void afterClass() {
//		log.info("Post - Condition - Close browser");
//		removeDriver();
	}

}
