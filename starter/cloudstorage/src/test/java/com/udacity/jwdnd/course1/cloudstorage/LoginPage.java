package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public static final String signupLinkString = "signup-link";

    @FindBy(id = signupLinkString)
    public WebElement signupLink;

    @FindBy(id = "inputUsername")
    private WebElement userNameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goSignup() {
        signupLink.click();
    }

    public void fillInfoAndSubmit(String username) {
        userNameField.sendKeys(username);
        passwordField.sendKeys("1234");
        submitButton.click();
    }
}
