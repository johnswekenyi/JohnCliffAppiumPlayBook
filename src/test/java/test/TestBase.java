package test;

import client.ApiClientException;
import client.ConfirmationCodeApiClient;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.*;
import providers.KeSignInPageStringsProvider;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public abstract class TestBase {

    public static String KE_PREFIX = "712";
    public static String KE_COUNTRY_CODE = "254";
    AndroidDriver<MobileElement> driver;
    private String phoneNumber = generateRandomKePhoneNumber();
    private String numberWithCountryCode = generatedNumberWithCountryCode();
    private static String setPin = "1111";
    KeSignInPageStringsProvider keSignInPageStringProviders = new KeSignInPageStringsProvider();

    public String generateRandomKePhoneNumber() {
        String num = RandomStringUtils.randomNumeric( 6 );
        String generatedNumber = KE_PREFIX.concat( num );
        return generatedNumber;
    }

    public void signIn() throws IOException, ApiClientException, InterruptedException {
        SplashScreenKePage splashScreenKePage = new SplashScreenKePage( driver );
        SignUpKePage signUpKePage = new SignUpKePage( driver );
        SignInKePage signInKePage = new SignInKePage( driver );
        splashScreenToSignUpPage();
        signUpPageToConfirmationCodePage();
        retrieveConfirmationCode();
        setAndConfirmFourDigitPin();
        setAndConfirmFourDigitPin();
        tearDownAppium();
        splashScreenToSignInPage();
        signInKePage.getNumberEdit().sendKeys( phoneNumber );
        assertThat( signInKePage.getTitleText().getText(), is( keSignInPageStringProviders.getgetTitleTextString() ) );
        assertThat( signInKePage.getDescriptionText().getText(), is( keSignInPageStringProviders.getDescriptionTextString() ) );
        assertThat( signInKePage.getCodeEdit().getText(), is( keSignInPageStringProviders.getCodeEditString() ) );
        assertThat( signInKePage.getBackButton().getText(), is( keSignInPageStringProviders.getBackButtonString() ) );
        assertThat( signInKePage.getNextButton().getText(), is( keSignInPageStringProviders.getNextButtonString() ) );

        signInKePage.clickNextButton();
        setAndConfirmFourDigitPin();

    }

    public String generatedNumberWithCountryCode() {
        String numberWithCountryCode = KE_COUNTRY_CODE.concat( phoneNumber );
        return numberWithCountryCode;
    }

    @BeforeSuite
    public void setUpAppium() throws MalformedURLException {
        URL url = new URL( "http://127.0.0.1:4723/wd/hub" );
        File appDir = new File( "/Users/swekenyi/Documents/Tala/Projects/Android_Native/app/build/outputs/apk/app-KE_QA-release.apk" );
        File appSrc = new File( appDir, "app-KE_QA-release.apk" );
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability( MobileCapabilityType.APP, appSrc.getAbsolutePath() );
        desiredCapabilities.setCapability( MobileCapabilityType.DEVICE_NAME, "Device" );

        driver = new AndroidDriver<MobileElement>( url, desiredCapabilities );
    }

    @AfterMethod
    public void tearDownAppium() {
        driver.closeApp();
    }

    public void splashScreenToSignUpPage() {
        SplashScreenKePage splashScreenKePage = new SplashScreenKePage( driver );
        splashScreenKePage.clickSignUpButton();
    }

    public void splashScreenToSignInPage() {
        SignInKePage signInKePage = new SignInKePage( driver );
        SplashScreenKePage splashScreenKePage = new SplashScreenKePage( driver );
        splashScreenKePage.clickSignInButton();
    }

    public void signUpPageToConfirmationCodePage() {
        SignUpKePage signUpKePage = new SignUpKePage( driver );
        signUpKePage.getNumberEdit().sendKeys( phoneNumber );

        signUpKePage.clickNextButton();
    }

    public void retrieveConfirmationCode() throws IOException, ApiClientException {
        ConfirmationCodePage confirmationCodePage = new ConfirmationCodePage( driver );
        MobileElement firstDigitEditText = confirmationCodePage.getFirstDigit();
        MobileElement secondDigitEditText = confirmationCodePage.getSecondDigit();
        MobileElement thirdDigitEditText = confirmationCodePage.getThirdDigit();
        MobileElement fourthDigitEditText = confirmationCodePage.getFourthDigit();

        ConfirmationCodeApiClient confirmationCodeApiClient = new ConfirmationCodeApiClient();
        String confirmationCode = confirmationCodeApiClient.retrieveConfirmationCode( numberWithCountryCode );

        if (null != confirmationCode) {
            int length = confirmationCode.length();
            if (length > 0) {
                firstDigitEditText.setValue( Character.toString( confirmationCode.charAt( 0 ) ) );
            }
            if (length > 1) {
                secondDigitEditText.setValue( Character.toString( confirmationCode.charAt( 1 ) ) );
            }
            if (length > 2) {
                thirdDigitEditText.setValue( Character.toString( confirmationCode.charAt( 2 ) ) );
            }
            if (length > 3) {
                fourthDigitEditText.setValue( Character.toString( confirmationCode.charAt( 3 ) ) );
            }
            driver.hideKeyboard();
        }
        confirmationCodePage.clickNextButton();
    }

    public void setAndConfirmFourDigitPin() {
        SetPinKePage setPinKePage = new SetPinKePage( driver );
        MobileElement firstPinDigitEditText = setPinKePage.getFirstDigitEdit();
        MobileElement secondPinDigitEditText = setPinKePage.getSecondDigitEdit();
        MobileElement thirdPinDigitEditText = setPinKePage.getThirdDigitEdit();
        MobileElement fourthPinDigitEditText = setPinKePage.getFourthDigitEdit();
        if (null != setPin) {
            int length = setPin.length();
            if (length > 0) {
                firstPinDigitEditText.setValue( Character.toString( setPin.charAt( 0 ) ) );
            }
            if (length > 1) {
                secondPinDigitEditText.setValue( Character.toString( setPin.charAt( 1 ) ) );
            }
            if (length > 2) {
                thirdPinDigitEditText.setValue( Character.toString( setPin.charAt( 2 ) ) );
            }
            if (length > 3) {
                fourthPinDigitEditText.setValue( Character.toString( setPin.charAt( 3 ) ) );
            }
            driver.hideKeyboard();
        }
        setPinKePage.clickNextButton();
    }

    public void loadHomePgae() {
        HopePage hopePage = new HopePage( driver ) {
            @Override
            public void verifyAtPage(Object waitUntil) {
                super.verifyAtPage( waitUntil );
            }

            @Override
            public LoanCardPage getLoanCard() {
                return super.getLoanCard();
            }

            @Override
            public StatusCardPage getMyTalaStatusCard() {
                return super.getMyTalaStatusCard();
            }

            @Override
            public StoriesCardPage getStoriesCard() {
                return super.getStoriesCard();
            }

            @Override
            public InviteCardPage getInviteCard() {
                return super.getInviteCard();
            }

            @Override
            public HelpCardPage getHelpCard() {
                return super.getHelpCard();
            }
        };
    }
}
