package be.adrisuys.odoo.dkv.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import be.adrisuys.odoo.dkv.R;
import be.adrisuys.odoo.dkv.model.Entry;
import be.adrisuys.odoo.dkv.model.Storage;

public class EntriesActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(Storage.getHistory().getEntries());
        recyclerView.setAdapter(mAdapter);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Entry> entries;

        public MyAdapter(List<Entry> entries) {
            this.entries = entries;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_entry, parent, false);
            return new MyViewHolder(viewItem);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Entry entry = entries.get(position);
            holder.displayInfos(entry);
        }

        @Override
        public int getItemCount() {
            return entries.size();
        }

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private TextView liters;
        private TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.entry_date);
            liters = itemView.findViewById(R.id.entry_liters);
            price = itemView.findViewById(R.id.entry_price);
        }

        public void displayInfos(Entry entry){
            String myFormat = "dd/MM/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
            date.setText(sdf.format(entry.getDate().getTime()));
            liters.setText(entry.getLiters() + " L");
            price.setText(entry.getPrice() + " €");
        }
    }
}
