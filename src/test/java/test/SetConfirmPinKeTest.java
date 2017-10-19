package test;

import client.ApiClientException;
import org.testng.annotations.Test;
import pages.SetPinKePage;
import providers.KeConfirmPinStringProviders;
import providers.KeSetPinStringProviders;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.AssertJUnit.assertTrue;

public class SetConfirmPinKeTest extends TestBase {
    KeSetPinStringProviders keSetPinStringProviders = new KeSetPinStringProviders();
    KeConfirmPinStringProviders keConfirmPinStringProviders = new KeConfirmPinStringProviders();

    @Test
    public void setPinTest() throws IOException, ApiClientException {

        splashScreenToSignUpPage();
        signUpPageToConfirmationCodePage();
        retrieveConfirmationCode();

        SetPinKePage setPinKePage = new SetPinKePage(driver);
        assertThat(setPinKePage.getTitleText().getText(), is(keSetPinStringProviders.getTitleTextString()));
        assertTrue(setPinKePage.getMenuIcon().isDisplayed());
        assertThat(setPinKePage.getHeaderText().getText(), is(keSetPinStringProviders.getHeaderTextString()));
        assertThat(setPinKePage.getNextButton().getText(), is(keSetPinStringProviders.getNextButtonString()));
        assertThat(setPinKePage.getBackButton().getText(), is(keSetPinStringProviders.getBackButtonString()));

        set_ConfirmFourDigitPin();
    }

    @Test
    public void confirmPinTest() throws IOException, ApiClientException, InterruptedException {
        splashScreenToSignUpPage();
        signUpPageToConfirmationCodePage();
        retrieveConfirmationCode();
        set_ConfirmFourDigitPin();

        SetPinKePage setPinKePage = new SetPinKePage(driver);
        assertThat(setPinKePage.getTitleText().getText(), is(keConfirmPinStringProviders.getTitleTextString()));
        assertTrue(setPinKePage.getMenuIcon().isDisplayed());
        assertThat(setPinKePage.getHeaderText().getText(), is(keConfirmPinStringProviders.getHeaderTextString()));
        assertThat(setPinKePage.getNextButton().getText(), is(keConfirmPinStringProviders.getNextButtonString()));
        assertThat(setPinKePage.getBackButton().getText(), is(keConfirmPinStringProviders.getBackButtonString()));

        set_ConfirmFourDigitPin();
        Thread.sleep( 5000);
    }
}
