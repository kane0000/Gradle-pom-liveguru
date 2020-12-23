package com.jquery.upload;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.jQuery.PageGeneratorManager;
import pageObjects.jQuery.UploadPageObject;

public class Level_11_Upload_File extends AbstractTest {
	private WebDriver driver;
	private UploadPageObject uploadPage;
	String name_image_01 = "img_01.jpg";
	String name_image_02 = "img_02.jpg";
	String name_image_03 = "img_03.jpg";
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		uploadPage = PageGeneratorManager.getUploadPage(driver);
	}
		
	@Test
	public void TC_01_Upload_One_File_Once() {
		uploadPage.uploadMultipleFiles(driver, name_image_01);
		
		Assert.assertTrue(uploadPage.isFileLoaded(name_image_01));
		uploadPage.clickToStartButton(name_image_01);
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(name_image_01));
		
		uploadPage.uploadMultipleFiles(driver, name_image_02);
		Assert.assertTrue(uploadPage.isFileLoaded(name_image_02));
		uploadPage.clickToStartButton(name_image_02);
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(name_image_02));
		
		uploadPage.uploadMultipleFiles(driver, name_image_03);
		Assert.assertTrue(uploadPage.isFileLoaded(name_image_03));
		uploadPage.clickToStartButton(name_image_03);
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(name_image_03));
	}
	@Test
	public void TC_02_Upload_Multiple_File_Once() {
		uploadPage.uploadMultipleFiles(driver, name_image_01, name_image_02, name_image_03);
	
		Assert.assertTrue(uploadPage.isFileLoaded(name_image_01));
		Assert.assertTrue(uploadPage.isFileLoaded(name_image_02));
		Assert.assertTrue(uploadPage.isFileLoaded(name_image_03));
		uploadPage.clickToStartButton(name_image_01);
		uploadPage.clickToStartButton(name_image_02);
		uploadPage.clickToStartButton(name_image_03);
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(name_image_01));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(name_image_02));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(name_image_03));
	}
		

	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}

}
