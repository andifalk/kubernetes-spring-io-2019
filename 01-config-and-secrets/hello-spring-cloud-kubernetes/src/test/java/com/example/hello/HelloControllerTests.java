package com.example.hello;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class HelloControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MessageConfig messageConfig;

	@Test
	void verifyGetHello() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void verifyHealth() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(containsString("UP")));
	}

	@Test
	void verifyPostHello() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/").with(SecurityMockMvcRequestPostProcessors.csrf())
			.content("test")).andExpect(MockMvcResultMatchers.status().isCreated());
	}

}
