package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:data/demo.feature"},
        glue = {"test.stepdefs"},
        junit = {"--step-notifications"}
)
public class demoRunner {
}
