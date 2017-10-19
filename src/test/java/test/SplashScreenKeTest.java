package test;

import org.testng.annotations.Test;
import pages.SplashScreenKePage;
import providers.KeSplashScreenStringsProvider;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class SplashScreenKeTest extends TestBase {
    KeSplashScreenStringsProvider keSplashScreenStringProviders = new KeSplashScreenStringsProvider();

    @Test
    public void testSplashScreen() {
        SplashScreenKePage splashScreenKePage = new SplashScreenKePage( driver );
        assertThat( splashScreenKePage.getTitleText().getText(), is( keSplashScreenStringProviders.getTitleString() ) );
        assertTrue( splashScreenKePage.getLogoImage().isDisplayed() );
        assertThat( splashScreenKePage.getDescriptionText().getText(), is( keSplashScreenStringProviders.getDescriptionTestString() ) );
        assertThat( splashScreenKePage.getSignUpButton().getText(), is( keSplashScreenStringProviders.getSignUpButtonString() ) );
        assertThat( splashScreenKePage.getSignInButton().getText(), is( keSplashScreenStringProviders.getSignInbuttonString() ) );
    }

}
