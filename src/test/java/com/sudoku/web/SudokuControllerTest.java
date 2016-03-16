package com.sudoku.web;

import com.sudoku.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by nickc on 3/16/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SudokuControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetBoard() throws Exception {

            mockMvc.perform(post("/getBoard").contentType(contentType))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(contentType))
                    .andExpect(jsonPath("$", hasSize(9)))
                    .andExpect(jsonPath("$[0].[0]", is(7)));
    }

    @Test
    public void testSetCellValue() throws Exception {
        mockMvc.perform(post("/setCellValue")
                .param("row", "1")
                .param("column", "1")
                .param("value", "3")
                .contentType(contentType))
                .andExpect(status().isOk());

        mockMvc.perform(post("/getBoard").contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[1].[1]", is(3)));
    }

    @Test
    public void testCheckMove() throws Exception{
        mockMvc.perform(post("/checkMove")
                .param("row", "1")
                .param("column", "1")
                .param("value", "2")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        mockMvc.perform(post("/checkMove")
                .param("row", "1")
                .param("column", "1")
                .param("value", "5")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    public void testSudokuStatus() throws Exception {
        mockMvc.perform(post("/checkSudokuStatus")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }



}
