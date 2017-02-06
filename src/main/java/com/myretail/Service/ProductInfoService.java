package com.myretail.Service;

import com.myretail.Model.ItemPrice;
import com.myretail.Repository.ProductRepository;
import com.myretail.Model.ProductInfo;
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


    public ProductInfoResponse getProductDetails (String id) {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();

        if (id == null) {
            productInfoResponse.setErrorMessage("404 - Not Found");
        }
        else {

            ProductInfo productInfo = getProductDescription(id);

            if (productInfo.getErrorMessage() != null) {
                if (productInfo.getErrorMessage().contains("403")) {
                    productInfo.setErrorMessage("403 Forbidden: This item cannot be retrieved via this guest-facing endpoint state");
                }
                productInfoResponse.setErrorMessage(productInfo.getErrorMessage());
            }
            else {
                ItemPrice itemPriceDetails = getProductPrice(productInfo);

                ItemPrice itemPriceResponse = new ItemPrice();
                if(itemPriceDetails != null) {
                    productInfoResponse.setTcin(productInfo.getProduct().getItem().getTcin());
                    productInfoResponse.setName(productInfo.getProduct().getItem().getProduct_description().getTitle());
                    itemPriceResponse.setPrice(itemPriceDetails.getPrice());
                    itemPriceResponse.setCurrency(itemPriceDetails.getCurrency());
                    productInfoResponse.setItemPrice(itemPriceResponse);
                    productInfoResponse.setErrorMessage(null);
                }
                else {
                    productInfoResponse.setTcin(productInfo.getProduct().getItem().getTcin());
                    productInfoResponse.setName(productInfo.getProduct().getItem().getProduct_description().getTitle());
                    productInfoResponse.setErrorMessage("ERROR: Item Price information is not available.");
                }

            }

        }
        return productInfoResponse;
    }



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

        LOGGER.info("getProductDescription:" + productInfo.toString());
        return productInfo;
    }



    public ItemPrice getProductPrice (ProductInfo productInfo) {

        String tcin = productInfo.getProduct().getItem().getTcin();
        ItemPrice itemPrice = productRepository.getPrice(tcin);
        return itemPrice;
    }

    public ProductInfoResponse updateProductPrice (ItemPrice newItemPrice, String tcin) {

        ItemPrice itemPrice = productRepository.updatePrice(newItemPrice, tcin);
        ProductInfoResponse outputWUpdatedPrice = new ProductInfoResponse();
        if(itemPrice != null){
            outputWUpdatedPrice.setTcin(tcin);
            outputWUpdatedPrice.setItemPrice(itemPrice);
            outputWUpdatedPrice.setErrorMessage("****SUCCESS***fully updated Product Price Information");
        }
        else {
            outputWUpdatedPrice.setTcin(tcin);
            outputWUpdatedPrice.setErrorMessage("****ERROR*** Unable to update Product Price Information");
        }

        return outputWUpdatedPrice;

    }

}
