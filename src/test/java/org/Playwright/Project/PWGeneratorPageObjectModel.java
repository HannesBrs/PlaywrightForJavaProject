package org.Playwright.Project;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page Object Model for the Password Generator page.
 */
public class PWGeneratorPageObjectModel {

    private final Page page;
    private final String title;
    private final Locator h1_selectPasswordParams;
    private final Locator checkbox_lowerCaseLetters;
    private final Locator checkbox_capitalLetters;
    private final Locator checkbox_Numbers;
    private final Locator checkbox_specialCharacters;
    private final Locator button_generateButton;
    private final Locator button_regenerateButton;
    private final Locator input_lengthSlider;
    private final Locator label_lengthValueLabel;

    /**
     * Constructor to initialize the page and the needed locators for the elements on the page.
     * @param page the browser page
     */
    public PWGeneratorPageObjectModel(Page page) {
        this.page = page;
        this.title = page.title();
        this.h1_selectPasswordParams = page.locator("#selectPasswordParamsHeader");
        this.checkbox_lowerCaseLetters = page.locator("#cbxLowerCaseLetters");
        this.checkbox_capitalLetters = page.locator("#cbxCapitalLetters");
        this.checkbox_Numbers = page.locator("#cbxNumbers");
        this.checkbox_specialCharacters = page.locator("#cbxSpecialCharacters");
        this.button_generateButton = page.locator("#generateButton");
        this.button_regenerateButton = page.locator("#regenerateButton");
        this.input_lengthSlider = page.locator("#lengthSlider");
        this.label_lengthValueLabel = page.locator("#lengthValueLabel");
    }

    /**
     * This method opens the page
     */
    public void open() {
        String absolutePath = Utility.absolutePath("src/main/PW-Generator/index.html");
        page.navigate("file://" + absolutePath);
    }

    /**
     * This method provides the page title
     * @return page title
     */
    public String getPageTitle() {
        return title;
    }

    /**
     * This method provides the header
     * @return header
     */
    public String getHeader() {
        return h1_selectPasswordParams.textContent();
    }

    /**
     * This method provides whether the checkbox is checked or not
     * @return true if checked, false otherwise
     */
    public boolean getCheckboxLowercaseLetters() {
        return checkbox_lowerCaseLetters.isChecked();
    }

    /**
     * This method provides whether the checkbox is checked or not
     * @return true if checked, false otherwise
     */
    public boolean getCheckboxCapitalLetters() {
        return checkbox_capitalLetters.isChecked();
    }

    /**
     * This method provides whether the checkbox is checked or not
     * @return true if checked, false otherwise
     */
    public boolean getCheckboxNumbers() {
        return checkbox_Numbers.isChecked();
    }

    /**
     * This method provides whether the checkbox is checked or not
     * @return true if checked, false otherwise
     */
    public boolean getCheckboxSpecialCharacters() {
        return checkbox_specialCharacters.isChecked();
    }

    /**
     * This method provides whether the button is disabled or not
     * @return true if disables, false otherwise
     */
    public boolean getGenerateButtonStatus() {
        return button_generateButton.isDisabled();
    }

    /**
     * This method provides whether the button is disabled or not
     * @return true if disables, false otherwise
     */
    public boolean getRegenerateButtonStatus() {
        return button_regenerateButton.isDisabled();
    }

    /**
     * This method clicks on the length slider which sets it to the middle
     */
    public void clickPasswordLengthSlider() {
        input_lengthSlider.click();
    }

    /**
     * This method provides the content of the password length label
     * @return password length (as a String)
     */
    public String getPasswordLengthLabel() {
        return label_lengthValueLabel.textContent();
    }

}
