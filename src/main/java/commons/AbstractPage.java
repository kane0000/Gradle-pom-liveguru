package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.wordpress.admin.DashboardPageObject;
import pageObject.wordpress.user.HomePageObject;
import pageObject.wordpress.user.PostDetailPageObject;
import pageObject.wordpress.user.SearchResultPageObject;
import pageObjects.liveGuru.AboutUsPageObject;
import pageObjects.liveGuru.AdvancedSearchPageObject;
import pageObjects.liveGuru.ContactUsPageObject;
import pageObjects.liveGuru.CustomerServicePageObject;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.SearchTermsPageObject;
import pageUI.wordpress.admind.AbstractWordpressPageUI;
import pageUIs.liveGuru.AbsTractPageUI;

public abstract class AbstractPage {
	// hàm dùng chung cho Package: PageObjects
	// selenium API wrapper(core)

	// Đóng gói những dòng code/ command thành 1 hàm (funtion)-> call funtion để sử dụng

	// 1 - Set access modifier là public hết
	// 2 - Tên hàm:lowerCase
	// 3 - Tham số của hàm (param)
	// 4 - Kiểu trả về (return type)
	// 5 - sử dụng biến toàn cục hay cục bộ
	// 47 hàm cơ bản

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public void setImplicitWait(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void sendkeyToAlert(WebDriver driver, String text) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	public String getTextAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
// WebElement
	public By byXpath(String xpathValue) {
		return By.xpath(xpathValue);
	}

	public WebElement find(WebDriver driver, String xpathValue) {
		return driver.findElement(byXpath(xpathValue));
	}

	public List<WebElement> finds(WebDriver driver, String xpathValue) {
		return driver.findElements(byXpath(xpathValue));
	}
	// lấy ra locator tối ưu nhất để đại diện cho nhiều locator giống nhau(dùng %s: để đại diện)
	public String getDynamicLocator(String xpathValue, String... values) {
		xpathValue = String.format(xpathValue, (Object[]) values);
		return xpathValue;
		
	}
	
	public void clickToElement(WebDriver driver, String xpathValue) {
		// dành cho IE
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, xpathValue);
			sleepInSecond(3);
		}else {
		find(driver, xpathValue).click();
	}
	}

	public void clickToElement(WebDriver driver, String xpathValue, String... values) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, xpathValue, values);
			sleepInSecond(3);
		}else {
		find(driver, getDynamicLocator(xpathValue,values)).click();
	}
	}

	public void sendkeyToElement(WebDriver driver, String xpathValue, String value) {
		element = find(driver, xpathValue);
		element.clear();
		element.sendKeys(value);
	}
	public void sendkeyEnterToElement(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		element.sendKeys(Keys.ENTER);
	}
	public void sendkeyToElement(WebDriver driver, String xpathValue, String value, String... values) {
		element = find(driver, getDynamicLocator(xpathValue,values));
		element.clear();
		element.sendKeys(value);
	}
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	public void selectItemDropdown(WebDriver driver, String xpathValue, String itemValue) {
		select = new Select(find(driver, xpathValue));
		select.selectByVisibleText(itemValue);
	}

	public String getSelectedItemDropdown(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		find(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));

		elements = finds(driver, childItemLocator);

		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getAttributeValue(WebDriver driver, String xpathValue, String attributeName) {
		return find(driver, xpathValue).getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).getText();
	}

	// đếm xem được bao nhiêu Element
	public int countElementNumber(WebDriver driver, String xpathValue) {
		return finds(driver, xpathValue).size();
	}
	public int countElementNumber(WebDriver driver, String xpathValue, String... values) {
		return finds(driver, getDynamicLocator(xpathValue, values)).size();
	}

	public void checkToCheckbox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (!element.isSelected()) {
			element.click();
		}
	}
	public void checkToCheckbox(WebDriver driver, String xpathValue,String... values) {
		element = find(driver, getDynamicLocator(xpathValue, values));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckbox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (element.isSelected()) {
			element.click();
		}
	}
	public void unCheckToCheckbox(WebDriver driver, String xpathValue,String... values) {
		element = find(driver, getDynamicLocator(xpathValue, values));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathValue, String... values) {
		return find(driver, getDynamicLocator(xpathValue,values)).isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isSelected();
	}

	public void switchToFrameOrIframe(WebDriver driver, String xpathValue) {
		driver.switchTo().frame(find(driver, xpathValue));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String xpathValue) {
		action = new Actions(driver);
		action.moveToElement(find(driver, xpathValue)).perform();
	}
	public void hoverToElement(WebDriver driver, String xpathValue, String... values) {
		action = new Actions(driver);
		action.moveToElement(find(driver, getDynamicLocator(xpathValue,values))).perform();
	}

	public void sendkeyBoardToElement(WebDriver driver, String xpathValue, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, xpathValue), key);
	}

	public void highlightElement(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		jsExecutor = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", find(driver, xpathValue));
	}
	public void clickToElementByJS(WebDriver driver, String xpathValue,String... values ) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", find(driver, getDynamicLocator(xpathValue,values)));
	}

	public void scrollToElement(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(driver, xpathValue));
		sleepInSecond(1);
	}
	public void scrollToElement(WebDriver driver, String xpathValue, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(driver, getDynamicLocator(xpathValue,values)));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathValue, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", find(driver, xpathValue));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, xpathValue));
	}

	public boolean isImageDisplayed(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) (jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !=" + " \"undefined\"&& arguments[0].naturalWidth > 0", find(driver, xpathValue)));
		if (status) {
			return (true);
		}
		return (false);
		
	}
	public boolean isImageDisplayed(WebDriver driver, String xpathValue, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) (jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !=" + " \"undefined\"&& arguments[0].naturalWidth > 0", find(driver, getDynamicLocator(xpathValue,values))));
		if (status) {
			return (true);
		}
		return (false);

	}
	public boolean waitForJStoLoad(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply (WebDriver driver) {
			try {
				return ((Long)jsExecutor.executeScript("return jQuery.active")== 0);
			}catch (Exception e) {
				return true;
			}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyStste").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad)&& explicitWait.until(jsLoad);
	}
	
	public void waitElementVisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathValue)));
	}

	public void waitElementVisible(WebDriver driver, String xpathValue, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(getDynamicLocator(xpathValue,values))));
	}
	public void waitElementsVisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		elements = finds(driver, xpathValue);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void waitElementInvisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
	}
	public void waitElementInvisible(WebDriver driver, String xpathValue, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(getDynamicLocator(xpathValue,values))));
	}
	//Invisible số nhiều
	public void waitElementsInvisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		elements = finds(driver, xpathValue);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public void waitElementClickable(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
	}

	public void waitElementClickable(WebDriver driver, String xpathValue, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(getDynamicLocator(xpathValue,values))));
	}
	public void overrideImplicitTimeout(WebDriver driver, long timeInSecond) {
		driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
	}
	// kiểm tra không hiển thị trên UI và trong DOM
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideImplicitTimeout(driver, shortTimeout);
		elements = finds(driver, locator);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size()== 0) {
			return true;
		} else if(elements.size() > 0 && !elements.get(0).isDisplayed()){
			return true;
		}else {
			return false;
		}
	}
	public boolean isElementUndisplayed(WebDriver driver, String locator, String... value) {
		overrideImplicitTimeout(driver, shortTimeout);
		elements = finds(driver, getDynamicLocator(locator, value));
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size()== 0) {
			return true;
		} else if(elements.size() > 0 && !elements.get(0).isDisplayed()){
			return true;
		}else {
			return false;
		}
	}
	//Sort theo chuỗi(chữ)
// Sort Ascending (sắp xếp tăng dần)	
	public boolean isDataStringSortedAscending(WebDriver driver, String xpathValue) {
		//khai báo array list
		ArrayList<String>arrayList = new ArrayList<>();
		//Tìm tất cả element Matching vs điều kiện (Name/Price...)
		List<WebElement> elementList = finds(driver, xpathValue);
		
		// Lấy text của từng element add vào array list
		for(WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("----------Dữ liệu trên UI----------");
		for(String name : arrayList) {
			System.out.println(name);
		}
		//Coppy qua 1 arayList mới để SORT trong Code
		ArrayList<String>sortedList = new ArrayList<>();
		for (String child: arrayList) {
			sortedList.add(child);
		}
		// Thực hiện sort Asc
		Collections.sort(sortedList);
		System.out.println("----------Dữ liệu đã SORT ASC trong code:----------");
		for(String name:sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}
// Sort Descending( sắp xếp giảm dần dạng chuỗi)
	public boolean isDataStringSortedDescending(WebDriver driver, String xpathValue) {
		//khai báo array list
				ArrayList<String>arrayList = new ArrayList<String>();
				//Tìm tất cả element Matching vs điều kiện (Name/Price...)
				List<WebElement> elementList = finds(driver, xpathValue);
				
				// Lấy text của từng element add vào array list
				for(WebElement element : elementList) {
					arrayList.add(element.getText());
				}
				System.out.println("----------Dữ liệu trên UI----------");
				for(String name : arrayList) {
					System.out.println(name);
				}
				//Coppy qua 1 arayList mới để SORT trong Code
				ArrayList<String>sortedList = new ArrayList<>();
				for (String child: arrayList) {
					sortedList.add(child);
				}
				// Thực hiện sort Asc
				Collections.sort(sortedList);
				
				//Reverse data để sort DESC (Dùng 1 trong 2 cách dưới)
				Collections.reverse(sortedList);
				//Collections.sort(arrayList,Collections.reverseOrder());
				System.out.println("----------Dữ liệu đã SORT DESC trong code:----------");
				for(String name : sortedList) {
					System.out.println(name);
				}
				// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
				return sortedList.equals(arrayList);
	}
	//Sort bằng số tăng dần
	public boolean isPriceSortedAscending(WebDriver driver, String xpathValue) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		
		List<WebElement>elementList = finds(driver, xpathValue);
		for(WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
			
		}
		System.out.println("----------Dữ liệu trên UI----------");
		for(Float name : arrayList) {
			System.out.println(name);
		}
		ArrayList<Float>sortedList = new ArrayList<>();
		for (Float child: arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		System.out.println("----------Dữ liệu đã SORT ASC trong code:----------");
		for(Float name:sortedList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
	//Sort bằng số giảm dần
	public boolean isPriceSortedDescending(WebDriver driver, String xpathValue) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		
		List<WebElement>elementList = finds(driver, xpathValue);
		for(WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
			
		}
		System.out.println("----------Dữ liệu trên UI----------");
		for(Float name : arrayList) {
			System.out.println(name);
		}
		ArrayList<Float>sortedList = new ArrayList<>();
		for (Float child: arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		System.out.println("----------Dữ liệu đã SORT DESC trong code:----------");
		for(Float name:sortedList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
		

	public ContactUsPageObject openContactUsPagelink(WebDriver driver) {
		waitElementClickable(driver, AbsTractPageUI.CONTACT_US_LINK);
		clickToElement(driver,  AbsTractPageUI.CONTACT_US_LINK);
		return PageGeneratorManager.getContactUsPage(driver);
	}
	public CustomerServicePageObject openCustomerServivePageLink(WebDriver driver) {
		waitElementClickable(driver, AbsTractPageUI.CUSTOMER_SERVICE_LINK);
		clickToElement(driver,  AbsTractPageUI.CUSTOMER_SERVICE_LINK);
		return PageGeneratorManager.getCustomerServicePage(driver);
	}
	public AdvancedSearchPageObject openAdvancedSearchPageLink(WebDriver driver) {
		waitElementClickable(driver, AbsTractPageUI.ADVANCED_SEARCH_LINK);
		clickToElement(driver,  AbsTractPageUI.ADVANCED_SEARCH_LINK);
		return PageGeneratorManager.getAdvancedSearchPage(driver);
	}
	public SearchTermsPageObject openSearchTermsPageLink(WebDriver driver) {
		waitElementClickable(driver, AbsTractPageUI.SEARCH_TERMS_LINK);
		clickToElement(driver,  AbsTractPageUI.SEARCH_TERMS_LINK);
		return PageGeneratorManager.getSearchTermsPage(driver);
	}
	// Kiểm tra đang sử dụng hệ điều hành nào để gán foldername vào
	public String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}
	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}
	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}
	public boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0 || osName.toLowerCase().indexOf("aix") > 0);
	}
	public boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}
//Upload file
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath =System.getProperty("user.dir")+getDirectorySlash("uploadFiles");
		// lấy ra đường dẫn của file
		String fullFileName ="";
		//Duyệt qua từng file để nối tất cả đường dẫn file vào
		for (String file : fileNames) {
			fullFileName = fullFileName +filePath + file + "\n";
		}
		// Cắt bỏ khoảng trắng( )/tab(\t)/xuống dòng(\n) đầu hoặc cuối chuỗi (hàm .trim())
		fullFileName = fullFileName.trim();
		sendkeyToElement(driver, AbsTractPageUI.UPLOAD_FILE_TYPE, fullFileName);
	
	}
	// Verify Upload Thành công
	public boolean areFileUploadedDisplay(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;
		waitElementsInvisible(driver, AbsTractPageUI.MEDIA_PROGRESS_BAR_ICON);
		sleepInSecond(5);
		elements = finds(driver, AbsTractPageUI.ALL_UPLOAD_IMAGE);
		List<String> imageValues = new ArrayList<String>();
		int i = 0;
		// Lấy ra cái source value của nó = chứa cái tên hình
		for(WebElement image : elements) {
			System.out.println(image.getAttribute("src"));
			imageValues.add(image.getAttribute("src"));
			i++;
			if(i == number) {
				break;
			}
		}
		for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();
			
			for(i = 0; i < imageValues.size(); i ++) {
				if (!imageValues.get(i).contains(fileName)) {
					status = false;
					if (i == imageValues.size() - 1) {
						return status;
					}
				}else {
					status = true;
					break;
				}
			}
		}
		return status;
	}
	
	
	public AboutUsPageObject openAboutUsPageLink(WebDriver driver) {
		waitElementClickable(driver, AbsTractPageUI.ABOUT_US_LINK);
		clickToElement(driver,  AbsTractPageUI.ABOUT_US_LINK);
		return PageGeneratorManager.getAboutUsPage(driver);
	}
	// Cách 1: Số lượng ít hơn 20 page
	public AbstractPage openFooterPageLink(WebDriver driver,String pageName ) {
		waitElementClickable(driver,  AbsTractPageUI.FOOTER_PAGE, pageName);;
		clickToElement(driver, AbsTractPageUI.FOOTER_PAGE, pageName);
		
//		if (pageName.equals("About Us")) {
//			return PageGeneratorManager.getAboutUsPage(driver);
//		} else if (pageName.equals("Advanced Search")){
//			return PageGeneratorManager.getAdvancedSearchPage(driver);
//		}else if (pageName.equals("Customer Service")){
//			return PageGeneratorManager.getCustomerServicePage(driver);
//		}
//		else if (pageName.equals("Search Terms")){
//			return PageGeneratorManager.getSearchTermsPage(driver);
//		}
//		else {
//			return PageGeneratorManager.getContactUsPage(driver);
//		}
		
		switch (pageName) {
		case "About Us":
			return PageGeneratorManager.getAboutUsPage(driver);
		case "Advanced Search":
			return PageGeneratorManager.getAdvancedSearchPage(driver);
		case "Customer Service":
			return PageGeneratorManager.getCustomerServicePage(driver);
		case "Search Terms":
			return PageGeneratorManager.getSearchTermsPage(driver);
		default:
			return PageGeneratorManager.getContactUsPage(driver);
		}
	}
	// Cách 2: Số lượng nhiều page
	public void openFooterPageByName(WebDriver driver,String pageName ) {
		waitElementClickable(driver,  AbsTractPageUI.FOOTER_PAGE, pageName);;
		clickToElement(driver, AbsTractPageUI.FOOTER_PAGE, pageName);
	}
	public void inputToDynamicTextbox(WebDriver driver, String idAttributeValue,String inputValue ) {
		waitElementVisible(driver, AbsTractPageUI.DYNAMIC_TEXTBOX, idAttributeValue);
		sendkeyToElement(driver, AbsTractPageUI.DYNAMIC_TEXTBOX, inputValue, idAttributeValue);
	}
	public HomePageObject openEndUserPage(WebDriver driver) {
		openPageUrl(driver, GlobalConstants.USER_WORDPRESS_URL);
		return commons.PageGeneratorManager.getHomeUserPage(driver);
	}
		public DashboardPageObject openAdminLoginedPage(WebDriver driver) {
			openPageUrl(driver, GlobalConstants.ADMIN_WORDPRESS_URL);
			return commons.PageGeneratorManager.getDashboardAminPage(driver);
		}
		public SearchResultPageObject inputToSearchTextboxAtEndUserPage(WebDriver driver, String value) {
			waitElementVisible(driver, AbstractWordpressPageUI.SEARCH_ICON);
			clickToElement(driver, AbstractWordpressPageUI.SEARCH_ICON);
			sendkeyToElement(driver, AbstractWordpressPageUI.SEARCH_TEXTBOX, value);
			clickToElement(driver, AbstractWordpressPageUI.SEARCH_BUTTON);
			return commons.PageGeneratorManager.getSearchResultUserPage(driver);
		}
//		public boolean isSuccessMesageDisplayedWithvalue(WebDriver driver,String value) {
//			waitElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_SUCCESS_MESSAGE_WITH_VALUE, value);
//			return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_SUCCESS_MESSAGE_WITH_VALUE, value);
//		}
		public boolean isSuccessMesageDisplayedvalue(WebDriver driver, String textValue) {
			waitElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_SUCCESS_MESSAGE_VALUE,textValue);
			return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_SUCCESS_MESSAGE_VALUE, textValue);
		}
		public boolean isRowValueDisplayedAtColmnName(WebDriver driver,String colmnName, String rowValue) {
			waitElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_COLUMN_AND_ROW,colmnName,rowValue);
			return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_COLUMN_AND_ROW,colmnName,rowValue);
		}
		public boolean isRowValueUnDisplayedAtColmnName(WebDriver driver,String colmnName, String rowValue) {
			waitElementInvisible(driver, AbstractWordpressPageUI.DYNAMIC_COLUMN_AND_ROW,colmnName,rowValue);
			return isElementUndisplayed(driver, AbstractWordpressPageUI.DYNAMIC_COLUMN_AND_ROW,colmnName,rowValue);
		}
		public void openMenuPageByName(WebDriver driver, String pageName) {
			waitElementClickable(driver, AbstractWordpressPageUI.DYNAMIC_MENU_BY_NAME,pageName );
			clickToElement(driver, AbstractWordpressPageUI.DYNAMIC_MENU_BY_NAME,pageName );
		}
		public boolean isPostDisplayedOnlatestPost(WebDriver driver,String categoryValue, String titleValue, String dayValue) {
			waitElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_POST_LATEST_POST, categoryValue,titleValue,dayValue);
			return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_POST_LATEST_POST, categoryValue,titleValue,dayValue);
		}
		public boolean isPostUnDisplayedOnlatestPost(WebDriver driver,String categoryValue, String titleValue, String dayValue) {
			waitElementInvisible(driver, AbstractWordpressPageUI.DYNAMIC_POST_LATEST_POST, categoryValue,titleValue,dayValue);
			return isElementUndisplayed(driver, AbstractWordpressPageUI.DYNAMIC_POST_LATEST_POST, categoryValue,titleValue,dayValue);
		}
		public boolean isPostImageDisplayedAtTitleName(WebDriver driver,String postTitle, String avatarImageName) {
			avatarImageName = avatarImageName.split("\\.")[0];
			waitElementVisible(driver, AbstractWordpressPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle,avatarImageName);
			sleepInSecond(1);
			return isElementDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle,avatarImageName) 
					&& isImageDisplayed(driver, AbstractWordpressPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle,avatarImageName);
			
		}
		public boolean isPostImageUnDisplayedAtTitleName(WebDriver driver,String postTitle, String avatarImageName) {
			avatarImageName = avatarImageName.split("\\.")[0];
			waitElementInvisible(driver, AbstractWordpressPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle,avatarImageName);
			return isElementUndisplayed(driver, AbstractWordpressPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle,avatarImageName);
		}
		public PostDetailPageObject clickToPostDetailWithTitleName(WebDriver driver,String postTitle) {
			waitElementClickable(driver, AbstractWordpressPageUI.DYNAMIC_POST_TITLE, postTitle);
			clickToElementByJS(driver, AbstractWordpressPageUI.DYNAMIC_POST_TITLE, postTitle);
			return commons.PageGeneratorManager.getPostDetailUserPage(driver);
		}
	
	private Alert alert;
	private Select select;
	private Actions action;
	private WebElement element;
	private List<WebElement> elements;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private long longTimeout = 30;
	private long shortTimeout = 5;
	private String osName = System.getProperty("os.name");
}
