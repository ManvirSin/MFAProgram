import java.util.Map;
import java.util.Scanner;

// Login controller class
public class LoginController {
    private final MFA mfa;
    public int remainingTries = 3;

    public LoginController(MFA mfa, Map<String, String> loginCredentials) {
        this.mfa = mfa;
    }

    public LoginController(MFA mfa) {

        this.mfa = null;
    }


    public void login() {
        Scanner scanner = new Scanner(System.in);

        while (remainingTries > 0) {
            System.out.print("Enter verification code: ");
            String inputCode = scanner.nextLine();

            if (inputCode.equals(mfa.generateCode())) {
                System.out.println("Logged in successfully!");
                return;
            }

            remainingTries--;
            System.out.println("Invalid verification code. Remaining tries: " + remainingTries);
        }

        System.out.println("Maximum number of login attempts reached. System will shut down.");
    }


    public void verifyMFA() {
        String code = mfa.generateCode();

        System.out.println("Verification code sent to your device: " + code);
        Scanner scanner = new Scanner(System.in);
        int remainingMfaTries = 3;

        while (remainingMfaTries > 0) {
            System.out.print("Enter verification code: ");
            String inputCode = scanner.nextLine();

            if (inputCode.equals(code)) {
                System.out.println("MFA verified successfully!");
                return;
            }

            remainingMfaTries--;
            System.out.println("Invalid verification code. Remaining tries: " + remainingMfaTries);
        }

        System.out.println("Maximum number of MFA verification attempts reached. System will shut down.");
    }
}
