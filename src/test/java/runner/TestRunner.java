package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import steps.CommonMethods;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/feature/",
        plugin = {"pretty", "html:target/cucmber-html.report"},
        glue = "steps",
//        tags = "@verifyNameLength"
//        tags = "@verifyStatsChanged"
        tags = "@verifyNameLength or @verifyStatsChanged"
//        tags = "@adventure"
)

public class TestRunner {
    @BeforeClass
    public static void beforeAll(){
        CommonMethods.go_to_site();
    }

    @AfterClass
    public static void afterAll() throws InterruptedException {
        CommonMethods.makeScreenshot("CharacterCreation");
        CommonMethods.closeBrowser();
    }

}
