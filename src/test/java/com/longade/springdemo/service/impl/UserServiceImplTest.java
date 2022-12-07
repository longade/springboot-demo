package com.longade.springdemo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.longade.springdemo.controller.UserController;
import com.longade.springdemo.domain.User;
import com.longade.springdemo.security.SecurityConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class UserServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = new ArrayList<>() {{
            add(new User("dave", "long", "dave.long@test.com"));
        }};
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc
                .perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("dave"))
                .andExpect(jsonPath("$[0].lastName").value("long"))
                .andExpect(jsonPath("$[0].email").value("dave.long@test.com"));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User("dave", "long", "dave.long@test.com");
        when(userService.getUser(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(get("/api/users/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testSaveUser() throws Exception {
        User user = new User("dave", "long", "dave.long@test.com");
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestJSON = objectWriter.writeValueAsString(user);

        mockMvc
                .perform(
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("User saved successfully!"));
    }

}
