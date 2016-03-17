/**
 * 
 */
package org.B2Rolling;

import org.B2Rolling.GameConfig.BackSoundType;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGSize;

/**
 * Stringauthor Administrator
 *
 */
public class MenuScene extends CCLayer {

	private CCSprite backImg;
	private CCSprite titleImg;
	private CCMenuItemImage btnPlay;
	//private CCMenuItemImage btnScore;

	/**
	 * 
	 */
	public MenuScene() {
		// TODO Auto-generated constructor stub
		
			this.isTouchEnabled_ = true;
			load();
			SoundManager.sharedSoundManager().playBackgroundMusic(BackSoundType.MUSIC_MAIN_SOUND.ordinal());
		
	}
	
	private void load() {
	    CGSize winSize = CCDirector.sharedDirector().winSize();
	    
	    String str = "scene-main.png";
	    String str1;
	    
	    backImg = CCSprite.sprite(str);
	    backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
	    backImg.setScaleX(G.scale_x);
	    backImg.setScaleY(G.scale_y);
	    addChild(backImg, 0);
	    
	    str = "scene-main-title.png";
	    
	    titleImg = CCSprite.sprite(str);
	    titleImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
	    titleImg.setScaleX(G.scale_x);
	    titleImg.setScaleY(G.scale_y);
	    addChild(titleImg, 0);
	    
	    str = "button-play-normal.png";
	    str1 = "button-play-press.png"; 
	    
	    btnPlay = CCMenuItemImage.item(str, str1, this, "onPlay");
	    btnPlay.setPosition(G.getX(230), G.getY(45));
	    btnPlay.setScaleX(G.scale_x);
	    btnPlay.setScaleY(G.scale_y);
	    str = "button-clear1-normal.png";
	    str1 = "button-clear1-press.png";
	    
//	    btnScore = CCMenuItemImage.item(str, str1, this, "onScore");
//	    btnScore.setPosition(G.getX(360), G.getY(30));
//	    btnScore.setScaleX(G.scale_x);
//	    btnScore.setScaleY(G.scale_y);    
	    CCMenu menu = CCMenu.menu(btnPlay);
	    
	    menu.setPosition(0, 0);
	   
	    addChild(menu, 1);
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
	    removeSpriteUsedTexture(backImg);
	    removeSpriteUsedTexture(titleImg);
	    
	    removeItemUsedTexture(btnPlay);
	    //removeItemUsedTexture(btnScore);

	    super.onExit();
	}

	private void removeSpriteUsedTexture(CCSprite sprite)
	{
	    CCTexture2D tex;
	    
	    tex = sprite.getTexture();
	    CCTextureCache.sharedTextureCache().removeTexture(tex);
	    sprite.removeFromParentAndCleanup(true);
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
	
	public void onPlay() {
		CCScene scene = CCScene.node();
		scene.addChild(new LevelSelectScene());
		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5f, scene));
	}
	
	public void onScore() {
//		CCScene scene = CCScene.node();
//		scene.addChild(new LevelScore());
//		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5f, scene));

	}
}
