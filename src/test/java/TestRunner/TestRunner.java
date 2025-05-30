package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/apiFeature",
	    glue = {"StepDefinitions", "Hooks"},
	    plugin = {
	        "pretty",
	        "html:target/Reports/report.html",
	        "json:target/Reports/report.json" 
	    },
	    tags = "@ui",
	    monochrome = true
)
public class TestRunner {}
