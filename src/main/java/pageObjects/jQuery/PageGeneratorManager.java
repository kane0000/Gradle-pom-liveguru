package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	//khởi tạo các page
	
	public static UploadPageObject getUploadPage(WebDriver driver) {
		return new UploadPageObject(driver);
	}
	
}
