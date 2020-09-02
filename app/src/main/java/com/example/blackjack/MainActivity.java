package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Button btn_tamam;
Button btn_ver;
TextView tv_kasa;
TextView tv_oyuncu;
ArrayList<kart> deste = new ArrayList<kart>();
ArrayList<kart> oyuncuel = new ArrayList<kart>();
ArrayList<kart> kasael = new ArrayList<kart>();
int o_eltop =0;
int k_eltop = 0;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        desteolustur();


    btn_ver = (Button)findViewById(R.id.btn_devam);
    btn_tamam = (Button)findViewById(R.id.btn_tamam);
    tv_oyuncu = (TextView)findViewById(R.id.tv_oyuncu);
    tv_kasa = (TextView)findViewById(R.id.tv_kasatext);
    elsifirla();
        btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kart cekKart = kartcek();
                oyuncuel.add(cekKart);


                o_eltop += cekKart.deger;
                tv_oyuncu.setText("Oyuncu Eli : " + o_eltop);
                if (o_eltop > 21){
                    Toast.makeText(getApplicationContext(),"KAYBETTİNİZ",Toast.LENGTH_SHORT).show();
                    elsifirla();
                }

            }
        });
        btn_tamam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(o_eltop == 21){
                    Toast.makeText(getApplicationContext(),"KAZANDINIZ",Toast.LENGTH_SHORT).show();
                    elsifirla();
                }
               else if (o_eltop > 21){
                    Toast.makeText(getApplicationContext(),"KAYBETTİNİZ",Toast.LENGTH_SHORT).show();
                    elsifirla();
                }
               else if(o_eltop < 21 && k_eltop < o_eltop){
                   while(k_eltop <= 21 && k_eltop < o_eltop){
                       kart k2 = kartcek();
                       kasael.add(k2);
                       k_eltop += k2.deger;
                       tv_kasa.setText("Kasa Degeri : "+ k_eltop);

                   }
                    if(k_eltop > 21){
                        Toast.makeText(getApplicationContext(),"KAZANDINIZ",Toast.LENGTH_SHORT).show();
                        elsifirla();
                    }
                    if(k_eltop <=21 && k_eltop > o_eltop){
                        Toast.makeText(getApplicationContext(),"KAYBETTİNİZ",Toast.LENGTH_SHORT).show();
                        elsifirla();
                    }
                }
            }
        });

    }

    kart kartcek(){
        kart k = deste.get(deste.size()-1);
        deste.remove(deste.size()-1);
        Toast.makeText(getApplicationContext(),"Çekilen Kart : " + k.tur + " " + k.deger,Toast.LENGTH_SHORT).show();
        return k;
    }

    void elsifirla(){
k_eltop = 0;
    kasael.removeAll(kasael);
    kart k1 = deste.get(3);
        kasael.add(k1);
    deste.remove(3);
    kart k2 = deste.get(4);
        kasael.add(k2);
    deste.remove(4);
    k_eltop = k1.deger + k2.deger;
    oyuncuel.removeAll(oyuncuel);

    tv_kasa.setText("Kasa El : " + k_eltop);
        o_eltop = 0;
    k1 = deste.get(1);
    oyuncuel.add(k1);
    k2 = deste.get(2);
    oyuncuel.add(k2);
    deste.remove(1);
    deste.remove(2);
    o_eltop = k1.deger + k2.deger;
    tv_oyuncu.setText("Oyuncu Toplam Değer : " + o_eltop);

    }

    void desteolustur(){
        for(int i = 0;i<4;i++){
            for(int j = 0;j<13;j++){
                if(i ==0) // maca
                {kart k;
                   if(j+1 <=10){
                       k = new kart("maca",j+1);
                   }
                   else{
                        k = new kart("maca",10);
                   }
                    deste.add(k);
                }
                if(i == 1){
                    kart k;
                    if(j+1 <=10){
                       k = new kart("karo",j+1);
                    }
                    else{
                       k = new kart("karo",10);
                    }
                    deste.add(k);
                }
               if(i == 2){
                   kart k;
                   if(j+1 <=10){
                       k = new kart("kupa",j+1);
                   }
                   else{
                       k = new kart("kupa",10);
                   }
                   deste.add(k);
               }
               if(i ==3){
                   kart k;
                   if(j+1 <=10){
                       k = new kart("sinek",j+1);
                   }
                   else{
                       k = new kart("sinek",10);
                   }
                   deste.add(k);
               }
            }
            Collections.shuffle(deste); // desteyi kardım
        }
    }
}