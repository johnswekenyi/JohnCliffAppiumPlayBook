package test;

import org.testng.annotations.Test;
import pages.SignUpKePage;
import providers.KeSignUpPageStringProviders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class SignUpKeTest extends TestBase {
    KeSignUpPageStringProviders keSignUpPageStringProviders = new KeSignUpPageStringProviders();

    @Test
    public void testSignUp_fromSplashScreen(){

        //When
        splashScreenToSignUpPage();

        //Then
        SignUpKePage signUpKePage = new SignUpKePage(driver);
        assertThat(signUpKePage.getTitleText().getText(), is(keSignUpPageStringProviders.getTitleString()));
        assertThat(signUpKePage.getDescriptionText().getText(), is(keSignUpPageStringProviders.getDescriptionTestString()));
        assertThat(signUpKePage.getSecondaryDescriptionText().getText(),
                is(keSignUpPageStringProviders.getSecondaryDescriptionString()));
        assertThat(signUpKePage.getCodeEdit().getText(), is(keSignUpPageStringProviders.getCodeString()));
        assertTrue(signUpKePage.getNumberEdit().isDisplayed());

        //When
        signUpPageToConfirmationCodePage();

        //Then
        assertThat(signUpKePage.getBackButon().getText(), is(keSignUpPageStringProviders.getBackButonString()));
        assertThat(signUpKePage.getNextButon().getText(),is(keSignUpPageStringProviders.getNextButonString()));
    }
}
