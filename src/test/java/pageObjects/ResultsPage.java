package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ResultsPage {

    WebDriver driver;

    @FindBy(xpath = "//*[contains(@class, 's-item__title') and not(contains(., 'New Listing')) and not(contains(., 'Sponsored')) and not(contains(., 'Shop on eBay')) and not(contains(., 'Results matching fewer words'))]")
    List<WebElement> resultTitles;



    @FindBy(css = "h1.srp-controls__count-heading")
    WebElement noResultsMessage;
    
    @FindBy(css = "a.pagination__next.icon-link")
    WebElement nextBtn;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void clickNextButton() {
    	nextBtn.click();
    }
    
    public boolean resultsContain(String searchTerm) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfAllElements(resultTitles));

            if (resultTitles.isEmpty()) {
                System.out.println("No search results found.");
                return false;
            } else {
                System.out.println("Number of results found: " + resultTitles.size());
            }

            String searchTermLower = searchTerm.toLowerCase();

            for (WebElement titleElement : resultTitles) {
                String titleText = titleElement.getText().toLowerCase().trim();
                System.out.println("Title: " + titleText);

                if (titleText.contains(searchTermLower)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in resultsContain: " + e.getMessage());
        }
        return false;
    }


    
    public boolean isNoResultsMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(noResultsMessage));
            String message = noResultsMessage.getText().toLowerCase();
            return message.contains("0 results") || message.contains("no exact matches found");
        } catch (Exception e) {
            System.out.println("Exception in isNoResultsMessageDisplayed: " + e.getMessage());
            return false;
        }
    }

    
    public String getNoResultsMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(noResultsMessage));
            return noResultsMessage.getText();
        } catch (Exception e) {
            System.out.println("Exception in getNoResultsMessage: " + e.getMessage());
            return "";
        }
    }



    
    public boolean isResultsListEmpty() {
        try {
            return resultTitles.isEmpty();
        } catch (Exception e) {
            System.out.println("Exception in isResultsListEmpty: " + e.getMessage());
            return true;
        }
    }
}
