package runners;

import org.junit.platform.suite.api.IncludeEngines;

import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.ConfigurationParameter;

import static io.cucumber.junit.platform.engine.Constants.*;

//@Suite
//@IncludeEngines("cucumber")
//@SelectClasspathResource("features/Second/PostReqs.feature")
//@SelectClasspathResource("features/Second/GetReq.feature")
//@SelectClasspathResource("features/Second/PutReq.feature")
//@SelectClasspathResource("features/Second/DeleteReq.feature")
//
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps,hooks") 
//@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, html:target/cucumber-reports.html, json:target/cucumber-reports.json")
//@ConfigurationParameter(key = "cucumber.publish", value = "true")
//public class SecondRunner {
//}
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/Second/PostReqs.feature")
@SelectClasspathResource("features/Second/GetReqs.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps,hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html, json:target/cucumber-reports.json")
@ConfigurationParameter(key = "cucumber.publish", value = "true")
public class SecondTest {
}
