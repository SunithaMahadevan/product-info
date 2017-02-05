package com.myretail.Service;

import com.myretail.Repository.ProductRepository;
import com.myretail.Response.Item;
import com.myretail.Response.Product;
import com.myretail.Response.ProductInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoService.class);

    @Autowired
    ProductRepository productRepository;

    RestTemplate restTemplate = new RestTemplate();

    public ProductInfoResponse getProductDescription (Integer id) {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        if(id != null) {

            String url = "http://redsky.target.com/v1/pdp/tcin/" + id;
            LOGGER.info(url);
            try {
                productInfoResponse = restTemplate.getForObject(url, ProductInfoResponse.class);
            }
            catch (HttpClientErrorException ex){
                productInfoResponse.setError_message(ex.toString());
            }
        }
        else {
            productInfoResponse.setError_message("404 - Page not found");
        }
        LOGGER.info(productInfoResponse.toString());
        return productInfoResponse;
    }

    public ProductInfoResponse getProductPrice (ProductInfoResponse productInfoResponse) {

        Item inputItem = productInfoResponse.getProduct().getItem();
        LOGGER.info(inputItem.toString());

        Item item = productRepository.getPrice(inputItem.getTcin());

        Product product = new Product();
        product.setItem(item);
        productInfoResponse.setProduct(product);

        return productInfoResponse;
    }
}
