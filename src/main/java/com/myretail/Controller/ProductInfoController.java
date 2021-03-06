package com.myretail.Controller;


import com.myretail.Model.ItemPrice;
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

    //GET API to get the Product details from an external API and price information from DB
    // id refers to the TCIN of the product
    @ResponseBody
    @RequestMapping (value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductInfoResponse getProductData (@PathVariable("id") String id) {

        ProductInfoResponse productInfoResponse = productInfoService.getProductDetails(id);

        return productInfoResponse;

    }

    //UPDATE API to update the price information of a product
    //TCIN, New Price value and New currency code are mandatory inputs
    @ResponseBody
    @RequestMapping (value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ProductInfoResponse updateProductData (@PathVariable("id") String id,
                                                  @RequestParam(required = true) Float price,
                                                  @RequestParam(required = true) String currency_code) {

        ItemPrice newItemPrice = new ItemPrice();
        newItemPrice.setPrice(price);
        newItemPrice.setCurrency(currency_code);
        ProductInfoResponse updatePriceResponse = productInfoService.updateProductPrice(newItemPrice, id);

        return updatePriceResponse;
    }


}
