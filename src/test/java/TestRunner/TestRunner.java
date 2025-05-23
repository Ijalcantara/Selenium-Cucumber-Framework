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
	        "html:target/Reports/report.html",
	        "json:target/Reports/report.json" 
	    },
	    tags = "@tag1 or @tag2 or @tag3 or @tag4 ",
	    monochrome = true
)
public class TestRunner {}
