package pageFactory.liveGuru;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	public void sendkeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	public void selectItemDropdown(WebDriver driver, WebElement element, String itemValue) {
		select = new Select(element);
		select.selectByVisibleText(itemValue);
	}

	public String getSelectedItemDropdown(WebDriver driver, WebElement element) {
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, WebElement element) {
		select = new Select(element);
		return select.isMultiple();
	}

	

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getAttributeValue(WebDriver driver,WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver,WebElement element) {
		return  element.getText();
	}
	public void checkToCheckbox(WebDriver driver, WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckbox(WebDriver driver, WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver,WebElement element) {
		return  element.isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, WebElement element) {
		return  element.isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, WebElement element) {
		return  element.isSelected();
	}

	public void switchToFrameOrIframe(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		jsExecutor = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, WebElement element) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();",  element);
	}

	public void scrollToElement(WebDriver driver, WebElement element) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",  element);
	}

	public void sendkeyToElementByJS(WebDriver driver, WebElement element, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",  element);
	}

	public void removeAttributeInDOM(WebDriver driver, WebElement element, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",  element);
	}

	public boolean isImageDisplayed(WebDriver driver, WebElement element) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) (jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !=" + " \"undefined\"&& arguments[0].naturalWidth > 0",  element));
		if (status) {
			return (true);
		}
		return (false);

	}

	public void waitElementVisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOf( element));
	}

	public void waitElementInvisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitElementClickable(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	private Select select;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private long longTimeOut = 30;
}
