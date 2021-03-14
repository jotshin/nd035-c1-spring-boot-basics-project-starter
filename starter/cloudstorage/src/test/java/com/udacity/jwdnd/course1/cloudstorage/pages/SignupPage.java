package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.udacity.jwdnd.course1.cloudstorage.Util.*;

public class SignupPage {
    private final WebDriver driver;

    @FindBy(id = inputFirstNameString)
    private WebElement firstNameField;

    @FindBy(id = inputLastNameString)
    private WebElement lastNameField;

    @FindBy(id = inputUsernameString)
    private WebElement userNameField;

    @FindBy(id = inputPasswordString)
    private WebElement passwordField;

    @FindBy(id = submitButtonString)
    private WebElement submitButton;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInfoAndSubmit(String username) {
        sendKey(driver, firstNameField, "T");
        sendKey(driver, lastNameField, "J");
        sendKey(driver, userNameField, username);
        sendKey(driver, passwordField, "1234");
        click(driver, submitButton);
    }

    public void signup(WebDriver driver, String baseURL) {
        String username = "tj";

        driver.get(baseURL + "/signup");

        fillInfoAndSubmit(username);
    }
}
