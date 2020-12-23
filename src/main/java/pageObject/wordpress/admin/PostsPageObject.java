package pageObject.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.wordpress.admind.AbstractWordpressPageUI;
import pageUI.wordpress.admind.PostsPageUI;
import pageUIs.liveGuru.ManagerCustomerPageUI;

public class PostsPageObject extends AbstractPage {
	private WebDriver driver;
	public PostsPageObject(WebDriver driver) {
	this.driver = driver;
}
	public NewEditPostPageObject clickToAddNewButton() {
		waitElementClickable(driver, PostsPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, PostsPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, PostsPageUI.CLASSIC_EDITOR_BUTTON);
		return PageGeneratorManager.getNewEditPostAdmindPage(driver);
	}
	public void inputToSearchTexbox(String valueName) {
		waitElementVisible(driver, PostsPageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, PostsPageUI.SEARCH_TEXTBOX,valueName);
	}
	public void clickToSearchButton() {
		waitElementClickable(driver, PostsPageUI.SEARCH_BUTTON);
		clickToElement(driver, PostsPageUI.SEARCH_BUTTON);
		
	}
	public boolean isNoPostFoundMesageDisplayed(String textValue) {
		waitElementVisible(driver,PostsPageUI.NO_POST_FOUND, textValue);
		return isElementDisplayed(driver,PostsPageUI.NO_POST_FOUND, textValue);
	}
	public boolean isValueDisplayedColumnNameByRowIndex(String columnName, String rowNumber, String value) {
		waitElementVisible(driver, ManagerCustomerPageUI.DYNAMIC_COLUMN_NAME, columnName);
		String columnNameIndex = String.valueOf(countElementNumber(driver, ManagerCustomerPageUI.DYNAMIC_COLUMN_NAME, columnName)+1);
		
		waitElementVisible(driver, ManagerCustomerPageUI.DYNAMIC_VALUE_ROWNUMBER_AND_COLUMNNAME, rowNumber, columnNameIndex, value);
		return isElementDisplayed(driver, ManagerCustomerPageUI.DYNAMIC_VALUE_ROWNUMBER_AND_COLUMNNAME, rowNumber, columnNameIndex, value);
	}
	public NewEditPostPageObject clickToPostDetailByTitleName(String newPostTitle) {
		waitElementVisible(driver, AbstractWordpressPageUI.POST_TITLE_LINK,newPostTitle);
		hoverToElement(driver, AbstractWordpressPageUI.POST_TITLE_LINK, newPostTitle);
		waitElementVisible(driver, AbstractWordpressPageUI.CLASSIC_EDITOR, newPostTitle);
		clickToElement(driver, AbstractWordpressPageUI.CLASSIC_EDITOR, newPostTitle);
		return PageGeneratorManager.getNewEditPostAdmindPage(driver);
	}
	
	
}
