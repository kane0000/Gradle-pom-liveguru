package pageObject.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.wordpress.admind.NewEditPostPageUI;

public class NewEditPostPageObject extends AbstractPage {
	private WebDriver driver;

	public NewEditPostPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToPostTitleTextbox(String titleName) {
		waitElementVisible(driver, NewEditPostPageUI.POST_TITLE_TEXBOX);
		sendkeyToElement(driver, NewEditPostPageUI.POST_TITLE_TEXBOX, titleName);
	}

	public void inputToPostContentTextbox(String value) {
		switchToFrameOrIframe(driver, NewEditPostPageUI.TENY_MCE_IFRAME);
		waitElementVisible(driver, NewEditPostPageUI.TENY_MCE_CONTENT_TEXBOX);
		sendkeyToElement(driver, NewEditPostPageUI.TENY_MCE_CONTENT_TEXBOX, value);
		switchToDefaultContent(driver);
	}

	public void selectToCategoryCheckbox(String checkboxName) {
		waitElementClickable(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxName);
		clickToElement(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxName);
		sleepInSecond(1);

	}
	public void deSelectToCategoryCheckbox(String newPostCategory) {
		waitElementClickable(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, newPostCategory);
		clickToElementByJS(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, newPostCategory);
		sleepInSecond(1);
	}


	public void inputToTagTextbox(String tagValue) {
		waitElementVisible(driver, NewEditPostPageUI.TAG_TEXTBOX, tagValue);
		sendkeyToElement(driver, NewEditPostPageUI.TAG_TEXTBOX, tagValue);

	}

	public void clickToSetFeaturedImageLink() {
		waitElementClickable(driver, NewEditPostPageUI.SET_FEATURE_IMAGE);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURE_IMAGE);
	}

	public void clickToUploadFileTab() {
		waitElementClickable(driver, NewEditPostPageUI.UPLOAD_FILE_TAB);
		clickToElement(driver, NewEditPostPageUI.UPLOAD_FILE_TAB);

	}

	public void clickToselectButtonAtChooseImage() {
		waitElementClickable(driver, NewEditPostPageUI.SET_FEATURED_BUTTON);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURED_BUTTON);
	}

	public void clickToPublishOrUpdateButton() {
		waitElementClickable(driver, NewEditPostPageUI.PUBLISH_OR_UPDATE_BUTTON);
		clickToElementByJS(driver, NewEditPostPageUI.PUBLISH_OR_UPDATE_BUTTON);
	}


	public void clickToDeleteTagIconWithTagName(String newTag) {
		waitElementClickable(driver, NewEditPostPageUI.REMOVE_TAG_BUTTON, newTag);
		clickToElement(driver, NewEditPostPageUI.REMOVE_TAG_BUTTON,newTag);
	}


	public PostsPageObject clickToMoveToTrashButton() {
		waitElementClickable(driver, NewEditPostPageUI.MOVE_TO_TRASH);
		clickToElement(driver, NewEditPostPageUI.MOVE_TO_TRASH);
		return PageGeneratorManager.getPostsAdminPage(driver);
	}

	// Lấy ra fileName đầu tiên khi tách dấu "." ra
	public boolean isImagePostDisplayed(String imageName) {
		String[] files = imageName.split("\\.");
		waitElementVisible(driver, NewEditPostPageUI.POST_IMAGE_DISPLAYED, files[0]);
		return isElementDisplayed(driver, NewEditPostPageUI.POST_IMAGE_DISPLAYED, files[0]);
	}

	public void clickToAddTagButton() {
		waitElementClickable(driver, NewEditPostPageUI.TAG_BUTTON);
		clickToElement(driver, NewEditPostPageUI.TAG_BUTTON);
	}

	

}
