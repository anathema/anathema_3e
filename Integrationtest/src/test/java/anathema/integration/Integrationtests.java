package anathema.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@Integration"}, features="classpath:net/sf/anathema",  glue = "classpath:net/sf/anathema")
public class Integrationtests {
}
