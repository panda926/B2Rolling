package org.B2Rolling;

import org.cocos2d.nodes.CCDirector;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * program`s setting model
 */
public class GameSetting{
	
	public static final String PREFS_NAME = "B2RollingSetting";
	public static SharedPreferences	m_settings;

	public static boolean isContain( String strKey ){
		return m_settings.contains(strKey);
	}
	
	public static int getIntValue( String strKey, int nDefValue ){
		return m_settings.getInt(strKey, nDefValue);
	}
	
	public static boolean getBooleanValue( String strKey, boolean bDefValue ){
		return m_settings.getBoolean(strKey, bDefValue);
	}
	
	public static float getFloatValue( String strKey, float ftDefValue ){
		return m_settings.getFloat(strKey, ftDefValue);
	}
	
	public static long getLongValue( String strKey, long lDefValue ){
		return m_settings.getLong(strKey, lDefValue);
	}
	
	public static String getStringValue( String strKey, String strDefValue ){
		return m_settings.getString(strKey, strDefValue);
	}
	
	public static void putValue( String strKey, int nValue ){
		SharedPreferences.Editor edit = m_settings.edit();
		edit.putInt(strKey, nValue);
		edit.commit();
	}
	
	public static void putValue( String strKey, boolean bValue ){
		SharedPreferences.Editor edit = m_settings.edit();
		edit.putBoolean(strKey, bValue);
		edit.commit();
	}
	
	public static void putValue( String strKey, float ftValue ){
		SharedPreferences.Editor edit = m_settings.edit();
		edit.putFloat(strKey, ftValue);
		edit.commit();
	}
	
	public static void putValue( String strKey, long lValue ){
		SharedPreferences.Editor edit = m_settings.edit();
		edit.putLong(strKey, lValue);
		edit.commit();
	}
	
	public static void putValue( String strKey, String strValue ){
		SharedPreferences.Editor edit = m_settings.edit();
		edit.putString(strKey, strValue);
		edit.commit();
	}
	
	public static void remove( String strKey ){
		SharedPreferences.Editor edit = m_settings.edit();
		edit.remove(strKey);
		edit.commit();
	}
	
	public static void removeAll(){
		m_settings.getAll().clear();
	}
	
/*	public static Map<String, Object> m_settingMap = new ConcurrentHashMap<String, Object> ();

	public static boolean isContain( String strKey ){
		return m_settingMap.containsKey(strKey);
	}
	
	public static int getIntValue( String strKey, int nDefValue ){
		if( !isContain(strKey) )	return nDefValue;
		return (Integer) m_settingMap.get(strKey);
	}
	
	public static boolean getBooleanValue( String strKey, boolean bDefValue ){
		if( !isContain(strKey) )	return bDefValue;
		return (Boolean) m_settingMap.get(strKey);
	}
	
	public static float getFloatValue( String strKey, float ftDefValue ){
		if( !isContain(strKey) )	return ftDefValue;
		return (Float) m_settingMap.get(strKey);
	}
	
	public static long getLongValue( String strKey, long lDefValue ){
		if( !isContain(strKey) )	return lDefValue;
		return (Long) m_settingMap.get(strKey);
	}
	
	public static String getStringValue( String strKey, String strDefValue ){
		if( !isContain(strKey) )	return strDefValue;
		return (String) m_settingMap.get(strKey);
	}
	
	public static void putValue( String strKey, int nValue ){
		m_settingMap.put(strKey, nValue);
	}
	
	public static void putValue( String strKey, boolean bValue ){
		m_settingMap.put(strKey, bValue);
	}
	
	public static void putValue( String strKey, float ftValue ){
		m_settingMap.put(strKey, ftValue);
	}
	
	public static void putValue( String strKey, long lValue ){
		m_settingMap.put(strKey, lValue);
	}
	
	public static void putValue( String strKey, String strValue ){
		m_settingMap.put(strKey, strValue);
	}
*/

    /** initialize setting */
    public static void initialize()
    {    	
    	m_settings = CCDirector.sharedDirector().getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    	removeAll();		
    	if( !isContain("FIRST_INSTALL") ){
			putValue("FIRST_INSTALL", true);
			
			// Game Info
			putValue("Max Level Num", 0);	
			
			// settings
			putValue("BGM_VOLUME", 0.5f);
			putValue("EFFECT_VOLUME", 0.5f);		
    	}
	}
}
