/**
 * 
 */
package org.B2Rolling;

import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Iterator;

import javax.microedition.khronos.opengles.GL10;

import org.B2Rolling.GameConfig.BackSoundType;
import org.B2Rolling.GameConfig.EffectSoundType;
import org.cocos2d.actions.UpdateCallback;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.config.ccMacros;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemToggle;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import android.util.Log;
import android.view.MotionEvent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;

/**
 * @author Administrator
 *
 */
public class GameScene extends CCLayer {

	public World mWorld;
	private CGSize m_winSize;
	private MyListener m_pContactListener;
	private CCMenuItemImage btnMenu;
	private CCMenuItemImage btnReplay;
	private CCMenuItemImage btnSelectLevel;
	private CCMenuItemImage btnMusicOn;
	private CCMenuItemImage btnMusicOff;
	private CCMenuItemImage btnSoundOn;
	private CCMenuItemImage btnSoundOff;
	private CCMenuItemImage btnNextlevel;
	private CCMenuItemImage btnBackReset;
	private CCLabel levelLabel;
	private CCLabel scoreLabel;
	private CCSprite aniSprite;
	private CCLabel aniLabel1;
	private CCLabel aniLabel2;
	private CCLabel aniLabel3;
	private ArrayList<VRope> m_ropeArray;
	private ArrayList<CCSprite> m_spriteArray;
	private ArrayList<CCSpriteSheet> m_ropeBatchArray;
	private boolean m_fGameOver;
	private boolean m_fGameDone;
	private int m_nScore;
	private float m_nMomentX;
	private float m_nMomentY;
	private CCSprite backImg;
	
	public static GameScene mGameScene;

	/**
	 * 
	 */
	public GameScene() {
		// TODO Auto-generated constructor stub
		this.isTouchEnabled_ = true;
        mGameScene = this;
        G.nT = -1;
        m_winSize = CCDirector.sharedDirector().winSize();
     
        Vector2 gravity = new Vector2(0, -10);
        mWorld = new World(gravity, true);
        
        m_pContactListener = new MyListener();
        m_pContactListener.setWorld(mWorld, this);
        mWorld.setContactListener((ContactListener)m_pContactListener);
        
        mWorld.setContinuousPhysics(true);
        
        //debugdraw
        
        
        
        
        m_ropeArray = new ArrayList<VRope>();        
        m_spriteArray = new ArrayList<CCSprite>(); 
        m_ropeBatchArray = new ArrayList<CCSpriteSheet>();
        
        String str,str1;
        
        str = "bt_menu_01.png";
        str1 = "bt_menu_02.png";
        
        btnMenu = CCMenuItemImage.item(str,
        		str1, this,"onMenu" );
        btnMenu.setPosition(G.getX(125), G.getY(15));
        btnMenu.setScaleX(G.scale_x);
        btnMenu.setScaleY(G.scale_y);
        str = "bt_reset_01.png";
        str1 = "bt_reset_02.png";
        
        btnReplay = CCMenuItemImage.item(str,
                                           str1, this,"onReplay");
        btnReplay.setPosition(G.getX(50), G.getY(15));
        btnReplay.setScaleX(G.scale_x);
        btnReplay.setScaleY(G.scale_y);
        str = "bt_selectlevel_01.png";
        str1 = "bt_selectlevel_02.png";
        
        btnSelectLevel = CCMenuItemImage.item(str,
        		str1, this,"onSelect");
        btnSelectLevel.setPosition(G.getX(240), G.getY(15));
        btnSelectLevel.setScaleX(G.scale_x);
        btnSelectLevel.setScaleY(G.scale_y);
        str = "bt_music_01.png";
        str1 = "bt_music_02.png";
        
        btnMusicOn = CCMenuItemImage.item(str,	str1);
        
        str = "bt_music_03.png";
        str1 = "bt_music_04.png";
        
        btnMusicOff = CCMenuItemImage.item(str,
        		str1);
        
        CCMenuItemToggle btnMusic;
        
        if (G.g_fMusic)
            btnMusic = CCMenuItemToggle.item(this, "onMusic" ,btnMusicOn, btnMusicOff);
        else 
            btnMusic = CCMenuItemToggle.item(this,"onMusic", btnMusicOff, btnMusicOn);
        
        btnMusic.setPosition (G.getX(420), G.getY(15));
        btnMusic.setScaleX(G.scale_x);
        btnMusic.setScaleY(G.scale_y);
        str = "bt_sound_01.png";
        str1 = "bt_sound_02.png";
        
        btnSoundOn = CCMenuItemImage.item(str,
        		str1);
        
        str = "bt_sound_03.png";
        str1 = "bt_sound_04.png";
        
        btnSoundOff = CCMenuItemImage.item(str,
        		str1);
        
        CCMenuItemToggle btnSound;
        
        if (G.g_fSound)
            btnSound = CCMenuItemToggle.item(this,"onSound", btnSoundOn, btnSoundOff,null);
        else 
            btnSound = CCMenuItemToggle.item(this, "onSound",btnSoundOff, btnSoundOn,null);
        
        btnSound.setPosition(G.getX(450), G.getY(15));
        btnSound.setScaleX(G.scale_x);
        btnSound.setScaleY(G.scale_y);
        str = "bt_nextlevel_01.png";
        str1 = "bt_nextlevel_02.png";
        
        btnNextlevel = CCMenuItemImage.item(str,
        		str1,this,"onNextLevel");
        btnNextlevel.setVisible(false);
        
        str = "bt_reset_01.png";
        str1 = "bt_reset_02.png";
        
        btnBackReset = CCMenuItemImage.item(str,
        		str1, this,"onBackReset");
        btnBackReset.setVisible(false);
        
        CCMenu menu = CCMenu.menu(btnReplay, btnMenu,btnSelectLevel,btnMusic,btnSound,btnNextlevel, btnBackReset);
        
        menu.setPosition(0, 0);
        this.addChild(menu ,8);
        
        levelLabel = CCLabel.makeLabel(str ,CGSize.make(480,50),TextAlignment.CENTER,"Marker Felt", 18);
        levelLabel.setColor(new ccColor3B(255, 255, 255));
        levelLabel.setScaleX(G.scale_x); levelLabel.setScaleY(G.scale_y);
        levelLabel.setPosition(CGPoint.make(G.getX(50), G.getY(290)));        
        
        this.addChild(levelLabel, 6);
        
        scoreLabel = CCLabel.makeLabel(str, CGSize.make(480,50),TextAlignment.CENTER,"Marker Felt", 18);
        scoreLabel.setColor( new  ccColor3B(255, 255, 255));
        scoreLabel.setScaleX(G.scale_x); scoreLabel.setScaleY(G.scale_y);
        scoreLabel.setPosition(CGPoint.make(G.getX(150), G.getY(290)));        
        
        this.addChild(scoreLabel, 6);
        
        str = "level_back.png";
        aniSprite = CCSprite.sprite(str);
        addChild(aniSprite, 7);
        aniSprite.setVisible(false);
        
        aniLabel1 = CCLabel.makeLabel(str ,CGSize.make(480,50),TextAlignment.CENTER,"Marker Felt" ,26);
        aniLabel1.setColor(new ccColor3B(255, 255, 255));
        aniLabel1.setScaleX (G.scale_x); aniLabel1.setScaleY (G.scale_y);
        this.addChild(aniLabel1, 8);
        aniLabel1.setVisible(false);        
        
        aniLabel2 = CCLabel.makeLabel(str ,CGSize.make(480,50),TextAlignment.CENTER, "Marker Felt", 20);
        aniLabel2.setColor (new ccColor3B(255, 255, 255));
        aniLabel2.setScaleX(G.scale_x); aniLabel2.setScaleY(G.scale_y);
        this.addChild(aniLabel2, 8);
        aniLabel2.setVisible(false);        
        
        aniLabel3 = CCLabel.makeLabel(str ,CGSize.make(480,50),TextAlignment.CENTER ,"Marker Felt" ,20);
        aniLabel3.setColor (new ccColor3B(255, 255, 255));
        aniLabel3.setScaleX(G.scale_x); aniLabel3.setScaleY (G.scale_y);
        this.addChild(aniLabel3 ,8);
        aniLabel3.setVisible(false);        
        
        this.initGame();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
	 //   releaseGame();
	 //   removeRopes();	    
		G.nT = -1;
		G.bTT = false;
	    m_pContactListener = null;	
	    mWorld = null;	
//	    m_debugDraw = null;
	    
	    removeItemUsedTexture(btnMenu);
	    removeItemUsedTexture(btnReplay);
	    removeSpriteUsedTexture(backImg);
	    
	    removeItemUsedTexture(btnMusicOn);
	    removeItemUsedTexture(btnMusicOff);
	    
	    removeItemUsedTexture(btnSoundOn);
	    removeItemUsedTexture(btnSoundOff);
	    
	    removeItemUsedTexture(btnSelectLevel);
	    
	    removeItemUsedTexture(btnNextlevel);
	    removeItemUsedTexture(btnBackReset);
	    
		super.onExit();
	}

	private void initGame()
	{  
	    m_fGameOver = false;
	    m_fGameDone = false;
	    
	    loadLevel();
	}

	private void releaseGame()
	{
//	    aniSprite.setVisible(false);
//	    btnNextlevel.setVisible(false);
//	    btnBackReset.setVisible(false);
//	    aniLabel1.setVisible(false);
//	    aniLabel2.setVisible(false);
//	    aniLabel3.setVisible(false);    
//	    
//	    unscheduleAllSelectors();
//	    
//	    m_fGameDone = false;
//	    m_fGameOver = false;
//	    
//	    removeRopes();
//	   	    
//	    Iterator <Body> allBodys = mWorld.getBodies();
//	    while (allBodys.hasNext() )
//	    {
//	        Body b = allBodys.next();
//	        
//	        SpriteInfo myActor = (SpriteInfo)b.getUserData();
//	        
//	        if (myActor != null)
//	        {
//	            this.removeChild(myActor.sprite ,true);
//	            
//	            if (b!= null)
//	            	mWorld.destroyBody(b);
//	            b = null;
//	            myActor.removeSelf();  
//	            myActor = null;
//	        }
//	    }
//
//	    for (int i = 0; i < m_spriteArray.size(); i ++)
//	        removeChild(m_spriteArray.get(i), true);
	    
		CCScene scene = CCScene.node();
		scene.addChild(new GameScene());
		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5f, scene));

	}

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
//		world->DrawDebugData();
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
	    if (m_ropeArray.size() != 0) 
	    {
	        for(int i = 0;i < m_ropeArray.size();i ++)
	            (m_ropeArray.get(i)).updateSprites();
	    }
		super.draw(gl);
	}

	public void update(float dt) 
	{
	    int velocityIterations = 16;//8
		int positionIterations = 2;//1
		
		// Instruct the world to perform a single step of simulation. It is
		// generally best to keep the time step and iterations fixed.
		if(G.bTT == true)
		{
			Iterator <Body> allBodys = mWorld.getBodies();
		    
		    while( allBodys.hasNext() )
		    {
		    	Body b = allBodys.next();
		        if (b.getUserData() != null)
		        {
		            SpriteInfo myActor = (SpriteInfo )b.getUserData();
		            
		            if ( myActor.nTagName == G.nT)
		    	    {
		            	
		    	        myActor.fDynamic = true;
		    	        b.setType(BodyType.DynamicBody);
		    	        //this.setZombieDynamic(G.nT);
		    	           	
		    	    }
		            
		        }
		    }
		    G.bTT = false;
		    
		}
		dt = dt * 1.5f;
		mWorld.step(dt, velocityIterations, positionIterations);
	    
	    Iterator <Body> allBodys = mWorld.getBodies();
	    
	    while( allBodys.hasNext() )
	    {
	    	Body b = allBodys.next();
	        if (b.getUserData() != null)
	        {
	        	if ( G.bTT == false )
	        	{
	        		SpriteInfo myActor = (SpriteInfo )b.getUserData();
		            CCSprite sprite = myActor.sprite;
		            sprite.setPosition (b.getPosition().x * VRope.PTM_RATIO,
		                                    b.getPosition().y * VRope.PTM_RATIO);
		            sprite.setRotation (-1 * ccMacros.CC_RADIANS_TO_DEGREES(b.getAngle()));
		            
	        	}
	            
	        }
	    }
	    
	    for (int i = 0; i < m_ropeArray.size(); i++)
	    {
	        VRope rope = m_ropeArray.get(i);
	
	        rope.update(dt);
	//        [rope debugDraw);
	    }
	    
//	    AppDelegate* del = (AppDelegate*)[UIApplication sharedApplication].delegate;
	    
	    if (!m_fGameDone)
	    {
	        if (m_fGameOver)
	            return;
	        
	        if (isValidGameDone())
	        {
	            dispGameDone();
//	            [del submitScore:g_nTotalScore];
	        }
	    }

	    if (!m_fGameOver)
	    {
	        if (m_fGameDone)
	            return;
	        
	        if (isValidGameOver())
	        {
	            dispGameOver();
	            
	            G.g_levelMgr.calcTotalScore();
	            
	            //	            [del submitScore:g_nTotalScore];	            
	           SoundManager.sharedSoundManager().playBackgroundMusic(BackSoundType.LEVEL_FAILED_SOUND.ordinal());
	        }
	    }   
	    //this.update(0.01f);
	}
	
	private void dispGameDone()
	{
	    if (m_fGameDone)
	        return;
	    
	    unschedule("onScore");
	    
	    String str;
	    
	    m_fGameDone = true;
	    
	    if (G.g_nRealLevelNum == LevelSelectScene.MAX_LEVEL)
	        str = String.format("GAME SUCCESS");
	    else
	        str = String.format("LEVEL COMPLETED");        
	    
	    int n1, n2, n3, n4, n5;
	    
	    n1 = 160;
	    n2 = 200;
	    n3 = 160;
	    n4 = 130;
	    n5 = 110;
	    
	    int nDiffY = 320;
	    int nx = 240;
	    
	    aniSprite.setVisible(true);
	    
	    btnNextlevel.setVisible(true);
	    
	    aniLabel1.setVisible(true);
	    aniLabel2.setVisible(true);
	    aniLabel3.setVisible(true);
	    
	    aniSprite.setPosition(G.getX(nx), G.getY(n1 + nDiffY));
	    aniSprite.setScaleX(G.scale_x);
	    aniSprite.setScaleY(G.scale_y);
	    aniLabel1.setPosition(G.getX(nx), G.getY(n2 + nDiffY));
	    aniLabel1.setString(str);
	    aniLabel1.setScaleX(G.scale_x);
	    aniLabel1.setScaleY(G.scale_y);
	    aniLabel2.setPosition(G.getX(nx), G.getY(n3 + nDiffY));
	    str = String.format("LEVEL SCORE:   %d", m_nScore);    
	    aniLabel2.setString(str);
	    aniLabel2.setScaleX(G.scale_x);
	    aniLabel2.setScaleY(G.scale_y);
	    LevelScore score = G.g_levelMgr.m_levelInfo.get(G.g_nRealLevelNum - 1);
	    score.setScore(m_nScore);
	    
	    if (G.g_nRealLevelNum >= LevelSelectScene.MAX_LEVEL)
	    {
	        
	    }
	    else 
	    {
	        if (G.g_nRealLevelNum == G.g_levelMgr.m_levelInfo.size())
	        {
	            score = new LevelScore();
	            G.g_levelMgr.m_levelInfo.add(score);
//	            [score release];
	        }
	    }
	    
	    G.g_levelMgr.calcTotalScore();
	    
	    aniLabel3.setPosition(G.getX(nx), G.getY(n4 + nDiffY));
	    str = String.format("TOTAL SCORE:   %d", G.g_nTotalScore);
	    aniLabel3.setString(str);
	    aniLabel3.setScaleX(G.scale_x);
	    aniLabel3.setScaleY(G.scale_y);
	    
	    btnNextlevel.setVisible(true);
	    btnNextlevel.setPosition(G.getX(nx), G.getY(n5 + nDiffY));
	    btnNextlevel.setScaleX(G.scale_x);
	    btnNextlevel.setScaleY(G.scale_y);
	    CCIntervalAction actionMove1, actionMove2, actionMove3, actionMove4, actionMove5;
	    
	    float rTime = 0.3f;
	    
	    actionMove1 = CCMoveTo.action(rTime , CGPoint.make(G.getX(nx), G.getY(n1)));
	    actionMove2 = CCMoveTo.action(rTime  ,CGPoint.make(G.getX(nx), G.getY(n2)));
	    actionMove3 = CCMoveTo.action(rTime  ,CGPoint.make(G.getX(nx), G.getY(n3)));
	    actionMove4 = CCMoveTo.action(rTime  ,CGPoint.make(G.getX(nx), G.getY(n4)));
	    actionMove5 = CCMoveTo.action(rTime  ,CGPoint.make(G.getX(nx), G.getY(n5)));
	    
	    aniSprite.runAction(CCSequence.actions(actionMove1));
	    
	    aniLabel1.runAction(CCSequence.actions(actionMove2));
	    aniLabel2.runAction(CCSequence.actions(actionMove3));
	    aniLabel3.runAction(CCSequence.actions(actionMove4));
	    
	    btnNextlevel.runAction(CCSequence.actions(actionMove5));    
	    
	    G.saveScoreInfoXML();
	}

	private void dispGameOver()
	{
	    if (m_fGameOver)
	        return;
	    
	    String str;
	    
	    m_fGameOver = true;
	    
	    str = "LEVEL FAIL";
	    
	    int n1, n2, n3, n4, n5;
	    
	    n1 = 160;
	    n2 = 200;
	    n3 = 160;
	    n4 = 130;
	    n5 = 110;
	    
	    int nDiffY = 320;
	    int nx = 240;
	    
	    aniSprite.setVisible(true);    
	    btnBackReset.setVisible(true);    
	    aniLabel1.setVisible(true);
	    
	    aniSprite.setPosition(G.getX(nx), G.getY(n1 + nDiffY));
	    aniSprite.setScaleX(G.scale_x);
	    aniSprite.setScaleY(G.scale_y);
	    aniLabel1.setPosition(G.getX(nx), G.getY(n2 + nDiffY));
	    aniLabel1.setString(str);
	    aniLabel1.setScaleX(G.scale_x);
	    aniLabel1.setScaleY(G.scale_y);
	    btnBackReset.setVisible(true);
	    btnBackReset.setPosition(G.getX(nx), G.getY(n5 + nDiffY));
	    btnBackReset.setScaleX(G.scale_x);
	    btnBackReset.setScaleY(G.scale_y);
	    CCIntervalAction actionMove1, actionMove2, actionMove5;
	    
	    float rTime = 0.3f;
	    
	    actionMove1 = CCMoveTo.action(rTime , CGPoint.make(G.getX(nx), G.getY(n1)));
	    actionMove2 = CCMoveTo.action(rTime  ,CGPoint.make(G.getX(nx), G.getY(n2)));
	    actionMove5 = CCMoveTo.action(rTime  ,CGPoint.make(G.getX(nx), G.getY(n5)));
	    
	    aniSprite.runAction(CCSequence.actions(actionMove1));
	    aniLabel1.runAction(CCSequence.actions(actionMove2));
	    
	    btnBackReset.runAction(CCSequence.actions(actionMove5));
	}

	public void onScore( float dt )
	{
	    m_nScore --;
	    
	    if (m_nScore < 10)
	        m_nScore = 10;
	    
	    scoreLabel.setString(String.format("SCORE  %d", m_nScore));
	}

	private void addNormalSprite(SpriteInfo info)
	{
	    CCSprite sprite = info.sprite;
	    sprite.setRotation (info.rRotate);
	    sprite.setScaleX(G.scale_x);
	    sprite.setScaleY(G.scale_y);
	    
	    this.addChild(sprite ,3);
	    
	    if (info.nTagName >= GameConfig.GameSpriteTag.BALL1 && info.nTagName <= GameConfig.GameSpriteTag.BALL10)
	        sprite.setPosition(info.x, info.y);
	    
	    else 
	        sprite.setPosition(info.x, info.y);
	    
	    TemplateDefInfo tmpInfo;
	    tmpInfo = G.g_tmplateDefMgr.m_TmpDefsInfoArray.get(info.nTmpType);        
	    
	    int physicsType = tmpInfo.nPhysicsType;        
	    PhysicalTypeInfo physicalinfo = G.g_physicalTypeMgr.m_physicalInfoArray.get(physicsType);
	    
	    BodyDef bodyDef = new BodyDef(); 
	    bodyDef.type = info.fDynamic ? BodyType.DynamicBody: BodyType.StaticBody;
	    
	    if (info.nTagName >= GameConfig.GameSpriteTag.BALL1 && info.nTagName <= GameConfig.GameSpriteTag.BALL10)
	        bodyDef.position.set((info.x) / VRope.PTM_RATIO, (info.y) / VRope.PTM_RATIO);
	    else 
	        bodyDef.position.set(info.x / VRope.PTM_RATIO, info.y / VRope.PTM_RATIO);
	    
	   
	    bodyDef.angle = ccMacros.CC_DEGREES_TO_RADIANS(info.rRotate);
	    Body ballBody = mWorld.createBody(bodyDef);
	    ballBody.setUserData(info);
	    
	    FixtureDef shapeDef = new FixtureDef();
	    
	    if (info.fCircle)
	    {
	        CircleShape circle  =  new CircleShape() ;
	        circle.setRadius((float)tmpInfo.nWidth / VRope.PTM_RATIO);  
	        shapeDef.shape = circle;        
	    }
	    else 
	    {
	        PolygonShape poly = new PolygonShape();        
	        poly.setAsBox((float)tmpInfo.nWidth / (VRope.PTM_RATIO * 2), (float)tmpInfo.nHeight / (VRope.PTM_RATIO * 2));
	        shapeDef.shape = poly;
	    }
	    
	    shapeDef.density = 1000.0f;
	    shapeDef.friction = 0;
	    shapeDef.restitution = 0.0f;
	   //physicalinfo.rRestitution
	    ballBody.createFixture(shapeDef);
	    
//	    MassData mass = new MassData();
//	    mass.mass = 100.0f;
//	    ballBody.setMassData(mass);
	    
	    if (info.nTagName >= GameConfig.GameSpriteTag.BALL1 && info.nTagName <= GameConfig.GameSpriteTag.BALL10)
	        ballBody.setLinearVelocity(new Vector2((float)m_nMomentX / VRope.PTM_RATIO , (float)m_nMomentY/ VRope.PTM_RATIO));
//	    CCSprite sprite = info.sprite;
//	    sprite.setRotation(info.rRotate);
//	    
//	    addChild(sprite, 3);
//	    
//	    if (info.nTagName >= GameConfig.BALL1 && info.nTagName <= GameConfig.BALL10)
//	        sprite.setPosition(info.x, info.y);
//	    else 
//	        sprite.setPosition(info.x, info.y);
//	    
//	    TemplateDefInfo tmpInfo;
//	    tmpInfo = G.g_tmplateDefMgr.m_TmpDefsInfoArray.get(info.nTmpType);        
//	    
//	    int physicsType = tmpInfo.nPhysicsType;        
//	    PhysicalTypeInfo physicalinfo = G.g_physicalTypeMgr.m_physicalInfoArray.get(physicsType);
//	    
//	    BodyDef bodyDef = new BodyDef();
//	    bodyDef.type = info.fDynamic ? BodyType.DynamicBody : BodyType.StaticBody;
//	    
//	    if (info.nTagName >= GameConfig.BALL1 && info.nTagName <= GameConfig.BALL10)
//	        bodyDef.position.set((info.x) / VRope.PTM_RATIO, (info.y) / VRope.PTM_RATIO);
//	    else 
//	        bodyDef.position.set(info.x / VRope.PTM_RATIO, info.y / VRope.PTM_RATIO);
//	    
//	    bodyDef.angle = ccMacros.CC_DEGREES_TO_RADIANS(info.rRotate);
//	    Body ballBody = mWorld.createBody(bodyDef);
//	    ballBody.setUserData(info);
//	    
//	    FixtureDef shapeDef = new FixtureDef();
//	    
//	    if (info.fCircle)
//	    {
//	        CircleShape circle = new CircleShape();
//	        circle.setRadius((float)tmpInfo.nWidth / VRope.PTM_RATIO);        
//	        shapeDef.shape = circle;
//	    }
//	    else 
//	    {
//	        PolygonShape poly = new PolygonShape();        
//	        poly.setAsBox((float)tmpInfo.nWidth / (VRope.PTM_RATIO * 2), (float)tmpInfo.nHeight / (VRope.PTM_RATIO * 2));
//	        shapeDef.shape = poly;
//	    }
//	    
//	    shapeDef.density = physicalinfo.rDensity;
//	    shapeDef.friction = physicalinfo.rFriction;
//	    shapeDef.restitution = physicalinfo.rRestitution;
//	    
//	    ballBody.createFixture(shapeDef);
//	    
//	    if (info.nTagName >= GameConfig.BALL1 && info.nTagName <= GameConfig.BALL10)
//	        ballBody.setLinearVelocity(new Vector2((float)m_nMomentX / VRope.PTM_RATIO , (float)m_nMomentY/ VRope.PTM_RATIO));
	}

	public void addZombieSprite(SpriteInfo info)
	{
	    CCSprite sprite = info.sprite;
		
	    sprite.setRotation(info.rRotate);
	    
	    this.addChild(sprite, 3);
	    sprite.setPosition(info.x, info.y);
	    sprite.setScaleX(G.scale_x);
	    sprite.setScaleY(G.scale_y);	    
	    
	    PhysicalTypeInfo physicalinfo = G.g_physicalTypeMgr.m_physicalInfoArray.get(GameConfig.PhysicalType.ZOMBIEPART_TYPE);
	    
	    BodyDef bodyDef = new BodyDef();
	    bodyDef.type = info.fDynamic ?  BodyType.DynamicBody : BodyType.StaticBody;
//	    bodyDef.type = BodyType.DynamicBody;
	    
	    bodyDef.position.set(info.x / VRope.PTM_RATIO, info.y / VRope.PTM_RATIO);
	    
	    bodyDef.angle = ccMacros.CC_DEGREES_TO_RADIANS(info.rRotate);
	    
	    Body ballBody = mWorld.createBody(bodyDef);
	    ballBody.setUserData(info); 
	    
	    FixtureDef shapeDef = new FixtureDef();
	    
	    if (info.fCircle)
	    {
	        CircleShape circle = new CircleShape() ;
	        circle.setRadius(info.rWidth / VRope.PTM_RATIO);        
	        shapeDef.shape = circle;
	    }
	    else 
	    {
	        PolygonShape poly = new PolygonShape();        
	        poly.setAsBox(info.rWidth / (VRope.PTM_RATIO * 2), info.rHeight / (VRope.PTM_RATIO * 2));        
	        shapeDef.shape = poly;
	    }
	    
	    shapeDef.density = 1.0f;
	    shapeDef.friction = 0;
	    shapeDef.restitution = physicalinfo.rRestitution;
	    //shapeDef.filter.groupIndex = -1;
	    
	    ballBody.createFixture(shapeDef);
//	    CCSprite sprite = info.sprite;
//
//	    sprite.setRotation(info.rRotate);
//	    
//	    addChild(sprite, 3);
//	    
//	    sprite.setPosition(info.x, info.y);
//	    
//	    PhysicalTypeInfo physicalinfo = G.g_physicalTypeMgr.m_physicalInfoArray.get(GameConfig.ZOMBIEPART_TYPE);
//	    
//	    BodyDef bodyDef = new BodyDef();
//	    bodyDef.type = info.fDynamic ? BodyType.DynamicBody : BodyType.StaticBody;
//	    
//	    bodyDef.position.set(info.x / VRope.PTM_RATIO, info.y / VRope.PTM_RATIO);
//	    
////	    bodyDef.userData = info;
//	    bodyDef.angle = ccMacros.CC_DEGREES_TO_RADIANS(info.rRotate);
//	    
//	    Body ballBody = mWorld.createBody(bodyDef);
//	    ballBody.setUserData(info);
//	    
//	    FixtureDef shapeDef = new FixtureDef();
//	    
//	    if (info.fCircle)
//	    {
//	        CircleShape circle = new CircleShape();
//	        circle.setRadius(info.rWidth / VRope.PTM_RATIO);        
//	        shapeDef.shape = circle;
//	    }
//	    else 
//	    {
//	        PolygonShape poly = new PolygonShape();        
//	        poly.setAsBox(info.rWidth / (VRope.PTM_RATIO * 2), info.rHeight / (VRope.PTM_RATIO * 2));        
//	        shapeDef.shape = poly;
//	    }
//	    
//	    shapeDef.density = physicalinfo.rDensity;
//	    shapeDef.friction = physicalinfo.rFriction;
//	    shapeDef.restitution = physicalinfo.rRestitution;
//	    shapeDef.filter.groupIndex = -1;
//	    
//	    ballBody.createFixture(shapeDef);
	}

	private void addRopeConnect(int tag1, int tag2, float rOffsetX, float rOffsetY)
	{
	    String str = "properties-chain.png";
	    CCSpriteSheet ropeBatch;
	    ropeBatch = CCSpriteSheet.spriteSheet(str);	
	    //ropeBatch.setScaleX(G.scale_x);
	    //ropeBatch.setScaleY(G.scale_y);
	    
	    this.addChild(ropeBatch, 1);
	    m_ropeBatchArray.add(ropeBatch);
	    
	    Vector2 tmp1 = null;
	    Vector2 tmp2 = null;
	    
	    Body body1 = null;
	    Body body2 = null;
	    
	   
	    Iterator <Body> allBodys = mWorld.getBodies();
	    while( allBodys.hasNext() )
	    {
	    	Body b = allBodys.next();
	        if (b.getUserData() != null)
	        {
	            SpriteInfo myActor = (SpriteInfo )b.getUserData();            
	            if (myActor.nTagName == tag1)
	            {
//	                tmp1.x = b.getPosition().x;
//	                tmp1.y = b.getPosition().y;
	                tmp1 = b.getPosition();
	                body1 = b;
	                 
	            }
	            else if (myActor.nTagName == tag2)
	            {
//	            	tmp2.x = b.getPosition().x;
//	                tmp2.y = b.getPosition().y;
	            	tmp2 = b.getPosition();
	                body2 = b;
	                
	            }
	        }
	    }
	    
	    RopeJointDef jd = new RopeJointDef ();
	    jd.bodyA = body1; 
	  	jd.bodyB = body2;	
		jd.localAnchorA.set(new Vector2(0,0));
		jd.localAnchorB.set(new Vector2(rOffsetX / VRope.PTM_RATIO, rOffsetY / VRope.PTM_RATIO));
	
	    Vector2 changetmp2 = new Vector2(tmp2.x + rOffsetX / VRope.PTM_RATIO, tmp2.y + rOffsetY / VRope.PTM_RATIO);
	    Vector2 temp = new Vector2( changetmp2.x - tmp1.x, changetmp2.y - tmp1.y); 
		jd.maxLength = temp.len();
	    
	    RopeJoint joint = (RopeJoint)mWorld.createJoint(jd);
	    
	    VRope rope;
	//    rope = [[VRope alloc] init:body1 body2:body2 spriteSheet:ropeBatch offsetX:rOffsetX offsetY:rOffsetY);
	    rope = new VRope(joint, ropeBatch);

		m_ropeArray.add(rope);
	

//	    String str = "properties-chain.png";
//	    CCSpriteSheet ropeBatch;
//	    ropeBatch = CCSpriteSheet.spriteSheet(str);
//	    
//	    this.addChild(ropeBatch, 1);
//	    m_ropeBatchArray.add(ropeBatch);
//	    
//	    Vector2 tmp1 = new Vector2(0,0);
//	    Vector2 tmp2 = new Vector2(0,0);
//	    
//	    Body body1 = null, body2 = null;
//	
//	    Iterator <Body> allBodys = mWorld.getBodies();
//	    while( allBodys.hasNext() )
//	    {
//	    	Body b = allBodys.next();
//	        if (b.getUserData() != null)
//	        {
//	            SpriteInfo myActor = (SpriteInfo )b.getUserData();            
//	            if (myActor.nTagName == tag1)
//	            {
//	                tmp1.x = b.getPosition().x;
//	                tmp1.y = b.getPosition().y;
//	                body1 = b;
//	            }
//	            else if (myActor.nTagName == tag2)
//	            {
//	            	tmp2.x = b.getPosition().x;
//	                tmp2.y = b.getPosition().y;
//	                body2 = b;
//	            }
//	        }
//	    }
//	    
//	    RopeJointDef jd = new RopeJointDef ();
//	    
//		jd.bodyA = body1;
//		jd.bodyB = body2;
//	    
//		jd.localAnchorA.set(0,0);
//		jd.localAnchorB.set(rOffsetX / VRope.PTM_RATIO, rOffsetY / VRope.PTM_RATIO);
//
//		Vector2 changetmp2 = new Vector2(tmp2.x + rOffsetX / VRope.PTM_RATIO, tmp2.y + rOffsetY / VRope.PTM_RATIO);
//		jd.maxLength= changetmp2.len() - tmp1.len();
//	    
//		RopeJoint joint = (RopeJoint)mWorld.createJoint(jd);
//	    
//	    VRope rope;
////	    rope = [[VRope alloc] init:body1 body2:body2 spriteSheet:ropeBatch offsetX:rOffsetX offsetY:rOffsetY];
//	    rope = new VRope(joint, ropeBatch);
//		m_ropeArray.add(rope);
	}

	private void addRopeConnect(int tag1, int tag2, float rOffsetX1, float rOffsetX2, boolean fSame)
	{
	    if (!fSame)
	        return;
	    
	    String str = "properties-chain.png";
	    CCSpriteSheet ropeBatch;
	    ropeBatch = CCSpriteSheet.spriteSheet(str);
//	    ropeBatch.setScaleX(G.scale_x);
//	    ropeBatch.setScaleY(G.scale_y);
	    this.addChild(ropeBatch, 1);
	    m_ropeBatchArray.add(ropeBatch);
	    
	    Vector2 tmp1 = null;
	    Vector2 tmp2 = null;
	    
	    Body body1 = null;
	    Body body2 = null;
	    Iterator <Body> allBodys = mWorld.getBodies();
	    
	    while( allBodys.hasNext() )
	    {
	    	Body b = allBodys.next();
	        if (b.getUserData() != null)
	        {
	            SpriteInfo myActor = (SpriteInfo )b.getUserData();
	            
	            if (myActor.nTagName == tag1)
	            {
//	                tmp1.x = b.getPosition().x;
//	                tmp1.y = b.getPosition().y;
	                tmp1 = b.getPosition();
	                body1 = b;
	            }
	            else if (myActor.nTagName == tag2)
	            {
//	                tmp2.x = b.getPosition().x;
//	                tmp2.y = b.getPosition().y;
	            	tmp2 = b.getPosition();
	                body2 = b;
	            }
	        }
	    }
	    
	    RopeJointDef jd = new RopeJointDef() ;
	    jd.bodyA = body1;
	    jd.bodyB = body2;
		jd.localAnchorA.set(new Vector2(rOffsetX1 / VRope.PTM_RATIO, 0));
		jd.localAnchorB.set(new Vector2(rOffsetX2 / VRope.PTM_RATIO, 0));
	    
	    Vector2 changetmp1 = new Vector2(tmp1.x + rOffsetX1 / VRope.PTM_RATIO, tmp1.y);
	    Vector2 changetmp2 = new Vector2(tmp2.x + rOffsetX2 / VRope.PTM_RATIO, tmp2.y);
	    Vector2 Temp = new Vector2(changetmp2.x - changetmp1.x, changetmp2.y - changetmp2.y  );
		jd.maxLength= Temp.len();
	    
		RopeJoint joint = (RopeJoint)mWorld.createJoint(jd);
	    
	    VRope rope;
	    //    rope = [[VRope alloc] init:body1 body2:body2 spriteSheet:ropeBatch offsetX:rOffsetX offsetY:rOffsetY);
	    rope = new VRope (joint, ropeBatch);
		m_ropeArray.add(rope);
//	    if (!fSame)
//	        return;
//	    
//	    String str = "properties-chain.png";
//	    CCSpriteSheet ropeBatch;
//	    ropeBatch = CCSpriteSheet.spriteSheet(str);
//	    
//	    this.addChild(ropeBatch, 1);
//	    m_ropeBatchArray.add(ropeBatch);
//	    
//	    Vector2 tmp1 = new Vector2(0,0);
//	    Vector2 tmp2 = new Vector2(0,0);
//	    
//	    Body body1 = null, body2 = null;
//	
//	    Iterator <Body> allBodys = mWorld.getBodies();
//	    while( allBodys.hasNext() )
//	    {
//	    	Body b = allBodys.next();
//	        if (b.getUserData() != null)
//	        {
//	            SpriteInfo myActor = (SpriteInfo )b.getUserData();            
//	            if (myActor.nTagName == tag1)
//	            {
//	                tmp1.x = b.getPosition().x;
//	                tmp1.y = b.getPosition().y;
//	                body1 = b;
//	            }
//	            else if (myActor.nTagName == tag2)
//	            {
//	            	tmp2.x = b.getPosition().x;
//	                tmp2.y = b.getPosition().y;
//	                body2 = b;
//	            }
//	        }
//	    }
//	    
//	    RopeJointDef jd = new RopeJointDef();
//	    
//		jd.bodyA = body1;
//		jd.bodyB = body2;
//	    
//		jd.localAnchorA.set(rOffsetX1 / VRope.PTM_RATIO, 0);
//		jd.localAnchorB.set(rOffsetX2 / VRope.PTM_RATIO, 0);
//	    
//	    Vector2 changetmp1 = new Vector2(tmp1.x + rOffsetX1 / VRope.PTM_RATIO, tmp1.y);
//	    Vector2 changetmp2 = new Vector2(tmp2.x + rOffsetX2 / VRope.PTM_RATIO, tmp2.y);
//	    
//		jd.maxLength= changetmp2.len() - changetmp1.len();
//	    
//		RopeJoint joint = (RopeJoint)mWorld.createJoint(jd);
//	    
//	    VRope rope;
//	    //    rope = [[VRope alloc] init:body1 body2:body2 spriteSheet:ropeBatch offsetX:rOffsetX offsetY:rOffsetY];
//	    rope = new VRope(joint, ropeBatch);
//		m_ropeArray.add(rope);
	}

	private void addZombieJoint(int tag, int nJointType)
	{
	    Body body1 = null , body2 = null;
	    
	    SpriteInfo jointInfo = null;//new SpriteInfo()
	    Iterator <Body> allBodys = mWorld.getBodies();
	    
	    while( allBodys.hasNext() )
	    {
	    	Body b = allBodys.next();
	    	if (b.getUserData() != null)
	        {
	            SpriteInfo myActor = (SpriteInfo )b.getUserData();
	            
	            if (myActor.nTagName == tag && myActor.nZombiePartType == GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE)
	            {
	                body1 = b;
	            }
	            else if (myActor.nTagName == tag && myActor.nZombiePartType == this.getZombiePartType(nJointType))
	            {
	                body2 = b;                
	                jointInfo = myActor;
	            }
	        }
	    }
	    
	    if(jointInfo == null)
	    	return;
	    
	    Vector2 vec = new  Vector2(jointInfo.rZombieJointX / VRope.PTM_RATIO, jointInfo.rZombieJointY / VRope.PTM_RATIO);
	    RevoluteJointDef joint = new RevoluteJointDef();
	    joint.initialize(body1, body2, vec);
	    
	    if (mWorld != null)
	    {
	        RevoluteJoint jn = (RevoluteJoint)mWorld.createJoint(joint);
	        
	        if (nJointType == GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE)
	        {
	            jn.setLimits(ccMacros.CC_DEGREES_TO_RADIANS(-45.0f), ccMacros.CC_DEGREES_TO_RADIANS(45.0f));
	            jn.enableLimit(true);
	        }
	        else 
	        {
	            jn.setLimits(ccMacros.CC_DEGREES_TO_RADIANS(0.0f), ccMacros.CC_DEGREES_TO_RADIANS(180));
	            jn.enableLimit(true);
	        }
	    }    
//	    Body body1 = null, body2 = null;
//	    
//	    SpriteInfo jointInfo = new SpriteInfo();
//	    Iterator <Body> allBodys = mWorld.getBodies();
//	    
//	    while( allBodys.hasNext() )
//	    {
//	    	Body b = allBodys.next();
//	    	if (b.getUserData() != null)
//	        {
//	            SpriteInfo myActor = (SpriteInfo )b.getUserData();
//	            
//	            if (myActor.nTagName == tag && myActor.nZombiePartType == GameConfig.ZOMBIE_BODY_TYPE)
//	            {
//	                body1 = b;
//	            }
//	            else if (myActor.nTagName == tag && myActor.nZombiePartType == this.getZombiePartType(nJointType))
//	            {
//	                body2 = b;                
//	                jointInfo = myActor;
//	            }
//	        }
//	    }
//	    
//	    RevoluteJointDef joint = new RevoluteJointDef();
//	    
//	    Vector2 vec = new Vector2(jointInfo.rZombieJointX / VRope.PTM_RATIO, jointInfo.rZombieJointY / VRope.PTM_RATIO);
//	    joint.initialize(body1, body2, vec);
//	    
//	    if (mWorld != null)
//	    {
//	        RevoluteJoint jn = (RevoluteJoint)mWorld.createJoint(joint);
//	        
//	        if (nJointType == GameConfig.ZOMBIE_BODY_HEAD_TYPE)
//	        {
//	            jn.setLimits(ccMacros.CC_DEGREES_TO_RADIANS(-45.0f), ccMacros.CC_DEGREES_TO_RADIANS(45.0f));
//	            jn.enableLimit(true);
//	        }
//	        else 
//	        {
//	            jn.setLimits(ccMacros.CC_DEGREES_TO_RADIANS(0.0f), ccMacros.CC_DEGREES_TO_RADIANS(180f));
//	            jn.enableLimit(true);
//	        }
//	    }    
	}

	public void onReplay()
	{
		G.bTT = false;
		G.nT = -1;
	    releaseGame();
	//	removeRopes();
	//    loadLevel();
	}

	public void onMenu()
	{
		G.nT = -1;
		G.bTT = false;
		CCScene scene = CCScene.node();
		scene.addChild(new MenuScene());
		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5f, scene));
	}

	public void onSound()
	{
	    G.g_fSound = !G.g_fSound;
	    SoundManager.sharedSoundManager().setEffectMute(G.g_fSound);
//	    [[SoundManager sharedSoundManager] setEffectMute:g_fSound];
	}

	public void onMusic()
	{
	    G.g_fMusic = !G.g_fMusic;  
		SoundManager.sharedSoundManager().setBackgroundMusicMute(G.g_fMusic);
//	    [[SoundManager sharedSoundManager] setBackgroundMusicMute:g_fMusic];
	}

	public void onSelect()
	{
		G.nT = -1;
		G.bTT = false;
		CCScene scene = CCScene.node();
		scene.addChild(new LevelSelectScene());
		CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5f, scene));
	}

	public void onNextLevel()
	{
		G.nT = -1;
		G.bTT = false;
	    G.g_nRealLevelNum ++;
	    
	    if (G.g_nRealLevelNum > LevelSelectScene.MAX_LEVEL)
	        G.g_nRealLevelNum = 1;
	    
	    releaseGame();
	    //loadLevel();
	}

	public void onBackReset()
	{
		G.nT = -1;
		G.bTT = false;
	    releaseGame();
	    //loadLevel();
	}

	private void removeRopes()
	{
	    if (m_ropeArray.size() != 0) 
	    {
	        for(int i = 0; i < m_ropeArray.size(); i ++) 
	        {
	            m_ropeArray.get(i).removeSprites();	            
	        }
	        
	        //m_ropeArray.removeAll(m_ropeArray);        
	    }
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

	private void addBackImg(String str)
	{
	    if (backImg != null)
	    {
	        removeChild(backImg, true);
	        backImg = null;
	    }
	    
	    String strName = String.format("scene-challenge-%s", str);
	    backImg = CCSprite.sprite(G.getBackImgName(strName));
	    backImg.setPosition(m_winSize.width / 2.0f, m_winSize.height / 2.0f);
	    backImg.setScaleX(G.scale_x);
	    backImg.setScaleY(G.scale_y);
	    addChild(backImg, 0);
	}

	private void addZombiesJointConnect(int nZombieNum)
	{
	    int nStartIdx = GameConfig.GameSpriteTag.MAN1;
	    int nEndIdx = GameConfig.GameSpriteTag.MAN1 + nZombieNum;
	    
	    for (int i = nStartIdx; i < nEndIdx; i ++)
	    {
	        for (int j = GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE; j <= GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_LEG_TYPE; j++)
	        {
	            addZombieJoint(i, j);
	        }
	    }
	    
	    G.g_nZombieNum = nZombieNum;
	}

	private void setSpriteInfo(int spritetag, int nx, int ny, float rotate, boolean dynamic, int tmptype)
	{
	    SpriteInfo info = new SpriteInfo();
	    info.setSpriteInfo(spritetag, G.getX(nx), G.getY(ny), rotate, dynamic, tmptype);
	    
	    addNormalSprite(info);
	}

	private void setZombieSprite(int spritetag, int nx, int ny, int nZomibeType)
	{
	    SpriteInfo info;
	    
	    for (int i = GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE; i <= GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE; i ++)
	    {
	        info = new SpriteInfo();
	        info.setZombieType(spritetag, G.getX(nx), G.getY(ny), nZomibeType, i);
//	        if ( i == GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE ||
//	        		i == GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE )
//	        {
//	        	info.fDynamic = false;
////	        	addZombieSprite(info,false);
//	        }
//	        else
//	        {
//	        	info.fDynamic = true;
////	        	addZombieSprite(info,true);
//	        }
	        addZombieSprite(info);
	        
	    }
	}

	private void setMomentPos(int nx, int ny)
	{
	    m_nMomentX = G.getX(-nx)+ 100.0f;
	    m_nMomentY = G.getY(-ny) + 20.0f;
	    
	}

	private void setZombieCollision(SpriteInfo info, CGPoint ballpt)
	{
		Iterator <Body> allBodys = mWorld.getBodies();
		while( allBodys.hasNext())
		{
			Body b = allBodys.next();
			
	        if (b.getUserData() != null)
	        {
	            SpriteInfo myActor = (SpriteInfo )b.getUserData();
	            if (myActor.fDynamic)
	                continue;
	            else 
	            {
	                if (myActor.nTagName >= GameConfig.GameSpriteTag.MAN1 && myActor.nTagName <= GameConfig.GameSpriteTag.MAN12)
	                {
	                    if (info.nTagName >= GameConfig.GameSpriteTag.BALL1 && info.nTagName <= GameConfig.GameSpriteTag.BALL10)
	                    {
	                        if (CrashDetectUtils.DetectCircleAndRect(ballpt, info.rWidth, myActor.m_zombieRect) != CrashDetectUtils.CRASH_NO)
	                        {
	                            setZombieDynamic(myActor.nTagName);
	                        }
	                    }                   
	                }
	            }
	        }
	    }
	}

	private void setZombieDynamic(int tag)
	{
		Iterator <Body> allBodys = mWorld.getBodies();
		while( allBodys.hasNext())
		{
			Body b = allBodys.next();
		    if (b.getUserData() != null)
	        {
	            SpriteInfo myActor = (SpriteInfo )b.getUserData();
	            
	            if (myActor.nTagName == tag)
	            {
	                myActor.fDynamic = true;
	               b.setType(BodyType.DynamicBody);
	                //break;
	            }
	        }
	    }
	}

	private int getZombiePartType(int nType)
	{
	    switch (nType)
	    {
	        case GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE:
	            return GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE;
	            
	        case GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_ARM_TYPE:
	            return GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE;
	            
	        case GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_ARM_TYPE:
	            return GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE;
	            
	        case GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_LEG_TYPE:
	            return GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE;
	            
	        case GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_LEG_TYPE:
	            return GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE;
	            
	        default:
	            break;
	    }
	    
	    return GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE;
	}

	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		// TODO Auto-generated method stub
		CGPoint location  = CGPoint.make( event.getX(), 
				event.getY() );
			
	    location = CCDirector.sharedDirector().convertToGL(location);
	        
        Joint tmp;
        
        for (int i = 0; i < m_ropeArray.size(); i ++) 
        {
        	Iterator <Joint> allJoint = mWorld.getJoints();
            
        	while(allJoint.hasNext())
        	{
        		tmp = allJoint.next();
                VRope rope = m_ropeArray.get(i);
                
                if (rope.cutRope(location, tmp.getBodyA(), tmp.getBodyB())) 
                {
                	
                	m_ropeArray.get(i).removeSprites();                	
                	m_ropeArray.remove(i);
                	
                    mWorld.destroyJoint(tmp);
                    
                    Body jointBody2 = tmp.getBodyB();
                    SpriteInfo myActor = (SpriteInfo )jointBody2.getUserData();
                    
                    if (myActor.nTagName == GameConfig.GameSpriteTag.R && !myActor.fDynamic)
                    {
                        myActor.fDynamic = true;
                        jointBody2.setType(BodyType.DynamicBody);
                    }
                    
                    SoundManager.sharedSoundManager().playEffect(EffectSoundType.CHAIN_CUT_SOUND);
                    break;
                }
            }
        }
	
		return super.ccTouchesBegan(event);
	}

	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		// TODO Auto-generated method stub
		CGPoint location  = CGPoint.make( event.getX(), 
				event.getY() );
		
	    
	    location = CCDirector.sharedDirector().convertToGL(location);
	        Joint tmp;
	        
        for (int i = 0; i < m_ropeArray.size(); i ++) 
        {
        	Iterator <Joint> allJoint = mWorld.getJoints();
        	while( allJoint.hasNext() )
        	{
        		tmp = allJoint.next();
        		VRope rope = m_ropeArray.get(i);
                 
                if (rope.cutRope(location, tmp.getBodyA(), tmp.getBodyB())) 
                {
                    Log.e("GameScene", "cut rope");
                    m_ropeArray.get(i).removeSprites();//[[m_ropeArray objectAtIndex:i] removeSprites);
                    m_ropeArray.remove(i);
                 
                    mWorld.destroyJoint(tmp);
                    SoundManager.sharedSoundManager().playEffect(EffectSoundType.CHAIN_CUT_SOUND);
                    
                    break;
                }
            }
        }
		return super.ccTouchesMoved(event);
	}

	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.ccTouchesEnded(event);
	}

	
	public void  loadLevel()
	{
	    switch (G.g_nRealLevelNum) 
	    {
	        case 1:
	            this.addBackImg("street1");   
	            
	            this.setMomentPos(-21 ,-1);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 95,161, 0.0f ,true, GameConfig.TemplateType.ball15);    
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,95 ,263 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,204 ,181 ,0.0f ,false, GameConfig.TemplateType.street_anchor15);    
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.STONE,212 ,26 ,0.0f ,false, GameConfig.TemplateType.brick200);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,238 ,64 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,196 ,58 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            
	            this.addZombiesJointConnect(2);            
	            break;
	        case 2:
	            this.addBackImg("street2");   
	            
	            this.setMomentPos(39 ,16);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 423,219 ,0.0f, true, GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,423 ,302 ,0.0f ,false, GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,322 ,238 ,0.0f ,false, GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,200 ,93 ,0.0f ,false, GameConfig.TemplateType.brick200);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,160 ,130 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,210 ,129 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            
	            this.addZombiesJointConnect(2);
	            break;
	        case 3:
	            this.addBackImg("street3");   
	            
	            this.setMomentPos(29 ,42);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 422,166 ,0.0f ,true,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 101,218 ,0.0f, true,GameConfig.TemplateType.ball15); //
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,309 ,166 ,0.0f ,false, GameConfig.TemplateType.street_anchor15);//
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,422 ,260 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);//
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,42 ,290 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,162 ,290 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,240 ,165 ,0.0f ,false ,GameConfig.TemplateType.brick100);//
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,131 ,31 ,0.0f ,false ,GameConfig.TemplateType.brick200);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,231 ,201 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,110 ,69 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,160 ,63 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            
	            this.addZombiesJointConnect(3);    
	            break;
	        case 4:
	            this.addBackImg("street4");   
	            
	            this.setMomentPos(50 ,28);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 152,192 ,0.0f ,true ,GameConfig.TemplateType.ball15); 
	//            this.setSpriteInfo(BALL2 : 269:192 :0.0f:true :GameConfig.GameConfig.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 384,192 ,0.0f ,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,71 ,256 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,186 ,257 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,303 ,256 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,416 ,256 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,412 ,33 ,0.0f ,false ,GameConfig.TemplateType.brick80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,65 ,63 ,0.0f ,false ,GameConfig.TemplateType.brick80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,193 ,17 ,0.0f ,false ,GameConfig.TemplateType.brick150);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	//            this.addRopeConnect(ANCHOR3 :BALL2:0.0:0.0);
	//            this.addRopeConnect(ANCHOR2 :BALL2:0.0:0.0);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,90 ,95 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,210 ,53 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,408 ,65 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            
	            break;
	        
	        case 5:
	            this.addBackImg("street5");   
	            
	            this.setMomentPos(-93 ,-10);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 85,233 ,0.0f ,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 391,228 ,0.0f ,true ,GameConfig.TemplateType.ball15);
	//            this.setSpriteInfo:R:231 :140 :0.0f:true :wood100);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,23 ,295 ,0.0f ,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,153 ,295 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	//            this.setSpriteInfo(ANCHOR3 :191 :256 :0.0f:false :street_anchor15);
	//            this.setSpriteInfo(ANCHOR4 :271 :256 :0.0f:false :street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,322 ,295 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR6 ,458 ,295 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,251 ,37 ,0.0f,false ,GameConfig.TemplateType.brick400);//
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,387 ,136 ,0.0f,false ,GameConfig.TemplateType.brick200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,75 ,137 ,0.0f,false ,GameConfig.TemplateType.brick200);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            
	//            this.addRopeConnect(ANCHOR4 :R:40.0:0.0);
	//            this.addRopeConnect(ANCHOR3 :R:-40.0:0.0);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR6 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,404 ,73 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,95 ,69 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,149 ,75 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            
	            break;
	            
	        case 6:
	            this.addBackImg("street5");   
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 281,246 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 204,246 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 281,182 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,122 ,287 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,350 ,287 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,195 ,19 ,0.0f,false ,GameConfig.TemplateType.brick80);//
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,408 ,50 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,75 ,85 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,298 ,19 ,0.0f,false ,GameConfig.TemplateType.brick80);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL1 ,GameConfig.GameSpriteTag.BALL2,0.0f, 0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL1 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,297 ,55 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,197 ,57 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,411 ,82 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,89 ,123 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            
	            this.addZombiesJointConnect(4);
	            
	            break;
	
	        case 7:
	            this.addBackImg("street2");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 54 + 10,213 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 200 + 10,213 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 316,220 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 230,100 ,0.0f,false ,GameConfig.TemplateType.wood120);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,54 ,284 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,200 ,284 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,133 ,213 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,270 ,281 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,366 ,282 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,54 ,35 ,0.0f,false ,GameConfig.TemplateType.brick80);//
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,290 ,37 ,0.0f,false ,GameConfig.TemplateType.brick200);            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,243 ,87 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,436 ,136 ,0.0f,false ,GameConfig.TemplateType.brick60);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,71 ,71 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,446 ,168 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,371 ,75 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,268 ,133 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);            
	            
	            this.addZombiesJointConnect(4);
	            
	            break;
	            
	        case 8:
	            this.addBackImg("street3");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 212 ,168 ,0.0f,false ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL4 , 405 ,168 ,0.0f,false ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 44 ,195 ,0.0f,true ,GameConfig.TemplateType.ball15);             
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 153 ,230 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,44 ,281 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,121 ,195 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,138 ,306 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,242 ,285 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,259 ,15 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,138 ,15 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,349 ,15 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,449 ,15 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,212 ,145 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,405 ,145 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,136 ,51 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,258 ,51 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,447 ,47 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,350 ,53 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);            
	            
	            this.addZombiesJointConnect(4);
	            
	            break;
	            
	        case 9:
	            this.addBackImg("street4");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 250 ,195 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 363 ,203 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,164 ,221 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,242 ,281 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,326 ,281 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,405 ,281 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,431 ,64 ,0.0f,false ,GameConfig.TemplateType.brick80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,109 ,49 ,0.0f,false ,GameConfig.TemplateType.brick80);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,109 ,87 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,410 ,102 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	
	            this.addZombiesJointConnect(2);
	            
	            break;
	            
	        case 10:
	            this.addBackImg("street1");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 189 ,250 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 404 ,232 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 190 ,144 ,0.0f,true ,GameConfig.TemplateType.wood120); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,189 ,307 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR6 ,454 ,290 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,321 ,290 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,230 ,251 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,150 ,252 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,75 ,19 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,322 ,15 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,357 ,139 ,0.0f,false ,GameConfig.TemplateType.brick200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,76 ,138 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,206 ,61 ,-15.0f ,false ,GameConfig.TemplateType.brick100);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.R,40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.R,-40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR6 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,79 ,57 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,90 ,176 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,342 ,47 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            
	            break;
	            
	        case 11:
	            this.addBackImg("street1");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 180 ,95 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 254 - 10 ,222 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 43 ,158 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,180 ,244 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,254 ,304 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,350 ,222 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,15 ,224 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,120 ,189 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,336 ,82 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,314 ,10 ,0.0f,false ,GameConfig.TemplateType.brick200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,71 ,10 ,0.0f,false ,GameConfig.TemplateType.brick80);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,74 ,48 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,390 ,46 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            
	            this.addZombiesJointConnect(2);
	            
	            break;
	        case 12:
	            this.addBackImg("street2");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 370 ,217 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 99 ,199 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 230 ,205 ,0.0f,true ,GameConfig.TemplateType.box30); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,429 ,290 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,42 ,270 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,180 ,199 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,319 ,290 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,230 ,290 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,138 ,28 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,284 ,34 ,0.0f,false ,GameConfig.TemplateType.brick80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,422 ,46 ,0.0f,false ,GameConfig.TemplateType.brick80);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.R,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,137 ,60 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,287 ,70 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,415 ,82 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            break;
	            
	        case 13:
	            this.addBackImg("street3");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 431 ,214 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 67 ,221 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 275 ,118 ,0.0f,false ,GameConfig.TemplateType.wood120); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,234 ,218 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,164 ,264 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,8 ,274 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,40 ,274 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,441 ,287 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,125 ,28 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,75 ,71 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,297 ,106 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,407 ,39 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,98 ,116 ,0.0f,false ,GameConfig.TemplateType.brick150);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.R,-40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,314 ,153 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,152 ,64 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,414 ,77 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            break;
	            
	        case 14:
	            this.addBackImg("street4");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 144 ,219 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 75 ,198 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 423 ,227 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 298 ,84 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 298 ,55 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 267 ,56 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 267 ,114 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 267 ,85 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 298 ,145 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 298 ,114 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 236 ,55 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 236 ,85 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 237 ,114 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 236 ,144 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 267 ,144 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 236 ,175 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 268 ,175 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 298 ,175 ,0.0f,true ,GameConfig.TemplateType.box30);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,26 ,229 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,191 ,262 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,460 ,307 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,347 ,227 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,415 ,115 ,0.0f,false ,GameConfig.TemplateType.brick200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,280 ,35 ,0.0f,false ,GameConfig.TemplateType.brick400);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL1 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,444 ,147 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,437 ,67 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,354 ,67 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            break;
	            
	        case 15:
	            
	            this.addBackImg("street5");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 300 ,206 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 386 ,206 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX,79 ,114 ,0.0f,false ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R,174 ,204 ,0.0f,true ,GameConfig.TemplateType.box30);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,173 ,278 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,300 ,278 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,220 ,206 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,360 ,156 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,413 ,278 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,114 ,92 ,0.0f,false ,GameConfig.TemplateType.brick150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,326 ,35 ,0.0f,false ,GameConfig.TemplateType.brick60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,429 ,35 ,0.0f,false ,GameConfig.TemplateType.brick60);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.R,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,79 ,160 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,322 ,71 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,429 ,71 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            
	            break;
	           
	        case 16:
	            this.addBackImg("street1");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 91 ,232 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 284 ,160 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 373 ,146 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 373 ,175 ,0.0f,true ,GameConfig.TemplateType.box30);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,35 ,285 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,182 ,232 ,0.0f,false ,GameConfig.TemplateType.street_anchor15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,290 ,255 ,0.0f,false ,GameConfig.TemplateType.street_anchor15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,165 ,14 ,0.0f,false ,GameConfig.TemplateType.brick150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,408 ,124 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,312 ,73 ,45.0f ,false ,GameConfig.TemplateType.brick150);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);  
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);  
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,435 ,156 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,123 ,50 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,158 ,46 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            
	            break;
	            
	        case 17:
	            this.addBackImg("street2");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 413 ,240 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 353 ,228 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 77 ,233 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 17 ,112 ,0.0f,false ,GameConfig.TemplateType.box30); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 47 ,112 ,0.0f,false ,GameConfig.TemplateType.box30); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 134 ,111 ,0.0f,false ,GameConfig.TemplateType.box30); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 79 ,91 ,0.0f,false ,GameConfig.TemplateType.wood150); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,424 ,303 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,271 ,259 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,38 ,305 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,115 ,305 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,284 ,9 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,78 ,78 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,214 ,197 ,0.0f,false ,GameConfig.TemplateType.brick60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,428 ,39 ,0.0f,false ,GameConfig.TemplateType.brick60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,357 ,64 ,-30.0f,false ,GameConfig.TemplateType.brick100);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL1 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,302 ,41 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,210 ,233 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,443 ,75 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,136 ,154 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            
	            this.addZombiesJointConnect(4);
	            break;
	            
	        case 18:
	            this.addBackImg("street3");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 106 ,241 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 173 ,241 ,0.0f,true ,GameConfig.TemplateType.ball15);
	
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 349 ,135 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 379 ,135 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 377 ,205 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 349 ,165 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 378 ,165 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 364 ,235 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 348 ,206 ,0.0f,true ,GameConfig.TemplateType.box30);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 367 ,114 ,0.0f,true ,GameConfig.TemplateType.wood150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 365 ,185 ,0.0f,true ,GameConfig.TemplateType.wood100);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,57 ,274 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,234 ,278 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);            
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,332 ,22 ,0.0f,false ,GameConfig.TemplateType.brick200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,322 ,69 ,-90.0f,false ,GameConfig.TemplateType.brick80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,409 ,69 ,-90.0f,false ,GameConfig.TemplateType.brick80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,91 ,11 ,0.0f,false ,GameConfig.TemplateType.brick100);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL1 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,371 ,54 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,94 ,49 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            
	            this.addZombiesJointConnect(2);
	            break;
	            
	        case 19:
	            this.addBackImg("street4");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 424 ,245 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 58 ,222 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 247 ,214 ,0.0f,true ,GameConfig.TemplateType.box30); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 243 ,185 ,0.0f,true ,GameConfig.TemplateType.box30); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,467 ,300 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,347 ,282 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,15 ,275 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,142 ,261 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,321 ,74 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,258 ,164 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,200 ,82 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,245 ,12 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,248 ,44 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,333 ,106 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,200 ,118 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            
	            this.addZombiesJointConnect(3);
	            break;
	            
	        case 20:
	            this.addBackImg("street5");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 131 ,245 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 65 ,235 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 394 ,219 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,35 ,302 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,142 ,307 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR6 ,351 ,305 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR7 ,437 ,307 ,0.0f,false ,GameConfig.TemplateType.street_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,356 ,126 ,0.0f,false ,GameConfig.TemplateType.brick40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,390 ,16 ,0.0f,false ,GameConfig.TemplateType.brick150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,154 ,39 ,0.0f,false ,GameConfig.TemplateType.brick150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,239 ,143 ,-10.0f,false ,GameConfig.TemplateType.brick200);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL1 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR7 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR6 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,355 ,158 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,144 ,71 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,332 ,52 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,195 ,77 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            
	            this.addZombiesJointConnect(4);
	            break;
	            
	        case 21:
	            this.addBackImg("sewer1");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 303 ,247 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 360 ,226 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 413 ,239 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,269 ,296 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,449 ,298 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);            
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,106 ,72 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,48 ,21 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,159 ,27 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,212 ,45 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,287 ,6 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL1 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,108 ,104 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,290 ,38 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,49 ,59 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,213 ,83 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN5 ,160 ,63 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2);
	            
	            this.addZombiesJointConnect(5);
	            break;
	            
	        case 22:
	            this.addBackImg("sewer3");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 100 ,204 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 249 ,206 ,0.0f,true ,GameConfig.TemplateType.wood100); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 250 ,226 ,0.0f,true ,GameConfig.TemplateType.box30); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 250 ,255 ,0.0f,true ,GameConfig.TemplateType.box30); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,61 ,275 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);   
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,179 ,204 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);   
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,209 ,302 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);   
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,288 ,302 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);   
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,160 ,17 ,0.0f,false ,GameConfig.TemplateType.stage2_100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,388 ,105 ,0.0f,false ,GameConfig.TemplateType.stage2_100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,350 ,18 ,0.0f,false ,GameConfig.TemplateType.stage2_150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,106 ,96 ,0.0f,false ,GameConfig.TemplateType.stage2_150);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.R,40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.R,-40.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,186 ,55 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,355 ,137 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,388 ,54 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,302 ,54 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2);
	            
	            this.addZombiesJointConnect(4);
	            
	            break;
	            
	        case 23:
	            this.addBackImg("sewer2");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL5 , 94 ,121 ,0.0f,false ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL6 , 35 ,122 ,0.0f,false ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 227 ,216 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 292 ,205 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 60 ,98 ,0.0f,false ,GameConfig.TemplateType.stage3_120);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX,63 ,142 ,0.0f,false ,GameConfig.TemplateType.staticwood120);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,115 ,187 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);     
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,201 ,280 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);     
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,331 ,257 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);           
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.R,55.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL2 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE ,299 ,36 ,-10.0f,false ,GameConfig.TemplateType.stage2_200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE ,159 ,61 ,-10.0f,false ,GameConfig.TemplateType.stage2_100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE ,437 ,20 ,0.0f,false ,GameConfig.TemplateType.stage2_100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE ,411 ,154 ,-90.0f,false ,GameConfig.TemplateType.stage2_150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE ,-8 ,141 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE ,16 ,83 ,0.0f,false ,GameConfig.TemplateType.stage2_80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE ,411 ,32 ,-90.0f,false ,GameConfig.TemplateType.stage2_40);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,458 ,56 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2);
	            
	            this.addZombiesJointConnect(1);
	            break;
	            
	        case 24:
	            this.addBackImg("sewer4");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 347 ,232 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 100 ,161 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 219 ,199 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,219 ,293 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,50 ,230 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,171 ,230 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,275 ,282 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,402 ,282 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,78 ,46 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,350 ,136 ,0.0f,false ,GameConfig.TemplateType.stage2_200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,340 ,47 ,0.0f,false ,GameConfig.TemplateType.stage2_150);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,74 ,84 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,315 ,79 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,367 ,85 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            
	            this.addZombiesJointConnect(3);
	            
	            break;
	            
	        case 25:
	            this.addBackImg("sewer5");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 129 ,207 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 32 ,211 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 269 ,242 ,0.0f,true ,GameConfig.TemplateType.ball15); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 267 ,126 ,0.0f,true ,GameConfig.TemplateType.wood100); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,156 ,285 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,34 ,288 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,234 ,305 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,313 ,305 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,224 ,114 ,0.0f,false ,GameConfig.TemplateType.stage2_150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,416 ,108 ,0.0f,false ,GameConfig.TemplateType.stage2_120);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,226 ,12 ,0.0f,false ,GameConfig.TemplateType.stage2_80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,91 ,8 ,0.0f,false ,GameConfig.TemplateType.stage2_100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,417 ,193 ,0.0f,false ,GameConfig.TemplateType.stage2_150);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL1 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,448 ,146 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,212 ,48 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,48 ,44 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2);
	            
	            this.addZombiesJointConnect(3);
	            break;
	            
	        case 26:
	            this.addBackImg("sewer1");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 72 ,242 ,0.0f,true ,GameConfig.TemplateType.ball15);             
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 72 ,119 ,0.0f,true ,GameConfig.TemplateType.ball15);             
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 240 ,178 ,0.0f,true ,GameConfig.TemplateType.wood100);    
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 280 ,125 ,0.0f,true ,GameConfig.TemplateType.wood100);    
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,129 ,295 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,16 ,295 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,200 ,264 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,280 ,264 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR6 ,370 ,173 ,0.0f,false ,GameConfig.TemplateType.stage2_150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR7 ,71 ,179 ,0.0f,false ,GameConfig.TemplateType.stage2_120);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,180 ,9 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,425 ,15 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,86 ,9 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.R,40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.R,-40.0f,0.0f);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR6 ,GameConfig.GameSpriteTag.BOX,-50.0f,40.0f,true);
	            this.addRopeConnect(GameConfig.GameSpriteTag.R ,GameConfig.GameSpriteTag.BOX,-40.0f,0.0f);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL2 ,GameConfig.GameSpriteTag.ANCHOR7,50.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.BALL2 ,GameConfig.GameSpriteTag.ANCHOR7,-50.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,412 ,209 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,81 ,41 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,180 ,47 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,417 ,51 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2);
	            
	            this.addZombiesJointConnect(4);
	            
	            break;
	            
	        case 27:
	            this.addBackImg("sewer2");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 88 ,224 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 195 ,224 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 316 ,224 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 315 ,161 ,0.0f,true ,GameConfig.TemplateType.wood100);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,88 ,300 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,316 ,300 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,275 ,236 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,355 ,235 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,161 ,300 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR6 ,231 ,300 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,193 ,28 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,422 ,13 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,349 ,99 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,293 ,108 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,407 ,158 ,0.0f,false ,GameConfig.TemplateType.stage2_80);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,163 ,160 ,0.0f,false ,GameConfig.TemplateType.stage2_200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,91 ,28 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,29 ,62 ,-45.0f ,false ,GameConfig.TemplateType.stage2_100);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.R,40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.R,-40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR6 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,416 ,196 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,192 ,64 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,425 ,51 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,94 ,66 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            
	            this.addZombiesJointConnect(4);
	            
	            break;
	            
	        case 28:
	            this.addBackImg("sewer3");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 101 ,217 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 231 ,247 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL4 , 386 ,218 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 232 ,189 ,0.0f,true ,GameConfig.TemplateType.wood150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 384 ,137 ,0.0f,true ,GameConfig.TemplateType.wood100);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,135 ,275 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,65 ,275 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,172 ,260 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);      
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,292 ,260 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);         
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,231 ,306 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);  
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR7 ,344 ,222 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);  
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR8 ,424 ,222 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR9 ,386 ,295 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,309 ,12 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,415 ,10 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,369 ,75 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,96 ,153 ,0.0f,false ,GameConfig.TemplateType.stage2_200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,161 ,80 ,0.0f,false ,GameConfig.TemplateType.stage2_150);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,460 ,133 ,0.0f,false ,GameConfig.TemplateType.stage2_40);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,294 ,140 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.R,60.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.R,-60.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR8 ,GameConfig.GameSpriteTag.BOX,40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR7 ,GameConfig.GameSpriteTag.BOX,-40.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR9 ,GameConfig.GameSpriteTag.BALL4,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,416 ,48 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,111 ,116 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,309 ,48 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,18 ,185 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN5 ,463 ,169 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2);
	            
	            this.addZombiesJointConnect(5);
	            
	            break;
	            
	        case 29:
	            this.addBackImg("sewer4");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 193 ,215 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 71 ,215 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL3 , 374 ,206 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 193 ,140 ,0.0f,true ,GameConfig.TemplateType.wood150); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 144 ,102 ,0.0f,true ,GameConfig.TemplateType.box30); 
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,193 ,310 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15); 
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,133 ,230 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);           
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,253 ,230 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,28 ,310 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR6 ,124 ,310 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR7 ,334 ,265 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);                
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR8 ,419 ,265 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);                
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,212 ,79 ,0.0f,false ,GameConfig.TemplateType.stage2_200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,420 ,24 ,0.0f,false ,GameConfig.TemplateType.stage2_100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,398 ,151 ,0.0f,false ,GameConfig.TemplateType.stage2_200);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,31 ,9 ,0.0f,false ,GameConfig.TemplateType.stage2_60);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.R,60.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.R,-60.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR6 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR8 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR7 ,GameConfig.GameSpriteTag.BALL3,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,464 ,189 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,442 ,62 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,18 ,45 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2);
	            
	            this.addZombiesJointConnect(3);
	            
	            break;
	            
	        case 30:
	            this.addBackImg("sewer5");
	            
	            this.setMomentPos(-0 ,-0);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL1 , 381 ,225 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BALL2 , 107 ,230 ,0.0f,true ,GameConfig.TemplateType.ball15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.R , 217 ,159 ,0.0f,true ,GameConfig.TemplateType.stage2_120);
	
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 216 ,89 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 215 ,60 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 269 ,90 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 269 ,61 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 317 ,91 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 318 ,59 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 170 ,90 ,0.0f,true ,GameConfig.TemplateType.box30);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.BOX , 169 ,60 ,0.0f,true ,GameConfig.TemplateType.box30);            
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR1 ,217 ,238 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR2 ,36 ,275 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR3 ,168 ,302 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR4 ,321 ,275 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.ANCHOR5 ,435 ,275 ,0.0f,false ,GameConfig.TemplateType.sewer_anchor15);
	            
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,280 ,37 ,0.0f,false ,GameConfig.TemplateType.stage2_400);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,99 ,156 ,0.0f,false ,GameConfig.TemplateType.stage2_100);
	            this.setSpriteInfo(GameConfig.GameSpriteTag.RECTANGLE,392 ,141 ,0.0f,false ,GameConfig.TemplateType.stage2_200);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR2 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR3 ,GameConfig.GameSpriteTag.BALL2,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR4 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR5 ,GameConfig.GameSpriteTag.BALL1,0.0f,0.0f);
	            
	            this.addRopeConnect(GameConfig.GameSpriteTag.ANCHOR1 ,GameConfig.GameSpriteTag.R,0.0f,0.0f);
	            
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN1 ,56 ,188 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN2 ,458 ,179 ,GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN3 ,457 ,69 ,GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2);
	            this.setZombieSprite(GameConfig.GameSpriteTag.MAN4 ,92 ,73 ,GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1);
	            
	            this.addZombiesJointConnect(4);
	            
	            break;
	            
	        default:
	            break;
	    }
	    
	    m_nScore = 200;
	    
	    levelLabel.setString(String.format("LEVEL  %d", G.g_nRealLevelNum));
	    scoreLabel.setString(String.format("SCORE  %d", m_nScore));
	    
	    SoundManager.sharedSoundManager().playBackgroundMusic(BackSoundType.MUSIC_START_SOUND.ordinal());
	    
//	    this.schedule("update");
	    
		
		this.schedule("update");
	    this.schedule("onScore", 0.3f);
	}

	private boolean isValidGameDone()
	{
	    if (G.g_nZombieNum == 0)
	        return true;
	    else 
	        return false;
	}

	private boolean isValidGameOver()
	{
	    VRope rope;
	    for (int i = 0; i < m_ropeArray.size(); i ++)
	    {
	        rope = m_ropeArray.get(i);
	        
	        if (rope.isValidGameOverState())
	            continue;
	        else
	            return false;
	    }
	  
	    Iterator <Body> allBodys = mWorld.getBodies();
	    while( allBodys.hasNext() )
	    {
	    	Body b = allBodys.next();
	        if (b.getUserData() != null)
	        {
	            SpriteInfo myActor = (SpriteInfo )b.getUserData();
	            
	            if ((myActor.nTagName >= GameConfig.GameSpriteTag.BALL1 && myActor.nTagName <= GameConfig.GameSpriteTag.BALL10) || myActor.nTagName == GameConfig.GameSpriteTag.R || myActor.nTagName == GameConfig.GameSpriteTag.BOX)
	            {
	                Vector2 vec = b.getLinearVelocity();
	
	                CGPoint pt = CGPoint.make(b.getPosition().x * VRope.PTM_RATIO, b.getPosition().y * VRope.PTM_RATIO);
	                
	                if (pt.y <= -10)
	                {
	                    continue;
	                }
	                else 
	                {
	                    float rTh = vec.len();
	                    
	                    if (rTh >= 0.02f)
	                        return false;
	                }                
	            }
	        }
	    }
	    
	    return true;
	}
}
