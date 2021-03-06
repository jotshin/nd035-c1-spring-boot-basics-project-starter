package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.udacity.jwdnd.course1.cloudstorage.Util.*;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(id = inputUsernameString)
    private WebElement userNameField;

    @FindBy(id = inputPasswordString)
    private WebElement passwordField;

    @FindBy(id = submitButtonString)
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInfoAndSubmit(String username) {
        sendKey(driver, userNameField, username);
        sendKey(driver, passwordField, "1234");
        click(driver, submitButton);
    }

    public void login(WebDriver driver, String baseURL) {
        String username = "tj";

        driver.get(baseURL + "/login");

        waitUntilElementClickable(driver, signupLinkString);

        fillInfoAndSubmit(username);
    }
}
