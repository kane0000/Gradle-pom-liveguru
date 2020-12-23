package pageObject.saucelab;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.saucelab.SortPageUI;

public class SortPageObject extends AbstractPage {
	 WebDriver driver;
	public SortPageObject(WebDriver driver) {
	this.driver = driver;
}
	public void selectItemInDropdown(String itemValue) {
		waitElementClickable(driver, SortPageUI.PRODUCT_SORT);
		selectItemDropdown(driver, SortPageUI.PRODUCT_SORT, itemValue);
		
	}
	public boolean isNameSortAsceding() {
		waitElementsVisible(driver, SortPageUI.ITEM_NAME);
		return isDataStringSortedAscending(driver, SortPageUI.ITEM_NAME);
	}
	public boolean isNameSortDesceding() {
		waitElementsVisible(driver, SortPageUI.ITEM_NAME);
		return isDataStringSortedDescending(driver, SortPageUI.ITEM_NAME);
	}
	public boolean isPriceSortAsceding() {
		waitElementsVisible(driver, SortPageUI.ITEM_PRICE);
		return isPriceSortedAscending(driver, SortPageUI.ITEM_PRICE);
	}
	public boolean isPriceSortDesceding() {
		waitElementsVisible(driver, SortPageUI.ITEM_PRICE);
		return isPriceSortedDescending(driver, SortPageUI.ITEM_PRICE);
	}
}
