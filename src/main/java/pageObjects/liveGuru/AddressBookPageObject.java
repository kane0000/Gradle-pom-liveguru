package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveGuru.AddressBookPageUI;

public class AddressBookPageObject extends AbstractPage {
private WebDriver driver;
public AddressBookPageObject(WebDriver driver) {
this.driver = driver;
}
public void inputTelephoneTexbox(String telephoneValue) {
	waitElementVisible(driver, AddressBookPageUI.TELEPHONE_TEXTBOX);
	sendkeyToElement(driver, AddressBookPageUI.TELEPHONE_TEXTBOX, telephoneValue);
	
}
public void inputZipTextbox(String zipValue) {
	waitElementVisible(driver, AddressBookPageUI.ZIP_TEXTBOX);
	sendkeyToElement(driver, AddressBookPageUI.ZIP_TEXTBOX, zipValue);
	
	
}
public void inputStreetAddressTextbox(String streetValue) {
	waitElementVisible(driver, AddressBookPageUI.STREET_ADDRESS_TEXTBOX);
	sendkeyToElement(driver, AddressBookPageUI.STREET_ADDRESS_TEXTBOX, streetValue);
		
}
public void inputCityTexbox(String cityValue) {
	waitElementVisible(driver, AddressBookPageUI.CITY_TEXTBOX);
	sendkeyToElement(driver, AddressBookPageUI.CITY_TEXTBOX, cityValue);
		
}
public void selectValueInCountryDropdown(String countryValue) {
	waitElementVisible(driver, AddressBookPageUI.COUNTRY_DROPDOWN);
	selectItemDropdown(driver, AddressBookPageUI.COUNTRY_DROPDOWN, countryValue);
		
}
public void clickSaveAddressButton() {
	waitElementClickable(driver, AddressBookPageUI.SAVE_ADDRESS_BUTTON);
	clickToElement(driver, AddressBookPageUI.SAVE_ADDRESS_BUTTON);
		
}
public boolean isSuccessMessageDisplayed() {
	waitElementVisible(driver, AddressBookPageUI.SUCCESS_MESSAGE_ADDRESS);
	return isElementDisplayed(driver, AddressBookPageUI.SUCCESS_MESSAGE_ADDRESS);
}

}
