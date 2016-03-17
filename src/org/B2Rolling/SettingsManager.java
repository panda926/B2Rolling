package org.B2Rolling;

import java.util.HashMap;

public class SettingsManager {

	private static SettingsManager _setMgr = null;
	private HashMap<String, String> settings;
	
	public static SettingsManager sharedSettingsManager() {
		if( _setMgr == null )
			_setMgr = new SettingsManager();
		return _setMgr;
	}

	private SettingsManager() {
		// TODO Auto-generated constructor stub
		settings = new HashMap<String, String>();
	}

	public String getString( String value )
	{	
		return settings.get(value);
	}

	public int getInt( String value ) 
	{
		return Integer.parseInt(settings.get(value));
	}

	public void setValue(String value, String aValue)
	{
		settings.put(value, aValue);
	}

	public void setValue(String value, int aValue) 
	{
		String temp = String.format("%d", aValue);
		settings.put(value, temp);
	}
}
