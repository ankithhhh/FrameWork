package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.ExcelReader;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import java.util.logging.Logger;

import context.TestContext;

public class GetRequestSteps {

    private static final Logger LOGGER = Logger.getLogger(GetRequestSteps.class.getName());
    
    private TestContext context;

    public GetRequestSteps(TestContext context) {
        this.context = context;
    }

//    @Given("I load the place_id from Excel {string}, sheet {string}, row {int}")
//    public void loadPlaceIdFromExcel(String filePath, String sheetName, int rowIndex) throws IOException {
//        LOGGER.info("Loading place_id from Excel: "+ filePath + ", Sheet: " + sheetName + ", Row: "+ rowIndex);
//        String placeId = ExcelReader.readCellValue(filePath, sheetName, rowIndex, "place_id");
//        context.setPlaceId(placeId);
//        LOGGER.info("Loaded place_id: "+ placeId);
//    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        context.response = given()
        		.queryParams(context.queryParams.toMap())
        		.queryParam("place_id", context.getPlaceId())
        		.when()
        		.get(endpoint);
        LOGGER.info("Response body: " + context.response.asPrettyString());
    }
}
