package org.B2Rolling;

import java.util.ArrayList;

import org.cocos2d.nodes.CCNode;

public class ZombieInfo extends CCNode {

	private String[] strZombieName = 
	{
	    "l-zombie1",
	    "l-zombie2",
	    "l-zombie3",
	    "l-zombie4",
	    
	    "m-zombie1",
	    "m-zombie2",
	    "m-zombie3",
	    "m-zombie4",
	    
	    "s-zombie1",
	    "s-zombie2",
	    "s-zombie3",
	    "s-zombie4",
	};
	public ArrayList<ZombiePartInfo> m_zombieTypeArray;
	public ArrayList<ZombieRevoluteInfo> m_zombieJointArray;
	private int nZombieType;
	
	public ZombieInfo() {
		m_zombieTypeArray = new ArrayList<ZombiePartInfo>();
		m_zombieJointArray = new ArrayList<ZombieRevoluteInfo>();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		m_zombieTypeArray.removeAll(m_zombieTypeArray);
		m_zombieJointArray.removeAll(m_zombieJointArray);
		super.onExit();
	}

	public void makeZombieInfo(int nType)
	{
	    nZombieType = nType;
	    
	    if (nType == GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1 || nType == GameConfig.ZombieType.ZOMBIE_LARGE_TYPE2 || 
	    	nType == GameConfig.ZombieType.ZOMBIE_LARGE_TYPE3 || nType == GameConfig.ZombieType.ZOMBIE_LARGE_TYPE4)
	    {
	        setZombiePartInfo(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE, GameConfig.PhysicalType.ZOMBIEBOY_TYPE, 
	        		244, 150, 40, 60, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE));
	       
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		240, 131, 12, 21, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		248, 131, 12, 21, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		243, 145, 23, 25, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		232, 143, 10, 20, true, true, -10.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		255, 143, 10, 20, true, true, 10.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		244, 166, 14, 14, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE));
	    }
	    else if (nType == GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1 || nType == GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE2 || 
	    		nType == GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE3 || nType == GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE4)
	    {
	        setZombiePartInfo(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE, GameConfig.PhysicalType.ZOMBIEBOY_TYPE, 
	        		240, 153, 30, 56, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE));

	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		236, 134, 9, 18, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		243, 134, 9, 18, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		240, 147, 18, 18, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		231, 145, 10, 17, true, true, -10.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		249, 145, 10, 17, true, true, 10.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		239, 167, 14, 14, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE));       
	    }
	    else if (nType == GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1 || nType == GameConfig.ZombieType.ZOMBIE_SHORT_TYPE2)
	    {
	        setZombiePartInfo(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE, GameConfig.PhysicalType.ZOMBIEBOY_TYPE,
	        		239, 152, 30, 48, true, false, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		236, 136, 8, 16, true, false, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		241, 136, 8, 16, true, false, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		239, 145, 14, 18, true, false, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		232, 144, 7, 13, true, false, -10.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		246, 144, 7, 13, true, false, 10.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		238, 163, 13, 13, true, false, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE));
	    }
	    else if (nType == GameConfig.ZombieType.ZOMBIE_SHORT_TYPE3 || nType == GameConfig.ZombieType.ZOMBIE_SHORT_TYPE4)
	    {
	        setZombiePartInfo(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE, GameConfig.PhysicalType.ZOMBIEBOY_TYPE, 
	        		239, 152, 30, 48, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		236, 136, 8, 16, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		241, 136, 8, 16, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		239, 145, 14, 18, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE));
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		232, 144, 7, 13, true, true, -10.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE));
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		246, 144, 7, 13, true, true, 10.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE));        
	        
	        setZombiePartInfo(GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE, GameConfig.PhysicalType.ZOMBIEPART_TYPE, 
	        		238, 163, 13, 13, true, true, 0.0f, getZombiePartName(GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE));
	    }    
	}

	public void makeZombieJointInfo(int nType)
	{
	    if (nType >= GameConfig.ZombieType.ZOMBIE_LARGE_TYPE1 && nType <= GameConfig.ZombieType.ZOMBIE_LARGE_TYPE4)
	    {
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE, 244, 154, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_ARM_TYPE, 234, 150, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_ARM_TYPE, 254, 150, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_LEG_TYPE, 239, 137, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_LEG_TYPE, 248, 137, 7, 7, 0.0f);
	    }
	    else if (nType >= GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE1 && nType <= GameConfig.ZombieType.ZOMBIE_MIDDLE_TYPE4)
	    {
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE, 240, 154, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_ARM_TYPE, 231, 150, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_ARM_TYPE, 248, 150, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_LEG_TYPE, 236, 139, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_LEG_TYPE, 243, 139, 7, 7, 0.0f);
	    }
	    else if (nType >= GameConfig.ZombieType.ZOMBIE_SHORT_TYPE1 && nType <= GameConfig.ZombieType.ZOMBIE_SHORT_TYPE4)
	    {
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_HEAD_TYPE, 239, 152, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_ARM_TYPE, 233, 148, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_ARM_TYPE, 244, 148, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_LEFT_LEG_TYPE, 236, 139, 7, 7, 0.0f);
	        setZombieRevoluteInfo(GameConfig.ZombiRevoluteJointType.ZOMBIE_BODY_RIGHT_LEG_TYPE, 242, 138, 7, 7, 0.0f);
	    }
	}

	private void setZombiePartInfo(int type, int nphysicstype, int nx, int ny, int nwidth, int nheight, 
			boolean fdynamic, boolean fman, float rotate, String str)
	{
	    ZombiePartInfo info = new ZombiePartInfo();
	    
	    if (type == GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE)
	        info.setZombiePartInfo(type, GameConfig.PhysicalType.ZOMBIEBOY_TYPE, nx, ny, nwidth, nheight, fdynamic, fman, rotate, str);    
	    else 
	        info.setZombiePartInfo(type, GameConfig.PhysicalType.ZOMBIEPART_TYPE, nx, ny, nwidth, nheight, fdynamic, fman, rotate, str);    
	    
	    m_zombieTypeArray.add(info);
	}

	private void setZombieRevoluteInfo(int nType, float x, float y, float width, float height, float rotate)
	{
	    ZombieRevoluteInfo info = new ZombieRevoluteInfo();
	    
	    info.setZombieRevoluteInfo(nType, x, y, width, height, rotate);
	    m_zombieJointArray.add(info);
	}

	private String getZombiePartName(int nType)
	{
	    String str = null;
	    
	    switch (nType)
	    {
	        case GameConfig.ZombiePartType.ALL_ZOOMBIE_TYPE:
	            str = String.format("%s.png", strZombieName[nZombieType]);
	            break;
	            
	        case GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE:
	            str = String.format("%s-head.png", strZombieName[nZombieType]);
	            break;
	            
	        case GameConfig.ZombiePartType.ZOMBIE_BODY_TYPE:
	            str = String.format("%s-body.png", strZombieName[nZombieType]);
	            break;
	            
	        case GameConfig.ZombiePartType.ZOMBIE_LEFT_ARM_TYPE:
	            str = String.format("%s-arm-left.png", strZombieName[nZombieType]);
	            break;
	            
	        case GameConfig.ZombiePartType.ZOMBIE_RIGHT_ARM_TYPE:
	            str = String.format("%s-arm-right.png", strZombieName[nZombieType]);
	            break;
	            
	        case GameConfig.ZombiePartType.ZOMBIE_LEFT_LEG_TYPE:
	            str = String.format("%s-leg-left.png", strZombieName[nZombieType]);
	            break;
	            
	        case GameConfig.ZombiePartType.ZOMBIE_RIGHT_LEG_TYPE:
	            str = String.format("%s-leg-right.png", strZombieName[nZombieType]);
	            break;
	            
	        default:
	            break;
	    }
	    
	    return str;
	}
}
