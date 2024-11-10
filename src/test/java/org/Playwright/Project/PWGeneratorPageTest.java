package org.Playwright.Project;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This Test class tests chosen features of the password generator website
 * using the 'Page Object Model'
 */
public class PWGeneratorPageTest extends BaseTest {

    private PWGeneratorPageObjectModel pwGeneratorPage;

    /**
     * This method creates the page instance and opens it
     */
    @BeforeEach
    public void createPWGeneratorPageObject() {
        pwGeneratorPage = new PWGeneratorPageObjectModel(page);
        pwGeneratorPage.open();
    }

    /**
     * Checks the title of the page
     * ID 032
     */
    @Test
    public void checkPageTitle() {
        String actualTitle = pwGeneratorPage.getPageTitle();
        String expectedTitle = "Password-Generator";
        assertEquals(expectedTitle, actualTitle);
    }

    /**
     * Checks the header of the page
     * ID 012
     */
    @Test
    public void checkHeader() {
        String actualHeading = pwGeneratorPage.getHeader();
        String expectedHeading = "Select your Password Parameters!";
        assertEquals(expectedHeading, actualHeading);
    }

    /**
     * Checks if the checkboxes are checked correctly by default
     * ID 031
     */
    @Test
    public void checkCheckBoxes() {
        assertTrue(pwGeneratorPage.getCheckboxLowercaseLetters() &&
                pwGeneratorPage.getCheckboxCapitalLetters() &&
                pwGeneratorPage.getCheckboxNumbers() &&
                pwGeneratorPage.getCheckboxSpecialCharacters());
    }

    /**
     * Checks if the generate and regenerate buttons are disabled when no checkboxes are checked
     * ID 021
     */
    @Test
    public void disableGenerateButtonsWithoutCheckedCheckboxes() {
        Utility.uncheckCheckBoxes(page);
        assertTrue(pwGeneratorPage.getGenerateButtonStatus() && pwGeneratorPage.getRegenerateButtonStatus());
    }

    /**
     * Checks if the Label showing the int value is updated when moving the slider
     * ID 058
     */
    @Test
    public void increaseLabelValueOnSliderMove() {
        pwGeneratorPage.clickPasswordLengthSlider();
        String expected = "20";  //20, because when "clicking" length slider, Playwright takes the "middle", which equals 20 in our case
        String actual = pwGeneratorPage.getPasswordLengthLabel();
        assertEquals(expected, actual);
    }
}