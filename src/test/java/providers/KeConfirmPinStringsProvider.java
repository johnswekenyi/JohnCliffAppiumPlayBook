package providers;

public class KeConfirmPinStringsProvider implements SetPinStringsProvider {
    @Override
    public String getTitleTextString() {
        return "SIGN UP";
    }

    @Override
    public String getHeaderTextString() {
        return "Confirm 4 digit PIN";
    }

    @Override
    public String getDetailsTextString() {
        return "Please confirm your PIN. You will need it every time you open Tala.";
    }

    @Override
    public String getBackButtonString() {
        return "BACK";
    }

    @Override
    public String getNextButtonString() {
        return "SUBMIT";
    }

    @Override
    public String getSwitchMaskTextString() {
        return "SHOW";
    }
}
