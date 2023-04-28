import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Hashmap of Email-Password Pairs
        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials.put("msingh13@bradford.ac.uk", "Thisismypassword123");
        loginCredentials.put("ialsogoto@bradford.ac.uk", "hello123byebye");
        loginCredentials.put("mynameis@bradford.ac.uk", "ishouldhavejoinedthearmy07");

        // Input email and password from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email: ");
        String inputEmail = scanner.nextLine();
        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        // Check if email and password match
        if (loginCredentials.containsKey(inputEmail) && loginCredentials.get(inputEmail).equals(inputPassword)) {
            System.out.println("Logged in successfully!");
            // Get MFA type from user
            System.out.print("Choose MFA type, 1 for email, 2 for SMS, 3 for Phone Call (Not implemented): ");
            int mfaType = scanner.nextInt();

            // MFA verification based on user's choice
            MFA mfa;
            if (mfaType == 1) {
                mfa = new EmailMFA();
            } else if (mfaType == 2) {
                mfa = new SMSMFA();
            } else {
                System.out.println("Invalid MFA type.");
                return;
            }
            verifyMFA(mfa);
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    public static void verifyMFA(MFA mfa) {
        String code = mfa.generateCode();

        System.out.println("Verification code sent to your device: " + code);
        Scanner scanner = new Scanner(System.in);
        int remainingMfaTries = 3;
        //counts the number of MFA tires down from 3
        while (remainingMfaTries > 0) {
            System.out.print("Enter verification code: ");
            String inputCode = scanner.nextLine();

            if (inputCode.equals(code)) {
                System.out.println("MFA verified successfully!");
                return;
            }
            // Tells user number of tries left
            remainingMfaTries--;
            System.out.println("Invalid verification code. Remaining tries: " + remainingMfaTries);
        }
        //Shutdown
        System.out.println("Maximum number of MFA verification attempts reached. System will shut down.");
    }
}