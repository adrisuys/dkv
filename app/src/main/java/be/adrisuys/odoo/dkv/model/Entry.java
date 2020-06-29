package be.adrisuys.odoo.dkv.model;

import java.io.Serializable;
import java.util.Date;

public class Entry implements Serializable {

    private Date date;
    private double liters;
    private double price;

    public Entry(Date date, double liters, double price) {
        this.date = date;
        this.liters = liters;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
