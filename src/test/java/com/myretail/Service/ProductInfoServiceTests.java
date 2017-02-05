package com.myretail.Service;

import com.myretail.Response.ProductInfoResponse;
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
    ProductInfoResponse productInfoResponse;

    @InjectMocks
    ProductInfoService productInfoService;

    @Test
    public void TC0001_API_nullProductIDInput() {

        ProductInfoResponse productInfoResponse = productInfoService.getProductDescription(null);
        assertNull(productInfoResponse.getProduct());
        assertEquals(productInfoResponse.getError_message(),"404 - Page not found");
    }

    @Test
    public void TC0002_API_validProductIDInput() {

        ProductInfoResponse productInfoResponse = productInfoService.getProductDescription(13860428);
     //   assertEquals(productInfoResponse.getProduct().getItem().getTcin(),13860428);
        assertNull(productInfoResponse.getError_message());
    }

    @Test
    public void TC0003_API_invalidProductIDInput() {
        ProductInfoResponse productInfoResponse = productInfoService.getProductDescription(15117729);
        assertNull(productInfoResponse.getProduct());
        assertTrue(productInfoResponse.getError_message().contains("403"));
    }

}
