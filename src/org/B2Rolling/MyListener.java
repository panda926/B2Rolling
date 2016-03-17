
package org.B2Rolling;

import java.util.ArrayList;
import java.util.Iterator;

import org.cocos2d.config.ccMacros;
import org.cocos2d.nodes.CCSprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class MyListener implements ContactListener
{
	public final static int k_maxContactPoints = 2048;

	public final static float ZOMBIE_LIGHT_COLLISION_TH = 0.01f;//0.002f
	public final static float ZOMBIE_HEAVY_COLLISION_TH  = 0.3f;//0.2f

	public final static int OBJ_LIGHT_COLLISION_TH  = 300;//7
	public final static int OBJ_HEAVY_COLLISION_TH  = 5000;//12
	
	private World world;
	private GameScene scene;
	public void setWorld( World w, GameScene s ) {
		world = w;
		scene = s;
	}

	public void freeBodyJoint(int tag )
	{
		Iterator <Joint> allJoint = world.getJoints();
		while( allJoint.hasNext() )
		{
			Joint j  = allJoint.next();
			Body b = j.getBodyA();
	
			if (b.getUserData() != null) 
			{
				CCSprite ccSprite = (CCSprite)b.getUserData();
			
	            if (ccSprite == null)
					continue;
				
	            if(ccSprite.getTag() == tag)
	            	((RevoluteJoint)j).enableLimit( false);
			}
		}
	}

	public void setObjDynamic(Body body1, Body body2, int tag1, int tag2)
	{
	    SpriteInfo data1 = (SpriteInfo)body1.getUserData();
	    SpriteInfo data2 = (SpriteInfo)body2.getUserData();
	    
	    if (tag1 == GameConfig.GameSpriteTag.MAN1 || tag2 == GameConfig.GameSpriteTag.MAN1)
	    {
	    	if (data1.nTagName >= GameConfig.GameSpriteTag.MAN1 && data1.nTagName <= GameConfig.GameSpriteTag.MAN12)
	        {
	            if (data2.nTagName >= GameConfig.GameSpriteTag.MAN1 && data2.nTagName <= GameConfig.GameSpriteTag.MAN12)
	            {
	            	if ( !data1.fDynamic )
	            	{
	            		data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);  
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	            	}
	            	if ( !data2.fDynamic )
	            	{
	            		data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);  
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	            	}
	            }
	        }
	        if (data1.nTagName >= GameConfig.GameSpriteTag.MAN1 && data1.nTagName <= GameConfig.GameSpriteTag.MAN12)
	        {
	            if (data2.nTagName >= GameConfig.GameSpriteTag.BALL1 && data2.nTagName <= GameConfig.GameSpriteTag.BALL10)
	            {
	                if (!data1.fDynamic)
	                {
	                	
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);
	                    
	                    setZombieDynamic(data1.nTagName);
	                    G.bTT = true;
	                    G.nT = data1.nTagName;
	                    
	                }
	            }
	            else if (tag1 == data2.nTagName || tag2 == data2.nTagName)
	            {
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	            }
	        }
	        else if (tag2 == GameConfig.GameSpriteTag.MAN1 && (data2.nTagName >= GameConfig.GameSpriteTag.MAN1 && data2.nTagName <= GameConfig.GameSpriteTag.MAN12))
	        {
	            if (data1.nTagName >= GameConfig.GameSpriteTag.BALL1 && data2.nTagName <= GameConfig.GameSpriteTag.BALL10)
	            {
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);
	                    
	                    setZombieDynamic(data2.nTagName);
	                    G.bTT = true;
	                    G.nT = data2.nTagName;
	                    
	                }
	            }
	            else if (tag1 == data1.nTagName || tag2 == data1.nTagName)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);  
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	            }
	        }
	    }    
	    else if (tag1 == GameConfig.GameSpriteTag.BALL1 || tag2 == GameConfig.GameSpriteTag.BALL1)
	    {
	    	if (data1.nTagName >= GameConfig.GameSpriteTag.MAN1 && data1.nTagName <= GameConfig.GameSpriteTag.MAN12)
	        {
	            if (data2.nTagName >= GameConfig.GameSpriteTag.MAN1 && data2.nTagName <= GameConfig.GameSpriteTag.MAN12)
	            {
	            	if ( !data1.fDynamic )
	            	{
	            		data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);  
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	            	}
	            	if ( !data2.fDynamic )
	            	{
	            		data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);  
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	            	}
	            }
	        }
	        if (data1.nTagName >= GameConfig.GameSpriteTag.BALL1 && data1.nTagName <= GameConfig.GameSpriteTag.BALL10)
	        {
	            if (data2.nTagName >= GameConfig.GameSpriteTag.BALL1 && data2.nTagName <= GameConfig.GameSpriteTag.BALL10)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);  
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody); 
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	                }
	            }
	            else if (tag1 == data2.nTagName || tag2 == data2.nTagName)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);  
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);  
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	                }
	            }
	        }
	        else if (data2.nTagName >= GameConfig.GameSpriteTag.BALL1 && data2.nTagName <= GameConfig.GameSpriteTag.BALL10)
	        {
	            if (data1.nTagName >= GameConfig.GameSpriteTag.BALL1 && data1.nTagName <= GameConfig.GameSpriteTag.BALL10)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);  
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);    
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	                }
	            }
	            else if (tag1 == data1.nTagName || tag2 == data1.nTagName)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);  
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);     
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	                }
	            }
	        }
	    }
	    else if (tag1 == data1.nTagName || tag1 == data2.nTagName)
	    {
	        if (tag1 == data1.nTagName)
	        {
	            if (tag2 == data2.nTagName)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody); 
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);  
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	                }
	            }
	        }
	        else if (tag1 == data2.nTagName)
	        {
	            if (tag1 == data2.nTagName)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);  
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);  
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	                }
	            }
	        }
	    }
	    else if (tag2 == data1.nTagName || tag2 == data2.nTagName)
	    {
	        if (tag2 == data1.nTagName)
	        {
	            if (tag1 == data2.nTagName)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody); 
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);  
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	                }
	            }
	        }
	        else if (tag2 == data2.nTagName)
	        {
	            if (tag1 == data1.nTagName)
	            {
	                if (!data1.fDynamic)
	                {
	                    data1.fDynamic = true;
	                    body1.setType(BodyType.DynamicBody);   
	                    G.nT = data1.nTagName;
	                    G.bTT = true;
	                }
	                if (!data2.fDynamic)
	                {
	                    data2.fDynamic = true;
	                    body2.setType(BodyType.DynamicBody);  
	                    G.nT = data2.nTagName;
	                    G.bTT = true;
	                }
	            }
	        }
	    }
	}
	
	public void setZombieDynamic(int tag)
	{
		Iterator <Body> allBodys = scene.mWorld.getBodies();
		while( allBodys.hasNext() )
		{
            BodyType type;
			Body b = allBodys.next();
	        if (b.getUserData() != null)
	        {
	            SpriteInfo myActor = (SpriteInfo )b.getUserData();
	            
	            if (myActor.nTagName == tag)
	            {
	            	type = b.getType();
	                myActor.fDynamic = true;
//	        	    MassData mass = new MassData();
//	        	    mass.mass = 0.01f;
//	                b.setMassData(mass);
//	                world.destroyBody(b);
//	                scene.addZombieSprite(myActor);
	                b.setType(BodyType.DynamicBody);
//	                type = b.getType();
//	                myActor.fDynamic = true;
	                
	            }
	        }
	    }
	}
	
	public void setZombieSound(int tag)
	{
		Iterator <Body> allBodys = world.getBodies();
		while( allBodys.hasNext() )
		{
			Body b = allBodys.next();
	        if (b.getUserData() != null)
	        {
	            SpriteInfo myActor = (SpriteInfo)b.getUserData();
	            
	            if (myActor.nTagName == tag)
	            {
	                myActor.fDynamic = true;
	                myActor.fZombieSound = true;
	                
	            }
	        }
	    }
	}
	

	
	public int getZombieSoundIdx(SpriteInfo info, boolean fLight)
	{
	    ArrayList<ZombieSoundList> zombieSoundArray = G.g_soundListMgr.m_zombieSoundArray;
	    
	    ZombieSoundList zombieSoundListInfo = null ;//= new ZombieSoundList() 
	    
	    boolean fFlag = false;
	    for (int i = 0; i < zombieSoundArray.size(); i ++)
	    {
	        zombieSoundListInfo = zombieSoundArray.get(i);
	        
	        if (info.nZombieType == zombieSoundListInfo.nZombieType)
	        {
	            fFlag = true;
	            break;            
	        }
	    }
	    
	    if (fFlag)
	    {
	        if (fLight)
	            return zombieSoundListInfo.lightSound;
	        else 
	            return zombieSoundListInfo.heavySound;
	    }
	    else 
	        return GameConfig.EffectSoundType.NON_SOUND_ID;
	}
	
	public int getObjCollisionSoundIdx(SpriteInfo info1,SpriteInfo info2,boolean fLight)
	{
	    ArrayList<SoundList> soundListArray = G.g_soundListMgr.m_soundListArray;
	    
	    SoundList soundListInfo = null;//new SoundList()
	    
	    boolean fFlag = false;
	    
	    TemplateDefInfo tmpinfo1 = G.g_tmplateDefMgr.m_TmpDefsInfoArray.get(info1.nTmpType);
	    TemplateDefInfo tmpinfo2 = G.g_tmplateDefMgr.m_TmpDefsInfoArray.get(info2.nTmpType);
	    
	    int type1 = tmpinfo1.nPhysicsType;
	    int type2 = tmpinfo2.nPhysicsType;
	    
	    for (int i = 0; i < soundListArray.size(); i ++)
	    {
	        soundListInfo = soundListArray.get(i);
	        
	        if ( soundListInfo.isValidSoundID(type1 ,type2))
	        {
	            fFlag = true;
	            break;
	        }        
	    }
	    
	    if (fFlag)
	    {
	        if (fLight)
	            return soundListInfo.lightSound;
	        else 
	            return soundListInfo.heavySound;
	    }
	    else 
	        return GameConfig.EffectSoundType.NON_SOUND_ID;
	}

	public void beginContact(Contact arg0) {
		// TODO Auto-generated method stub
	    Fixture fixtureA = arg0.getFixtureA();
	    Fixture fixtureB = arg0.getFixtureB();
	    
	    Body body1 = fixtureA.getBody();
	    Body body2 = fixtureB.getBody();
	    setObjDynamic(body1, body2, GameConfig.GameSpriteTag.MAN1, GameConfig.GameSpriteTag.MAN1);
  	    setObjDynamic(body1, body2, GameConfig.GameSpriteTag.BALL1, GameConfig.GameSpriteTag.BOX);
	    setObjDynamic(body1, body2, GameConfig.GameSpriteTag.BALL1, GameConfig.GameSpriteTag.MAN1);
	    setObjDynamic(body1, body2, GameConfig.GameSpriteTag.BALL1, GameConfig.GameSpriteTag.R);
	    setObjDynamic(body1, body2, GameConfig.GameSpriteTag.BALL1, GameConfig.GameSpriteTag.BOX);
	    setObjDynamic(body1, body2, GameConfig.GameSpriteTag.BALL1, GameConfig.GameSpriteTag.BALL1);
	    setObjDynamic(body1, body2, GameConfig.GameSpriteTag.R, GameConfig.GameSpriteTag.BOX);
	    
	   
	}

	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
	    // find the strength of the impulse..
	    int count = contact.getWorldManifold().getNumberOfContactPoints();
	    
	    float maxImpulse = 0.0f;
	    
	    for (int i = 0; i < count; i ++) 
	        maxImpulse = Math.max(maxImpulse, impulse.getNormalImpulses()[i]);
	    
	    if (maxImpulse <= ZOMBIE_LIGHT_COLLISION_TH)
	        return;
	    
	    Fixture fixtureA = contact.getFixtureA();
	    Fixture fixtureB = contact.getFixtureB();
	    
	    Body body1 = fixtureA.getBody();
	    Body body2 = fixtureB.getBody();
	      
	    SpriteInfo data1 = (SpriteInfo)body1.getUserData();
	    SpriteInfo data2 = (SpriteInfo)body2.getUserData();
	    
	    int soundId;
	    
	    if (data1.nTagName >= GameConfig.GameSpriteTag.MAN1 && data1.nTagName <= GameConfig.GameSpriteTag.MAN12)
	    {
	        if (!data1.fZombieSound)
	        {
	            if (maxImpulse >= ZOMBIE_LIGHT_COLLISION_TH && maxImpulse <= ZOMBIE_HEAVY_COLLISION_TH)
	            {
	                soundId = getZombieSoundIdx(data1, true);
//	                [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	                SoundManager.sharedSoundManager().playEffect(soundId);
	                soundId = getObjCollisionSoundIdx(data1, data2, true);
	                SoundManager.sharedSoundManager().playEffect(soundId);
//	                [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	            }
	            else if (maxImpulse >= ZOMBIE_HEAVY_COLLISION_TH) 
	            {
	                soundId = getZombieSoundIdx(data1, false);
//	                [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	                SoundManager.sharedSoundManager().playEffect(soundId);
	                soundId = getObjCollisionSoundIdx(data1, data2, false);
	                SoundManager.sharedSoundManager().playEffect(soundId);
//	                [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	            }
	            
	            G.g_nZombieNum --;
	            setZombieSound(data1.nTagName);
	            
	        }
	    }
	    else if (data2.nTagName >= GameConfig.GameSpriteTag.MAN1 && data2.nTagName <= GameConfig.GameSpriteTag.MAN12)
	    {
	        if (!data2.fZombieSound)
	        {
	            if (maxImpulse >= ZOMBIE_LIGHT_COLLISION_TH && maxImpulse <= ZOMBIE_HEAVY_COLLISION_TH)
	            {
	                soundId = getZombieSoundIdx(data2, true);
	                SoundManager.sharedSoundManager().playEffect(soundId);
//	                [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	                
	                soundId = getObjCollisionSoundIdx(data1, data2, true);
	                SoundManager.sharedSoundManager().playEffect(soundId);
//	                [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	            }
	            else if (maxImpulse >= ZOMBIE_HEAVY_COLLISION_TH) 
	            {
	                soundId = getZombieSoundIdx(data2, false);
	                SoundManager.sharedSoundManager().playEffect(soundId);
//	                [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	                
	                soundId = getObjCollisionSoundIdx(data1, data2, false);
	                SoundManager.sharedSoundManager().playEffect(soundId);
//	                [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	            }
	            
	            G.g_nZombieNum --;
	            setZombieSound(data2.nTagName);
	            
	        }
	    }
	    else 
	    {
	        if (maxImpulse >= OBJ_LIGHT_COLLISION_TH && maxImpulse <= OBJ_HEAVY_COLLISION_TH)
	        {
	            soundId = getObjCollisionSoundIdx(data1, data2, true);
//                SoundManager.sharedSoundManager().playEffect(soundId);
//	            [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	            
	        }
	        else if (maxImpulse >= OBJ_HEAVY_COLLISION_TH)
	        {
	            soundId = getObjCollisionSoundIdx(data1, data2, false);
 //               SoundManager.sharedSoundManager().playEffect(soundId);
//	            [[SoundManager sharedSoundManager] playEffect:(int)soundId];
	            
	        }
	    }
	}

	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}
		
}