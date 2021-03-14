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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class CredentialOperationTests {

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
    public void testCreateCredential() {
        signupPage.signup(driver, baseURL);

        loginPage.login(driver, baseURL);

        WebElement newCredentialButton = navigateToCredentialTabAndCheckUsername("Example Credential Password", "Example Credential Username", newCredentialButtonString);

        Util.click(driver, newCredentialButton);

        waitUntilElementClickable(driver, credentialTitleString);
        homePage.fillCredentialInfoAndSubmit("admin");

        resultPage.checkSuccessResult();

        navigateToCredentialTabAndCheckUsername("1234", "admin", newCredentialButtonString);

        homePage.logout(driver);
    }

    @Test
    public void testEditCredential() {
        loginPage.login(driver, baseURL);

        WebElement updateCredentialButton = navigateToCredentialTabAndCheckUsername("1234", "admin", credentialUpdateButtonString);

        Util.click(driver, updateCredentialButton);

        WebElement passwordField = waitUntilElementClickable(driver, credentialPasswordString);

        // check if password is decrypted
        assertEquals(passwordField.getAttribute("value"), "1234");

        homePage.fillCredentialInfoAndSubmit("admin1");

        resultPage.checkSuccessResult();

        navigateToCredentialTabAndCheckUsername("1234", "admin1", credentialUpdateButtonString);

        homePage.logout(driver);
    }

    @Test
    public void testRemoveCredential() {
        loginPage.login(driver, baseURL);

        WebElement deleteButton = navigateToCredentialTabAndCheckUsername("1234", "admin1", credentialDeleteButtonString);

        click(driver, deleteButton);

        WebElement credentialSubmitButton = waitUntilElementClickable(driver, credentialAddSubmitButtonString);
        click(driver, credentialSubmitButton);

        resultPage.checkSuccessResult();

        navigateToCredentialTabAndCheckUsername("Example Credential Password", "Example Credential Username", newCredentialButtonString);

        homePage.logout(driver);
    }

    private WebElement navigateToCredentialTabAndCheckUsername(String password, String username, String buttonId) {
        WebElement navNotesTab = waitUntilElementClickable(driver, navCredentialsTabString);
        click(driver, navNotesTab);

        WebElement button = waitUntilElementClickable(driver, buttonId);
        assertNotNull(button);

        WebElement usernameField = driver.findElement(By.cssSelector("#credentialTable > tbody > tr > td:nth-child(3)"));
        assertEquals(username, usernameField.getText());
        WebElement passwordField = driver.findElement(By.cssSelector("#credentialTable > tbody > tr > td:nth-child(3)"));
        assertNotEquals(password, passwordField.getText());

        return button;
    }
}
