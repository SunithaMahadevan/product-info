package com.myretail.Service;

import com.myretail.Response.ProductInfoConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ProductInfoServiceTests {

    @Mock
    ProductInfoConsumer productInfoConsumer;

    @InjectMocks
    ProductInfoService productInfoService;

    @Test
    public void TC0001_API_nullProductIDInput() {

        ProductInfoConsumer productInfoConsumer = productInfoService.getProductDescription(null);
        assertNull(productInfoConsumer.getProduct());
        assertEquals(productInfoConsumer.getError_message(),"404 - Page not found");
    }

    @Test
    public void TC0002_API_validProductIDInput() {

        ProductInfoConsumer productInfoConsumer = productInfoService.getProductDescription(13860428);
        assertEquals(productInfoConsumer.getProduct().getItem().getTcin(),13860428);
        assertNull(productInfoConsumer.getError_message());
    }

    @Test
    public void TC0003_API_invalidProductIDInput() {
        ProductInfoConsumer productInfoConsumer = productInfoService.getProductDescription(15117729);
        assertNull(productInfoConsumer.getProduct());
        assertTrue(productInfoConsumer.getError_message().contains("403"));
    }

}
