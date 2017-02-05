package com.myretail.Service;

import com.myretail.Model.ProductInfo;
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

        ProductInfo productInfo = productInfoService.getProductDescription(null);
        assertNull(productInfo.getProduct());
        assertEquals(productInfoResponse.getErrorMessage(),"404 - Page not found");
    }

    @Test
    public void TC0002_API_validProductIDInput() {

        ProductInfo productInfo = productInfoService.getProductDescription("13860428");
     //   assertEquals(productInfoResponse.getProduct().getItem().getTcin(),13860428);
        assertNull(productInfo.getErrorMessage());
    }

    @Test
    public void TC0003_API_invalidProductIDInput() {
        ProductInfo productInfo = productInfoService.getProductDescription("15117729");
        assertNull(productInfo.getProduct());
        assertTrue(productInfo.getErrorMessage().contains("403"));
    }

}
