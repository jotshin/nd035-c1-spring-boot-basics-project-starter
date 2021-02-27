package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    public static final String inputFirstName = "inputFirstName";
    public static final String successMessage = "success-msg";

    @FindBy(id = inputFirstName)
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement userNameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = successMessage)
    private WebElement successMsg;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInfoAndSubmit(String username) {
        firstNameField.sendKeys("T");
        lastNameField.sendKeys("J");
        userNameField.sendKeys(username);
        passwordField.sendKeys("1234");
        submitButton.click();
    }

    public String getSuccessText() {
        return successMsg.getText();
    }
}
