package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.udacity.jwdnd.course1.cloudstorage.Util.*;

public class HomePage {
    private final WebDriver driver;

    @FindBy(id = logoutButtonString)
    private WebElement logoutButton;

    @FindBy(id = noteTitleString)
    private WebElement noteTitleField;

    @FindBy(id = noteEditTitleString)
    private WebElement noteEditTitleField;

    @FindBy(id = noteDescriptionString)
    private WebElement noteDescriptionField;

    @FindBy(id = noteAddSubmitButtonString)
    private WebElement noteSubmitButton;

    @FindBy(id = credentialUrlString)
    private WebElement credentialUrlField;

    @FindBy(id = credentialUsernameString)
    private WebElement credentialUsernameField;

    @FindBy(id = credentialPasswordString)
    private WebElement credentialPasswordField;

    @FindBy(id = credentialAddSubmitButtonString)
    private WebElement credentialSubmitButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillNoteInfoAndSubmit(String title) {
        sendKey(driver, noteTitleField, title);
        sendKey(driver, noteDescriptionField, "1234");
        click(driver, noteSubmitButton);
    }

    public void fillCredentialInfoAndSubmit() {
        sendKey(driver, credentialUrlField, "https://localhost:8080/login");
        sendKey(driver, credentialUsernameField, "admin");
        sendKey(driver, credentialPasswordField, "1234");
        click(driver, credentialSubmitButton);
    }
}
