package com.myretail.Service;

import com.myretail.Model.Item;
import com.myretail.Model.ItemPrice;
import com.myretail.Model.Product;
import com.myretail.Model.ProductInfo;
import com.myretail.Repository.ProductRepository;
import com.myretail.Response.ProductInfoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class ProductInfoServiceTests {

    @Mock
    ProductInfoResponse productInfoResponse;

    @InjectMocks
    ProductInfoService productInfoService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void TC0001_Service_nullProductIDInput_getProductDetails() {

        ProductInfoResponse productInfoResponse = productInfoService.getProductDetails(null);
        assertNull(productInfoResponse.getTcin());
        assertEquals(productInfoResponse.getErrorMessage(),"404 - Not Found");
    }

    @Test
    public void TC0002_Service_validProductIDInput_getProductDetails() {

        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setPrice((float)4.99);
        itemPrice.setCurrency("USD");
        given(productRepository.getPrice(anyString())).willReturn(itemPrice);

        ProductInfoResponse output = productInfoService.getProductDetails("13860428");
        assertEquals(output.getTcin(),"13860428");
        assertNull(output.getErrorMessage());
    }

    @Test
    public void TC0003_DB_validProductIDInput_getProductPrice() {

        ProductInfo productInfo = new ProductInfo();
        Product product = new Product();
        Item item = new Item();
        item.setTcin("13860428");
        product.setItem(item);
        productInfo.setProduct(product);

        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setPrice((float)4.99);
        itemPrice.setCurrency("USD");
        given(productRepository.getPrice(anyString())).willReturn(itemPrice);

        ItemPrice output = productInfoService.getProductPrice(productInfo);

        assertEquals(output.getPrice(),(float)4.99);
        assertEquals(output.getCurrency(),"USD");
    }

    @Test
    public void TC0004_API_invalidProductIDInput() {
        ProductInfo productInfo = productInfoService.getProductDescription("15117729");
        assertNull(productInfo.getProduct());
        assertTrue(productInfo.getErrorMessage().contains("403"));
    }

}
