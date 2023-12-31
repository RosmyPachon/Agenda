package com.example.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agenda.adaptadores.ListaContactosAdapter;
import com.example.agenda.db.DbContactos;
import com.example.agenda.db.DbHelper;
import com.example.agenda.entidades.Contactos;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import kotlin.contracts.Returns;

public class MainActivity extends AppCompatActivity {



    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));
        DbContactos dbContactos = new DbContactos(MainActivity.this);

        listaArrayContactos = new ArrayList<>();

        ListaContactosAdapter adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        listaContactos.setAdapter(adapter);



        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuInicio) {
                    return true;
                } else if (item.getItemId() == R.id.menuAgregar) {
                    Intent intent = new Intent(MainActivity.this, NuevoActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

//        btnCrear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DbHelper dbHelper = new DbHelper(MainActivity.this);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                if (db != null){
//                    Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Error al crear base de datos", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }
  public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() ==R.id.menuInicio ) {
          nuevoRegistro();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}