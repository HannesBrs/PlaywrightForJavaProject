package org.Playwright.Project;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Class for the Playwright Example Test
 */
public class ExampleTest extends BaseTest {

    /**
     * This method is a simple Example Test that uses Playwright
     * to assert the Google page title.
     */
    @Test
    public void checkGooglePageTitle() {
        page.navigate("https://www.google.com/");
        String actualTitle = page.title();
        String expectedTitle = "Google";
        assertEquals(expectedTitle, actualTitle);
    }

}
