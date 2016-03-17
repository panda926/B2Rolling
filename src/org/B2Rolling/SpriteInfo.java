package org.B2Rolling;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGRect;

public class SpriteInfo extends CCNode 
{
    float x;
    float y;
    
    float rRotate;
    
  //  GameSpriteTag nTagName;
  //  GameConfig nTmpType;
    public int nTagName;
    int nTmpType;
    
    boolean fDynamic;    
    public boolean fZombie;
    public boolean fCircle;
    boolean fZombieSound;
    
//    ZombieType nZombieType;
//    GameConfig nZombiePartType;
    
    int nZombieType;
    int nZombiePartType;
    
    CCSprite sprite;   
    
    float rWidth;
    float rHeight;    
    
    float rZombieJointX;
    float rZombieJointY;
    
    CGRect m_zombieRect;

	public SpriteInfo()
	{
        fCircle = false;
        fZombieSound = false;
	}
	
	
	public void setSpriteInfo(int  spritetag, float nx,  
			float ny,  float rotate, boolean dynamic, int tmptype)
	{
	    nTagName = spritetag;
	    
	    x = nx;
	    y = ny;
	    
	    nTmpType = tmptype;
	    rRotate = rotate;
	    
	    fDynamic = dynamic;
	    
	    if (nTmpType == GameConfig.TemplateType.ball15)
	        fCircle = true;
	    
	    TemplateDefInfo info = G.g_tmplateDefMgr.m_TmpDefsInfoArray.get(tmptype);
	    
	    sprite = CCSprite.sprite(info.fileName);
	    
	    rWidth = info.nWidth; rHeight = info.nHeight;
	}
	
	public void setZombieType( int spritetag, float nx, float ny, 
			int type, int nparttype)
	{
	    nTagName = spritetag;
	
	    nZombieType = type;
	    nZombiePartType = nparttype;
	    
	    ZombieInfo zombieinfo = G.g_zombieInfoMgr.m_zombieArray.get(type);
	    
	    ZombiePartInfo partinfo;
	    partinfo = zombieinfo.m_zombieTypeArray.get(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE);
	    
	    float offsetx = nx - (float)partinfo.x;
	    float offsety = ny - (float)partinfo.y;
	    
	    m_zombieRect = CGRect.make(partinfo.x - partinfo.nWidth / 2.0f, partinfo.y - partinfo.nHeight / 2.0f, partinfo.nWidth, partinfo.nHeight);    
	    
	    partinfo = zombieinfo.m_zombieTypeArray.get(nparttype);
	    
	    rRotate = partinfo.rRotate;
	    
	    fDynamic = false;
	    fCircle = partinfo.fCircle;
	    
	    sprite = CCSprite.sprite(partinfo.strName);
	    
	    x = partinfo.x + offsetx;
	    y = partinfo.y + offsety;
	    
	    rWidth = partinfo.nWidth;
	    rHeight = partinfo.nHeight;
	    
	    ZombieRevoluteInfo revoluteInfo = zombieinfo.m_zombieJointArray.get(this.getZombieRevoluteType(nparttype));
	    
	    rZombieJointX = revoluteInfo.rX + offsetx;
	    rZombieJointY = revoluteInfo.rY + offsety;
	    
	    m_zombieRect.origin.x += offsetx;
	    m_zombieRect.origin.y += offsety;
	}
	
	public int getZombieRevoluteType(int nType)
	{
	    switch (nType)
	    {
	        case GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE:
	            return GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_LEG_TYPE;
	                        
	        case GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE:
	            return GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_LEG_TYPE;
	       
	            
	        case GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE:
	            return GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE;
	       
	            
	        case GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE:
	            return GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE;
	       
	            
	        case GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE:
	            return GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_ARM_TYPE;
	       
	            
	        case GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE:
	            return GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_ARM_TYPE;
	                   
	        default:
	            break;
	    }
	    
	    return GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE;
	}

}
