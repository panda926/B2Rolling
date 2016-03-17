package org.B2Rolling;

import java.util.ArrayList;

import org.cocos2d.nodes.CCNode;

public class SoundListMgr extends CCNode {
	
	public ArrayList<ZombieSoundList> m_zombieSoundArray;
	public ArrayList<SoundList> m_soundListArray;

	private SoundListMgr() {
		m_zombieSoundArray = new ArrayList<ZombieSoundList>();
		m_soundListArray = new ArrayList<SoundList>();
		
		makeSoundList();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		m_zombieSoundArray.removeAll(m_zombieSoundArray);
		m_soundListArray.removeAll(m_soundListArray);
		
		super.onExit();
	}
	
	private void makeSoundList() {
		makeZombieSoundList();
		makeObjCollisionSoundList();
	}

	private void makeZombieSoundList()
	{
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_LARGE_TYPE3, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_LARGE_TYPE4, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE3, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE4, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND11, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND12);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND11, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND12);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_SHORT_TYPE3, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	    setZombieSoundListInfo(GameConfig.ZombieType.ZOMBIE_SHORT_TYPE4, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND1, GameConfig.EffectSoundType.ZOMBIE_HURT_SOUND2);
	}
	
	private void makeObjCollisionSoundList() {
	    setSoundListInfo(GameConfig.PhysicalType.STEEL_TYPE, GameConfig.PhysicalType.STEEL_TYPE, GameConfig.EffectSoundType.BALL_IRON_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.BALL_IRON_COLLISION_LIGHTLY_SOUND);    
	    setSoundListInfo(GameConfig.PhysicalType.STEELBALL_TYPE, GameConfig.PhysicalType.STEEL_TYPE, GameConfig.EffectSoundType.BALL_IRON_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.BALL_IRON_COLLISION_LIGHTLY_SOUND);
	    
	    setSoundListInfo(GameConfig.PhysicalType.STEELBALL_TYPE, GameConfig.PhysicalType.STEELBALL_TYPE, GameConfig.EffectSoundType.BALL_IRON_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.BALL_IRON_COLLISION_LIGHTLY_SOUND);
	    
	    setSoundListInfo(GameConfig.PhysicalType.STEELBALL_TYPE, GameConfig.PhysicalType.STONE_TYPE, GameConfig.EffectSoundType.BALL_STONE_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.BALL_STONE_COLLISION_HEAVILY_SOUND);
	    
	    setSoundListInfo(GameConfig.PhysicalType.STEELBALL_TYPE, GameConfig.PhysicalType.WOOD_TYPE, GameConfig.EffectSoundType.BALL_WOOD_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.BALL_WOOD_COLLISION_HEAVILY_SOUND);
	    
	    setSoundListInfo(GameConfig.PhysicalType.STEELBALL_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, GameConfig.EffectSoundType.ZOMBIE_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.ZOMBIE_COLLISION_HEAVILY_SOUND);

	    setSoundListInfo(GameConfig.PhysicalType.WOOD_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, GameConfig.EffectSoundType.ZOMBIE_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.ZOMBIE_COLLISION_HEAVILY_SOUND);    
	    setSoundListInfo(GameConfig.PhysicalType.WOOD_TYPE, GameConfig.PhysicalType.STEEL_TYPE, GameConfig.EffectSoundType.WOOD_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.WOOD_COLLISION_HEAVILY_SOUND);    
	    setSoundListInfo(GameConfig.PhysicalType.WOOD_TYPE, GameConfig.PhysicalType.STONE_TYPE, GameConfig.EffectSoundType.WOOD_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.WOOD_COLLISION_HEAVILY_SOUND);
	    
	    setSoundListInfo(GameConfig.PhysicalType.STATIC_WOOD_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, GameConfig.EffectSoundType.ZOMBIE_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.ZOMBIE_COLLISION_HEAVILY_SOUND);
	    setSoundListInfo(GameConfig.PhysicalType.STATIC_WOOD_TYPE, GameConfig.PhysicalType.STEEL_TYPE, GameConfig.EffectSoundType.WOOD_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.WOOD_COLLISION_HEAVILY_SOUND);
	    setSoundListInfo(GameConfig.PhysicalType.STATIC_WOOD_TYPE, GameConfig.PhysicalType.STONE_TYPE, GameConfig.EffectSoundType.WOOD_COLLISION_LIGHTLY_SOUND, GameConfig.EffectSoundType.WOOD_COLLISION_HEAVILY_SOUND);
	}

	private void setZombieSoundListInfo(int nzombietype, int lightsoundID, int heavysoundID)
	{
	    ZombieSoundList soundInfo = new ZombieSoundList();
	    
	    soundInfo.setZombieSoundListInfo(nzombietype, lightsoundID, heavysoundID);    
	    m_zombieSoundArray.add(soundInfo);
	}

	private void setSoundListInfo(int nType1, int nType2, int lightsoundID, int heavySoundID)
	{
	    SoundList soundInfo = new SoundList();
	    
	    soundInfo.setSoundListInfo(nType1, nType2, lightsoundID, heavySoundID);
	    m_soundListArray.add(soundInfo);
	}
	
	public static void createSoundListMgr() {
		G.g_soundListMgr = new SoundListMgr();
	}
}
