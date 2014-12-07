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

import com.hua.DAO.DatabaseDAO;
import com.hua.loginview_sqlite.R;
import com.hua.model.User;
import com.hua.uilts.CheckUnit;

public class RegisterActivity extends Activity implements OnClickListener {

	private CheckUnit checkUnit = null;
	private DatabaseDAO dao = null;

	private EditText name, signature, password, password2 = null;
	private Button ok, cancel = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		dao = new DatabaseDAO(this);
		checkUnit = new CheckUnit(this);

		initData();
	}

	public void initData() {
		name = (EditText) findViewById(R.id.register_username);
		password = (EditText) findViewById(R.id.register_userpassword);
		password2 = (EditText) findViewById(R.id.register_userpassword2);
		signature = (EditText) findViewById(R.id.register_signature);

		ok = (Button) findViewById(R.id.ok);
		cancel = (Button) findViewById(R.id.cancel);

		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ok:
			User user = new User();
			user.setName(name.getText().toString());
			if (!checkUnit.checkUserName(user)) {
				if (password.getText().toString().equals(password2.getText().toString())) {
					user.setPassword(password.getText().toString());
					user.setSignature(signature.getText().toString());
					dao.dataAdd(user);
					setResult(RESULT_OK);

					Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

					intent.putExtra("userName", user.getName());
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(this, "两次密码输入有误!", 1000).show();
				}
			} else {
				Toast.makeText(this, "已有此用户名!", 1000).show();
			}
			name.setText("");
			password.setText("");
			password2.setText("");
			signature.setText("");
			break;
		case R.id.cancel:
			finish();
			break;
		}
	}
}
