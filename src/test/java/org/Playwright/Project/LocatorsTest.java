package org.Playwright.Project;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This Test Class makes use of different playwright locator types
 * (e.g. getByRole, getByText, etc.)
 */
public class LocatorsTest extends BaseTest{

    
    /**
     * This parameterized test checks whether the different headers are visible using the text locator.
     * @param headerText the text content of the according header
     * ID 031
     */
    @ParameterizedTest
    @ValueSource(strings = {"Select your Password Parameters!", "Your Generated Password", "Rules for strong Passwords"})
    public void checkHeaderVisibility(String headerText) {
        boolean visible = page.getByText(headerText).isVisible();

        assertTrue(visible);
    }

    /**
     * This parameterized test checks whether the different checkboxes are checked by default using the role locator.
     * @param checkboxLabel the label to the checkbox
     * ID 031
     */
    @ParameterizedTest
    @ValueSource(strings = {"use lower case letters", "use capital letters", "use numbers", "use special characters"})
    public void checkCheckBoxesChecked(String checkboxLabel) {
        boolean checked = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(checkboxLabel)).isChecked();

        assertTrue(checked);
    }

    /**
     * This parameterized test checks whether the different checkboxes are unchecked using the role locator.
     * @param checkboxLabel the label to the checkbox
     */
    @ParameterizedTest
    @ValueSource(strings = {"use lower case letters", "use capital letters", "use numbers", "use special characters"})
    public void checkCheckBoxesUnchecked(String checkboxLabel) {
        Locator locator = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(checkboxLabel));
        locator.setChecked(false);
        boolean checked = locator.isChecked();

        assertFalse(checked);
    }
}
