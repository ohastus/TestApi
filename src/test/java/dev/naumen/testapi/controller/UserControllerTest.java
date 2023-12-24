package dev.naumen.testapi.controller;

import dev.naumen.testapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUpdateUsers() throws Exception {

        doNothing().when(userService).updateUser(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/update")
                        .param("departmentId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService, times(1)).updateUser(1L);
    }
}