package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util {
    // HomePage
    public static final String logoutButtonString = "logout-button";
    public static final String navNotesTabString = "nav-notes-tab";
    public static final String newNoteButtonString = "new-note-button";
    public static final String noteAddSubmitButtonString = "add-note-submit-button";
    public static final String noteTitleString = "note-title";
    public static final String noteEditTitleString = "edit-note-title";
    public static final String noteDescriptionString = "note-description";
    public static final String noteEditButtonString = "edit-note-button";
    public static final String noteEditSubmitButtonString = "edit-note-submit-button";
    public static final String noteDeleteButtonString = "delete-note-button";
    public static final String noteDeleteSubmitButtonString = "delete-note-submit-button";

    // SignupPage
    public static final String successMessage = "success-msg";
    public static final String inputLastNameString = "inputLastName";
    public static final String inputFirstNameString = "inputFirstName";
    public static final String inputUsernameString = "inputUsername";
    public static final String inputPasswordString = "inputPassword";
    public static final String submitButtonString = "submit-button";

    // LoginPage
    public static final String signupLinkString = "signup-link";

    // Result
    public static final String successAlertString = "success-alert";

    public static void sendKey(WebDriver driver, WebElement webElement, String text) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", webElement);
    }

    public static void click(WebDriver driver, WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }
}
