package com.hua.uilts;

import android.content.Context;
import android.util.Log;

import com.hua.DAO.DatabaseDAO;
import com.hua.model.User;

public class CheckUnit {
	Context context = null;
	DatabaseDAO dao = null;;

	public CheckUnit(Context context) {
		dao = new DatabaseDAO(context);
	}

	// �����û��������Ƿ���ȷ
	public boolean checkUserLogin(User user) {
		User u = dao.dataQuery(user.getName());
		if (u != null) {
			if (u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

	// ����Ƿ���ڴ��û���
	public boolean checkUserName(User user) {
		User u = dao.dataQuery(user.getName());
		Log.i("TAG", String.valueOf(u != null));
		if (u != null) {
			Log.i("TAG", "true");
			return true;
		}
		return false;
	}
}
