package com.restservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Random;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestServiceApplication.class)
@AutoConfigureMockMvc
public class GitHubRestTestCase {

//    @Autowired
//    private RestServiceConfiguration restServiceConfiguration;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void applicationStarts() {
        RestServiceApplication.main(new String[]{});
    }

    @Test
    public void shouldReturn404WhenOwnerDoesNotExist() throws Exception {
        // Given
        String name = randomString();
        String repo = randomString();

        // When
        MvcResult result = mockMvc.perform(get(("http://localhost:8080/repositories/" + name + "/" + repo)))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        //Then
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void shouldReturn404WhenRepoDoesNotExistV2() throws Exception {

        // Given
        String name = "darkprinx";
        String repo = randomString();

        // When
        MvcResult result = mockMvc.perform(get(("http://localhost:8080/repositories/" + name + "/" + repo)))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        //Then
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void shouldReturn200WhenRepoExist() throws Exception {

        // Given
        String name = "darkprinx";
        String repo = "100-plus-Python-programming-exercises-extended";

        // When
        String subStringExpected = "python programming exercise";

        //Then
        this.mockMvc.perform(get(("http://localhost:8080/repositories/" + name + "/" + repo)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(subStringExpected)));
    }

    private String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
