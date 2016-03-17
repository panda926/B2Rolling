package org.B2Rolling;

import org.cocos2d.nodes.CCNode;

public class SoundList extends CCNode {

	public int physicsType1;
	public int physicsType2;
	public int lightSound;
	public int heavySound;

	public void setSoundListInfo(int nType1, int nType2, int lightsoundID, int heavySoundID)
	{
	    physicsType1 = nType1;
	    physicsType2 = nType2;
	    
	    lightSound = lightsoundID;
	    heavySound = heavySoundID;
	}

	public boolean isValidSoundID(int type1, int type2)
	{
	    if (type1 == physicsType1 && type2 == physicsType2)
	        return true;
	    else if (type1 == physicsType2 && type2 == physicsType1)
	        return true;
	    else 
	        return false;
	}
}
