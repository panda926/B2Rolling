package org.B2Rolling;

import org.cocos2d.nodes.CCDirector;

import android.content.Context;
import android.content.SharedPreferences;

public class G {

	public static PhysicalTypeMgr g_physicalTypeMgr = null;
	public static TemplateDefMgr g_tmplateDefMgr = null;
	public static ZombieInfoMgr g_zombieInfoMgr = null;
	public static LevelMgr g_levelMgr = null;
	public static SoundListMgr g_soundListMgr = null;
	
	public static int g_nTotalScore = 0;
	public static boolean g_fMusic = true;
	public static boolean g_fSound = true;
	public static int g_nRealLevelNum = 1;
	public static int g_nZombieNum = -1;
	
	public static float scale_x = 1.0f, scale_y = 1.0f;
	public static float width = 0.0f, height = 0.0f;
	public static int g_nCompleteLevelNum = 0;;
	
	public static boolean bTT = false;
	public static int nT = -1;
	//public static float yyy = 0;
	
	public static float getX( float x ) {
		if( width == 1024.0f && height == 768.0f )
			return x * 2 + 32;
		return x * scale_x;
	}
	
	public static float getY( float y ) {
		if( width == 1024.0f && height == 768.0f )
			return y * 2 + 64;
		return y * scale_y;
	}
	
	public static float getX1( float v ) {
		if( width == 1024.0f && height == 768.0f )
			return v * 2;
		return v * scale_x;
	}
	
	public static float getY1( float v ) {
		if( width == 1024.0f && height == 768.0f )
			return v * 2;
		return v * scale_y;
	}
	
	public static float get( float f ) {
		if( width == 1024.0f && height == 768.0f )
			return f * 2;
		return f;
	}
	
	public static String getBackImgName( String str ) {
		return String.format("%s.png", str);
	}
	
	public static void loadScoreInfoXML()
	{
//		SharedPreferences sp = CCDirector.sharedDirector().getActivity().getSharedPreferences("B2Rolling_Info", Context.MODE_PRIVATE);
//		g_nCompleteLevelNum = sp.getInt("Max Level Num", 0);
////		g_nCompleteLevelNum  = SettingsManager.sharedSettingsManager().getInt("Max Level Num");
		g_nCompleteLevelNum = GameSetting.getIntValue("Max Level Num", 0);
		
	    g_levelMgr.m_levelInfo.clear();    
		LevelScore saveInfo;
	    
	    if (g_nCompleteLevelNum == 0)
	    {
	        saveInfo = new LevelScore();
	        g_levelMgr.m_levelInfo.add(saveInfo);
//	        saveInfo release;
	        
	        g_nCompleteLevelNum = 1;
	    }
	    else 
	    {
	        for(int i = 0; i < g_nCompleteLevelNum; i ++)
	        {
	            saveInfo = new LevelScore();
	            
	            String scoreTagName = String.format("Level Score%d", i);
	            int score = GameSetting.getIntValue(scoreTagName, 0); 
	            
	            saveInfo.nScore = score;
	            g_levelMgr.m_levelInfo.add(saveInfo);
//	            saveInfo release;
	        }
	    }	
	}

	public static void saveScoreInfoXML()
	{
//		SharedPreferences sp = CCDirector.sharedDirector().getActivity().getSharedPreferences("B2Rolling_Info", Context.MODE_PRIVATE);
//		SharedPreferences.Editor et = sp.edit();
//	    GameSetting.putValue("FIRST_INSTALL", 1);
	
	    g_nCompleteLevelNum = g_levelMgr.m_levelInfo.size();
	    
	    GameSetting.putValue("Max Level Num", g_nCompleteLevelNum);
//	    SettingsManager.sharedSettingsManager().setValue("Max Level Num", g_nCompleteLevelNum);
	    
	    LevelScore saveInfo;
	    for (int i = 0; i < g_nCompleteLevelNum; i ++)
	    {
	        saveInfo = g_levelMgr.m_levelInfo.get(i);
	        
	        String scoreTagName = String.format("Level Score%d", i);
	        GameSetting.putValue(scoreTagName, saveInfo.nScore);
//	        SettingsManager.sharedSettingsManager().setValue(scoreTagName, saveInfo.nScore);
	    }
	}
}
