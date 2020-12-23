package pageObject.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.admind.DashBoardPageUI;

public class DashboardPageObject extends AbstractPage {
	private WebDriver driver;
	public DashboardPageObject(WebDriver driver) {
	this.driver = driver;
	
}
	public boolean isHeaderTextDisplayed() {
		waitElementVisible(driver, DashBoardPageUI.HEADER_TEXT);
		return isElementDisplayed(driver, DashBoardPageUI.HEADER_TEXT);
	}
	
}
