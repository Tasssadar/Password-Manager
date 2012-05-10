package com.frca.passmgr.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLiteHelper extends SQLiteOpenHelper
{
	public static final String TABLE_VARIABLES = "variables";
	public static final String VARIABLES_KEY = "key";
	public static final String VARIABLES_VALUE = "value";

	private static final String DATABASE_NAME = "savedData";
	private static final int DATABASE_VERSION = 1;

	// Database creation SQL statement
	private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_VARIABLES + "( "
			+ VARIABLES_KEY + " text primary key, "
			+ VARIABLES_VALUE + " text not null);";

	public SqlLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
		//onCreate(db);
	}

}
