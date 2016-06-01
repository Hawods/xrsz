package org.hawods.entity;

import org.hawods.entity.base.BaseEntity;

/**
 * Created by hawods on 5/30/16.
 */
public class Article extends BaseEntity<Long> {
    private String title;
    private String description;
    private String content;
    private Boolean isTop;
    private Admin operator;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Admin getOperator() {
        return operator;
    }

    public void setOperator(Admin operator) {
        this.operator = operator;
    }
}
