package org.Playwright.Project;

import com.microsoft.playwright.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the external redirect links
 */
public class LinkRedirectOptionalIT extends BaseTest {

    /**
     * Checks if the links redirect to the correct pages
     * @param linkId the id of the link
     * @param expectedUrl the expected url
     * ID 041, ID 042, ID 043
     */
    @ParameterizedTest
    @MethodSource("provideParametersForLinkRedirectTest")
    public void checkLinkRedirect(String linkId,String expectedUrl) {
        Locator loc = page.locator("id=" + linkId);
        loc.click();
        String actualUrl = page.url();
        assertEquals(expectedUrl, actualUrl);
        
    }

    /**
     * Provides the parameters for checkLinkRedirect (because they are tuples)
     * @return returns an argument-tuple of the link id and its corresponding url
     */
    private static Stream<Arguments> provideParametersForLinkRedirectTest() {
        return Stream.of(
                Arguments.of("google", "https://support.google.com/accounts/answer/32040?hl=en"),
                Arguments.of("microsoft", "https://support.microsoft.com/en-us/windows/create-and-use-strong-passwords-c5cebb49-8c53-4f5e-2bc4-fe357ca048eb"),
                Arguments.of("harvard", "https://privsec.harvard.edu/use-strong-passwords")
        );
    }
}
