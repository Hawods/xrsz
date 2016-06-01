package org.hawods.entity.base;

import org.hawods.entity.base.BaseEntity;
import org.hawods.entity.base.Order;

import java.math.BigDecimal;

/**
 * Created by hawods on 6/1/16.
 */
public class OrderItem extends BaseEntity<Long> {
    private Order order;
    private String name;
    private BigDecimal price;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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
}
