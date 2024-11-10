package org.Playwright.Project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class resembles an optional IT which can show that
 * if a test in a class ending with 'OptionalIT' fails,
 * the whole build does NOT fail.
 */
public class ShouldFailOptionalIT {

    /**
     * Test method that is optional to the build process
     * and always fails to show that the build can still be successful
     */
    @Test
    public void shouldFail() {
        assertTrue(false);
    }
}
