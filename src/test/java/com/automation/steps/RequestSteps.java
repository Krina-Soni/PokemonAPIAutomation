package com.automation.steps;


import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import javax.sound.midi.SysexMessage;
import java.io.File;


public class RequestSteps {

    @Given("user set up request for {string}")
    public void user_set_up_request_for_get_bookings(String endPoint) {
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @When("user perform GET call")
    public void user_perform_get_call() {
        RestAssuredUtils.get();
    }

    @When("user perform POST call")
    public void user_perform_post_call() {
        RestAssuredUtils.post();
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertTrue(RestAssuredUtils.getStatusCode() == statusCode);
    }

    @When("user set header {string} to {string}")
    public void userSetHeaderTo(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }

    @And("verify response matches the json schema {string}")
    public void verifyResponseMatchesTheJsonSchema(String schemaPath) {
        String jsonFolderPath = System.getProperty("user.dir") + ConfigReader.getProperty("json.folder.path");
        Response response = RestAssuredUtils.getResponse();
        response.then().log().all().assertThat().body(JsonSchemaValidator.
                matchesJsonSchema(new File(jsonFolderPath + schemaPath)));
    }
}
