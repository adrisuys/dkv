package be.adrisuys.odoo.dkv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class History implements Serializable {

    private List<Entry> entries;

    public History(){
        this.entries = new ArrayList<>();
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry){
        entries.add(0, entry);
    }

    public double getBalance(int thisMonth){
        double balance = 250;
        Calendar cal = Calendar.getInstance();
        for (Entry entry : entries){
            cal.setTime(entry.getDate());
            int entryMonth = cal.get(Calendar.MONTH);
            if (entryMonth == thisMonth){
                balance -= entry.getPrice();
            }
        }
        return balance;
    }
}
