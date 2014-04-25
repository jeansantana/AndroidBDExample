package com.example.dbexample;

import com.example.dbexample.UsuarioContract.UsuarioEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "usuario.db";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + UsuarioEntry.TABLE_USUARIOS + " ( " +
			UsuarioEntry.COLUMN_ID + " " + INTEGER_TYPE + " PRIMARY KEY " + COMMA_SEP +
			UsuarioEntry.COLUMN_IDADE + " " + INTEGER_TYPE + " " + COMMA_SEP +
			UsuarioEntry.COLUMN_NOME + " " + TEXT_TYPE + " )";
	
	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + UsuarioEntry.TABLE_USUARIOS;

	public UsuarioDBHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_ENTRIES);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);

	}

}
