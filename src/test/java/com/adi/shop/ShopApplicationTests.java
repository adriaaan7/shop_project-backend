package com.adi.shop;
//
//import com.adi.shop.repository.MenuRepository;
import com.adi.shop.repository.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties"
)
@SpringBootTest
class ShopApplicationTests {

    @Autowired
    private MockMvc mvc;

//    @Autowired
//    private MenuRepository menuRepository;

    @Autowired
    private DishRepository dishRepository;

    @Test
    void contextLoads() {
    }


}
