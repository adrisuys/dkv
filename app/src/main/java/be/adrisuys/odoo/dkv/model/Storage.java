package be.adrisuys.odoo.dkv.model;

import java.util.Calendar;

public class Storage {

    public static History history;
    public static Calendar calendar;

    public static Calendar getCalendar() {
        return calendar;
    }

    public static History getHistory() {
        return history;
    }

    public static void setHistory(History history) {
        Storage.history = history;
    }

    public static void setCalendar(Calendar calendar) {
        Storage.calendar = calendar;
    }
}
