package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class BasePage {

    private final AppiumDriver driver;

    public BasePage(final AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements( new AppiumFieldDecorator( driver, 10, TimeUnit.SECONDS ), this );
    }
}
