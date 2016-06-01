package org.hawods.entity;

import org.hawods.entity.base.BaseEntity;

/**
 * Created by hawods on 5/30/16.
 */
public class Role extends BaseEntity<Long> {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
