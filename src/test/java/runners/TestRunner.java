package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
	plugin = {
			"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
			"pretty", "html:target/cucumber-reports/report.html"
			
	},
    features = "src/test/resources/Features",
    glue = {"StepDefinitions"},
    
    monochrome = true
)

public class TestRunner {
}
