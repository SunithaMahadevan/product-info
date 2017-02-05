package com.myretail.Service;

import com.myretail.Model.ItemPrice;
import com.myretail.Repository.ProductRepository;
import com.myretail.Response.ProductInfo;
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

    public ProductInfo getProductDescription (String id) {

        ProductInfo productInfo = new ProductInfo();
        String url = "http://redsky.target.com/v1/pdp/tcin/" + id;
        LOGGER.info(url);
        try {
            productInfo = restTemplate.getForObject(url, ProductInfo.class);
        }
        catch (HttpClientErrorException ex){
            LOGGER.info("Exception:" + ex.toString());
            productInfo.setErrorMessage(ex.toString());
        }

        LOGGER.info(productInfo.toString());
        return productInfo;
    }



    public ItemPrice getProductPrice (ProductInfo productInfo) {

        String tcin = productInfo.getProduct().getItem().getTcin();
        ItemPrice itemPrice = productRepository.getPrice(tcin);
        return itemPrice;
    }
}
