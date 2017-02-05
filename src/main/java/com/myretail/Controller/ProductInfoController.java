package com.myretail.Controller;


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
    public ProductInfoResponse getProductData (@PathVariable("id") Integer id)  {

        ProductInfoResponse productInfoResponse = productInfoService.getProductDescription(id);

        if (productInfoResponse.getError_message() != null) {
            if(productInfoResponse.getError_message().contains("403")) {
                productInfoResponse.setError_message("403 Forbidden");
            }
        }
        else {
            productInfoResponse = productInfoService.getProductPrice(productInfoResponse);
        }

        return productInfoResponse;

    }

}
