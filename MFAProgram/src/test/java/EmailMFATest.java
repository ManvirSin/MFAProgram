import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailMFATest {

        @Test
        public void testGenerateCode() {
            EmailMFA mfa = new EmailMFA();
            String code = mfa.generateCode();
            assertTrue(code.matches("^\\d{6}$"));
        }
    }