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

	// 查询数据
	public User dataQuery(String name) {
		try {
			User user = new User();
			// 要查询的字段
			String[] cols = { DatabaseInfo.USER_NAME, DatabaseInfo.USER_PASSWORD, DatabaseInfo.USER_SIGNATURE };
			String[] names = { name };
			// 将查询条件
			// 用cursor接受返回结果
			Log.i("TAG", DatabaseInfo.USER_NAME + "");
			Cursor cur = db.query(DatabaseInfo.TABLE_NAME, cols, DatabaseInfo.USER_NAME + "= ?", names, null, null, null);
			while (cur.moveToNext()) {
				user.setName(cur.getString(cur.getColumnIndex(DatabaseInfo.USER_NAME)));
				user.setPassword(cur.getString(cur.getColumnIndex(DatabaseInfo.USER_PASSWORD)));
				user.setSignature(cur.getString(cur.getColumnIndex(DatabaseInfo.USER_SIGNATURE)));
				Log.i("TAG", "dataQuery:" + "查询成功!");
				return user;
			}
			return null;
		} catch (Exception e) {
			Log.i("TAG", "dataQuery:" + "查询失败!");
		}
		return null;
	}

	// 添加数据
	public void dataAdd(User user) {
		try {
			values = new ContentValues();
			values.put(DatabaseInfo.USER_NAME, user.getName());
			values.put(DatabaseInfo.USER_PASSWORD, user.getPassword());
			values.put(DatabaseInfo.USER_SIGNATURE, user.getSignature());

			long row = db.insert(DatabaseInfo.TABLE_NAME, null, values);
			if (row > 0) {
				Log.i("TAG", "数据添加成功!");
			} else {
				Log.i("TAG", "数据添加失败!");
			}
		} catch (Exception e) {
			Log.i("TAG", "数据添加失败!");
		}
	}

	// 修改数据
	public void dataUpdata() {

	}

	// 删除数据
	public void dataDelete() {

	}

	// 删除表
	public void tableDelete() {

	}

	// 新建表
	public void tableAdd() {

	}

}
