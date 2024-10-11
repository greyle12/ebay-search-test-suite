package pageObjects;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;

    @FindBy(id = "gh-ac")
    WebElement searchBar;

    @FindBy(id = "gh-btn")
    WebElement searchButton;
    
    @FindBy(xpath = "//*[@id=\"ui-id-1\"]/li[1]")
    WebElement searchSuggestion;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void enterSearchTerm(String term) {
        searchBar.clear();
        searchBar.sendKeys(term);
    }
    
    public void clickSearchButton() {
        searchButton.click();
    }

    public boolean isOnHomePage() {
        return driver.getTitle().contains("eBay");
    }
    
    public boolean hasSuggestion() {
    	 try {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
             wait.until(ExpectedConditions.visibilityOf(searchSuggestion));
             //String message = searchSuggestion.getText().toLowerCase();
             return true;
         } catch (Exception e) {
             System.out.println("Exception in hasSuggestion: " + e.getMessage());
             return false;
         }
    			
    }

    public boolean isPromptDisplayed() {
        // Implement logic to check if a prompt or message is displayed
        // when the search bar is empty and search button is clicked
        return false; // Adjust as necessary
    }
}
