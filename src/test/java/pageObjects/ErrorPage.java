package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ErrorPage {

    WebDriver driver;

    @FindBy(css = "div#error h1")
    WebElement errorHeading;

    @FindBy(css = "div#error p")
    WebElement errorMessage;

    public ErrorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getErrorMessage() {
        // Wait for the error heading to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(errorHeading));

        // Get the heading and message text
        String headingText = errorHeading.getText();
        String messageText = errorMessage.getText();

        // Return the combined error message
        return headingText + " " + messageText;
    }
}
