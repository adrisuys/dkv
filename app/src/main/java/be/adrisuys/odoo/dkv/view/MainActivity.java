package be.adrisuys.odoo.dkv.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import be.adrisuys.odoo.dkv.R;
import be.adrisuys.odoo.dkv.model.History;
import be.adrisuys.odoo.dkv.model.Storage;

public class MainActivity extends BaseActivity {

    private TextView balanceTxt, dateTxt, remainingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        balanceTxt = findViewById(R.id.balance);
        dateTxt = findViewById(R.id.date);
        remainingText = findViewById(R.id.remaining);
        History history = fetchHistory();
        Calendar calendar = Calendar.getInstance();
        double balance = history.getBalance(calendar.get(Calendar.MONTH));
        String strDouble = String.format("%.2f", balance);
        balanceTxt.setText(strDouble + " €");
        Storage.setHistory(history);
        Storage.setCalendar(calendar);
        updateDateLabel();
    }

    public void addNewEntry(View v){
        startActivity(new Intent(this, NewEntryActivity.class));
    }

    public void seeEntries(View v){
        startActivity(new Intent(this, EntriesActivity.class));
    }

    @Override
    public void onBackPressed() {
        //
    }

    private void updateDateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        dateTxt.setText(sdf.format(Storage.getCalendar().getTime()));
        int lastDay = Storage.getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
        int remaining = lastDay - Storage.getCalendar().get(Calendar.DAY_OF_MONTH);
        remainingText.setText(remaining + " days remaining");
    }
}
