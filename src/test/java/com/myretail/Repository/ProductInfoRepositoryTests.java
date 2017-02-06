package com.myretail.Repository;

import com.myretail.Application;
import com.myretail.Model.ItemPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)

public class ProductInfoRepositoryTests {

    @Autowired
    private ProductRepositoryImpl repo;

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
