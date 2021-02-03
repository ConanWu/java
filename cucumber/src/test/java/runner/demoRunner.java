package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:data/demo.feature"},
        glue = {"test.stepdefs"},
        junit = {"--step-notifications"},
//        plugin = {"test.stepdefs.ExtentCucumberFormatter:target/extent-report/report.html"}
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-report.json"}
)
public class demoRunner {
}
