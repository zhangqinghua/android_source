package com.hua.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hua.model.DatabaseInfo;
import com.hua.model.User;
import com.hua.uilts.DatabaseHelper;

public class DatabaseDAO {

	SQLiteOpenHelper helper = null;

	SQLiteDatabase db = null;

	ContentValues values = null;

	public DatabaseDAO(Context context) {
		helper = new DatabaseHelper(context);
		db = helper.getWritableDatabase();
	}

	// ��ѯ����
	public User dataQuery(String name) {
		try {
			User user = new User();
			// Ҫ��ѯ���ֶ�
			String[] cols = { DatabaseInfo.USER_NAME, DatabaseInfo.USER_PASSWORD, DatabaseInfo.USER_SIGNATURE };
			String[] names = { name };
			// ����ѯ����
			// ��cursor���ܷ��ؽ��
			Log.i("TAG", DatabaseInfo.USER_NAME + "");
			Cursor cur = db.query(DatabaseInfo.TABLE_NAME, cols, DatabaseInfo.USER_NAME + "= ?", names, null, null, null);
			while (cur.moveToNext()) {
				user.setName(cur.getString(cur.getColumnIndex(DatabaseInfo.USER_NAME)));
				user.setPassword(cur.getString(cur.getColumnIndex(DatabaseInfo.USER_PASSWORD)));
				user.setSignature(cur.getString(cur.getColumnIndex(DatabaseInfo.USER_SIGNATURE)));
				Log.i("TAG", "dataQuery:" + "��ѯ�ɹ�!");
				return user;
			}
			return null;
		} catch (Exception e) {
			Log.i("TAG", "dataQuery:" + "��ѯʧ��!");
		}
		return null;
	}

	// �������
	public void dataAdd(User user) {
		try {
			values = new ContentValues();
			values.put(DatabaseInfo.USER_NAME, user.getName());
			values.put(DatabaseInfo.USER_PASSWORD, user.getPassword());
			values.put(DatabaseInfo.USER_SIGNATURE, user.getSignature());

			long row = db.insert(DatabaseInfo.TABLE_NAME, null, values);
			if (row > 0) {
				Log.i("TAG", "������ӳɹ�!");
			} else {
				Log.i("TAG", "�������ʧ��!");
			}
		} catch (Exception e) {
			Log.i("TAG", "�������ʧ��!");
		}
	}

	// �޸�����
	public void dataUpdata() {

	}

	// ɾ������
	public void dataDelete() {

	}

	// ɾ����
	public void tableDelete() {

	}

	// �½���
	public void tableAdd() {

	}

}
