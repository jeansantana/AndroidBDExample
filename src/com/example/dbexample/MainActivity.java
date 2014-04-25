package com.example.dbexample;

import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends ListActivity {

	private UsuarioDAO datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		datasource = new UsuarioDAO(this);
		datasource.open();

		List<Usuario> values = datasource.getAllComments();

		ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this,
		android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Usuario> adapter = (ArrayAdapter<Usuario>) getListAdapter();
		Usuario usuario = null;
		switch (view.getId()) {
			case R.id.add:
				EditText editText1 = (EditText) findViewById(R.id.user_name);
				EditText editText2 = (EditText) findViewById(R.id.user_age);
				String name = editText1.getText().toString();
				long age = Integer.parseInt(editText2.getText().toString());
				usuario = datasource.createUsuario(name, age);
				adapter.add(usuario);
				break;
			case R.id.delete:
				if (getListAdapter().getCount() > 0) {
					usuario = (Usuario) getListAdapter().getItem(0);
					datasource.deleteUsuario(usuario);
					adapter.remove(usuario);
				}
				break;
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
