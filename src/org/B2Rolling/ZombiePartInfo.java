package org.B2Rolling;

import org.cocos2d.nodes.CCNode;

public class ZombiePartInfo extends CCNode {

	public boolean fCircle;
	public int nPartType;
	public int nPhysicalType;
	public float x;
	public float y;
	public float nWidth;
	public float nHeight;
	public float rRotate;
	public boolean fDynamic;
	public boolean fMan;
	public String strName;

	public ZombiePartInfo() {
		fCircle = false;
	}

	public void setZombiePartInfo(int type, int nphysicstype,  int nx, int ny, int nwidth, int nheight, 
			boolean fdynamic, boolean fman, float rotate, String str)
	{
	    nPartType = type;
	    nPhysicalType = nphysicstype;
	    
	    x = G.getX(nx); y = G.getY(ny); nWidth = G.getX(nwidth); nHeight = G.getY(nheight);
	    
	    rRotate = rotate;
	    
	    fDynamic = fdynamic; fMan = fman;
	    
	    strName = String.format("%s", str); 
	    
	    if (type == GameConfig.ZombiePartType.ZOMBIE_HEAD_TYPE)
	        fCircle = true;
	}
}
