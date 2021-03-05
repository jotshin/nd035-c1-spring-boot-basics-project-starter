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

    @FindBy(id = noteEditSubmitButtonString)
    private WebElement noteEditSubmitButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillNoteInfoAndSubmit(String title, String id) {
        sendKey(driver, id == noteAddSubmitButtonString ? noteTitleField : noteEditTitleField, title);
        sendKey(driver, noteDescriptionField, "1234");
        click(driver, id == noteAddSubmitButtonString ? noteSubmitButton : noteEditSubmitButton);
    }

    public void logout() {
        click(driver, logoutButton);
    }
}
