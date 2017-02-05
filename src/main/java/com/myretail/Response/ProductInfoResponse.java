package com.myretail.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myretail.Model.ItemPrice;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfoResponse {

    @JsonProperty("id")
    private String tcin;

    @JsonProperty("name")
    private String name;

    @JsonProperty("current_price")
    private ItemPrice itemPrice;

    @JsonProperty("error_message")
    private String errorMessage;

    public String getTcin() {
        return tcin;
    }

    public void setTcin(String tcin) {
        this.tcin = tcin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemPrice getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(ItemPrice itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ProductInfoResponse{" +
                "tcin='" + tcin + '\'' +
                ", name='" + name + '\'' +
                ", itemPrice=" + itemPrice +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
