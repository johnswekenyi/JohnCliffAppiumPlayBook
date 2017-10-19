package test;

import client.ApiClientException;
import org.testng.annotations.Test;
import pages.ConfirmationCodePage;
import providers.KeConfirmationCodeStringProviders;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class ConfirmationCodeKeTest extends TestBase {

    KeConfirmationCodeStringProviders keConfirmationCodeStringProviders = new KeConfirmationCodeStringProviders();

    @Test
    public void enterConfirmationCode() throws IOException, ApiClientException {

        //When
        splashScreenToSignUpPage();
        signUpPageToConfirmationCodePage();

        //Then
        ConfirmationCodePage confirmationCodePage = new ConfirmationCodePage(driver);
        assertThat(confirmationCodePage.getTitleText().getText(), is(keConfirmationCodeStringProviders.getTitleTextString()));
        assertThat(confirmationCodePage.getHeaderText().getText(), is(keConfirmationCodeStringProviders.getHeaderTextString()));
        assertTrue(confirmationCodePage.getMenuIcon().isDisplayed());
        assertThat(confirmationCodePage.getWrongPhoneNumber().getText(), is(keConfirmationCodeStringProviders.getWrongPhoneNumberString()));
        assertThat(confirmationCodePage.getConfirmationCodeDetailsText().getText(),
                is(keConfirmationCodeStringProviders.getConfirmationCodeDetailsTextString()));
        assertTrue(confirmationCodePage.getResendCodeCountdownText().getText()
                .contains(keConfirmationCodeStringProviders.getResendCodeCountdownTextString()));
        assertTrue(confirmationCodePage.getDetailsText().getText().contains(keConfirmationCodeStringProviders.getDetailsTextString()));
        assertThat(confirmationCodePage.getBackButton().getText(), is(keConfirmationCodeStringProviders.getBackButtonString()));
        assertThat(confirmationCodePage.getNextButton().getText(), is(keConfirmationCodeStringProviders.getNextButtonString()));
        assertTrue(confirmationCodePage.getFirstDigit().isDisplayed());
        assertTrue(confirmationCodePage.getSecondDigit().isDisplayed());
        assertTrue(confirmationCodePage.getThirdDigit().isDisplayed());
        assertTrue(confirmationCodePage.getFourthDigit().isDisplayed());

        retrieveConfirmationCode();
    }
}
