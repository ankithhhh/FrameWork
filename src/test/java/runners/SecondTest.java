package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = {
        "src/test/resources/features/Second/PostReqs.feature",
        "src/test/resources/features/Second/GetReqs.feature"
        // Add others here if needed
    },
    glue = {"steps", "hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber-reports.json"
    },
    publish = true
)
public class SecondTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
