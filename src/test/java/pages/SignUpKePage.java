
package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SignUpKePage extends BaseClass{

    @AndroidFindBy(id = "title_text_view")
    private MobileElement titleText;

    @AndroidFindBy(id = "description_text_view")
    private MobileElement descriptionText;

    @AndroidFindBy(id = "secondary_description_text_view")
    private MobileElement secondaryDescriptionText;

    @AndroidFindBy(id = "code_edit_text")
    private MobileElement codeEdit;

    @AndroidFindBy(id = "number_edit_text")
    private MobileElement numberEdit;

    @AndroidFindBy(id = "back_button")
    private MobileElement backButon;

    @AndroidFindBy(id = "next_button")
    private MobileElement nextButon;

    public SignUpKePage(AppiumDriver driver){
        super(driver);
    }

    public MobileElement getTitleText() {
        return titleText;
    }

    public MobileElement getDescriptionText() {
        return descriptionText;
    }

    public MobileElement getSecondaryDescriptionText() {
        return secondaryDescriptionText;
    }

    public MobileElement getCodeEdit() {
        return codeEdit;
    }

    public MobileElement getNumberEdit() {
        return numberEdit;
    }

    public MobileElement getBackButon() {
        return backButon;
    }

    public MobileElement getNextButon() {
        return nextButon;
    }

    public void clickBackButton(){
        backButon.click();
    }

    public void clickNextButton(){
        nextButon.click();
    }

    public void enterNumber(){
        numberEdit.click();
    }
}
