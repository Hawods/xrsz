package org.hawods.entity;

import java.math.BigDecimal;

/**
 * Created by hawods on 5/30/16.
 */
public class RoomOrderItem extends BaseEntity<Long> {
    private Order order;
    private Room room;
    private String name;
    private BigDecimal price;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
