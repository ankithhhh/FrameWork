package steps;

import io.restassured.RestAssured;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;
import utils.ExcelReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONObject;

import com.aventstack.extentreports.ExtentTest;

import context.TestContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import hooks.Hooks;

public class CommonSteps {
    private static final Logger LOGGER = Logger.getLogger(CommonSteps.class.getName());
    protected RequestSpecification request;
    protected Response response;
    private final TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    
    @Given("I load the base URL from config")
    public void loadBaseUrlFromConfig() throws IOException {
        String baseUrl = ConfigReader.getBaseUrl();
        RestAssured.baseURI = baseUrl;
        LOGGER.info("Base URL loaded from config: " + baseUrl);
    }


    @Given("I set up the Maps API request")
    public void setupRequest() {
        LOGGER.info("Setting up the Maps API request");
        context.request = given().header("Content-Type", "application/json");
    }
    
//    @Given("I load the place_id from {string}")
//    public void loadPlaceId(String filePath) throws IOException {
//        LOGGER.info("Loading place_id from: " + filePath);
//        String placeId = PlaceIdUtils.getPlaceId(); // or load from file
//        context.setPlaceId(placeId); // Save in context
//        LOGGER.info("Place_id loaded: " + placeId);
//    }
    
    @Given("I load the place_id from Excel {string}, sheet {string}, row {int}")
    public void loadPlaceIdFromExcel(String filePath, String sheetName, int rowIndex) throws IOException {
        LOGGER.info("Loading place_id from Excel: "+ filePath + ", Sheet: " + sheetName + ", Row: "+ rowIndex);
        String placeId = ExcelReader.readCellValue(filePath, sheetName, rowIndex, "place_id");
        context.setPlaceId(placeId);
        LOGGER.info("Loaded place_id: "+ placeId);
    }
    
//    @Given("I load the query parameters from Excel {string}, sheet {string}, row {int}")
//    public void loadQueryParamsFromExcel(String filePath, String sheetName, int rowIndex) {
//        LOGGER.info("Loading query parameters from Excel: " + filePath);
//        Map<String, String> data = ExcelReader.readData(filePath, sheetName, rowIndex);
//        context.queryParams = new JSONObject(data);
//        LOGGER.info("Query parameters loaded: " + context.queryParams.toString());
//    }

    @Given("I load the query parameters from Excel {string}, sheet {string}, row {int}")
    public void loadQueryParamsFromExcel(String filePath, String sheetName, int rowIndex) {
        LOGGER.info("Loading query parameters from Excel: " + filePath);
        Map<String, String> data = ExcelReader.readData(filePath, sheetName, rowIndex);

        // Extract only the 'key' parameter
        Map<String, String> queryParamMap = new HashMap<>();
        if (data.containsKey("key")) {
            queryParamMap.put("key", data.get("key"));
        }

        context.queryParams = new JSONObject(queryParamMap);
        LOGGER.info("Query parameters loaded: " + context.queryParams.toString());
    }



    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        LOGGER.info("Verifying response status code: " + statusCode);
        context.response.then().statusCode(statusCode);
    }

    @Then("the response should contain {string} with value {string}")
    public void verifyResponse(String key, String value) {
        LOGGER.info("Verifying response contains " + key + " with value " + value);
        
        context.response.then().body(key, equalTo(value));
    }

    @Then("I log the response body")
    public void logResponseBody() {
        LOGGER.info("Logging the response body");

        if (context.response != null) {
            String responseBody = context.response.getBody().asPrettyString();
            LOGGER.info("Response body: " + responseBody);

            ExtentTest test = Hooks.getTest();
            if (test != null) {
                test.info("Response Body: <pre>" + responseBody + "</pre>");
            } else {
                LOGGER.warning("ExtentTest was null — could not log to ExtentReports");
            }
        } else {
            LOGGER.warning("Response was null — nothing to log");
        }
    }

}