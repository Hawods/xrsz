package org.hawods.entity;

import org.hawods.entity.base.OrderItem;

import java.util.Date;

/**
 * Created by hawods on 5/30/16.
 */
public class RoomOrderItem extends OrderItem {
    private Room room;
    private Date startDate;
    private Date endDate;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
