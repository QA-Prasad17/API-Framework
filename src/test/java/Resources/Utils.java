package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String getGlobalValue(String key) throws IOException {
		Properties pr = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Admin\\eclipse-workspace\\APIFramework\\src\\test\\java\\Resources\\global.properties");
		pr.load(fis);
		return pr.getProperty(key);

	}
	
	
	public String getJsonPath(Response response, String key) 
	{
		String respo = response.asString();
		JsonPath js = new JsonPath(respo);
		return js.get(key).toString();
	}
}
