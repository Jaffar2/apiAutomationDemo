package apitest;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.BeforeTest;

import com.google.common.collect.ImmutableMap;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseTest {

	public PrintStream log;
	RequestLoggingFilter requestLoggingFilter;
	ResponseLoggingFilter responseLoggingFilter;

	@BeforeTest
	public void setUp() throws FileNotFoundException {

		{
			allureEnvironmentWriter(ImmutableMap.<String, String>builder().put("environment", "ts-dev")
					.put("invoice-service: version", "07.00").put("multi-trip-ticket: version", "07.00")
					.put("user", "Jshaik").build(), System.getProperty("user.dir") + "/allure-results/");
		}

		log = new PrintStream(new FileOutputStream("test_logging.txt"), true);
		requestLoggingFilter = new RequestLoggingFilter(log);
		responseLoggingFilter = new ResponseLoggingFilter(log);
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		// RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	/*
	 * @BeforeSuite void setAllureEnvironment() { allureEnvironmentWriter(
	 * ImmutableMap.<String, String>builder() .put("Browser", "Chrome")
	 * .put("Browser.Version", "70.0.3538.77") .put("URL",
	 * "http://testjs.site88.net") .build(), System.getProperty("user.dir") +
	 * "/allure-results/");
	 */
}

