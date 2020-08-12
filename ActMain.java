package com.example.jos.carteraclientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.jos.carteraclientes.BaseDatos.FeedReaderC;
import com.example.jos.carteraclientes.BaseDatos.DatosOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActMain extends AppCompatActivity {
    private ListView lstDatos;
    private ArrayAdapter<String> adaptator;
    private ArrayList<String> clientes;

    private SQLiteDatabase conexion;
    private DatosOpenHelper datosOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent (ActMain.this, ActNuevoCliente.class);
                //startActivity(it);
                startActivityForResult(it,0);
            }
        });
        actualizar();
    }
    private void actualizar(){
        lstDatos = findViewById(R.id.lstDatos);
        clientes = new ArrayList<String>();

        try {
            datosOpenHelper = new DatosOpenHelper(this);
            conexion = datosOpenHelper.getWritableDatabase();

            String[] projection = {
                    FeedReaderC.FeedEntry.COLUMN_NAME,
                    FeedReaderC.FeedEntry.COLUMN_PHONE
            };

            Cursor resultado = conexion.query(
                    FeedReaderC.FeedEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (resultado.getCount() > 0) {
                resultado.moveToFirst();
                do {
                    String cliente = resultado.getString(resultado.getColumnIndex(FeedReaderC.FeedEntry.COLUMN_NAME)) +
                            ": " + resultado.getString(resultado.getColumnIndex(FeedReaderC.FeedEntry.COLUMN_PHONE));
                    clientes.add(cliente);
                } while (resultado.moveToNext());
                resultado.close();
            }
            adaptator = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
            lstDatos.setAdapter(adaptator);
        }
        catch (Exception ex){
            AlertDialog.Builder dlg= new AlertDialog.Builder( this);
            dlg.setTitle("Aviso");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        actualizar();
        //super.onActivityResult(requestCode, resultCode, data);
    }
}












