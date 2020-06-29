package be.adrisuys.odoo.dkv.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import be.adrisuys.odoo.dkv.R;
import be.adrisuys.odoo.dkv.model.Entry;
import be.adrisuys.odoo.dkv.model.Storage;

public class NewEntryActivity extends BaseActivity {

    private EditText dateTxt, litersTxt, priceTxt;
    private Calendar myCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        dateTxt = findViewById(R.id.date_text);
        litersTxt = findViewById(R.id.liters_text);
        priceTxt = findViewById(R.id.price_text);
        myCalendar = Calendar.getInstance();
        updateLabel();
        onDateTextSelected();
    }

    private void onDateTextSelected() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        dateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NewEntryActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        dateTxt.setText(sdf.format(myCalendar.getTime()));
    }

    public void addEntry(View v){
        Date date = myCalendar.getTime();
        double liters = Double.parseDouble(litersTxt.getText().toString());
        double price = Double.parseDouble(priceTxt.getText().toString());
        Entry entry = new Entry(date, liters, price);
        Storage.getHistory().addEntry(entry);
        saveHistory(Storage.getHistory());
        startActivity(new Intent(this, MainActivity.class));
    }


}
