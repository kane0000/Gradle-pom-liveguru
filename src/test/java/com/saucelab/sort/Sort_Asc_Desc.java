package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObject.saucelab.SortPageObject;

public class Sort_Asc_Desc extends AbstractTest {
	private WebDriver driver;
	SortPageObject sortPage;
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		sortPage = new SortPageObject(driver);
	}
		
	@Test
	public void TC_01_Sort_Name() {
		sortPage.selectItemInDropdown("Name (A to Z)");
		verifyTrue(sortPage.isNameSortAsceding());
		
		sortPage.selectItemInDropdown("Name (Z to A)");
		verifyTrue(sortPage.isNameSortDesceding());
	}
	@Test
	public void TC_02_Sort_Price() {
		sortPage.selectItemInDropdown("Price (low to high)");
		verifyTrue(sortPage.isPriceSortAsceding());
		
		sortPage.selectItemInDropdown("Price (high to low)");
		verifyTrue(sortPage.isPriceSortDesceding());
	}
		

	
	@AfterClass
	public void afterClass() {
//		removeDriver();
	}

}
