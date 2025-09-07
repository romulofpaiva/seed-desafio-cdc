package com.desafio.cdc.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.desafio.cdc.SeedDesafioCdcApplication;

@SpringBootTest(classes = SeedDesafioCdcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AutorControllerTests {

	@LocalServerPort
	int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Nested
	@DisplayName("POST /autores")
	class Post {
		
		@Nested
		class Scenario_1_when_everything_is_ok {

			@Test
			void it_return_created() {
				String body = """
						{
							"nome": "Rômulo Ferreira Paiva",
							"email": "romulofpaiva@gmail.com",
							"descricao": "O autor..."
						}
						""";

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

				ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/autores"), HttpMethod.POST,
						httpEntity, Object.class);

				assertEquals(HttpStatus.CREATED, response.getStatusCode());
			}
		}

		@Nested
		class Scenario_2_when_email_is_already_registered {

			@Test
			void it_returns_bad_request() {
				String body = """
						{
							"nome": "Rômulo Ferreira Paiva",
							"email": "romulofpaiva@gmail.com",
							"descricao": "O autor..."
						}
						""";

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

				ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/autores"), HttpMethod.POST,
						httpEntity, Object.class);

				assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			}
		}
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
