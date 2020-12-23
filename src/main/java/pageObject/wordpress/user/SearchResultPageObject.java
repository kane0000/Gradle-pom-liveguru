package pageObject.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.SearchResultPageUI;

public class SearchResultPageObject extends AbstractPage {
	 WebDriver driver;
	public SearchResultPageObject(WebDriver driver) {
	this.driver = driver;
}
	public boolean isPostTitleDisplayedOnHearder(String newPostTitle) {
		waitElementVisible(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, newPostTitle);
		return isElementDisplayed(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, newPostTitle);
	}
	public boolean isPostTitleUnDisplayedOnHearder(String newPostTitle) {
		waitElementInvisible(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, newPostTitle);
		return isElementUndisplayed(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, newPostTitle);
	}
	
}
