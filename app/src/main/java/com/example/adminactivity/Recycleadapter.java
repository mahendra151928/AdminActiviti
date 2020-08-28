package com.example.adminactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycleadapter extends RecyclerView.Adapter<Recycleadapter.Recycle> {
    Context context;
    ArrayList<Pojo>arrayList;
    String del;

    public Recycleadapter(Context context, ArrayList<Pojo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Recycle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.rootlayout,parent,false);
        return new Recycle(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycle holder, int position) {

        holder.raid.setText(arrayList.get(position).getAid());
        holder.raname.setText(arrayList.get(position).getAname());
        holder.raemail.setText(arrayList.get(position).getAemail());
        holder.rapass.setText(arrayList.get(position).getApass());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Recycle extends RecyclerView.ViewHolder {
        TextView raid,raname,raemail,rapass;
        ImageView radel,raedit;
        public Recycle(@NonNull View itemView) {
            super(itemView);
            raid=itemView.findViewById(R.id.ryid);
            raname=itemView.findViewById(R.id.ryname);
            raemail=itemView.findViewById(R.id.ryemail);
            rapass=itemView.findViewById(R.id.rypass);
            radel=itemView.findViewById(R.id.rydel);
            raedit=itemView.findViewById(R.id.ryedit);

            final Dbmanager dbmanager=new Dbmanager(context);
            dbmanager.open();



            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    SharedPreferences sharedPreferences=context.getSharedPreferences("raju",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    final String atdname=raname.getText().toString();

                    editor.putString("sup",atdname);
                    editor.commit();

                    final AlertDialog alertDialog=new AlertDialog.Builder(context).create();
                    View view1=LayoutInflater.from(context).inflate(R.layout.attaandancelayout,null,false);
                    ImageView atclose=view1.findViewById(R.id.atclose);
                    final EditText atname=view1.findViewById(R.id.atname);
                    Button atsub=view1.findViewById(R.id.atsub);
                    final RadioGroup atgrp=view1.findViewById(R.id.atgroup);

                    alertDialog.setCancelable(false);
                    alertDialog.setView(view1);

                    SharedPreferences sharedPreferences2=context.getSharedPreferences("raju",Context.MODE_PRIVATE);
                    atname.setText(sharedPreferences2.getString("sup",""));

                    atsub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String atenname=atname.getText().toString();
                            int atradio=atgrp.getCheckedRadioButtonId();
                            RadioButton att=(RadioButton)view.findViewById(atradio);
                            String mahe=att.getText().toString();

                            Pojo pojo=new Pojo();
                            pojo.setAname(atenname);
                            pojo.setGroup(atradio);
                            dbmanager.atinsert(pojo);



                            Toast.makeText(context, "submitted successfully", Toast.LENGTH_SHORT).show();

                        }
                    });
                    atclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });

                   alertDialog.show();
                    return false;
                }
            });

            radel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    del=raid.getText().toString();
                    dbmanager.delete(del);
                }
            });

            raedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String upid=raid.getText().toString();
                    String upname=raname.getText().toString();
                    String upemail=raemail.getText().toString();
                    String uppass=rapass.getText().toString();

                    SharedPreferences sharedPreferences=context.getSharedPreferences("mahe",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putString("mahee",upid);
                    editor.putString("raju",upname);
                    editor.putString("sai",upemail);
                    editor.putString("nari",uppass);
                    editor.commit();

                    final AlertDialog alertDialog=new AlertDialog.Builder(context).create();
                   View itemView=LayoutInflater.from(context).inflate(R.layout.updatelayout,null,false);
                    final EditText updid=itemView.findViewById(R.id.uid);
                    final EditText updname=itemView.findViewById(R.id.uname);
                    final EditText updemail=itemView.findViewById(R.id.uemail);
                    final EditText updpass=itemView.findViewById(R.id.upass);
                    Button updupdate=itemView.findViewById(R.id.uupdate);
                    ImageView updclose=itemView.findViewById(R.id.uclose);

                    alertDialog.setCancelable(false);
                    alertDialog.setView(itemView);

                    final Dbmanager dbmanager=new Dbmanager(context);
                    dbmanager.open();

                    final SharedPreferences sharedPreferences1=context.getSharedPreferences("mahe",Context.MODE_PRIVATE);

                    updid.setText(sharedPreferences1.getString("mahee",""));
                    updname.setText(sharedPreferences1.getString("raju",""));
                    updemail.setText(sharedPreferences1.getString("sai",""));
                    updpass.setText(sharedPreferences1.getString("nari",""));

                    updupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Pojo pojo = new Pojo();
                            pojo.setAid(updid.getText().toString());
                            pojo.setAname(updname.getText().toString());
                            pojo.setAemail(updemail.getText().toString());
                            pojo.setApass(updpass.getText().toString());
                            dbmanager.update(pojo);

                            Intent intent = new Intent(context, com.example.adminactivity.Recycle.class);
                            context.startActivity(intent);
                            Toast.makeText(context, "sucessfully updated", Toast.LENGTH_SHORT).show();
                        }
                    });


                    updclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();

                }
            });

        }
    }
}
