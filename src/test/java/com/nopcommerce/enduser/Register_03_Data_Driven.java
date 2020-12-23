package com.nopcommerce.enduser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.EndUserJson;

import commons.AbstractTest;
import commons.GlobalConstants;

public class Register_03_Data_Driven extends AbstractTest {
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUlr) {
		log.info("Pre-Condition - Open browser and application");
		driver = getBrowserDriver(browserName, appUlr);

		data = EndUserJson.getEndUserData(GlobalConstants.ROOT_FOLDER+"\\testdata\\com\\nopcommerce\\data\\EndUser.json");
		
		firstName = data.getFirstName();
		lastName = data.getLastName();
		email = data.getEmail()+ randomNumber() + "@hotmail.com";
		company = data.getCompany();
		password = data.getPassword();
	}

	@Test
	public void Register_To_System() {
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
	}

	@Test
	public void Verify_My_Account() {
		driver.findElement(By.className("ico-account")).click();
		verifyEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		verifyEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		verifyEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		verifyEquals(driver.findElement(By.id("Company")).getAttribute("value"), company);
	}

	WebDriver driver;
	EndUserJson data;
	String firstName,lastName,email,company,password, postFixEmail;
	
	@AfterClass
	public void afterClass() {
//		log.info("Post - Condition - Close browser");
//		removeDriver();
	}

}
