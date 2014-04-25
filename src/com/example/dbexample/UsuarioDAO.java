package com.example.dbexample;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {

	private SQLiteDatabase db;
	private UsuarioDBHelper dbHelper;
	private String[] allColumns = { UsuarioContract.UsuarioEntry.COLUMN_ID, UsuarioContract.UsuarioEntry.COLUMN_IDADE, UsuarioContract.UsuarioEntry.COLUMN_NOME };

	public UsuarioDAO(Context context) {
		dbHelper = new UsuarioDBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Usuario createUsuario(String nome, long idade) {
		ContentValues values = new ContentValues();
		values.put(UsuarioContract.UsuarioEntry.COLUMN_IDADE, idade);
		values.put(UsuarioContract.UsuarioEntry.COLUMN_NOME, nome);
		long insertId = db.insert(UsuarioContract.UsuarioEntry.TABLE_USUARIOS, null,
			values);
		Cursor cursor = db.query(UsuarioContract.UsuarioEntry.TABLE_USUARIOS,
			allColumns, UsuarioContract.UsuarioEntry.COLUMN_ID + " = " + insertId, null,
			null, null, null);
		cursor.moveToFirst();
		Usuario newUsuario = cursorToUsuario(cursor);
		cursor.close();
		return newUsuario;
	}

	public void deleteUsuario(Usuario usuario) {
		long id = usuario.getId();
		System.out.println("Comment deleted with id: " + id);
		db.delete(UsuarioContract.UsuarioEntry.TABLE_USUARIOS, UsuarioContract.UsuarioEntry.COLUMN_ID + " = " + id, null);
	}

	public List<Usuario> getAllComments() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		Cursor cursor = db.query(UsuarioContract.UsuarioEntry.TABLE_USUARIOS,
				allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Usuario usuario = cursorToUsuario(cursor);
			usuarios.add(usuario);
			cursor.moveToNext();
		}
		cursor.close();
		return usuarios;
	}

	private Usuario cursorToUsuario(Cursor cursor) {
		Usuario usuario = new Usuario();
		usuario.setId(cursor.getLong(0));
		usuario.setIdade(cursor.getLong(1));
		usuario.setNome(cursor.getString(2));
		return usuario;
	}

}
