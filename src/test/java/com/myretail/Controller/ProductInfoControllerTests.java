package com.myretail.Controller;


import com.myretail.Response.Item;
import com.myretail.Response.Product;
import com.myretail.Response.ProductInfoResponse;
import com.myretail.Service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductInfoController.class)
public class ProductInfoControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductInfoService productInfoService;

    @Test
    public void TC0001_getProductInfo() throws Exception {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        Product product = new Product();
        Item item = new Item();

        item.setTcin(13860428);
        product.setItem(item);
        productInfoResponse.setProduct(product);
        productInfoResponse.setError_message(null);

        given(productInfoService.getProductDescription(anyInt())).willReturn(productInfoResponse);

        mvc.perform(MockMvcRequestBuilders.get("/product_info/v1/13860428").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.product.item.tcin", equalTo(13860428)))
                .andExpect(jsonPath("$.error_message", equalTo(null)));

    }


    @Test
    public void TC0002_productNotFound() throws Exception {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();

        productInfoResponse.setError_message("org.springframework.web.client.HttpClientErrorException: 403 Forbidden");

        given(productInfoService.getProductDescription(anyInt())).willReturn(productInfoResponse);

        mvc.perform(MockMvcRequestBuilders.get("/product_info/v1/15117729").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.product", equalTo(null)))
                .andExpect(jsonPath("$.error_message", equalTo("403 Forbidden")));

    }

    @Test
    public void TC0003_ProductInputNotProvided() throws Exception {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();

        productInfoResponse.setError_message("404 - Page not found");

        given(productInfoService.getProductDescription(anyInt())).willReturn(productInfoResponse);

        mvc.perform(MockMvcRequestBuilders.get("/product_info/v1/15117729").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.product", equalTo(null)))
                .andExpect(jsonPath("$.error_message", equalTo("404 - Page not found")));

    }

}
