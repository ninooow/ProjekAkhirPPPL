package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // path ke folder .feature
        glue = "stepDefinitions",                 // package tempat step definition
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true                        // output bersih di console

)
public class Runner {
}