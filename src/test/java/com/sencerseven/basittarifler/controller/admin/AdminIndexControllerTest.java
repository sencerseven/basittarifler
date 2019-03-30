package com.sencerseven.basittarifler.controller.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AdminIndexControllerTest {


    AdminIndexController adminIndexController;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resources/templates/admin");
        viewResolver.setSuffix(".html");
        MockitoAnnotations.initMocks(this);
        adminIndexController = new AdminIndexController();
        mockMvc = MockMvcBuilders.standaloneSetup(adminIndexController).setViewResolvers(viewResolver).build();
    }


    @Test
    public void testMockMVC() throws Exception{


        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
    public void indexAction() {



    }
}