package org.B2Rolling;

import org.cocos2d.nodes.CCNode;

public class ZombieSoundList extends CCNode {

	public int nZombieType;
	public int lightSound;
	public int heavySound;

	public void setZombieSoundListInfo(int nzombietype, int lightsoundID, int heavysoundID)
	{
	    nZombieType = nzombietype;
	    
	    lightSound = lightsoundID;
	    heavySound = heavysoundID;
	}
}
