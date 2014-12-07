package com.hua.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hua.loginview_sqlite.R;
import com.hua.model.User;
import com.hua.uilts.CheckUnit;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText editName, editPassword = null;
	private Button login, register = null;

	private CheckUnit checkUnit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		checkUnit = new CheckUnit(this);

		initData();
	}

	public void initData() {
		editName = (EditText) findViewById(R.id.user_name);
		editPassword = (EditText) findViewById(R.id.user_pwd);

		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.regrister);

		login.setOnClickListener(this);
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:

			User user = new User();
			user.setName(editName.getText().toString());
			user.setPassword(editPassword.getText().toString());
			if (checkUnit.checkUserLogin(user)) {
				Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
				intentLogin.putExtra("userName", user.getName());
				startActivity(intentLogin);
				finish();
			} else {
				Toast.makeText(this, "用户名或者密码有误!", 1000).show();
				editName.setText("");
				editPassword.setText("");
			}
			break;

		case R.id.regrister:
			Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivityForResult(intentRegister, 1);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			finish();
		}
	}
}
