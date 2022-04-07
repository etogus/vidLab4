package com.mamedovga.vidlab4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    RealmResults<Mono> monoList;

    public MyAdapter(Context context, RealmResults<Mono> monoList) {
        this.context = context;
        this.monoList = monoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Mono mono = monoList.get(position);
        holder.name.setText(mono.getName());
        String companyText = mono.getCompany() + ", ";
        holder.company.setText(companyText);
        String screenText = mono.getScreen() + "\", ";
        holder.screen.setText(screenText);
        String ramText = mono.getRam() + " ГБ ОЗУ, ";
        holder.ram.setText(ramText);
        String ssdText = mono.getSsd() + " ГБ SSD, ";
        holder.ssd.setText(ssdText);
        holder.system.setText(mono.getSystem());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("name", mono.getName());
                intent.putExtra("company", mono.getCompany());
                intent.putExtra("screen", mono.getScreen());
                intent.putExtra("ram", mono.getRam());
                intent.putExtra("ssd", mono.getSsd());
                intent.putExtra("system", mono.getSystem());
                view.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("Удалить");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Удалить")){
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            mono.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context,"Удалено",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return monoList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView company;
        TextView screen;
        TextView ram;
        TextView ssd;
        TextView system;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            company = itemView.findViewById(R.id.company);
            screen = itemView.findViewById(R.id.screen);
            ram = itemView.findViewById(R.id.ram);
            ssd = itemView.findViewById(R.id.ssd);
            system = itemView.findViewById(R.id.system);
        }
    }
}
