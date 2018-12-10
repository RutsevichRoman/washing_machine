package com.electrolux.washmachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WashingMachineApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WashingMachineApplicationTests {

	@LocalServerPort
	private int port;

	private final HttpHeaders headers = new HttpHeaders();
	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void contextLoads() {
	}

	@Test
	public void testExistingModesGetRequest() throws Exception {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/washing_machine/modes"),
				HttpMethod.GET, entity, String.class);

		String expected = "[\"QUICK_WASHING_30\", \"SYNTHETICS\", \"COTTON_30\", \"COTTON_60\"]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}

