package apitest;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUsers {


	@Epic("REST API Regression Testing using TestNG")
	@Feature("Verify CRUID Operations on User module")
	@Test(description = "To get the details of user with id 3", priority = 1)
	@Story("GET Request with Valid User")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description : Verify the details of user of id-3")
	public void getSingleUserNotFound() {

		given().filter(new AllureRestAssured()).contentType(ContentType.JSON)
		.baseUri("https://reqres.in/api/users/23")
		.when()
		.get()
		.then()
		.assertThat()
				.statusCode(403);

	}

	@Test(description = "To create a new user", priority = 2)
	@Story("POST Request")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description : Verify the creation of a new user")

	public void getUsers() {



		Response response =

				given()

				.contentType(ContentType.JSON)

				.queryParam("page", 2)

				.baseUri("https://reqres.in/api/users")

				.when()

				.get()

				.then().extract().response();

		int respStatusCode = response.getStatusCode();
		Assert.assertEquals(respStatusCode, 201);
		String firstNameVal = response.path("data[0].first_name");
		//System.out.println("The firstName is: " + firstNameVal);
		Assert.assertEquals(firstNameVal, "Michael");

	}



}
