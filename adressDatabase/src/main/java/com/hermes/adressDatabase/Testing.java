package com.hermes.adressDatabase;

import com.hermes.adressDatabase.customer.CustomerController;
import com.hermes.adressDatabase.user.StartController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class Testing {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CustomerController customerController;

    @Autowired
    StartController startController;

    @Test
    public void contextLoads(){
    }

    @Test
    public void startLoads() throws java.lang.Exception {
        assertThat(startController).isNotNull();
    }

    @Test
    public void customerLoads() throws java.lang.Exception {
        assertThat(customerController).isNotNull();
    }

    @Test
    public void startIsOk() throws java.lang.Exception {
        this.mockMvc.perform(get("/start")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void customerIsFound() throws java.lang.Exception {
        this.mockMvc.perform(get("/customer")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void singleCustomerIsFound() throws java.lang.Exception {
        this.mockMvc.perform(get("/customer/6")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void signUpIsFound() throws java.lang.Exception {
        this.mockMvc.perform(get("/sign-Up")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void signInIsOk() throws java.lang.Exception {
        this.mockMvc.perform(get("/sign-in")).andDo(print()).andExpect(status().isOk());
    }
}
