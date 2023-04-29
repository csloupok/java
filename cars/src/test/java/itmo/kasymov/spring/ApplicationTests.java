package itmo.kasymov.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.kasymov.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateBrand() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now(), 1L);
        String brandJson = objectMapper.writeValueAsString(brand);

        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Brand")));
    }

    @Test
    public void testGetBrandById() throws Exception {
        mockMvc.perform(get("/brand/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Brand")));
    }

    @Test
    public void testGetByBrandName() throws Exception {
        mockMvc.perform(get("/getByBrandName?name=Brand"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Brand")));
    }
}
