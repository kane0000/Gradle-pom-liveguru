package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveGuru.ManagerCustomerPageUI;

public class ManagerCustomerPageObject extends AbstractPage {
    WebDriver driver;
	public ManagerCustomerPageObject(WebDriver driver) {
	this.driver = driver;
	}
	public void clickToClose() {
		waitElementClickable(driver, ManagerCustomerPageUI.CLOSE_INCOMING);
		clickToElement(driver, ManagerCustomerPageUI.CLOSE_INCOMING);
		
	}
	public void inputTextboxTableByColumnName(String columnName, String value) {
		waitElementVisible(driver, ManagerCustomerPageUI.DYNAMIC_COLUMN_NAME, columnName);
		String columnNameIndex = String.valueOf(countElementNumber(driver, ManagerCustomerPageUI.DYNAMIC_COLUMN_NAME, columnName)+1);
		
		waitElementVisible(driver, ManagerCustomerPageUI.DYNAMIC_TEXTBOX_BY_INDEX, columnNameIndex);
		sendkeyToElement(driver, ManagerCustomerPageUI.DYNAMIC_TEXTBOX_BY_INDEX, value, columnNameIndex);
	}
	public void clickToButtonByTitleName(String titleName) {
		waitElementClickable(driver, ManagerCustomerPageUI.DYNAMIC_BUTTON_TITLE, titleName);
		clickToElement(driver, ManagerCustomerPageUI.DYNAMIC_BUTTON_TITLE, titleName);
	}
	public boolean isValuesDisplayRowNumber(String columnName, String rowNumber, String value) {
		waitElementVisible(driver, ManagerCustomerPageUI.DYNAMIC_COLUMN_NAME, columnName);
		String columnNameIndex = String.valueOf(countElementNumber(driver, ManagerCustomerPageUI.DYNAMIC_COLUMN_NAME, columnName)+1);
		
		waitElementVisible(driver, ManagerCustomerPageUI.DYNAMIC_VALUE_ROWNUMBER_AND_COLUMNNAME, rowNumber, columnNameIndex, value);
		return isElementDisplayed(driver, ManagerCustomerPageUI.DYNAMIC_VALUE_ROWNUMBER_AND_COLUMNNAME, rowNumber, columnNameIndex, value);
	}
	}

