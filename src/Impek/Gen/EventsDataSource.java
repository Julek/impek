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
				allColumns, null, null, null, null, MySQLiteHelper.COLUMN_DATE);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Event event = cursorToEvent(cursor);
			events.add(event);
			Log.e("added","the event");
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

	public List<Event> runsearch(String query) {
	    // TODO Auto-generated method stub
	    
	    /*
	     * code for search in progress .. will do laterzz 
	     */
	    return getAllEvents();
	}

	
	
	
	
	/*
	 * Start of code for search (snippet from tutorial .. )
	 * 
	 */
	
	// String selection = KEY_WORD + " MATCH ?";
	       // String[] selectionArgs = new String[] {query+"*"};

	      //  return query(selection, selectionArgs, columns);

	        /* This builds a query that looks like:
	         *     SELECT <columns> FROM <table> WHERE <KEY_WORD> MATCH 'query*'
	         * which is an FTS3 search for the query text (plus a wildcard) inside the word column.
	         *
	         * - "rowid" is the unique id for all rows but we need this value for the "_id" column in
	         *    order for the Adapters to work, so the columns need to make "_id" an alias for "rowid"
	         * - "rowid" also needs to be used by the SUGGEST_COLUMN_INTENT_DATA alias in order
	         *   for suggestions to carry the proper intent data.
	         *   These aliases are defined in the DictionaryProvider when queries are made.
	         * - This can be revised to also search the definition text with FTS3 by changing
	         *   the selection clause to use FTS_VIRTUAL_TABLE instead of KEY_WORD (to search across
	         *   the entire table, but sorting the relevance could be difficult.
	         */
	    }

	    /**
	     * Performs a database query.
	     * @param selection The selection clause
	     * @param selectionArgs Selection arguments for "?" components in the selection
	     * @param columns The columns to return
	     * @return A Cursor over all rows matching the query
	     */
//	    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
//	        /* The SQLiteBuilder provides a map for all possible columns requested to
//	         * actual columns in the database, creating a simple column alias mechanism
//	         * by which the ContentProvider does not need to know the real column names
//	         */
//	        //SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
//	        builder.setTables(FTS_VIRTUAL_TABLE);
//	        builder.setProjectionMap(mColumnMap);
//
//	        Cursor cursor = builder.query(mDatabaseOpenHelper.getReadableDatabase(),
//	                columns, selection, selectionArgs, null, null, null);
//
//	        if (cursor == null) {
//	            return null;
//	        } else if (!cursor.moveToFirst()) {
//	            cursor.close();
//	            return null;
//	        }
//	        return cursor;
//	    }
//
//	    return null;
//	}


		