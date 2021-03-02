package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public static final String logoutButtonString = "logout-button";
    public static final String navNotesTabString = "nav-notes-tab";
    public static final String newNoteButtonString = "new-note-button";
    public static final String noteSubmitButtonString = "#noteModal > div > div > div.modal-footer > button.btn.btn-primary";
    public static final String newNoteTitleString = "note-title";

    @FindBy(id = logoutButtonString)
    private WebElement logoutButton;

    @FindBy(id = navNotesTabString)
    private WebElement navNotesTab;

    @FindBy(id = newNoteButtonString)
    private WebElement newNoteButton;

    @FindBy(id = newNoteTitleString)
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(css = noteSubmitButtonString)
    private WebElement noteSubmitButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillNoteInfoAndSubmit() {
        noteTitleField.sendKeys("test");
        noteDescriptionField.sendKeys("1234");
        noteSubmitButton.click();
    }

    public void logout() {
        logoutButton.click();
    }
}
