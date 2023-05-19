package com.ataide.sqlitelecc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ataide.sqlitelecc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Criar objecto do Binding
    private ActivityMainBinding mainBinding;

    //Criar contante para nome da base de dados e versão da db
    private static final String DB_NAME = "ALUNOSLECC.DB";
    private static final int DB_VERSION = 1;

    //Criar objecto da tabela DBHelper - Sera usado o método save()
    private static DBHelper db;

    //SetOnClick Listener pode usar a expressão lambda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        //Instanciar o objecto do DBhelper
        db = new DBHelper(this, DB_NAME, null, DB_VERSION);

        mainBinding.okBtn.setOnClickListener(
              v -> {
                  gravar();
              }
        );
    }

    //Gravar
    private void gravar() {

        if( db.save(
              mainBinding.nomeED.getText().toString(),
              mainBinding.cursoED.getText().toString()
        ) != -1 ){
            Toast.makeText(this, "SUCESSS", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}