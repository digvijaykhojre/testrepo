package com.uxpsystems.assignment;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uxpsystems.assignment.config.BootjpaApplication;
import com.uxpsystems.assignment.controller.UserController;
import com.uxpsystems.assignment.pojos.User;
import com.uxpsystems.assignment.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootjpaApplication.class)
public class BootjpaApplicationTest {
	protected MockMvc mvc;

	@InjectMocks
	UserController userController;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	private UserService userService;

	@Before
	public void setUp() {
		userController.setService(userService);
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * To check if user exists
	 * 
	 * @throws Exception
	 */
	@Test
	public void getExistingUser() throws Exception {
		// "{ \"userId\": 102, \"userName\": \"\", \"password\": \"\", \"status\": \"\"
		// }"
		String uri = "/getUser";
		User user = new User(101L, "", "", "");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(user);
		System.out.println("Getting existing user Request:"+jsonRequest);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonRequest))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		mvcResult.getResponse();
		String content = mvcResult.getResponse().getContentAsString();
		user = objectMapper.readValue(content, User.class);
		System.out.println("Getting existing user Response:"+content);
		assertEquals("00", user.getErrorCode());

	}

	/**
	 * Get a non existing user
	 * 
	 * @throws Exception
	 */
	@Test
	public void getNonExistingUser() throws Exception {
		// "{ \"userId\": 102, \"userName\": \"\", \"password\": \"\", \"status\": \"\"
		// }"
		String uri = "/getUser";
		User user = new User(105L, "", "", "");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(user);
		System.out.println("Getting non existing user Response:"+jsonRequest);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonRequest))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		mvcResult.getResponse();
		String content = mvcResult.getResponse().getContentAsString();
		user = objectMapper.readValue(content, User.class);
		System.out.println("Getting non existing user Response:"+content);
		assertEquals("01", user.getErrorCode());
		assertEquals("User Not Found", user.getErrorDesc());

	}

	/**
	 * To create new user.
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveUser() throws Exception {
		// "{ \"userId\": 102, \"userName\": \"\", \"password\": \"\", \"status\": \"\"
		// }"
		String uri = "/saveUser";
		User user = new User(102L, "TestUser", "Admin@123", "Active");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(user);
		System.out.println("Saving user Request:"+jsonRequest);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonRequest))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		mvcResult.getResponse();
		String content = mvcResult.getResponse().getContentAsString();
		user = objectMapper.readValue(content, User.class);
		System.out.println("Saving user Response:"+content);
		assertEquals("00", user.getErrorCode());

	}

	/**
	 * To update user.
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateUser() throws Exception {
		// "{ \"userId\": 102, \"userName\": \"\", \"password\": \"\", \"status\": \"\"
		// }"
		String uri = "/updateUser";
		User user = new User(101L, "TestUser", "Admin@123", "Inactive");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(user);
		System.out.println("Testing update user Request:"+jsonRequest);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonRequest))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		mvcResult.getResponse();
		String content = mvcResult.getResponse().getContentAsString();
		user = objectMapper.readValue(content, User.class);
		System.out.println("Update existing user Response:"+content);
		assertEquals("00", user.getErrorCode());

	}

	/**
	 * To delete existing User.
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteExistingUser() throws Exception {
		// "{ \"userId\": 102, \"userName\": \"\", \"password\": \"\", \"status\": \"\"
		// }"
		String uri = "/deleteUser";
		User user = new User(102L, "TestUser", "Admin@123", "Active");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(user);
		System.out.println("Testing delete exiting user Request:"+jsonRequest);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonRequest))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		mvcResult.getResponse();
		String content = mvcResult.getResponse().getContentAsString();
		user = objectMapper.readValue(content, User.class);
		System.out.println("Delete existing user Response:"+content);
		assertEquals("00", user.getErrorCode());

	}
	
	/**
	 * Delete a non existing user. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteNotExisting() throws Exception {
		// "{ \"userId\": 102, \"userName\": \"\", \"password\": \"\", \"status\": \"\"
		// }"
		String uri = "/deleteUser";
		User user = new User(106L, "", "", "");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(user);
		System.out.println("Testing delete non-existing user Request:"+jsonRequest);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonRequest))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		mvcResult.getResponse();
		String content = mvcResult.getResponse().getContentAsString();
		user = objectMapper.readValue(content, User.class);
		System.out.println("Delete non existing user Response:"+content);
		assertEquals("01", user.getErrorCode());
		assertEquals("User Not Found", user.getErrorDesc());

	}
	
	
	/**
	 * To test unsupported method.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUnSupportedMethod() throws Exception {
		// "{ \"userId\": 102, \"userName\": \"\", \"password\": \"\", \"status\": \"\"
		// }"
		String uri = "/updateUser";
		User user = new User(101L, "TestUser", "Admin@123", "Inactive");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(user);
		System.out.println("Testing unsupported method Request:"+jsonRequest);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonRequest))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		System.out.println("Testing unsuppported user Response Code:"+mvcResult.getResponse().getContentAsString());
		assertEquals(405, status);

	}
}
