package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

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
    public void getUnauthorizedLoginPageAccess() {
        driver.get(baseURL + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testUnauthorizedSignupPageAccess() {
        driver.get(baseURL + "/signup");

        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    public void testUnauthorizedAccess() {
        driver.get(baseURL + "/home");

        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testSigningInAndChat() {
        String username = "tj";

		driver.get(baseURL + "/signup");

		signupPage.fillInfoAndSubmit(username);

        driver.get(baseURL + "/login");

        waitUntilElementIdFound(loginPage.signupLinkString);

        loginPage.fillInfoAndSubmit(username);

        WebElement logoutButton = waitUntilElementIdFound(homePage.logoutButtonString);
        assertNotNull(logoutButton);

        logoutButton.click();

        waitUntilElementIdFound(loginPage.signupLinkString);

        testUnauthorizedAccess();
    }

    private WebElement waitUntilElementIdFound(String id) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait.until(webDriver -> webDriver.findElement(By.id(id)));
    }

    private WebElement waitUntilElementTagFound(String tag) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait.until(webDriver -> webDriver.findElement(By.tagName(tag)));
    }
}
