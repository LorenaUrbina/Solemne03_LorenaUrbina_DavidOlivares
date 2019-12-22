package com.example.solemne03_lorenaurbina_davidolivares.SQLite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;


public class DBSQLite extends SQLiteOpenHelper {

    private static final String BDName="sqlite.db";
    private static final int VERSION=1;
    private static final String TABLA_MENSAJE = "CREATE TABLE MENSAJES(ID INTEGER PRIMARY KEY, HORA TEXT,MENSAJE TEXT)";

    public DBSQLite(@Nullable Context context) {
        super(context, BDName, null, VERSION);
    }

    @Override
    public void onCreate( final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_MENSAJE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists"+TABLA_MENSAJE);
        sqLiteDatabase.execSQL(TABLA_MENSAJE);
    }

    public void AgregarMensaje(String mensaje){
        SQLiteDatabase bd = getWritableDatabase();
        if(bd!=null){
            bd.execSQL("INSERT INTO MENSAJES(HORA,MENSAJE) VALUES(datetime('now'),'"+mensaje+"')");
        }
    }




}

