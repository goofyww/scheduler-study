package com.zcoox.entity;

public class Order {

    private int id;

    /**
     * 0 :未处理；1 :已处理
     */
    private int state;

    public Order() {
    }

    public Order(int id, int state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", state=" + state +
                '}';
    }
}
