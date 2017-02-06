package com.myretail.Controller;


import com.myretail.Model.ItemPrice;
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
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
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
        productInfoResponse.setTcin("13860428");
        productInfoResponse.setErrorMessage(null);

        given(productInfoService.getProductDetails(anyString())).willReturn(productInfoResponse);

        mvc.perform(MockMvcRequestBuilders.get("/product_info/v1/13860428").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", equalTo("13860428")))
                .andExpect(jsonPath("$.error_message", equalTo(null)));

    }


    @Test
    public void TC0002_productNotFound() throws Exception {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();

        productInfoResponse.setErrorMessage("403 Forbidden: This item cannot be retrieved via this guest-facing endpoint state");

        given(productInfoService.getProductDetails(anyString())).willReturn(productInfoResponse);

        mvc.perform(MockMvcRequestBuilders.get("/product_info/v1/15117729").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", equalTo(null)))
                .andExpect(jsonPath("$.error_message", equalTo("403 Forbidden: This item cannot be retrieved via this guest-facing endpoint state")));

    }

    @Test
    public void TC0003_ProductInputNotProvided() throws Exception {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();

        productInfoResponse.setErrorMessage("404 - Page not found");

        given(productInfoService.getProductDetails(anyString())).willReturn(productInfoResponse);

        mvc.perform(MockMvcRequestBuilders.get("/product_info/v1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void TC0004_updateProductPrice() throws Exception {

        ProductInfoResponse updatedPriceResponse = new ProductInfoResponse();
        updatedPriceResponse.setTcin("13860428");
        ItemPrice updatedPrice = new ItemPrice();
        updatedPrice.setPrice((float)5.99);
        updatedPrice.setCurrency("USD");
        updatedPriceResponse.setItemPrice(updatedPrice);
        updatedPriceResponse.setErrorMessage("****SUCCESS***fully updated Product Price Information");

        given(productInfoService.updateProductPrice(anyObject(),anyString())).willReturn(updatedPriceResponse);

        mvc.perform(MockMvcRequestBuilders.put("/product_info/v1/13860428?price=5.99&currency_code=USD").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", equalTo("13860428")))
                .andExpect(jsonPath("$.error_message", equalTo("****SUCCESS***fully updated Product Price Information")))
                .andExpect(jsonPath("$.current_price.value", equalTo(5.99)))
                .andExpect(jsonPath("$.current_price.currency_code", equalTo("USD")));

    }

}
