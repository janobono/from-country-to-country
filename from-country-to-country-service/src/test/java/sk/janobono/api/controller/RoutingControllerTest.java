package sk.janobono.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sk.janobono.api.service.so.RoutingResultSO;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class RoutingControllerTest {

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public MockMvc mvc;

    @Autowired
    public WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void routing() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/routing/CZE/ITA")).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        RoutingResultSO routingResultSO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RoutingResultSO.class);
        assertThat(routingResultSO.route().isEmpty()).isFalse();
        assertThat(routingResultSO.route().contains("CZE")).isTrue();
        assertThat(routingResultSO.route().contains("AUT")).isTrue();
        assertThat(routingResultSO.route().contains("ITA")).isTrue();
    }
}
