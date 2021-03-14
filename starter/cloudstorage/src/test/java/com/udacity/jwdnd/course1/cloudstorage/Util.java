package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
    // HomePage
    public static final String logoutButtonString = "logout-button";
    public static final String navNotesTabString = "nav-notes-tab";
    public static final String navCredentialsTabString = "nav-credentials-tab";

    // Note
    public static final String newNoteButtonString = "new-note-button";
    public static final String noteAddSubmitButtonString = "note-submit-button";
    public static final String noteTitleString = "note-title";
    public static final String noteDescriptionString = "note-description";
    public static final String noteEditButtonString = "edit-note-button";
    public static final String noteDeleteButtonString = "delete-note-button";

    // Credential
    public static final String newCredentialButtonString = "new-credential-button";
    public static final String credentialTitleString = "credentialModalLabel";
    public static final String credentialUrlString = "credential-url";
    public static final String credentialUsernameString = "credential-username";
    public static final String credentialPasswordString = "credential-password";
    public static final String credentialAddSubmitButtonString = "credential-submit-button";
    public static final String credentialUpdateButtonString = "update-credential-button";
    public static final String credentialDeleteButtonString = "delete-credential-button";


    // SignupPage
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

    public static WebElement waitUntilElementClickable(WebDriver driver, String id) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }
}
