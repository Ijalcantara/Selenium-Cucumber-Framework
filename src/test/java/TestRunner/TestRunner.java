package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
	    glue = {"StepDefinitions", "Hooks"},
	    plugin = {
	        "pretty",
	        "html:reports/extent_html/extent_report.html",
	        "json:target/Reports/report.json",
	        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
	    },
	    tags = "@contactlist",
	    monochrome = true
)
public class TestRunner {}
