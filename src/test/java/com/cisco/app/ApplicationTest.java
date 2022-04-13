package com.cisco.app;
/*
 * @author nbtwszol
 */

import com.cisco.app.constroller.CaseController;
import com.cisco.app.generated.model.Error;
import com.cisco.app.generated.model.ModelCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @LocalServerPort
    private int port;
    @Autowired
    private CaseController caseController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(caseController, notNullValue());
    }

    @Test
    public void getCaseById_Not_Found() {
        var url = "http://localhost:" + port + "/v1/cases/2";
        var response = this.restTemplate.getForEntity(url,
                Error.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void addCase_WithoutUser() {
        var url = "http://localhost:" + port + "/v1/cases/create";
        var caseModel = new ModelCase();
        caseModel.setStatus(ModelCase.StatusEnum.OPEN);
        caseModel.setTitle("title");
        caseModel.setDescription("description");
        var response = this.restTemplate.postForEntity(url,
                caseModel, Error.class);
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
