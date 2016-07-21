package com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.helpers.HomeTable;
import com.trailblazers.freewheelers.helpers.ManageItemTable;
import com.trailblazers.freewheelers.helpers.OrderTable;
import com.trailblazers.freewheelers.helpers.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.ADD_TO_CART_FAILURE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertTrue;

public class ScreenApi {
    private WebDriver driver;

    public ScreenApi(WebDriver driver) {
        this.driver = driver;
    }

    public void shows_error_alert(String expectedMessage) {
        expectMessageWithClass(expectedMessage, "error");
    }

    public ScreenApi shows_error(String expectedMessage, String id) {
        expectMessageWithField(expectedMessage, "text-error", id);
        return this;
    }


    public ScreenApi shows_message(String expectedMessage) {
        expectMessageWithClass(expectedMessage, "page-action");
        return this;
    }

    public void showsMessageInClass(String expectedMessage, String expectedClass) {
        expectMessageWithClass(expectedMessage, expectedClass);
    }

    public void shows_in_navbar(String expectedMessage) {
        expectMessageWithClass(expectedMessage, "navbar-text");
    }


    public ScreenApi shows_profile_for(String name) {
        String userDetails = driver.findElement(By.id("user-details")).getText();

        assertThat(userDetails, containsString(name));
        return this;
    }

    public ScreenApi shows_login() {
        assertThat(driver.getCurrentUrl(), is(URLs.login()));
        return this;
    }

    public ScreenApi shows_admin_profile() {
        assertThat(driver.getCurrentUrl(), is(URLs.admin()));
        return this;
    }

    public ScreenApi shows_in_manage_item_list(String item) {
        assertNumberOfRows(1, ManageItemTable.nameFieldFor(item));
        return this;
    }

    public ScreenApi shows_not_in_manage_item_list(String item) {
        assertNumberOfRows(0, ManageItemTable.nameFieldFor(item));
        return this;
    }

    public ScreenApi should_list_item(String item) {
        assertNumberOfRows(1, HomeTable.nameFieldFor(item));
        return this;
    }

    public ScreenApi should_not_list_item(String item) {
        assertNumberOfRows(0, HomeTable.nameFieldFor(item));
        return this;
    }

    public ScreenApi there_should_be_an_order(String item, String state) {
        WebElement select = driver.findElement(OrderTable.selectFor(item));
        String selected = new Select(select).getFirstSelectedOption().getText();

        assertThat(selected, is(state));

        return this;
    }

    private void assertNumberOfRows(int expectedRows, By selector) {
        List<WebElement> elements = driver.findElements(selector);
        assertThat(elements.size(), is(expectedRows));
    }

    public ScreenApi expectMessageWithClass(String expectedMessage, String messageClass) {
        String errorMessage = driver.findElement(By.className(messageClass)).getText();
        System.out.println("error: "+errorMessage);
        assertThat(errorMessage, containsString(expectedMessage));
        return this;
    }

    private ScreenApi expectMessageWithField(String expectedMessage, String messageClass, String fieldId) {
        String selector = "#" + fieldId + " ." + messageClass;
        String errorMessage = driver.findElement(By.cssSelector(selector)).getText();

        assertThat(errorMessage, containsString(expectedMessage));
        return this;
    }

    public ScreenApi should_show_access_denied() {
        assertThat(driver.getPageSource(),containsString("403 Your access is denied"));
        return this;
    }

    public ScreenApi should_not_contain_nps_report_link_in_header() {
        assertThat(driver.findElements(By.linkText("NPS Report")).size(), is(0));
        return this;
    }

    public ScreenApi shows_profile_for_country(String country) {
        String userDetails = driver.findElement(By.id("user-details")).getText();

        assertThat(userDetails, containsString(country));
        return this;
    }

    public ScreenApi checkPasswordIsMasked() {
        WebElement passwordField = driver.findElement(By.id("fld_password"));
        assertTrue(passwordField.getAttribute("type").equals("password"));

        return this;
    }

    public ScreenApi shows_cart_page() {
        assertThat(driver.getCurrentUrl(), is(URLs.shoppingCart()));
        return this;
    }

    public void shows_reservation_page() {
        assertThat(driver.getCurrentUrl(), is(URLs.reserve()));
    }

    public ScreenApi should_show_cart_item(String simplon_frame) {
        assertThat(driver.findElement(By.id("item_name")).getText(), containsString(simplon_frame));
        return this;
    }

    public void shows_home_page() {
        assertThat(driver.getCurrentUrl(), is(URLs.home()));
    }

    public ScreenApi should_see_error_in_card_form(String error) {
        assertThat(driver.findElement(By.id("credit_card_form")).getText(), containsString(error));

        return this;
    }


    public ScreenApi pageElementContainsMessage(String message) {
        assertThat(driver.findElement(By.id("update_item")).getText(), containsString(message));
        return this;
    }

    public ScreenApi shouldSeePaymentSuccess() {
        String successMessage = "Thank you. Your order will be delivered in ten days!";
        assertThat(driver.getPageSource(),containsString(successMessage));
        return this;
    }

    public ScreenApi shouldSeeSurvey(){
        assertThat(driver.findElement(By.id("modal")).getText(), containsString("1. How likely is it that you would recommend Freewheelers to a friend or colleague?"));
        return this;
    }

    public ScreenApi shouldSeeConfirmation(){
        assertThat(driver.findElement(By.id("surveyThankYou")).getText(), containsString("Thank you for filling out our survey!"));
        return this;
    }

    public ScreenApi shouldSeePaymentFailure() {
        String failureMessage = "Oh no! There was an error processing your request. Please try again.";
        assertThat(driver.getPageSource(),containsString(failureMessage));
        return this;
    }

    public ScreenApi shouldDisplayPurchasedItem(String itemName) {
        assertThat(driver.getPageSource(), containsString(itemName));
        return this;
    }

    public ScreenApi shouldDisplayShippingAddressPage() {
        String displayShippingAddressPage = "Shipping Address";
        assertThat(driver.getPageSource(), containsString(displayShippingAddressPage));
        return this;
    }

    public ScreenApi shouldNotShowAddToCartMessages() {
        assertThat(driver.getPageSource(), not(containsString(ADD_TO_CART_FAILURE)));
        assertThat(driver.getPageSource(), not(containsString("Awesome, you added")));
        return this;
    }

    public ScreenApi show_tax_on_cart_page(String grandtotal) {
        assertThat(driver.findElement(By.id("grand_total")).getText(), containsString(grandtotal));
        return this;
    }

    public ScreenApi shows_shipping_address(String address1, String address2, String city, String state, String postalCode) {
        assertThat(driver.findElement(By.id("shipping_address")).getText(), containsString(address1));
        assertThat(driver.findElement(By.id("shipping_address")).getText(), containsString(address2));
        assertThat(driver.findElement(By.id("shipping_address")).getText(), containsString(city));
        assertThat(driver.findElement(By.id("shipping_address")).getText(), containsString(state));
        assertThat(driver.findElement(By.id("shipping_address")).getText(), containsString(postalCode));
        return this;
    }

    public ScreenApi show_message_for_no_address() {
        assertThat(driver.findElement(By.id("shipping_address")).getText(), containsString("Sorry,no saved address."));

        return this;
    }
}
