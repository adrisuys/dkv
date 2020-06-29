package be.adrisuys.odoo.dkv.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Calendar;

import be.adrisuys.odoo.dkv.model.History;

public class BaseActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getApplicationContext().getSharedPreferences("dkv", MODE_PRIVATE);
        editor = sp.edit();
        gson = new Gson();
    }

    public void saveHistory(History history){
        String json = gson.toJson(history);
        editor.putString("history", json);
        editor.commit();
    }

    public History fetchHistory(){
        String json = sp.getString("history", "");
        if (json.equals("")){
            return new History();
        } else {
            return gson.fromJson(json, History.class);
        }
    }
}
