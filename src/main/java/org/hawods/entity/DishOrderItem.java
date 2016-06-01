package org.hawods.entity;

import org.hawods.entity.base.OrderItem;

/**
 * Created by hawods on 5/30/16.
 */
public class DishOrderItem extends OrderItem {
    private Dish dish;
    private Integer quantity;
    private Integer servedQuantity;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getServedQuantity() {
        return servedQuantity;
    }

    public void setServedQuantity(Integer servedQuantity) {
        this.servedQuantity = servedQuantity;
    }
}
