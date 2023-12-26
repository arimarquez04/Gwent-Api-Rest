package com.bewise.pasantia.local.controllerTest;

import com.bewise.pasantia.local.controller.JugadorController;
import com.bewise.pasantia.local.dto.JugadorRequestDto;
import com.bewise.pasantia.local.model.Jugador;
import com.bewise.pasantia.local.service.JugadorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JugadorController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JugadorControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String jugadorControllerPath = "/jugador";
    @MockBean
    private JugadorService jugadorService;

    @BeforeAll
    public void setUp(){
        initMocks(this);
        JugadorController jugadorController = new JugadorController(jugadorService);
        mockMvc = MockMvcBuilders.standaloneSetup(jugadorController).build();
    }
    @Test
    public void addJugadorConDatosValidos() throws Exception {
        JugadorRequestDto jugadorRequestDtoValido = new JugadorRequestDto();
        jugadorRequestDtoValido.setApodo("Ari");
        String requestBody = objectMapper.writeValueAsString(jugadorRequestDtoValido);



        Mockito.when(mockMvc.perform(MockMvcRequestBuilders.post(jugadorControllerPath)
                .contentType("application/json").content(requestBody)).andExpect(status().isBadRequest())).thenReturn((ResultActions) status().isBadRequest());


        mockMvc.perform(MockMvcRequestBuilders.post(jugadorControllerPath)
                .contentType("application/json").content(requestBody)).andExpect(status().isBadRequest());
        /*
        mockMvc.perform(post(jugadorControllerPath).contentType("application/json")
                 .content(requestBody)).andExpect(status().isBadRequest());
        */


        /*
        MvcResult result = mockMvc.perform(post(jugadorControllerPath).contentType("application/json").content(requestBody)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(MockHttpServletResponse.SC_BAD_REQUEST, response.getStatus());
         */
    }

}
