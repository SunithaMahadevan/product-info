package com.myretail.Service;

import com.myretail.Response.ProductInfoConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoService.class);

    RestTemplate restTemplate = new RestTemplate();

    public ProductInfoConsumer getProductDescription (Integer id) {

        String url = "http://redsky.target.com/v1/pdp/tcin/" + id;

        LOGGER.info(url);
        ProductInfoConsumer productInfoConsumer = new ProductInfoConsumer();

        try {

            productInfoConsumer = restTemplate.getForObject(url, ProductInfoConsumer.class);
        }

        catch (HttpClientErrorException ex){

            productInfoConsumer.setError_message(ex.toString());

        }

        LOGGER.info(productInfoConsumer.toString());

        return productInfoConsumer;
    }





}
