package com.munhozra.takehome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.munhozra.takehome.dto.ContactDTO;
import com.munhozra.takehome.service.impl.MockContactsSourceClientImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TakehomeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MockContactsSourceClientImpl mockContactsSourceClient;

	@Test
	void shouldHandleEmptyContactsList() throws Exception {
		when(mockContactsSourceClient.getContacts()).thenReturn(new ContactDTO[0]);
		MockHttpServletResponse response = mockMvc.perform(get("/contacts/sync"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		var objectMapper = new ObjectMapper();
		var contactArray = objectMapper.readValue(response.getContentAsString(), ContactDTO[].class);
		assertThat(contactArray.length == 0);
	}
}
