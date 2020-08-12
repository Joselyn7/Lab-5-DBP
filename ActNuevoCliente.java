package com.example.jos.carteraclientes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.jos.carteraclientes.BaseDatos.FeedReaderC;
import com.example.jos.carteraclientes.BaseDatos.DatosOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActNuevoCliente extends AppCompatActivity {
    private EditText edtNombre;
    private EditText edtDireccion;
    private EditText edtEmail;
    private EditText edtTelefono;

    private SQLiteDatabase conexion;
    private DatosOpenHelper datosOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nuevo_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNombre = findViewById(R.id.edtNombre);
        edtDireccion =  findViewById(R.id.edtDireccion);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefono =  findViewById(R.id.edtTelefono);

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_nuevo_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_ok:
                if(bCamposCorrectos()){
                    try {
                        datosOpenHelper = new DatosOpenHelper( this);
                        conexion = datosOpenHelper.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put(FeedReaderC.FeedEntry.COLUMN_NAME, edtNombre.getText().toString().trim());
                        values.put(FeedReaderC.FeedEntry.COLUMN_ADDRESS, edtDireccion.getText().toString().trim());
                        values.put(FeedReaderC.FeedEntry.COLUMN_EMAIL, edtEmail.getText().toString().trim());
                        values.put(FeedReaderC.FeedEntry.COLUMN_PHONE, edtTelefono.getText().toString().trim());

                        conexion.insert(FeedReaderC.FeedEntry.TABLE_NAME, null, values);
                        conexion.close();

                        finish();
                    } catch (Exception ex){
                        AlertDialog.Builder dlg = new AlertDialog.Builder( this);
                        dlg.setTitle("Aviso");
                        dlg.setMessage(ex.getMessage());
                        dlg.setNeutralButton( "OK", null);
                        dlg.show();
                    }
                }
                else{
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("Aviso");
                    dlg.setMessage("Existen campos vacios");
                    dlg.setNeutralButton(  "OK", null);
                    dlg.show();
                }
                break;
            case R.id.action_cancelar:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean bCamposCorrectos(){
        boolean res = true;
        if(edtNombre.getText().toString().trim().isEmpty()){
            edtNombre.requestFocus();
            res = false;
        }
        if(edtDireccion.getText().toString().trim().isEmpty()){
            edtNombre.requestFocus();
            res = false;
        }
        if(edtEmail.getText().toString().trim().isEmpty()){
            edtNombre.requestFocus();
            res = false;
        }
        if(edtTelefono.getText().toString().trim().isEmpty()){
            edtNombre.requestFocus();
            res = false;
        }
        return res;
    }
}