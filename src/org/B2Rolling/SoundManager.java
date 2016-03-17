package org.B2Rolling;

import org.B2Rolling.GameConfig.BackSoundType;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;


	
public class SoundManager extends Object
{
	public static int[] nEffectFiles =
	{
		R.raw.ball_iron_collision_heavily,
		R.raw.ball_iron_collision_lightly,
	    
		R.raw.ball_reward,
	    
	    R.raw.ball_stone_collision_heavily,
	    R.raw.ball_stone_collision_lightly,
	    
	    R.raw.ball_wood_collision_heavily,
	    R.raw.ball_wood_collision_lightly,
	    
	    R.raw.button_click,
	    R.raw.chain_cut,
	    R.raw.new_highscore,
	    R.raw.sound_button_press,
	    R.raw.sound_cut_iron_chain,
	    R.raw.sound_smash_ball_zombie,
	    
	    R.raw.star_appear,
	    
	    R.raw.wood_collision_heavily,
	    R.raw.wood_collision_lightly,
	    
	    R.raw.zombie_collision_heavily,
	    R.raw.zombie_collision_lightly,
	    
	    R.raw.zombie_hurt_1,
	    R.raw.zombie_hurt_2,
	    R.raw.zombie_hurt_3,
	    R.raw.zombie_hurt_4,
	    R.raw.zombie_hurt_5,
	    R.raw.zombie_hurt_6,
	    R.raw.zombie_hurt_7,
	    R.raw.zombie_hurt_11,
	    R.raw.zombie_hurt_12,
	    R.raw.zombie_hurt_13,
	    R.raw.zombie_hurt_14,
	    R.raw.zombie_hurt_15,
	};

	public static int[] nSoundFiles =
	{
		R.raw.level_clear,
		R.raw.level_failed,
		R.raw.music_main,
	    R.raw.music_start,
	};
	static SoundEngine soundEngine;
	
	public static boolean mbBackgroundMute;
	public static boolean mbEffectMute;
	public static int mBackSoundId;

	public static SoundManager _sharedSound = null;
	public static SoundManager sharedSoundManager() {
		if (_sharedSound == null) {
			_sharedSound = new SoundManager();
		}
		
		return _sharedSound;
	}
	
	public SoundManager(){
		super();
		soundEngine = SoundEngine.sharedEngine();
		soundEngine.setSoundVolume(1.0f);
		soundEngine.setEffectsVolume(1.0f);
		mBackSoundId = -1;
		mbBackgroundMute = false;
		mbEffectMute = false;
	}
	
	void loadEffectData(int effectId){
		soundEngine.preloadEffect(CCDirector.sharedDirector().getActivity(), nEffectFiles[effectId]);
	
	}
	void loadSoundData(int soundId){
		soundEngine.preloadSound(CCDirector.sharedDirector().getActivity(), nSoundFiles[soundId]);
	
	}
	public void releaseData(){
		soundEngine.realesAllSounds();
	}
	
	public static void playEffect(int effectId){
		if (effectId < 0 || effectId >= 32)
			return;
		if (mbEffectMute)
			return;
		if (effectId == -1)
			return;
//		loadEffectData(effectId);
		soundEngine.playEffect(CCDirector.sharedDirector().getActivity(), nEffectFiles[effectId]);

	}
	 
	public static void playBackgroundMusic(int soundId){
		if (soundId < 0 || soundId >= 4)
			return;
		if (mBackSoundId != soundId)
			mBackSoundId = soundId;
		else
			return;
		if (mbBackgroundMute)
			return;
//		loadSoundData(soundId);
		if(soundId == BackSoundType.LEVEL_FAILED_SOUND.ordinal())
			soundEngine.playSound(CCDirector.sharedDirector().getActivity(), nSoundFiles[soundId], false);
		else
			soundEngine.playSound(CCDirector.sharedDirector().getActivity(), nSoundFiles[soundId], true);
		
	}
	
	public void playRandomBackground(){
		int soundId = (int)Math.random()%4;
		playBackgroundMusic(soundId);
	}
	public void stopBackgroundMusic(){
		soundEngine.realesAllSounds();
	}

	public void pauseBackgroundMusic(){
		soundEngine.pauseSound();
	}
	
	public void resumeBackgroundMusic(){
		soundEngine.resumeSound();
	}

	public static void setBackgroundMusicMute(boolean bMute){
		mbBackgroundMute = !bMute;
		if(!mbBackgroundMute)
			soundEngine.playSound(CCDirector.sharedDirector().getActivity(), nSoundFiles[mBackSoundId], true);
		else
			soundEngine.pauseSound();
		
	}

	public static void setEffectMute(boolean bMute){
		mbEffectMute = !bMute;
	}

	public boolean getBackgroundMusicMuted(){
		return mbBackgroundMute;
	}

	public boolean getEffectMuted(){
		return mbEffectMute;
	}

	public  float backgroundVolume(){
		return soundEngine.getSoundsVolume();
	}

	public void setBackgroundVolume(float fVolume){
		soundEngine.setSoundVolume(fVolume);
	}

	public  void setEffectVolume(float fVolume){
		soundEngine.setEffectsVolume(fVolume);
	}

	public float effectVolume(){
		return soundEngine.getEffectsVolume();
	}
}

