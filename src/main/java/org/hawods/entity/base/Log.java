package org.hawods.entity.base;

import org.hawods.entity.base.BaseEntity;

/**
 * Created by hawods on 6/1/16.
 */
public class Log extends BaseEntity<Long> {

    private String operation;

    private String operator;

    private String parameter;

    private String ip;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
