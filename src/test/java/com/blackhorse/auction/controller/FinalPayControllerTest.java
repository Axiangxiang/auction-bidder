package com.blackhorse.auction.controller;

import com.blackhorse.auction.configuration.TestBase;
import com.blackhorse.auction.controller.DTO.FinalPayRequestDTO;
import com.blackhorse.auction.service.FinalPayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FinalPayControllerTest extends TestBase {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private FinalPayService finalPayService;

    @Test
    void should_return_success_when_pay_final_amount_given_correct_amount() throws Exception {
        String bid = "111";
        FinalPayRequestDTO requestDTO = FinalPayRequestDTO.builder().finalAmount(new BigDecimal(50000)).build();
        String requestJson = objectMapper.writeValueAsString(requestDTO);


        mockMvc.perform(post("/bidder-contract/" + bid + "/final-payment-request/confirmation")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(200)));
    }
}
