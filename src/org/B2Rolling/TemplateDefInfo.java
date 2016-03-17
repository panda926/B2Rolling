package org.B2Rolling;

import org.cocos2d.nodes.CCNode;

public class TemplateDefInfo extends CCNode {

	public int nTmplateType;
	public int nWidth;
	public int nHeight;
	public String fileName;
	public int nPhysicsType;

	public void setTmpDefInfo(int ntempType, int width, int height, String str , int physicsType)
	{
	    nTmplateType = ntempType;
	    
	    nWidth = (int) G.getX(width);
	    nHeight = (int) G.getY(height);
	    
	    fileName = String.format("%s", str);
	    
	    nPhysicsType = physicsType;
	}
}
