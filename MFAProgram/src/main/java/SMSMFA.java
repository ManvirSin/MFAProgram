// SMS implementation of MFA

public class SMSMFA implements MFA {
    @Override
    public String generateCode() {
        // Generate 6 digit code
        return String.format("%06d", (int) (Math.random() * 1000000));
    }
}
