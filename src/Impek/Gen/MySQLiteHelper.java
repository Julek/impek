package Impek.Gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_EVENTS = "events";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "comment";
	public static final String COLUMN_TIME = "time";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_DUR = "duration";
	public static final String COLUMN_LAT = "lattitude";
	public static final String COLUMN_LONG = "longlitude";
	public static final String COLUMN_ALIAS = "locationalias";
	public static final String COLUMN_GROUP = "groupid";
	
	
	// used to construcToolst a seperate groups table 
	// for pulling out profiles , uri , settings et c
	public static final String TABLE_GROUP = "groups";
	public static final String GROUP_ID = "_id";
	public static final String GROUP_DESC = "describtion";
	public static final String GROUP_MODE = "mode"; // shared , private
	public static final String GROUP_PROFILE = "profile"; // contains a link to the profile
	
	
	private static final String DATABASE_NAME = "events.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	// Using standard int   time (with 
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_EVENTS + "( " + COLUMN_ID+ " integer primary key autoincrement, "
			+ COLUMN_COMMENT+ " text not null, " 
			+ COLUMN_TIME + " integer not null, " 
			+ COLUMN_DATE + " date not null, " 
			+ COLUMN_DUR + " integer not null, "
			+ COLUMN_LAT + " real not null, "
			+ COLUMN_LONG + " real not null, "
			+ COLUMN_ALIAS + " text not null, "
			+ COLUMN_GROUP + " integer not null"
			+");";
	

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
		onCreate(db);
	}

}
