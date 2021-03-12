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
class CredentialOperationTests {

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
    public void testCreateCredential() {
        signup();

        login();

        WebElement newCredentialButton = navigateToCredentialTabAndCheckUsername("Example Credential Password", "Example Credential Username", newCredentialButtonString);

        Util.click(driver, newCredentialButton);

        waitUntilElementClickable(credentialTitleString);
        homePage.fillCredentialInfoAndSubmit("admin");

        checkSuccessResult();

        navigateToCredentialTabAndCheckUsername("1234", "admin", newCredentialButtonString);

        logout();
    }

    @Test
    public void testEditCredential() {
        login();

        WebElement updateCredentialButton = navigateToCredentialTabAndCheckUsername("1234", "admin", credentialUpdateButtonString);

        Util.click(driver, updateCredentialButton);

        WebElement passwordField = waitUntilElementClickable(credentialPasswordString);
        assertEquals(passwordField.getAttribute("value"), "1234");
        homePage.fillCredentialInfoAndSubmit("admin1");

        checkSuccessResult();

        navigateToCredentialTabAndCheckUsername("1234", "admin1", credentialUpdateButtonString);

        logout();
    }

    @Test
    public void testRemoveCredential() {
        login();

        WebElement deleteButton = navigateToCredentialTabAndCheckUsername("1234", "admin1", credentialDeleteButtonString);

        click(driver, deleteButton);

        WebElement credentialSubmitButton = waitUntilElementClickable(credentialAddSubmitButtonString);
        click(driver, credentialSubmitButton);

        checkSuccessResult();

        navigateToCredentialTabAndCheckUsername("Example Credential Password", "Example Credential Username", newCredentialButtonString);

        logout();
    }

    private WebElement navigateToCredentialTabAndCheckUsername(String password, String username, String buttonId) {
        WebElement navNotesTab = waitUntilElementClickable(navCredentialsTabString);
        click(driver, navNotesTab);

        WebElement button = waitUntilElementClickable(buttonId);
        assertNotNull(button);

        WebElement usernameField = driver.findElement(By.cssSelector("#credentialTable > tbody > tr > td:nth-child(3)"));
        assertEquals(username, usernameField.getText());
        WebElement passwordField = driver.findElement(By.cssSelector("#credentialTable > tbody > tr > td:nth-child(3)"));
        assertNotEquals(password, passwordField.getText());

        return button;
    }

    private void checkSuccessResult() {
        WebElement successLink = waitUntilElementClickable(successAlertString);

        click(driver, successLink);
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
