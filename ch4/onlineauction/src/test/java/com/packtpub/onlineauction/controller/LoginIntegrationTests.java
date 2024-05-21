package com.packtpub.onlineauction.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext context;

    @Test
    public void testSuccessfulLogin() throws Exception {
        mockMvc.perform(formLogin("/login").user("user").password("test123"))
                .andExpect(redirectedUrl("/home"))
        ;
    }


    @Test
    public void testProtectedResourceAccess() throws Exception {
        MvcResult result = mockMvc.perform(formLogin("/login")
                .user("admin").password("test123")).andReturn();

        mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                        .session((MockHttpSession) result.getRequest().getSession()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFailedLogin() throws Exception {
        mockMvc.perform(formLogin("/login").user("user").password("test123"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "admin", roles={"USER", "ADMIN"})
    public void testAdminAccess() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

}
