package be.adrisuys.odoo.dkv.model;

public class Stats {

    private String month;
    private Double cost;

    public Stats(String month, Double cost){
        this.month = month;
        this.cost = cost;
    }

    public Double getCost() {
        return cost;
    }

    public String getMonth() {
        return month;
    }
}
