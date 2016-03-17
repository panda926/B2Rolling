//
//  VStick.h
//
//  Created by patrick on 14/10/2010.
//
package org.B2Rolling;

import org.cocos2d.types.CGPoint;


public class VStick extends Object {
	
	VPoint pointA;
	VPoint pointB;
	float hypotenuse;

	public VStick(VPoint argA, VPoint argB ){
		{
			pointA = argA;
			pointB = argB;
			hypotenuse = CGPoint.ccpDistance(CGPoint.make(pointA.x,pointA.y),  CGPoint.make(pointB.x,pointB.y));
		}
		
	}
	
	public void contract()
	{
		float dx = pointB.x - pointA.x;
		float dy = pointB.y - pointA.y;
		float h = CGPoint.ccpDistance(CGPoint.ccp(pointA.x,pointA.y),CGPoint.ccp(pointB.x,pointB.y));
		
	    float diff = hypotenuse - h;
	    
		float offx = (diff * dx / h) * 0.5f;
		float offy = (diff * dy / h) * 0.5f;
	    
		pointA.x-=offx;
		pointA.y-=offy;
		pointB.x+=offx;
		pointB.y+=offy;
	}
	
	public VPoint getPointA() {
		return pointA;
	}
	
	public VPoint getPointB() {
		return pointB;
	}
}
