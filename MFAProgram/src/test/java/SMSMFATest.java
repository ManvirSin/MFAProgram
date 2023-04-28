import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SMSMFATest {
    @Test
    public void testGenerateCode() {
        SMSMFA mfa = new SMSMFA();
        String code = mfa.generateCode();
        assertTrue(code.matches("^\\d{6}$"));
    }
}
