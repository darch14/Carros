package com.example.david.carros;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Reportes extends AppCompatActivity {
    private ListView ls;
    private Resources res;
    private String[] op;
    private Intent i;
    private ArrayList<Carro> carros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        carros=Datos.getCarros();

        ls=(ListView)findViewById(R.id.lvReportes);
        res=this.getResources();
        op=res.getStringArray(R.array.listado_reportes);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,op);
        ls.setAdapter(adapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        new AlertDialog.Builder(Reportes.this).setMessage(res.getString(R.string.app_name)+
                                "= "+String.valueOf(carros.size())).show();
                        break;
                    case 1:
                        new AlertDialog.Builder(Reportes.this).setMessage(marcasContador()).show();
                        break;
                    case 2:
                        new AlertDialog.Builder(Reportes.this).setMessage(colorContador()).show();
                        break;
                    case 3:
                        i=new Intent(Reportes.this,ListadoModelo.class);
                        startActivity(i);
                        break;
                    case 4:
                        i=new Intent(Reportes.this,DatosCarroCaro.class);
                        startActivity(i);
                        break;
                    case 5:
                        i=new Intent(Reportes.this,DatosCarroEconomico.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }
    public String marcasContador(){
        String marca1,marca2,marca3,marca4,marcas,kiaS,masdaS,bmwS,fordS;
        int ford=0,masda=0,bmw=0,kia=0;
        kiaS=res.getString(R.string.marca_uno);
        masdaS=res.getString(R.string.marca_dos);
        bmwS=res.getString(R.string.marca_tres);
        fordS=res.getString(R.string.marca_cuatro);

        for (int i=0;i<carros.size();i++){
            if (carros.get(i).getMarca().equals(kiaS)){
                kia=kia+1;
            }
            if (carros.get(i).getMarca().equals(masdaS)){
                masda=masda+1;
            }
            if (carros.get(i).getMarca().equals(bmwS)){
                bmw=bmw+1;
            }
            if (carros.get(i).getMarca().equals(fordS)){
                ford=ford+1;
            }
        }

        marca1=kiaS+": "+String.valueOf(kia)+" ";
        marca2=masdaS+": "+String.valueOf(masda)+" ";
        marca3=bmwS+": "+String.valueOf(bmw)+" ";
        marca4=fordS+": "+String.valueOf(ford);
        marcas=marca1+marca2+marca3+marca4;
        return marcas;
    }

    public String colorContador(){
        String color1,color2,color3,color4,colores,blan,neg,roj,azu;
        int blanco=0,negro=0,rojo=0,azul=0;
        blan=res.getString(R.string.color_uno);
        neg=res.getString(R.string.color_dos);
        roj=res.getString(R.string.color_tres);
        azu=res.getString(R.string.color_cuatro);

        for (int i=0;i<carros.size();i++){
            if (carros.get(i).getColor().equals(blan)){
                blanco=blanco+1;
            }
            if (carros.get(i).getColor().equals(neg)){
                negro=negro+1;
            }
            if (carros.get(i).getColor().equals(roj)){
                rojo=rojo+1;
            }
            if (carros.get(i).getColor().equals(azu)){
                azul=azul+1;
            }
        }

        color1=blan+": "+String.valueOf(blanco)+" ";
        color2=neg+": "+String.valueOf(negro)+" ";
        color3=azu+": "+String.valueOf(azul)+" ";
        color4=roj+": "+String.valueOf(rojo);
        colores=color1+color2+color3+color4;
        return colores;
    }
}
