package com.myretail.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfoConsumer {

    private Integer tcin;
    private String title;

    public Integer getTcin() {
        return tcin;
    }

    public void setTcin(Integer tcin) {
        this.tcin = tcin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ProductInfoConsumer{" +
                "tcin=" + tcin +
                ", title='" + title + '\'' +
                '}';
    }


}
