package org.hawods.entity;

import java.math.BigDecimal;

/**
 * Created by hawods on 5/30/16.
 */
public class Dish extends BaseEntity<Long> {
    private String name;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private String description;
    private Integer stock;
    private Boolean isMarketable;
    private Boolean isTop;
    private Integer sales;
    private String[] images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getMarketable() {
        return isMarketable;
    }

    public void setMarketable(Boolean marketable) {
        isMarketable = marketable;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
