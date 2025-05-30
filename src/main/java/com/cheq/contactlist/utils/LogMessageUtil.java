package com.cheq.contactlist.utils;

import org.openqa.selenium.By;

/**
 * Utility class for generating consistent log messages across utilities.
 */
public class LogMessageUtil {

	public static final String TIMEOUT_EXCEPTION_ERROR_MESSAGE = "Timeout while waiting for element: {%s}";
    public static final String ACTION_SUCCESS_MESSAGE = "Action [%s] performed successfully on: {%s}";
    public static final String ACTION_FAILED_MESSAGE = "Action [%s] failed on: {%s}";
    public static final String CLASS_INITIALIZED_MESSAGE = "Utility class [%s] initialized successfully with timeout: %s seconds";

    // Keyboard specific messages
    public static final String PRESS_KEY_SUCCESS_MESSAGE = "Key [%s] pressed successfully on: {%s}";
    public static final String PRESS_KEY_FAILED_MESSAGE = "Failed to press key [%s] on: {%s}";
    public static final String TYPE_TEXT_SUCCESS_MESSAGE = "Typed text [%s] successfully on: {%s}";
    public static final String TYPE_TEXT_FAILED_MESSAGE = "Failed to type text [%s] on: {%s}";
    public static final String CLEAR_FIELD_SUCCESS_MESSAGE = "Cleared field successfully on: {%s}";
    public static final String CLEAR_FIELD_FAILED_MESSAGE = "Failed to clear field on: {%s}";
    public static final String COPY_PASTE_SUCCESS_MESSAGE = "Copy-paste action completed successfully between elements";
    public static final String COPY_PASTE_FAILED_MESSAGE = "Copy-paste action failed between elements";
    
    // Use for waitUtil class
    public static final String ACTION_STARTED_MESSAGE = "Started action: [%s] on: {%s}";

    // Specific wait-related messages
    public static final String ELEMENT_VISIBLE_MESSAGE = "Element visible: {%s}";
    public static final String ELEMENT_NOT_VISIBLE_MESSAGE = "Element not visible after timeout: {%s}";

    public static final String ELEMENT_CLICKABLE_MESSAGE = "Element clickable: {%s}";
    public static final String ELEMENT_NOT_CLICKABLE_MESSAGE = "Element not clickable after timeout: {%s}";

    public static final String ELEMENT_DISAPPEARED_MESSAGE = "Element disappeared: {%s}";
    public static final String ELEMENT_DID_NOT_DISAPPEAR_MESSAGE = "Element did not disappear after timeout: {%s}";

    public static final String URL_CONTAINS_MESSAGE = "URL contains expected substring: %s";
    public static final String URL_DOES_NOT_CONTAIN_MESSAGE = "URL does not contain expected substring after timeout: %s";

    public static final String TEXT_PRESENT_MESSAGE = "Text [%s] present in element: {%s}";
    public static final String TEXT_NOT_PRESENT_MESSAGE = "Text [%s] not present in element after timeout: {%s}";

    public static final String ALERT_PRESENT_MESSAGE = "Alert is present";
    public static final String ALERT_NOT_PRESENT_MESSAGE = "Alert not present after timeout";

    public static final String WAIT_VISIBILITY_TIMEOUT_MESSAGE = "Wait for visibility timed out for element: {%s}";

    public static final String IMPLICIT_WAIT_SET_MESSAGE = "Implicit wait set to %s seconds";
    public static final String EXPLICIT_WAIT_SET_MESSAGE = "Explicit wait set to %s seconds";

    public static final String PAUSE_MESSAGE = "Pausing execution for %s seconds";
    public static final String SLEEP_MESSAGE = "Sleeping for %s milliseconds";

    public static String formatMessage(String message, Object... values) {
        return String.format(message, values);
    }

    public static String actionSuccess(String action, Object target) {
        return formatMessage(ACTION_SUCCESS_MESSAGE, action, target.toString());
    }

    public static String actionFail(String action, Object target) {
        return formatMessage(ACTION_FAILED_MESSAGE, action, target.toString());
    }

    public static String initialized(String className, int timeoutSeconds) {
        return formatMessage(CLASS_INITIALIZED_MESSAGE, className, timeoutSeconds);
    }

    public static String timeoutException(Object locator) {
        return formatMessage(TIMEOUT_EXCEPTION_ERROR_MESSAGE, locator.toString());
    }

    // Keyboard specific helpers
    public static String pressKeySuccess(String key, Object target) {
        return formatMessage(PRESS_KEY_SUCCESS_MESSAGE, key, target.toString());
    }

    public static String pressKeyFailed(String key, Object target) {
        return formatMessage(PRESS_KEY_FAILED_MESSAGE, key, target.toString());
    }

    public static String typeTextSuccess(String text, Object target) {
        return formatMessage(TYPE_TEXT_SUCCESS_MESSAGE, text, target.toString());
    }

    public static String typeTextFailed(String text, Object target) {
        return formatMessage(TYPE_TEXT_FAILED_MESSAGE, text, target.toString());
    }

    public static String clearFieldSuccess(Object target) {
        return formatMessage(CLEAR_FIELD_SUCCESS_MESSAGE, target.toString());
    }

    public static String clearFieldFailed(Object target) {
        return formatMessage(CLEAR_FIELD_FAILED_MESSAGE, target.toString());
    }

    public static String copyPasteSuccess() {
        return COPY_PASTE_SUCCESS_MESSAGE;
    }

    public static String copyPasteFailed() {
        return COPY_PASTE_FAILED_MESSAGE;
    }
    
    public static String actionStarted(String action, Object target) {
        return String.format(ACTION_STARTED_MESSAGE, action, target);
    }

    public static String elementVisible(Object locator) {
        return String.format(ELEMENT_VISIBLE_MESSAGE, locator);
    }

    public static String elementNotVisible(Object locator) {
        return String.format(ELEMENT_NOT_VISIBLE_MESSAGE, locator);
    }

    public static String elementClickable(Object locator) {
        return String.format(ELEMENT_CLICKABLE_MESSAGE, locator);
    }

    public static String elementNotClickable(Object locator) {
        return String.format(ELEMENT_NOT_CLICKABLE_MESSAGE, locator);
    }

    public static String elementDisappeared(Object locator) {
        return String.format(ELEMENT_DISAPPEARED_MESSAGE, locator);
    }

    public static String elementDidNotDisappear(Object locator) {
        return String.format(ELEMENT_DID_NOT_DISAPPEAR_MESSAGE, locator);
    }

    public static String urlContains(String partialUrl) {
        return String.format(URL_CONTAINS_MESSAGE, partialUrl);
    }

    public static String urlDoesNotContain(String partialUrl) {
        return String.format(URL_DOES_NOT_CONTAIN_MESSAGE, partialUrl);
    }

    public static String textPresent(Object locator, String text) {
        return String.format(TEXT_PRESENT_MESSAGE, text, locator);
    }

    public static String textNotPresent(Object locator, String text) {
        return String.format(TEXT_NOT_PRESENT_MESSAGE, text, locator);
    }

    public static String alertPresent() {
        return ALERT_PRESENT_MESSAGE;
    }

    public static String alertNotPresent() {
        return ALERT_NOT_PRESENT_MESSAGE;
    }

    public static String waitVisibilityTimeout(Object locator) {
        return String.format(WAIT_VISIBILITY_TIMEOUT_MESSAGE, locator);
    }

    public static String implicitWaitSet(int seconds) {
        return String.format(IMPLICIT_WAIT_SET_MESSAGE, seconds);
    }

    public static String explicitWaitSet(int seconds) {
        return String.format(EXPLICIT_WAIT_SET_MESSAGE, seconds);
    }

    public static String pause(int seconds) {
        return String.format(PAUSE_MESSAGE, seconds);
    }

    public static String sleep(long millis) {
        return String.format(SLEEP_MESSAGE, millis);
    }
}
