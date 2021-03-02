package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteOperationTests {

    private static WebDriver driver;
    private String baseURL;
    @LocalServerPort
    private int port;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeAll
    static void beforeAll() {
        driver = new SafariDriver();
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = baseURL = "http://localhost:" + port;
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void testCreateNote() {
        signupAndLogin();

        WebElement newNoteButton = navigateToNoteTabAndCheckTitle("Example Note Title");

        newNoteButton.click();

        waitUntilElementClickable(homePage.newNoteTitleString);
        homePage.fillNoteInfoAndSubmit();

        navigateToNoteTabAndCheckTitle("test");

        logout();
    }

    private WebElement navigateToNoteTabAndCheckTitle(String title) {
        WebElement navNotesTab = waitUntilElementClickable(homePage.navNotesTabString);
        navNotesTab.click();

        WebElement newNoteButton = waitUntilElementClickable(homePage.newNoteButtonString);
        assertNotNull(newNoteButton);

        WebElement header = driver.findElement(By.cssSelector("#userTable > tbody > tr > th"));
        assertEquals(header.getText(), title);

        return newNoteButton;
    }

    private void signupAndLogin() {
        String username = "tj";

        driver.get(baseURL + "/signup");

        signupPage.fillInfoAndSubmit(username);

        driver.get(baseURL + "/login");

        waitUntilElementClickable(loginPage.signupLinkString);

        loginPage.fillInfoAndSubmit(username);
    }

    private void logout() {
        WebElement logoutButton = waitUntilElementClickable(homePage.logoutButtonString);
        assertNotNull(logoutButton);

        logoutButton.click();

        waitUntilElementClickable(loginPage.signupLinkString);
    }

    private WebElement waitUntilElementClickable(String id) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }
}
