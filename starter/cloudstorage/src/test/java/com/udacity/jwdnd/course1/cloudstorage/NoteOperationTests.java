package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.udacity.jwdnd.course1.cloudstorage.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private ResultPage resultPage;

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
        baseURL = "http://localhost:" + port;
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        resultPage = new ResultPage(driver);
    }

    @Test
    public void testCreateNote() {
        signupPage.signup(driver, baseURL);

        loginPage.login(driver, baseURL);

        WebElement newNoteButton = navigateToNoteTabAndCheckTitle("Example Note Title", newNoteButtonString);

        Util.click(driver, newNoteButton);

        waitUntilElementClickable(driver, noteTitleString);
        homePage.fillNoteInfoAndSubmit("test");

        resultPage.checkSuccessResult();

        navigateToNoteTabAndCheckTitle("test", newNoteButtonString);

        homePage.logout(driver);
    }

    @Test
    public void testEditNote() {
        loginPage.login(driver, baseURL);

        WebElement editButton = navigateToNoteTabAndCheckTitle("test", noteEditButtonString);

        Util.click(driver, editButton);

        waitUntilElementClickable(driver, noteTitleString);
        homePage.fillNoteInfoAndSubmit("test1");

        resultPage.checkSuccessResult();

        navigateToNoteTabAndCheckTitle("test1", noteEditButtonString);

        homePage.logout(driver);
    }

    @Test
    public void testRemoveNote() {
        loginPage.login(driver, baseURL);

        WebElement deleteButton = navigateToNoteTabAndCheckTitle("test1", noteDeleteButtonString);

        click(driver, deleteButton);

        WebElement noteSubmitButton = waitUntilElementClickable(driver, noteAddSubmitButtonString);
        click(driver, noteSubmitButton);

        resultPage.checkSuccessResult();

        navigateToNoteTabAndCheckTitle("Example Note Title", newNoteButtonString);

        homePage.logout(driver);
    }

    private WebElement navigateToNoteTabAndCheckTitle(String title, String buttonId) {
        WebElement navNotesTab = waitUntilElementClickable(driver, navNotesTabString);
        click(driver, navNotesTab);

        WebElement button = waitUntilElementClickable(driver, buttonId);
        assertNotNull(button);

        WebElement header = driver.findElement(By.cssSelector("#userTable > tbody > tr > th"));
        assertEquals(title, header.getText());

        return button;
    }
}
