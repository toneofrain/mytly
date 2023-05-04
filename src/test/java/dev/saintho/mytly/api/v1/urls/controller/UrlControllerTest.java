package dev.saintho.mytly.api.v1.urls.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.google.gson.Gson;

import dev.saintho.mytly.service.UrlService;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@WebMvcTest(UrlControllerTest.class)
class UrlControllerTest {
	@Autowired
	Gson gson;
	@MockBean
	UrlService urlService;
	MockMvc mockMvc;

	private static final String path = "/api/v1/urls";

	@BeforeEach
	void setUp(WebApplicationContext applicationContext, RestDocumentationContextProvider contextProvider) {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(applicationContext)
			.apply(documentationConfiguration(contextProvider))
			.addFilter(new CharacterEncodingFilter("UTF-8", true))
			.alwaysDo(print())
			.build();
	}

	@Test
	@DisplayName("단축 URL 생성")
	void shorUrl() throws Exception {

	}
}
