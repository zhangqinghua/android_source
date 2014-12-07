package com.hua.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hua.DAO.DatabaseDAO;
import com.hua.loginview_sqlite.R;
import com.hua.model.User;

public class MainActivity extends Activity {

	private TextView userInfoName, userInfoSignature = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("TAG", "--->MainActivity onCreate!");

		String userName = getIntent().getStringExtra("userName");

		DatabaseDAO dao = new DatabaseDAO(this);
		
		User user = dao.dataQuery(userName);

		userInfoName = (TextView) findViewById(R.id.user_info_name);
		userInfoSignature = (TextView) findViewById(R.id.user_info_signature);

		userInfoName.setText(user.getName());
		userInfoSignature.setText(user.getSignature());
	}
}
