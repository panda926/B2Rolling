package org.B2Rolling;

import org.cocos2d.nodes.CCNode;

public class PhysicalTypeInfo extends CCNode {
	
	public int nPhyscalType;
	public float rFriction;
	public float rDensity;
	public float rRestitution;

	public void setPhysicalTypeInfo(int nType, float rfriction, float rrestutition, float rdensity)
	{
	    nPhyscalType = nType;
	    
	    rFriction = rfriction;
	    rDensity = rdensity;
	    rRestitution = rrestutition;
	}
}
