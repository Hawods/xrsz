package org.hawods.entity;

import java.math.BigDecimal;

/**
 * Created by hawods on 5/30/16.
 */
public class MemberRank extends BaseEntity<Long> {
    private String title;
    private BigDecimal discount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
