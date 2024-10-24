package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
        features = {"classpath:Features/"},
        glue = {"classpath:StepDefinitions", "classpath:Helpers"},
        tags = {"@EndToEnd or @Smoke or @Sanity"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true)
public class TestRunner {

}