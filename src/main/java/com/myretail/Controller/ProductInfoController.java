package com.myretail.Controller;


import com.myretail.Model.ItemPrice;
import com.myretail.Response.ProductInfo;
import com.myretail.Response.ProductInfoResponse;
import com.myretail.Service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/product_info/v1")


public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoController.class);

    @ResponseBody
    @RequestMapping (value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductInfoResponse getProductData (@PathVariable("id") String id) {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        ProductInfo productInfo = productInfoService.getProductDescription(id);

        if (id == null) {
            productInfoResponse.setErrorMessage("404 - Not Found");
        }
        else {

            if (productInfo.getErrorMessage() != null) {
                if (productInfo.getErrorMessage().contains("403")) {
                    productInfo.setErrorMessage("403 Forbidden: This item cannot be retrieved via this guest-facing endpoint state");
                }
                productInfoResponse.setErrorMessage(productInfo.getErrorMessage());
            }
            else {
                ItemPrice itemPriceDetails = productInfoService.getProductPrice(productInfo);

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

}
