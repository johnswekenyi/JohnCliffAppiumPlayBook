package providers;

public class KeSignUpPageStringsProvider implements SignUpPageStringsProvider {
    public String getTitleString() {
        return "SIGN UP";
    }

    public String getDescriptionTestString() {
        return "Enter your M-Pesa number";
    }

    public String getSecondaryDescriptionString() {
        return "We’ll send a confirmation code to verify your number.";
    }

    public String getCodeString() {
        return "+254";
    }

    public String getBackButonString() {
        return "BACK";
    }

    public String getNextButonString() {
        return "NEXT";
    }
}
