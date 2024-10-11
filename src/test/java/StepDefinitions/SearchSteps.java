package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import pageObjects.HomePage;
import pageObjects.ResultsPage;
import pageObjects.ErrorPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchSteps {

    WebDriver driver;
    HomePage homePage;
    ResultsPage resultsPage;
    ErrorPage errorPage;

    @Given("I am on eBay homepage")
    public void i_am_on_ebay_homepage() {
        // Set up the ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to eBay homepage
        driver.get("https://www.ebay.com/");

        // Wait for the search bar to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gh-ac")));

        // Initialize the HomePage object
        homePage = new HomePage(driver);
    }

    @When("I enter {string} into the search bar")
    public void i_enter_into_the_search_bar(String searchTerm) {
        homePage.enterSearchTerm(searchTerm);
    }

    @When("I leave the search bar empty")
    public void i_leave_the_search_bar_empty() {
        homePage.enterSearchTerm("");
    }

    @When("I click on the search button")
    public void i_click_on_the_search_button() {
        homePage.clickSearchButton();
    }
    
    
    
    
    @When("^I enter a string of (\\d+) characters into the search bar$")
    public void i_enter_a_string_of_characters_into_the_search_bar(int length) {
        // Generate a string of the specified length
        StringBuilder longInputBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            longInputBuilder.append('a'); // You can use any character
        }
        String longInput = longInputBuilder.toString();

        // Enter the long string into the search bar
        homePage.enterSearchTerm(longInput);
    }

    
    @Then("I should see some search suggestions related to {string}")
    public void i_should_see_the_search_suggestion(String searchTerm) {
        Assert.assertTrue("Expected to be some search suggestions related to" +  searchTerm, homePage.hasSuggestion());
    }

    @Then("I should see results related to {string}")
    public void i_should_see_results_related_to(String searchTerm) {
        resultsPage = new ResultsPage(driver);
        Assert.assertTrue("Search results do not contain the term: " + searchTerm, resultsPage.resultsContain(searchTerm));
    }
    
    @Then("I should see results related to {string} then click on the next page button")
    public void i_click_next_btn(String searchTerm) {
        resultsPage = new ResultsPage(driver);
        Assert.assertTrue("Search results do not contain the term: " + searchTerm, resultsPage.resultsContain(searchTerm));
        resultsPage.clickNextButton();
    }

    @Then("I should see the homepage or a prompt to enter a search term")
    public void i_should_see_the_homepage_or_prompt() {
        Assert.assertTrue("Expected to be on the homepage or see a prompt", homePage.isOnHomePage() || homePage.isPromptDisplayed());
    }
    
    

    @Then("I should see a safe error message or no results")
    public void i_should_see_safe_error_message_or_no_results() {
        resultsPage = new ResultsPage(driver);
        Assert.assertTrue("Expected a safe error message or no results", resultsPage.isNoResultsMessageDisplayed() || resultsPage.isResultsListEmpty());
    }
    
    @Then("^I should see an error page with message \"([^\"]*)\"$")
    public void i_should_see_an_error_page_with_message(String expectedMessage) {
        // Initialize errorPage before using it
        errorPage = new ErrorPage(driver);
        String actualMessage = errorPage.getErrorMessage();
        Assert.assertTrue("Expected error message not found", actualMessage.contains(expectedMessage));
    }

    

    @Then("I should see a {string} message")
    public void i_should_see_a_message(String message) {
        resultsPage = new ResultsPage(driver);
        Assert.assertTrue("Expected message not found", resultsPage.getNoResultsMessage().contains(message));
    }
    

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
