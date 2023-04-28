import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testValidLoginWithEmailMFA() {
        // Arrange
        String input = "msingh13@bradford.ac.uk\nThisismypassword123\n1\n123456\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        Main.main(new String[] {});

        // Assert
        // The test passes if it doesn't throw an exception
    }

    @Test
    void testValidLoginWithSMSMFA() {
        // Arrange
        String input = "ialsogoto@bradford.ac.uk\nhello123byebye\n2\n654321\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        Main.main(new String[] {});

        // Assert
        // The test passes if it doesn't throw an exception
    }

    @Test
    void testInvalidLogin() {
        // Arrange
        String input = "invalid@example.com\npassword\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        Main.main(new String[] {});

        // Assert
        // The test passes if it doesn't throw an exception
    }

    @Test
    void testInvalidMFAType() {
        // Arrange
        String input = "msingh13@bradford.ac.uk\nThisismypassword123\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        Main.main(new String[] {});

        // Assert
        // The test passes if it doesn't throw an exception
    }

    @Test
    void testInvalidMFAVerificationCode() {
        // Arrange
        String input = "msingh13@bradford.ac.uk\nThisismypassword123\n1\n123\n123\n123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        Main.main(new String[] {});

        // Assert
        // The test passes if it doesn't throw an exception
    }
}



