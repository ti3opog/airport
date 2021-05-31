package com.airport.demo.controllers;

import com.airport.demo.entity.Airport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class AirportControllerTest {

    @Autowired
    private MockMvc mvc;

    private final Gson gson = new GsonBuilder().create();

    @Test
    @DisplayName("Test find all clients")
    void findAll() throws Exception{
        List<Airport> airportList = new ArrayList<>();

        Airport airport1 = new Airport(2L, 3L, 1L, "male", "Dima", 1234L, 123456L, "Dimov");
        Airport airport2 = new Airport(3L, 83L, 12L, "male", "Misha", 1234L, 123456L, "Mishin");
        Airport airport3 = new Airport(1L, 3L, 1L, "male", "Vanya", 1234L, 123456L, "Vanych");

        airportList.add(airport1);
        airportList.add(airport2);
        airportList.add(airport3);

        String expected = gson.toJson(airportList);

        String actual = mvc.perform(get("/airport")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test find one client by id")
    void findOne() throws Exception {

        Airport airport = new Airport(3L, 83L, 12L, "male", "Misha", 1234L, 123456L, "Mishin");

        String expected = gson.toJson(airport);

        String actual = mvc.perform(get("/airport/3")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test create new client in airport")
    void createAirport() throws Exception{

        Airport expected_airport = new Airport(16L, 25L, 2L, "male", "Petya", 1234L, 123456L, "Petyin");
        Airport airport = new Airport(25L, 2L, "male", "Petya", 1234L, 123456L, "Petyin");

        String actual_exam = gson.toJson(airport);
        String expected = gson.toJson(expected_airport);

        String actual = mvc.perform(post("/airport/")
                .content(actual_exam)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test replace client name and surname")
    void replaceAirport() throws Exception {
        Airport expected_airport = new Airport(16L, 3L, 1L, "male", "Vanya", 1234L, 123456L, "Vanych");
        Airport airport = new Airport( 3L, 1L, "male", "Vanya", 1234L, 123456L, "Vanych");

        String actual_airport = gson.toJson(airport);
        String expected = gson.toJson(expected_airport);

        String actual = mvc.perform(put("/airport/16")
                .content(actual_airport)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test delete client")
    void deleteAirport() throws Exception{
        String actual = mvc.perform(delete("/airport/16")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(actual);
        Assertions.assertNotNull(actual);
    }
}