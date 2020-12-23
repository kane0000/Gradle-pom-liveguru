package com.liveguru.users;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;

public class Level_11_Element_Undisplayed extends AbstractTest {
	WebDriver driver;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
	}
		
	@Test
	public void TC_01_() throws InterruptedException {
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// Có trên UI & DOM
		System.out.println("Start 1 :" + getDateTime());
		Assert.assertTrue(isElementDisplayed("//input[@name='reg_email__']"));
		System.out.println("End 1 :" + getDateTime());
		
		//Không có trên UI mà có trong DOM
		System.out.println("Start 2 :" + getDateTime());
		Assert.assertTrue(isContralUndisplayed("//input[@name='reg_email_confirmation__']"));
		System.out.println("End 2 :" + getDateTime());
		
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		Thread.sleep(3000);
		// Không có trên UI & DOM
		System.out.println("Start 3 :" + getDateTime());
		Assert.assertTrue(isContralUndisplayed("//div[@id='reg_box']"));
		System.out.println("End 3 :" + getDateTime());
	}

	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}
	// Mong đợi kiểm tra 1 element không hiển thị
	// Tìm 1 element: 1 locator duy nhất
public boolean isContralUndisplayed(String locator) {
	overrideImplicitTimeout(shortTime);
	List<WebElement> elements = driver.findElements(By.xpath(locator));
	overrideImplicitTimeout(longTime);
	if (elements.size()== 0) {
		return true;
	} else if(elements.size() > 0 && !elements.get(0).isDisplayed()){
		return true;
	}else {
		//size > 0 và element hiển thị
		return false;
	}
}
long longTime = 30;
long shortTime = 5;

public boolean isElementDisplayed(String locator) {
	try {
	// Happy path case
		// Element có trong DOM: dù có hiển thị trên UI hay không trả về giá trị true/false.
		return driver.findElement(By.xpath(locator)).isDisplayed();
	} catch (NoSuchElementException ex) {
		// Element không có trong DOM: khi không tìm thấy thì trả về false luôn.
		return false;
	}
}
// lấy ra ngày giờ hệ thống tại thời điểm chạy.
public String getDateTime() {
	Date date = new Date();
	return date.toString();
}
// Từ lúc được set thì timeout sẽ nhận giá trị cho các step về sau 
public void overrideImplicitTimeout(long timeInSecond) {
	driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
}
}
