package org.Playwright.Project;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

/**
 * This abstract class provides an isolated and reusable testing environment
 * setup to all Test classes that inherit from it.
 */
public abstract class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected Page page;
    protected BrowserContext browserContext;

    /**
     * This method creates the playwright instance and
     * launches the browser before all tests in order to
     * set up the test environment.
     */
    @BeforeAll
    public static void setUp() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch();//new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200));
    }

    /**
     * This method closes the browser and the playwright instance
     * in order to tear down the test environment.
     */
    @AfterAll
    public static void tearDown() {
        browser.close();
        playwright.close();
    }

    /**
     * This method creates a new browser context and navigates to the
     * page before each test is executed.
     */
    @BeforeEach
    public void createContextAndPage() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate("file://" + Utility.absolutePath("src/main/PW-Generator/index.html"));
    }

    /**
     * This method destroys the browser context
     * after each test has been executed.
     */
    @AfterEach
    public void destroyContext() {
        browserContext.close();
    }
}
