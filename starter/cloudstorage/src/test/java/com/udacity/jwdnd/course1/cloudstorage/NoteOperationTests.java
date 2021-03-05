package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.udacity.jwdnd.course1.cloudstorage.Util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
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
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
        signup();

        login();

        WebElement newNoteButton = navigateToNoteTabAndCheckTitle("Example Note Title", newNoteButtonString);

        Util.click(driver, newNoteButton);

        waitUntilElementClickable(noteTitleString);
        homePage.fillNoteInfoAndSubmit("test", noteAddSubmitButtonString);

        navigateToNoteTabAndCheckTitle("test", newNoteButtonString);

        logout();
    }

    @Test
    public void testEditNote() {
        login();

        WebElement editButton = navigateToNoteTabAndCheckTitle("test", noteEditButtonString);

        click(driver, editButton);

        waitUntilElementClickable(noteEditTitleString);
        homePage.fillNoteInfoAndSubmit("test1", noteEditSubmitButtonString);

        navigateToNoteTabAndCheckTitle("test1", noteEditButtonString);

        logout();
    }

    @Test
    public void testRemoveNote() {
        login();

        WebElement deleteButton = navigateToNoteTabAndCheckTitle("test1", noteDeleteButtonString);

        click(driver, deleteButton);

        WebElement noteSubmitButton = waitUntilElementClickable(noteDeleteSubmitButtonString);
        click(driver, noteSubmitButton);

        navigateToNoteTabAndCheckTitle("Example Note Title", newNoteButtonString);

        logout();
    }

    private WebElement navigateToNoteTabAndCheckTitle(String title, String buttonId) {
        WebElement navNotesTab = waitUntilElementClickable(navNotesTabString);
        click(driver, navNotesTab);

        WebElement button = waitUntilElementClickable(buttonId);
        assertNotNull(button);

        WebElement header = driver.findElement(By.cssSelector("#userTable > tbody > tr > th"));
        assertEquals(title, header.getText());

        return button;
    }

    private void signup() {
        String username = "tj";

        driver.get(baseURL + "/signup");

        signupPage.fillInfoAndSubmit(username);
    }

    private void login() {
        String username = "tj";

        driver.get(baseURL + "/login");

        waitUntilElementClickable(signupLinkString);

        loginPage.fillInfoAndSubmit(username);
    }

    private void logout() {
        WebElement logoutButton = waitUntilElementClickable(logoutButtonString);
        assertNotNull(logoutButton);

        click(driver, logoutButton);

        waitUntilElementClickable(signupLinkString);
    }

    private WebElement waitUntilElementClickable(String id) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }
}
