package Impek.Gen;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EventsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_COMMENT ,
			MySQLiteHelper.COLUMN_TIME,
			MySQLiteHelper.COLUMN_DATE,
			MySQLiteHelper.COLUMN_DUR,
			MySQLiteHelper.COLUMN_LAT,
			MySQLiteHelper.COLUMN_LONG,
			MySQLiteHelper.COLUMN_ALIAS,
			MySQLiteHelper.COLUMN_GROUP
			};
			

	public EventsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Event createEvent(String event, int time,String date,int duration,double longitude , double lat , String alias,int groupId) {
	    	ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COMMENT, event);
		values.put(MySQLiteHelper.COLUMN_TIME, time);
		values.put(MySQLiteHelper.COLUMN_DATE,date);
		values.put(MySQLiteHelper.COLUMN_DUR, duration);
		values.put(MySQLiteHelper.COLUMN_LAT, lat);
		values.put(MySQLiteHelper.COLUMN_LONG, longitude);
		values.put(MySQLiteHelper.COLUMN_ALIAS, alias);
		values.put(MySQLiteHelper.COLUMN_GROUP, groupId);
		
		
		long insertId = database.insert(MySQLiteHelper.TABLE_EVENTS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_EVENTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		
		cursor.moveToFirst();
		Event newComment = cursorToEvent(cursor);
		cursor.close();
		return newComment;
	}

	public void deleteEvent(Event event) {
		long id = event.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_EVENTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<Event>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_EVENTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Event event = cursorToEvent(cursor);
			events.add(event);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return events;
	}

	private Event cursorToEvent(Cursor cursor) {
		Event event = new Event();
		event.setId(cursor.getLong(0));
		event.setComment(cursor.getString(1));
		event.setTime(cursor.getInt(2));
		event.setDate(cursor.getString(3));
		event.setDuration(cursor.getInt(4));
		event.setLat(cursor.getDouble(5));
		event.setLongitude(cursor.getDouble(6));
		event.setAlias(cursor.getString(7));
		event.setGroupid(cursor.getInt(cursor.getInt(8)));
		return event;
	}
}

		