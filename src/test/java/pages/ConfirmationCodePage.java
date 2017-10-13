package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ConfirmationCodePage extends BaseClass{

    @AndroidFindBy(id = "title_text_view")
    private MobileElement titleText;

    @AndroidFindBy(id = "menu_icon")
    private MobileElement menuIcon;

    @AndroidFindBy(id = "header_text_view")
    private MobileElement headerText;

    @AndroidFindBy(id = "details_text_view")
    private MobileElement detailsText;

    @AndroidFindBy(id = "wrong_phone_number_button")
    private MobileElement wrongPhoneNumber;

    @AndroidFindBy(id = "first_digit_edit_text")
    private MobileElement firstDigit;

    @AndroidFindBy(id = "second_digit_edit_text")
    private MobileElement secondDigit;

    @AndroidFindBy(id = "third_digit_edit_text")
    private MobileElement thirdDigit;

    @AndroidFindBy(id = "fourth_digit_edit_text")
    private MobileElement fourthDigit;

    @AndroidFindBy(id = "confirmation_code_details_text_view")
    private MobileElement confirmationCodeDetailsText;

    @AndroidFindBy(id = "resend_code_countdown_text_view")
    private MobileElement resendCodeCountdownText;

    @AndroidFindBy(id = "back_button")
    private MobileElement backButton;

    @AndroidFindBy(id = "next_button")
    private MobileElement nextButton;


    public ConfirmationCodePage(AppiumDriver driver) {
        super(driver);
    }

    public MobileElement getTitleText() {
        return titleText;
    }

    public MobileElement getMenuIcon() {
        return menuIcon;
    }

    public MobileElement getHeaderText() {
        return headerText;
    }

    public MobileElement getDetailsText() {
        return detailsText;
    }

    public MobileElement getWrongPhoneNumber() {
        return wrongPhoneNumber;
    }

    public MobileElement getFirstDigit() {
        return firstDigit;
    }

    public MobileElement getSecondDigit() {
        return secondDigit;
    }

    public MobileElement getThirdDigit() {
        return thirdDigit;
    }

    public MobileElement getFourthDigit() {
        return fourthDigit;
    }

    public MobileElement getConfirmationCodeDetailsText() {
        return confirmationCodeDetailsText;
    }

    public MobileElement getResendCodeCountdownText() {
        return resendCodeCountdownText;
    }

    public MobileElement getBackButton() {
        return backButton;
    }

    public MobileElement getNextButton() {
        return nextButton;
    }

    public void clickNextButton(){
        nextButton.click();
    }
}

