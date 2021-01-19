package com.xrca.reflection.entity;


public class Computer {
    public String brand;

    private String cpu;

    private Double ssd;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Double getSsd() {
        return ssd;
    }

    public void setSsd(Double ssd) {
        this.ssd = ssd;
    }

    public void start() {
        System.out.println("启动....");
    }

    private void calc() {
        System.out.println("计算...");
    }
}
