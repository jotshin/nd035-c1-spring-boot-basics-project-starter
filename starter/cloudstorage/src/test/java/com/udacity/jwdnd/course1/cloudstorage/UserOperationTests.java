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
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.udacity.jwdnd.course1.cloudstorage.Util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserOperationTests {

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
    public void getUnauthorizedLoginPageAccess() {
        driver.get(baseURL + "/login");
        assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testUnauthorizedSignupPageAccess() {
        driver.get(baseURL + "/signup");

        assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    public void testUnauthorizedAccess() {
        driver.get(baseURL + "/home");

        assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testSigningInAndOut() {
        signupAndLogin();

        logout();

        testUnauthorizedAccess();
    }

    private void signupAndLogin() {
        String username = "tj";

        driver.get(baseURL + "/signup");

        signupPage.fillInfoAndSubmit(username);

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

//    private void fillLoginInfoAndVerify(String username) {
//        waitUntilElementIdFound(loginPage.signupLinkString);
//
//        loginPage.fillInfoAndSubmit(username);
//
//        WebElement messageText = waitUntilElementIdFound("messageText");
//        assertNotNull(messageText);
//
//        if (username == "tj") {
//            waitUntilElementIdFound(chat.messageTextString);
//            chat.fillInfoAndSubmit();
//        }
//
//        WebElement span = waitUntilElementTagFound("span");
//        assertTrue(span.getText().contains("tj: Test"));
//    }

    private WebElement waitUntilElementClickable(String id) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

    private WebElement waitUntilElementTagFound(String tag) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait.until(webDriver -> webDriver.findElement(By.tagName(tag)));
    }
}
