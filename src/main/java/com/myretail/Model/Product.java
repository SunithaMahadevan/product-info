package com.myretail.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonProperty("item")
    private Item item;

//    private String errorMessage;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

//    public String getErrorMessage() {
//        return errorMessage;
//    }
//
//    public void setErrorMessage(String errorMessage) {
//        this.errorMessage = errorMessage;
//    }
}
