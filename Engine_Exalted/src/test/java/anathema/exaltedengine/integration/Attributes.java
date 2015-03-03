package anathema.exaltedengine.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:net/sf/anathema/exaltedengine",  glue = "classpath:net/sf/anathema/exaltedengine")
public class Attributes {
}