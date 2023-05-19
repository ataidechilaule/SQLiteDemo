package com.ataide.sqlitelecc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //Constantes, nome da tabela e colunas
    private static final String TABLE_NAME = "ALUNOS";
    private static final String _ID = "id";
    private static final String NOME = "nome";
    private static final String CURSO = "curso";

    //Constante para criação da tabela - atenção aos espaços antes e depois de cada concatenação
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " TEXT NOT NULL, "
            + CURSO + " TEXT);";


    //Manter o construtor
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //  Executar a constante do sql com recurso ao objecto db
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    // Codigo usado em casos de upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Criar método para gravar na tabela.
    public long save(String nome, String curso){

        //Criar objecto que possibilita a escrita na base de dados
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Criação de content value do android content e inserção de valores
        ContentValues valores = new ContentValues();
        valores.put(NOME, nome);
        valores.put(CURSO, curso);

        //Instrução de inserção, devolve -1 em caso de erro
        long insert = sqLiteDatabase.insert(TABLE_NAME, null, valores);

        //Fecho do fluxo de escrita aberto
        sqLiteDatabase.close();

        // devolve o valor long
        return insert;

    }
}
