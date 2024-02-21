package Cucumber.option;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", plugin = "json:target/jsonReports/cucmber-report.json", glue={"stepdefinations"})

public class TestRunner {
    // Your test runner class code here
}