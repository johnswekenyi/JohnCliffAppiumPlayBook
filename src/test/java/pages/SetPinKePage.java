package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SetPinKePage extends BaseClass{
    @AndroidFindBy(id = "title_text_view")
    private MobileElement titleText;

    @AndroidFindBy(id = "menu_icon")
    private MobileElement menuIcon;

    @AndroidFindBy(id = "header_text_view")
    private MobileElement headerText;

    @AndroidFindBy(id = "details_text_view")
    private MobileElement detailsText;

    @AndroidFindBy(id = "first_digit_edit_text")
    private MobileElement firstDigitEdit;

    @AndroidFindBy(id = "second_digit_edit_text")
    private MobileElement secondDigitEdit;

    @AndroidFindBy(id = "third_digit_edit_text")
    private MobileElement thirdDigitEdit;

    @AndroidFindBy(id = "fourth_digit_edit_text")
    private MobileElement fourthDigitEdit;

    @AndroidFindBy(id = "back_button")
    private MobileElement backButton;

    @AndroidFindBy(id = "next_button")
    private MobileElement nextButton;

    @AndroidFindBy(id = "switch_mask_text_view")
    private MobileElement switchMaskText;

    public SetPinKePage(AppiumDriver driver) {
        super( driver );
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

    public MobileElement getFirstDigitEdit() {
        return firstDigitEdit;
    }

    public MobileElement getSecondDigitEdit() {
        return secondDigitEdit;
    }

    public MobileElement getThirdDigitEdit() {
        return thirdDigitEdit;
    }

    public MobileElement getFourthDigitEdit() {
        return fourthDigitEdit;
    }

    public MobileElement getBackButton() {
        return backButton;
    }

    public MobileElement getNextButton() {
        return nextButton;
    }

    public MobileElement getSwitchMaskText() {
        return switchMaskText;
    }

    public void clickBackButton(){
        backButton.click();
    }

    public void clickNextButton(){
        nextButton.click();
    }

    public void clickSwitchMask(){
        switchMaskText.click();
    }
}

