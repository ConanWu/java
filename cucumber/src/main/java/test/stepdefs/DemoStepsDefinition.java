package test.stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import model.DataTableHelper;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class DemoStepsDefinition {
    private Scenario scenario;

    @Before
    public void init(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("^CreateTicket.*")
    public void createticket(DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        Map<String, String> mapInputData = DataTableHelper.asMap(dataTable);
        System.out.println(mapInputData.get("name"));
        scenario.write("passssssssssssssss");
        Assert.assertTrue(Boolean.FALSE);
    }

    @And("CorrectTicket")
    public void correctticket() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("step2");
        Assert.assertTrue(Boolean.TRUE);
    }

    @Then("VerifyTrade")
    public void verifytrade() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("step2");
        Assert.assertTrue(true);
        Assert.assertTrue(Boolean.TRUE);
    }



}
