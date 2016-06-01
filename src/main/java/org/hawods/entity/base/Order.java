package org.hawods.entity.base;

import org.hawods.entity.Member;
import org.hawods.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hawods on 5/30/16.
 */
public class Order extends BaseEntity<Long> {
    public enum Status {
        pendingPayment,
        pendingReview,
        accepted,
        completed,
        canceled,
        denied
    }

    private BigDecimal price;
    private Member member;
    private Date scheduledTime;
    private Status status;
    private String remark;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
