package pageObject.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.PostDetailPageUI;

public class PostDetailPageObject extends AbstractPage {
	private WebDriver driver;
	public PostDetailPageObject(WebDriver driver) {
	this.driver = driver;
}
	public boolean isTitleNameDisplayed(String titleName) {
		waitElementVisible(driver, PostDetailPageUI.TITLE_NAME, titleName);
		return isElementDisplayed(driver, PostDetailPageUI.TITLE_NAME, titleName);
	}
	public boolean isTitleNameUnDisplayed(String titleName) {
		waitElementInvisible(driver, PostDetailPageUI.TITLE_NAME, titleName);
		return isElementUndisplayed(driver, PostDetailPageUI.TITLE_NAME, titleName);
	}
	public boolean isCategoryNameDisplayed(String categoryName) {
		waitElementVisible(driver, PostDetailPageUI.CATEGORY_NAME, categoryName);
		return isElementDisplayed(driver, PostDetailPageUI.CATEGORY_NAME, categoryName);
	}
	public boolean isCategoryNameUnDisplayed(String categoryName) {
		waitElementInvisible(driver, PostDetailPageUI.CATEGORY_NAME, categoryName);
		return isElementUndisplayed(driver, PostDetailPageUI.CATEGORY_NAME, categoryName);
	}
	public boolean isImageDisplayed(String imageName) {
		imageName=imageName.split("\\.")[0];
		waitElementVisible(driver, PostDetailPageUI.IMAGE_NAME, imageName);
		sleepInSecond(1);
		return isElementDisplayed(driver, PostDetailPageUI.IMAGE_NAME, imageName)
				&& isImageDisplayed(driver, PostDetailPageUI.IMAGE_NAME, imageName);
	}
	public boolean isContentNameDisplayed(String contentName) {
		waitElementVisible(driver, PostDetailPageUI.CONTENT_NAME, contentName);
		return isElementDisplayed(driver, PostDetailPageUI.CONTENT_NAME, contentName);
	}
	public boolean isTagsNameDisplayed(String tagName) {
		waitElementVisible(driver, PostDetailPageUI.TAG_NAME, tagName);
		return isElementDisplayed(driver, PostDetailPageUI.TAG_NAME, tagName);
	}
	public boolean isTagsNameUnDisplayed(String tagName) {
		waitForJStoLoad(driver);
		waitElementInvisible(driver, PostDetailPageUI.TAG_NAME, tagName);
		return isElementUndisplayed(driver, PostDetailPageUI.TAG_NAME, tagName);
	}
	public boolean isDateCreatedNameDisplayed(String dateCreate) {
		waitElementVisible(driver, PostDetailPageUI.DAY_CREATE, dateCreate);
		return isElementDisplayed(driver, PostDetailPageUI.DAY_CREATE, dateCreate);
	}
	public boolean isAuthorNameDisplayed(String authorName) {
		waitElementVisible(driver, PostDetailPageUI.AUTHOR_NAME, authorName);
		return isElementDisplayed(driver, PostDetailPageUI.AUTHOR_NAME, authorName);
	}
}
