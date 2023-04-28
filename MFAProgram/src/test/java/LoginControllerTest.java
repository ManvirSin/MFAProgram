import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

class LoginControllerTest {

    private MFA mockMfa;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        mockMfa = new MockMFA();
        loginController = new LoginController(mockMfa);
    }

    @Test
    @DisplayName("Verify login success with valid MFA code")
    void testLoginSuccessWithValidMfaCode() {
        // Set up input stream to simulate user input
        InputStream in = new ByteArrayInputStream("123456\n".getBytes());
        System.setIn(in);

        // Call login method
        loginController.login();

        // Check that remaining tries is reset
        Assertions.assertEquals(3, loginController.remainingTries);
    }

    @Test
    @DisplayName("Verify login failure with invalid MFA code")
    void testLoginFailureWithInvalidMfaCode() {
        // Set up input stream to simulate user input
        InputStream in = new ByteArrayInputStream("111111\n222222\n333333\n".getBytes());
        System.setIn(in);

        // Call login method
        loginController.login();

        // Check that remaining tries is 0
        Assertions.assertEquals(0, loginController.remainingTries);
    }

    @Test
    @DisplayName("Verify login failure with maximum login attempts reached")
    void testLoginFailureWithMaxAttemptsReached() {
        // Set up input stream to simulate user input
        InputStream in = new ByteArrayInputStream("111111\n222222\n333333\n444444\n555555\n".getBytes());
        System.setIn(in);

        // Call login method
        loginController.login();

        // Check that remaining tries is 0
        Assertions.assertEquals(0, loginController.remainingTries);
    }

    // Mock MFA class for testing purposes
    private static class MockMFA implements MFA {

        @Override
        public String generateCode() {
            return "123456";
        }
    }
}
