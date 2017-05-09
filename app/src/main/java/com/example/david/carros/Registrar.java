package com.example.david.carros;

import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Registrar extends AppCompatActivity {
    private EditText cajaplaca, cajaprecio;
    private Spinner combomarca,combomodelo,combocolor;
    private Resources res;
    private String[] op_marca,op_modelo,op_color;
    private ArrayAdapter<String> adapter_marca,adapter_modelo,adapter_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        res=this.getResources();
        cajaplaca=(EditText)findViewById(R.id.txtPlaca);
        cajaprecio=(EditText)findViewById(R.id.txtPrecio);
        combomarca=(Spinner)findViewById(R.id.cmdMarca);
        combomodelo=(Spinner)findViewById(R.id.cmdModelo);
        combocolor=(Spinner)findViewById(R.id.cmdColor);
        op_marca=res.getStringArray(R.array.opciones_marca);
        adapter_marca=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,op_marca);
        op_modelo=res.getStringArray(R.array.opciones_modelo);
        adapter_modelo=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,op_modelo);
        op_color=res.getStringArray(R.array.opciones_color);
        adapter_color=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,op_color);

        combomarca.setAdapter(adapter_marca);
        combomodelo.setAdapter(adapter_modelo);
        combocolor.setAdapter(adapter_color);
    }

    public void registrar(View v){
        if (validar()){
            String placa,marca,color,foto;
            int modelo,precio;
            placa=cajaplaca.getText().toString().trim();
            marca=combomarca.getSelectedItem().toString();
            modelo=Integer.parseInt(combomodelo.getSelectedItem().toString());
            color=combocolor.getSelectedItem().toString();
            precio=Integer.parseInt(cajaprecio.getText().toString().trim());
            foto=String.valueOf(fotoAleatoria());

            Carro c=new Carro(foto,placa,marca,modelo,color,precio);
            c.guardar();
            new AlertDialog.Builder(this).setMessage(R.string.registro_exito).show();
            limpiar();
        }
    }

    public int fotoAleatoria(){
        int fotos[]={R.drawable.imagen,R.drawable.imagen2,R.drawable.imagen3},numero;
        numero=(int)(Math.random()*3);
        return fotos[numero];
    }

    public boolean validar(){
        if (cajaplaca.getText().toString().isEmpty()){
            cajaplaca.requestFocus();
            cajaplaca.setError(res.getString(R.string.error_placa));
            return false;
        }
        if (cajaprecio.getText().toString().isEmpty()){
            cajaprecio.requestFocus();
            cajaprecio.setError(res.getString(R.string.error_precio));
            return false;
        }
        return true;
    }

    public void borrar(View v){
        limpiar();
    }

    public void limpiar(){
        cajaplaca.setText("");
        cajaprecio.setText("");
        combomarca.setSelection(0);
        combomodelo.setSelection(0);
        combocolor.setSelection(0);
        cajaplaca.requestFocus();
    }
}
