package org.Playwright.Project;

import com.microsoft.playwright.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the language on the website
 */
public class LanguageTest extends BaseTest {

    /**
     * Checks if the language headers are displayed correctly
     * @param languageName the name of the language
     * @param headerId the id of the header
     * @param expectedHeading the text of the header
     * ID 011
     */
    @ParameterizedTest
    @MethodSource("provideParametersForHeadersTest")
    public void checkLanguageHeaders(String languageName, String headerId, String expectedHeading) {
        Locator loc = page.locator("id=languageSelect");
        loc.selectOption(languageName);
        loc = page.locator("id=" + headerId);
        String actualHeading = loc.textContent();
        assertEquals(expectedHeading, actualHeading);
    }

    /**
     * Provides the parameters for checkLanguageHeaders (because they are tuples)
     * @return returns an argument-tuple of the language name, the header id and its corresponding text
     */
    private static Stream<Arguments> provideParametersForHeadersTest() throws IOException {
        return LanguageDataProvider.provideParametersForHeadersTest();
    }
}
