package pageUIs.jQuery;

public class UploadPageUI {
	public static final String DYNAMIC_IMAGE_TEXT ="//p[@class='name' and text()='%s']";
public static final String DYNAMIC_IMAGE_START_BUTTON = DYNAMIC_IMAGE_TEXT + "/parent::td/following-sibling::td/button[contains(@class,'start')]";
public static final String DYNAMIC_IMAGE_LINK ="//p[@class='name' ]/a[text()='%s']";
}
