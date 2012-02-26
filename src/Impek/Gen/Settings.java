package Impek.Gen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;

import android.content.Context;
import android.util.Log;

import org.json.*;

public class Settings implements Iterable<SettingsNode> {

	/**
	 * [ 
	 * 	 { 
	 * 		"name":"SomeLocation",
	 * 		"latitude":"SomeLatitude", 
	 * 		"longitude":"SomeLongitude",
	 * 		"arrival":"SomeArrivalDateTime" 
	 * 	},
	 *  .. 
	 * ]
	 */

	private final String FILE_NAME = "settings.xml";
	
	public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private PriorityQueue<SettingsNode> queue;
	private Context context;

	public Settings(Context context) {
		this.context = context;
		queue = new PriorityQueue<SettingsNode>(2048,
				new SettingsNode.SettingsNodeComparator());
		loadSettingsFile();
	}
	
	private void loadSettingsFile() {
		queue.clear();
		File f = context.getFileStreamPath(FILE_NAME);
		if(f.exists()) {
			try {
				FileInputStream fis = context.openFileInput(FILE_NAME);
				byte[] b = new byte[(int) f.length()];
				fis.read(b);
				fis.close();
				JSONArray js = new JSONArray(String.valueOf(b));
				for(int i = 0;i<js.length();i++) {
					SettingsNode element = createSettingsNode(js.getJSONObject(i));
					if(checkDateCondition(element)) 
						queue.add(element);
				}
			} catch (Exception e) {
				Log.e(Settings.class.getName(),e.getMessage());
			}
		}
	}
	
	private SettingsNode createSettingsNode(JSONObject jsobj) throws JSONException, ParseException {
		String name = jsobj.getString("name");
		double latitude = jsobj.getDouble("latitude");
		double longitude = jsobj.getDouble("longitude");
		Date date = dateFormat.parse(jsobj.getString("arrival"));
		return new SettingsNode(name, latitude,longitude,date);
	}
	
	public boolean checkDateCondition(SettingsNode elem) {
		return new Date().after(elem.getDate());
	}
	
	public boolean pushSettingsNode(String name, double latitude, double longitude, Date date) {
		SettingsNode set = new SettingsNode(name, latitude,longitude,date);
		if(!checkDateCondition(set)) 
			return false;
		queue.add(set);
		return true;		
	}
	
	public SettingsNode getFront() {
		return queue.peek();
	}
	
	public void popFront() {
		queue.remove();
	}

	public Iterator<SettingsNode> iterator() {
		return queue.iterator();
	}
	
	public void updateFile() {
		try {
			FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			OutputStreamWriter bos = new OutputStreamWriter(fos);
			bos.write("[\n");
			boolean ok = false;
			Iterator<SettingsNode> it = iterator();
			while(it.hasNext()) {
				if(ok)
					bos.write(",\n");
				bos.write(it.next().toString());
				ok = true;
			}
			bos.write("]");
		} catch (Exception e) {
			Log.e(Settings.class.getName(),e.getMessage());
		}
	}
}
