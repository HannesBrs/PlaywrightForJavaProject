package org.Playwright.Project;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.File;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * This utility class provides utility methods throughout the tests
 */
public class Utility {

    /**
     * Helper-Method to convert source paths to Absolute, since we have no URLS
     * @param relativePath the project path
     * @return the absolute path on the machine
     */
    public static String absolutePath(String relativePath) {
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }

    /**
     * Unchecks all checkboxes to see if the generate and regenerate button is disabled
     */
    public static void uncheckCheckBoxes(Page page) {
        String[] checkBoxIds = {"cbxLowerCaseLetters", "cbxCapitalLetters", "cbxNumbers", "cbxSpecialCharacters"};
        for (String cbx : checkBoxIds){
            Locator loc = page.locator("id=" + cbx);
            loc.uncheck();
            assertThat(loc).not().isChecked();
        }
    }
}
