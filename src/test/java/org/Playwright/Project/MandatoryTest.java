package org.Playwright.Project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class resembles a mandatory test which can show that
 * if a test in a class ending with 'Test' fails, the whole build fails.
 */
public class MandatoryTest {

    /**
     * Test method that is critical to the build process
     * (change 'true' to 'false' in order to fail the build on purpose)
     */
    @Test
    public void test() {
        assertTrue(true);
    }
}
