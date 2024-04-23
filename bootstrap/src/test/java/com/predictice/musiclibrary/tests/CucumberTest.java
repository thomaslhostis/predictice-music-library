package com.predictice.musiclibrary.tests;

import com.predictice.musiclibrary.Run;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber.json")
public class CucumberTest {

    @SpringBootTest(
            classes = Run.class,
            webEnvironment = RANDOM_PORT)
    @CucumberContextConfiguration
    @ActiveProfiles(value = "features")
    public class CucumberSpringConfiguration {

        @Before
        public void cleanUp() {
        }
    }
}
