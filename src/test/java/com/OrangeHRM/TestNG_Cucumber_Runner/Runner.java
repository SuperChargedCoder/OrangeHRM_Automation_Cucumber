package com.OrangeHRM.TestNG_Cucumber_Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/java/com/OrangeHRM/FeatureFiles", // Path to your feature files
		glue = { "com.OrangeHRM.StepDefinitions" }, // Package where step definitions are located
		plugin = { "pretty", // Prints pretty output
				"html:CustomReport/cucumber-reports.html", // Generates HTML report
				"json:CustomReport/cucumber-reports..json" // Generates JSON report
		}, monochrome = true, // Makes console output more readable (False By Default)
		dryRun = false, // If true, checks if steps are implemented without actually running tests
		tags = "not @Skip"
)
public class Runner extends AbstractTestNGCucumberTests {
	

}
