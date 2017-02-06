package com.myretail.Repository;

import com.myretail.Model.ItemPrice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ComponentScan

public class ProductInfoRepositoryTests {

    private ProductRepositoryImpl repo;

    @Before
    public void setup(){
        repo = new ProductRepositoryImpl();
    }

    @Test
    public void TC0001_testGetPriceValidInput() {

        String tcin = "13860428";

        ItemPrice expected = new ItemPrice();
        expected.setPrice((float)12.99);
        expected.setCurrency("USD");

        ItemPrice output = repo.getPrice(tcin);

        assertEquals(output.getPrice(),expected.getPrice());

    }
}
