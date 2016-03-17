package org.B2Rolling;

import org.cocos2d.nodes.CCNode;

public class ZombieRevoluteInfo extends CCNode {

	public int nZombieJointType;
	public float rX;
	public float rY;
	public float rWidth;
	public float rHeight;
	public float rRotate;

	public void setZombieRevoluteInfo(int nType, float x, float y, float width, float height, float rotate)
	{
	    nZombieJointType = nType;
	    
	    rX = G.getX(x); rY = G.getY(y); rWidth = G.getX(width); rHeight = G.getY(height);
	    rRotate = rotate;
	}
}
