package com.nopcommerce.enduser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.DataHelper;

public class Register_01 extends AbstractTest {
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUlr) {
		log.info("Pre-Condition - Open browser and application");
		driver = getBrowserDriver(browserName, appUlr);
// cách này phù hợp cho các dự án có số lượng dữ liệu ít
		data = DataHelper.getData();
		
		firstName = data.getFirstName();
		lastName = data.getLastName();
		email = data.getEmail();
		company = data.getCompanyName();
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
	DataHelper data;
	String firstName,lastName,email,company,password;
	
	@AfterClass
	public void afterClass() {
//		log.info("Post - Condition - Close browser");
//		removeDriver();
	}

}
