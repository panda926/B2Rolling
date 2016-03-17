package org.B2Rolling;

import org.B2Rolling.GameConfig.BackSoundType;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemToggle;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class LevelSelectScene extends CCLayer {
	
	private final float FISRT_LOAD_BTN_X = 40.0f;
	private final float FIRST_LOAD_BTN_Y = 280.0f;
	
	private final float BTN_DIFF_X = 45.0f;
	private final float BTN_DIFF_Y = 75.0f;
	
	private final float FIRST_LOAD_LABEL_Y = 240.0f;
	private final float FIRST_LOAD_SCORE_Y = 262.0f;
	
	public static final int MAX_LEVEL = 30;

	private CCSprite backImg;
	private CCLabel[] levelLabel = new CCLabel[30];
	private CCLabel[] levelScoreLabel = new CCLabel[30];
	private CCMenuItemImage[] m_btnLevel = new CCMenuItemImage[30];
	private CCMenuItemImage btnSoundOn;
	private CCMenuItemImage btnSoundOff;
	private CCMenuItemImage btnMusicOff;
	private CCMenuItemImage btnMusicOn;
	private CCMenuItemImage btnClear;
	private CCMenuItemImage btnReturn;
	private CCLabel totalScore;

	public LevelSelectScene() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {
//        G.loadScoreInfoXML();
        
        String str = "scene-main.png";
        String str1;
        
        SoundManager.sharedSoundManager().playBackgroundMusic(BackSoundType.MUSIC_MAIN_SOUND.ordinal());
        
        CGSize winSize = CCDirector.sharedDirector().winSize();
        
        backImg = CCSprite.sprite(str);
        backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
        backImg.setScaleX(G.scale_x);
        backImg.setScaleY(G.scale_y);
        addChild(backImg, 0);
        
        int i;
        CGPoint pt;
        
        CCLabel[] levelLabel = new CCLabel[MAX_LEVEL];
        
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_LABEL_Y));
        
        for (i = 0; i < 10; i ++)
        {
            str = String.format("%d", i + 1);
            levelLabel [i] = CCLabel.makeLabel(str, CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 18);
            levelLabel[i].setColor(new ccColor3B(255, 255, 255));
            levelLabel[i].setScaleX(G.scale_x); levelLabel[i].setScaleY(G.scale_y);
            levelLabel[i].setPosition(pt);
            addChild(levelLabel[i]);         
            
            pt.x += G.getX1(BTN_DIFF_X);
        }
        
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_LABEL_Y) - G.getY1(BTN_DIFF_Y));
        for (i = 10; i < 20; i ++)
        {
            str = String.format("%d", i + 1);
            levelLabel[i] = CCLabel.makeLabel(str, CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 18);
            levelLabel[i].setColor(new ccColor3B(255, 255, 255));
            levelLabel[i].setScaleX(G.scale_x); levelLabel[i].setScaleY(G.scale_y);
            levelLabel[i].setPosition(pt);
            addChild(levelLabel[i]);         
            
            pt.x += G.getX1(BTN_DIFF_X);
        }
        
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_LABEL_Y) - G.getY1(BTN_DIFF_Y) * 2);
        for (i = 20; i < MAX_LEVEL; i ++)
        {
            str = String.format("%d", i + 1);
            levelLabel[i] = CCLabel.makeLabel(str, CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 18);
            levelLabel[i].setColor(new ccColor3B(255, 255, 255));
            levelLabel[i].setScaleX(G.scale_x); levelLabel[i].setScaleY(G.scale_y);
            levelLabel[i].setPosition(pt);
            addChild(levelLabel[i]);         
            
            pt.x += G.getX1(BTN_DIFF_X);
        }
/////
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_SCORE_Y));
        
        for (i = 0; i < 10; i ++)
        {
            str = String.format("%d", i + 1);
            levelScoreLabel[i] = CCLabel.makeLabel(str, CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 12);
            levelScoreLabel[i].setColor(new ccColor3B(255, 255, 255));
            levelScoreLabel[i].setScaleX(G.scale_x); levelScoreLabel[i].setScaleY(G.scale_y);
            levelScoreLabel[i].setPosition(pt);
            addChild(levelScoreLabel[i], 3);         
            
            pt.x += G.getX1(BTN_DIFF_X);
            levelScoreLabel[i].setVisible(false);
        }
        
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_SCORE_Y) - G.getY1(BTN_DIFF_Y));
        for (i = 10; i < 20; i ++)
        {
            str = String.format("%d", i + 1);
            levelScoreLabel[i] = CCLabel.makeLabel(str, CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 12);
            levelScoreLabel[i].setColor(new ccColor3B(255, 255, 255));
            levelScoreLabel[i].setScaleX(G.scale_x); levelScoreLabel[i].setScaleY(G.scale_y);
            levelScoreLabel[i].setPosition(pt);
            addChild(levelScoreLabel[i], 3);         
            
            pt.x += G.getX1(BTN_DIFF_X);
            levelScoreLabel[i].setVisible(false);
        }
        
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_SCORE_Y) - G.getY1(BTN_DIFF_Y) * 2);
        for (i = 20; i < MAX_LEVEL; i ++)
        {
            str = String.format("%d", i + 1);
            levelScoreLabel[i] = CCLabel.makeLabel(str, CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 12);
            levelScoreLabel[i].setColor(new ccColor3B(255, 255, 255));
            levelScoreLabel[i].setScaleX(G.scale_x); levelScoreLabel[i].setScaleY(G.scale_y);
            levelScoreLabel[i].setPosition(pt);
            addChild(levelScoreLabel[i], 3);         
            
            pt.x += G.getX1(BTN_DIFF_X);
            levelScoreLabel[i].setVisible(false);
        }
///
        str = "level-enable.png";
        str1 = "level-disable.png";
        
        for (i = 0; i < MAX_LEVEL; i ++)
        {
            if (i < G.g_nCompleteLevelNum)
                m_btnLevel [i] = CCMenuItemImage.item(str, str, this, String.format("onLevel%02d", i+1));
            else 
                m_btnLevel[i] = CCMenuItemImage.item(str1, str1, this, String.format("onLevel%02d", i+1));            
        }
        
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_BTN_Y));
        for (i = 0; i < 10; i ++)
        {
            m_btnLevel[i].setPosition(pt);            
            pt.x += G.getX1(BTN_DIFF_X);
        }
        
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_BTN_Y) - G.getY1(BTN_DIFF_Y));
        for (i = 10; i < 20; i ++)
        {
            m_btnLevel[i].setPosition(pt);            
            pt.x += G.getX1(BTN_DIFF_X);
        }
        
        pt = CGPoint.make(G.getX(FISRT_LOAD_BTN_X), G.getY(FIRST_LOAD_BTN_Y) - G.getY1(BTN_DIFF_Y) * 2);
        for (i = 20; i < MAX_LEVEL; i ++)
        {
            m_btnLevel[i].setPosition(pt);            
            pt.x += G.getX1(BTN_DIFF_X);
        }
        
        str = "bt_menu_01.png";
        str1 = "bt_menu_02.png";
        
        btnReturn = CCMenuItemImage.item(str
                                          , str1, this, "onBack");
        btnReturn.setPosition(G.getX(125), G.getY(15));
        btnReturn.setScaleX(G.scale_x);
        btnReturn.setScaleY(G.scale_y);
        
        str = "bt_reset_01.png";
        str1 = "bt_reset_02.png";
        
        btnClear = CCMenuItemImage.item(str
                                          , str1, this, "onClear");
        btnClear.setPosition(G.getX(50), G.getY(15));
        btnClear.setScaleX(G.scale_x);
        btnClear.setScaleY(G.scale_y);
        str = "bt_music_01.png";
        str1 = "bt_music_02.png";
        
        btnMusicOn = CCMenuItemImage.item(str
                                          , str1);
        
        str = "bt_music_03.png";
        str1 = "bt_music_04.png";
        
        btnMusicOff = CCMenuItemImage.item(str
                                           , str1);
        
        CCMenuItemToggle btnMusic;
        
        if (G.g_fMusic)
            btnMusic = CCMenuItemToggle.item(this, "onMusic", btnMusicOn, btnMusicOff);
        else 
            btnMusic = CCMenuItemToggle.item(this, "onMusic", btnMusicOff, btnMusicOn);
        
        btnMusic.setPosition(G.getX(420), G.getY(15));
        btnMusic.setScaleX(G.scale_x);
        btnMusic.setScaleY(G.scale_y);
        
        str = "bt_sound_01.png";
        str1 = "bt_sound_02.png";
        
        btnSoundOn = CCMenuItemImage.item(str
                                           , str1);
        
        str = "bt_sound_03.png";
        str1 = "bt_sound_04.png";
        
        btnSoundOff = CCMenuItemImage.item(str
                                            , str1);
        
        CCMenuItemToggle btnSound;
        
        if (G.g_fSound)
            btnSound = CCMenuItemToggle.item(this, "onSound", btnSoundOn, btnSoundOff);
        else 
            btnSound = CCMenuItemToggle.item(this, "onSound", btnSoundOff, btnSoundOn);
        
        btnSound.setPosition(G.getX(450), G.getY(15));
        btnSound.setScaleX(G.scale_x);
        btnSound.setScaleY(G.scale_y);
        CCMenu menu = CCMenu.menu(m_btnLevel[0], m_btnLevel[1], m_btnLevel[2], m_btnLevel[3],m_btnLevel[4],m_btnLevel[5],m_btnLevel[6],m_btnLevel[7],m_btnLevel[8],m_btnLevel[9],m_btnLevel[10],
        		m_btnLevel[11],m_btnLevel[12],m_btnLevel[13],m_btnLevel[14],m_btnLevel[15],m_btnLevel[16],m_btnLevel[17],m_btnLevel[18],m_btnLevel[19], 
        		m_btnLevel[20],m_btnLevel[21],m_btnLevel[22],m_btnLevel[23],m_btnLevel[24],m_btnLevel[25],m_btnLevel[26],m_btnLevel[27],m_btnLevel[28],m_btnLevel[29],btnReturn, btnClear, btnMusic, btnSound);
        
        menu.setPosition(0,0);
        addChild(menu, 2);
        
        str = String.format("Total Score %d", G.g_nTotalScore);
        totalScore = CCLabel.makeLabel(str, CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 18);
        totalScore.setColor(new ccColor3B(255, 255, 255));
        totalScore.setScaleX(G.scale_x); totalScore.setScaleY(G.scale_y);
        totalScore.setPosition(G.getX(240), G.getY(35));
        totalScore.setScaleX(G.scale_x);
        totalScore.setScaleY(G.scale_y);
        addChild(totalScore);
        
        dispScoreLabels();
	}

	public void onLevel01() {
		if(G.g_nCompleteLevelNum < 1)
		return;
		
		G.g_nRealLevelNum = 1;
		goGameScene();		
	}

	public void onLevel02() {
		if(G.g_nCompleteLevelNum < 2)
			return;
		G.g_nRealLevelNum = 2;
		goGameScene();
	}

	public void onLevel03() {
		if(G.g_nCompleteLevelNum < 3)
			return;
		G.g_nRealLevelNum = 3;
		goGameScene();
	}

	public void onLevel04() {
		if(G.g_nCompleteLevelNum < 4)
			return;
		G.g_nRealLevelNum = 4;
		goGameScene();
	}

	public void onLevel05() {
		if(G.g_nCompleteLevelNum < 5)
			return;
		G.g_nRealLevelNum = 5;
		goGameScene();
	}

	public void onLevel06() {
		if(G.g_nCompleteLevelNum < 6)
			return;
		G.g_nRealLevelNum = 6;
		goGameScene();
	}

	public void onLevel07() {
		if(G.g_nCompleteLevelNum < 7)
			return;
		G.g_nRealLevelNum = 7;
		goGameScene();
	}

	public void onLevel08() {
		if(G.g_nCompleteLevelNum < 8)
			return;
		G.g_nRealLevelNum = 8;
		goGameScene();
	}

	public void onLevel09() {
		if(G.g_nCompleteLevelNum < 9)
			return;
		G.g_nRealLevelNum = 9;
		goGameScene();
	}

	public void onLevel10() {
		if(G.g_nCompleteLevelNum < 10)
			return;
		G.g_nRealLevelNum = 10;
		goGameScene();
	}

	public void onLevel11() {
		if(G.g_nCompleteLevelNum < 11)
			return;
		G.g_nRealLevelNum = 11;
		goGameScene();
	}

	public void onLevel12() {
		if(G.g_nCompleteLevelNum < 12)
			return;
		G.g_nRealLevelNum = 12;
		goGameScene();
	}

	public void onLevel13() {
		if(G.g_nCompleteLevelNum < 13)
			return;
		G.g_nRealLevelNum = 13;
		goGameScene();
	}

	public void onLevel14() {
		if(G.g_nCompleteLevelNum < 14)
			return;
		G.g_nRealLevelNum = 14;
		goGameScene();
	}

	public void onLevel15() {
		if(G.g_nCompleteLevelNum < 15)
			return;
		G.g_nRealLevelNum = 15;
		goGameScene();
	}

	public void onLevel16() {
		if(G.g_nCompleteLevelNum < 16)
			return;
		G.g_nRealLevelNum = 16;
		goGameScene();
	}

	public void onLevel17() {
		if(G.g_nCompleteLevelNum < 17)
			return;
		G.g_nRealLevelNum = 17;
		goGameScene();
	}

	public void onLevel18() {
		if(G.g_nCompleteLevelNum < 18)
			return;
		G.g_nRealLevelNum = 18;
		goGameScene();
	}

	public void onLevel19() {
		if(G.g_nCompleteLevelNum < 19)
			return;
		G.g_nRealLevelNum = 19;
		goGameScene();
	}

	public void onLevel20() {
		if(G.g_nCompleteLevelNum < 20)
			return;
		G.g_nRealLevelNum = 20;
		goGameScene();
	}

	public void onLevel21() {
		if(G.g_nCompleteLevelNum < 21)
			return;
		G.g_nRealLevelNum = 21;
		goGameScene();
	}

	public void onLevel22() {
		if(G.g_nCompleteLevelNum < 22)
			return;
		G.g_nRealLevelNum = 22;
		goGameScene();
	}

	public void onLevel23() {
		if(G.g_nCompleteLevelNum < 23)
			return;
		G.g_nRealLevelNum = 23;
		goGameScene();
	}

	public void onLevel24() {
		if(G.g_nCompleteLevelNum < 24)
			return;
		G.g_nRealLevelNum = 24;
		goGameScene();
	}

	public void onLevel25() {
		if(G.g_nCompleteLevelNum < 25)
			return;
		G.g_nRealLevelNum = 25;
		goGameScene();
	}

	public void onLevel26() {
		if(G.g_nCompleteLevelNum < 26)
			return;
		G.g_nRealLevelNum = 26;
		goGameScene();
	}

	public void onLevel27() {
		if(G.g_nCompleteLevelNum < 27)
			return;
		G.g_nRealLevelNum = 27;
		goGameScene();
	}

	public void onLevel28() {
		if(G.g_nCompleteLevelNum < 28)
			return;
		G.g_nRealLevelNum = 28;
		goGameScene();
	}

	public void onLevel29() {
		if(G.g_nCompleteLevelNum < 29)
			return;
		G.g_nRealLevelNum = 29;
		goGameScene();
	}

	public void onLevel30() {
		if(G.g_nCompleteLevelNum < 30)
			return;
		G.g_nRealLevelNum = 30;
		goGameScene();
	}

	private void goGameScene() {
		CCScene scene = CCScene.node();
		scene.addChild(new GameScene());
		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5f, scene));
	}
	
	public void onBack() {
		CCScene scene = CCScene.node();
		scene.addChild(new MenuScene());
		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5f, scene));
	}

	private void dispScoreLabels()
	{
	    String str;
	    LevelScore score;
	    
	    int i;
	    
	    for (i = 0; i < MAX_LEVEL; i ++)
	    {
	        levelScoreLabel[i].setVisible(false);
	    }
	    
	    for (i = 0; i < G.g_levelMgr.m_levelInfo.size(); i ++)
	    {
	        score = G.g_levelMgr.m_levelInfo.get(i);
	        
	        levelScoreLabel[i].setVisible(true);
	        str = String.format("%d", score.getScore());
	        levelScoreLabel[i].setString(str);
	    }
	    
	    G.g_levelMgr.calcTotalScore();
	    str = String.format("Total Score %d", G.g_nTotalScore);
	    totalScore.setString(str);
	}

	private void resetLevelImgs()
	{
	    String str, str1;
	    
	    int i;
	    
	    CCSprite sprite1, sprite2;
	    
	    CCTexture2D texture1, texture2;
	    
	    for (i = 0; i < MAX_LEVEL; i ++)
	    {
	        str = "level-enable.png";
	        str1 = "level-disable.png";
	        
	        sprite1 = (CCSprite)m_btnLevel[i].getNormalImage();        
	        sprite2 = (CCSprite)m_btnLevel[i].getSelectedImage();
	        
	        if (i < G.g_nCompleteLevelNum)
	        {
		        texture1 = CCTextureCache.sharedTextureCache().addImage(str);
		        texture2 = CCTextureCache.sharedTextureCache().addImage(str);
		        
	        }
	        else 
	        {
		        texture1 = CCTextureCache.sharedTextureCache().addImage(str1);
		        texture2 = CCTextureCache.sharedTextureCache().addImage(str1);
	        }
	        
	        sprite1.setTexture(texture1);
	        sprite2.setTexture(texture2);
	    }
	}
	
	public void onClear() {
	//	showAlertView();
		G.g_nRealLevelNum = 1;
		G.g_nCompleteLevelNum = 1;
		G.g_levelMgr.m_levelInfo.clear();
		LevelScore score = new LevelScore();
		G.g_levelMgr.m_levelInfo.add(score);
		this.resetLevelImgs();
		this.dispScoreLabels();
		G.saveScoreInfoXML();
	}
	
	private void showAlertView() {
		final AlertDialog.Builder alert = new AlertDialog.Builder(CCDirector.sharedDirector().getActivity());
		alert.setTitle("Rolling Fall")
        .setMessage("Do you want reset levels?")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                /* User clicked OK so do some stuff */
            }
        })
        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                /* User clicked Cancel so do some stuff */
            }
        })
		.setCancelable(false);
		alert.show();
	}
	
	public void onSound() {
		G.g_fSound = !G.g_fSound;
		SoundManager.sharedSoundManager().setEffectMute(G.g_fSound);
//	    [[SoundManager sharedSoundManager] setEffectMute:g_fSound];
	}
	
	public void onMusic() {
		G.g_fMusic = !G.g_fMusic;
		SoundManager.sharedSoundManager().setBackgroundMusicMute(G.g_fMusic);
//	    [[SoundManager sharedSoundManager] setBackgroundMusicMute:g_fMusic];
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
//	    removeSpriteUsedTexture(backImg);
	    
//	    for (int i = 0; i < MAX_LEVEL; i ++)
//	        removeItemUsedTexture(m_btnLevel[i]);
//	    
//	    removeItemUsedTexture(btnReturn);
//	    removeItemUsedTexture(btnClear);
//	    
//	    removeItemUsedTexture(btnMusicOn);
//	    removeItemUsedTexture(btnMusicOff);
//	    
//	    removeItemUsedTexture(btnSoundOn);
//	    removeItemUsedTexture(btnSoundOff);

	    super.onExit();
	}

	private void removeSpriteUsedTexture(CCSprite sprite)
	{
//	    CCTexture2D tex;
//	    
//	    tex = sprite.getTexture();
//	    CCTextureCache.sharedTextureCache().removeTexture(tex);
//	    removeFromParentAndCleanup(true);
	}

	private void removeItemUsedTexture(CCMenuItemImage item)
	{
	    CCTexture2D tex;
	    CCSprite sprite;
	    
	    sprite = (CCSprite)item.getNormalImage();
	    tex = sprite.getTexture();
	    CCTextureCache.sharedTextureCache().removeTexture(tex);
	    sprite.removeFromParentAndCleanup(true);
	    
	    sprite = (CCSprite)item.getSelectedImage();
	    tex = sprite.getTexture();
	    CCTextureCache.sharedTextureCache().removeTexture(tex);
	    sprite.removeFromParentAndCleanup(true);
	}
}
