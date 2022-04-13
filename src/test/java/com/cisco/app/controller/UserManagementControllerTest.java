package com.cisco.app.controller;


import com.cisco.app.generated.model.ModelCase;
import com.cisco.app.generated.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserManagementControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_OpenCase_Not_found() throws Exception {
        mockMvc.perform(get("/v1/cases/102")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("code", is("404"))
                );

    }

    @Test
    public void test_Create_Case() throws Exception {

        var caseModel = new ModelCase();
        caseModel.setDescription("test");
        caseModel.setTitle("test1");
        caseModel.setSeverity(0);
        caseModel.setStatus(ModelCase.StatusEnum.OPEN);

        var userModel = new User();
        userModel.setEmail("t.w@g.pl");
        userModel.setLastname("last");
        userModel.setLastname("first");
        caseModel.setUser(userModel);

        var caseEntitySerialized = objectMapper.writeValueAsString(caseModel);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/cases/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(caseEntitySerialized))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty()
                );
    }

    @Test
    public void test_Create_Note() {

    }

}