package org.Playwright.Project;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This Test class tests the slider / range input
 */
public class SliderRangeInputTest extends BaseTest{

    /**
     * This parameterized test checks whether the label that shows the password length changes to the
     * according value of the range input slider (in this case min: 5, max: 35)
     * @param rangeInputValue the value that the range input will be set to
     * ID 051
     */
    @ParameterizedTest
    @ValueSource(ints = {5, 20, 35})
    public void checkLengthLabelOnRangeInput(int rangeInputValue) {
        page.getByRole(AriaRole.SLIDER).evaluate("element => { " +
                "element.value = " + rangeInputValue + "; element.dispatchEvent(new Event('input')); }");
        Locator labelLocator = page.getByTestId("lengthValueLabel");

        int actualLabelValue = Integer.parseInt(labelLocator.textContent());

        assertEquals(rangeInputValue, actualLabelValue);
    }

    /**
     * This parameterized test checks whether the label that shows the password length changes to the
     * according value of the range input slider when the input is out of range (which it should not!)
     * @param rangeInputValue the value that the range input will be set to
     * ID 052
     */
    @ParameterizedTest
    @ValueSource(ints = {4, 36})
    public void checkLengthLabelOnRangeInputOutOfRange(int rangeInputValue) {
        page.getByRole(AriaRole.SLIDER).evaluate("element => { " +
                "element.value = " + rangeInputValue + "; element.dispatchEvent(new Event('input')); }");
        Locator labelLocator = page.getByTestId("lengthValueLabel");

        int actualLabelValue = Integer.parseInt(labelLocator.textContent());

        assertNotEquals(rangeInputValue, actualLabelValue);
    }

    /**
     * This test checks whether the label that shows the password length is set
     * to the default value of the slider which is '5'
     * ID 053
     */
    @Test
    public void checkLengthLabelOnRangeInputDefault() {
        Locator labelLocator = page.getByTestId("lengthValueLabel");

        int expectedDefault = 5;
        int actualLabelValue = Integer.parseInt(labelLocator.textContent());

        assertEquals(expectedDefault, actualLabelValue);
    }

    /**
     * This parameterized tests checks whether the slider movement works with arrow keys,
     * going from the "left" min value 5 to the "right" max value 35
     * -first moving 1 step to the right  (min value +1)
     * -then moving 15 steps to the right (center)
     * -then moving 30 steps to the right (max value)
     * @param steps amount of the arrow key presses
     * ID 054
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 30})
    public void checkSliderKeyboardMovementIncrements(int steps) {
        Locator sliderLocator = page.getByRole(AriaRole.SLIDER);
        sliderLocator.focus();

        for (int i = 0; i < steps; i++) {
            sliderLocator.press("ArrowRight");
        }

        Locator labelLocator = page.getByTestId("lengthValueLabel");

        int expectedValue = 5 + steps;
        int actualLabelValue = Integer.parseInt(labelLocator.textContent());

        assertEquals(expectedValue, actualLabelValue);
    }

    /**
     * This parameterized tests checks whether the slider movement works with arrow keys,
     * going from the "right" max value 35 to the "left" min value 5
     * -first moving 1 step to the left  (max value -1)
     * -then moving 15 steps to the left (center)
     * -then moving 30 steps to the left (min value)
     * @param steps amount of the arrow key presses
     * ID 055
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 30})
    public void checkSliderKeyboardMovementDecrements(int steps) {
        Locator sliderLocator = page.getByRole(AriaRole.SLIDER);
        sliderLocator.evaluate("element => element.value = 35");
        sliderLocator.focus();

        for (int i = 0; i < steps; i++) {
            sliderLocator.press("ArrowLeft");
        }

        Locator labelLocator = page.getByTestId("lengthValueLabel");

        int expectedValue = 35 - steps;
        int actualLabelValue = Integer.parseInt(labelLocator.textContent());

        assertEquals(expectedValue, actualLabelValue);
    }


    /**
     * This test checks whether the label that shows the password length changes to the
     * min -1 when the left arrow key is pressed, starting from the min
     * (which it should not, because the value should not get below the minimum value)
     * ID 056
     */
    @Test
    public void checkSliderKeyboardMovementOutOfRangeLeft() {
        Locator sliderLocator = page.getByRole(AriaRole.SLIDER);
        sliderLocator.focus();
        sliderLocator.press("ArrowLeft");

        Locator labelLocator = page.getByTestId("lengthValueLabel");

        int expectedValue = 4;
        int actualLabelValue = Integer.parseInt(labelLocator.textContent());

        assertNotEquals(expectedValue, actualLabelValue);
    }

    /**
     * This test checks whether the label that shows the password length changes to the
     * max +1 when the right arrow key is pressed, starting from the max
     * (which it should not, because the value should not get larger than the maximum value)
     * ID 057
     */
    @Test
    public void checkSliderKeyboardMovementOutOfRangeRight() {
        Locator sliderLocator = page.getByRole(AriaRole.SLIDER);
        sliderLocator.evaluate("element => { " +
                "element.value =  35; element.dispatchEvent(new Event('input')); }");
        sliderLocator.focus();
        sliderLocator.press("ArrowRight");


        Locator labelLocator = page.getByTestId("lengthValueLabel");

        int expectedValue = 36;
        int actualLabelValue = Integer.parseInt(labelLocator.textContent());

        assertNotEquals(expectedValue, actualLabelValue);
    }
}
