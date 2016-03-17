
package org.B2Rolling;

import java.util.ArrayList;

import org.cocos2d.config.ccMacros;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.types.CCTexParams;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;

public class VRope extends Object {
	public final static int PTM_RATIO = 32;
	int numPoints;
    
	public ArrayList<VPoint> vPoints;
	public ArrayList<VStick> vSticks;
	public ArrayList<CCSprite> ropeSprites;
	    
	CCSpriteSheet  spriteSheet;
    
	float antiSagHack;
    
	Body bodyA;
	Body bodyB;
    
    RopeJoint b2joint;
    
    float m_rOffsetX;
    float m_rOffsetY;

    
	public VRope(Body body1, Body body2, CCSpriteSheet spriteSheetArg ,float rOffsetX, float rOffsetY) 
	{
		bodyA = body1;
		bodyB = body2;
            
        m_rOffsetX = rOffsetX;
        m_rOffsetY = rOffsetY;
        
		CGPoint pointA = CGPoint.make(bodyA.getPosition().x*PTM_RATIO,bodyA.getPosition().y*PTM_RATIO);
		CGPoint pointB = CGPoint.make(bodyB.getPosition().x*PTM_RATIO + m_rOffsetX,bodyB.getPosition().y*PTM_RATIO + m_rOffsetY);
        
		spriteSheet = spriteSheetArg;
		createRope(pointA, pointB);
	}
	
	public VRope(RopeJoint joint,  CCSpriteSheet spriteSheetArg)
	{
	    b2joint = joint;
	    
	    bodyA = joint.getBodyA();
	    bodyB = joint.getBodyB();
	
	    Vector2 vec1 = joint.getAnchorA();
	    Vector2 Vector2 = joint.getAnchorB();
	    
	    CGPoint pointA = CGPoint.make(vec1.x*PTM_RATIO,vec1.y*PTM_RATIO);
		CGPoint pointB = CGPoint.make(Vector2.x*PTM_RATIO,Vector2.y*PTM_RATIO);
	    
	    spriteSheet = spriteSheetArg;
	    createRope(pointA, pointB);
	}
	
	public void reset ()
	{
		Vector2 vec1 = b2joint.getAnchorA();
	    Vector2 vec2 = b2joint.getAnchorB();
	    
	    CGPoint pointA = CGPoint.make(vec1.x*PTM_RATIO,vec1.y*PTM_RATIO);
	    CGPoint pointB = CGPoint.make(vec2.x*PTM_RATIO,vec2.y*PTM_RATIO);
	    
		resetWithPoints(pointA, pointB);
	}
	
	public void update(float dt) 
	{
		Vector2 vec1 = b2joint.getAnchorA();
	    Vector2 vec2 = b2joint.getAnchorB();
	    
	    CGPoint pointA = CGPoint.make(vec1.x*PTM_RATIO,vec1.y*PTM_RATIO);
	    CGPoint pointB = CGPoint.make(vec2.x*PTM_RATIO,vec2.y*PTM_RATIO);
	
		updateWithPoints(pointA, pointB, dt);
	}
	
	//initWithPoints
	public VRope(CGPoint pointA, CGPoint pointB, CCSpriteSheet spriteSheetArg )
	{
		spriteSheet = spriteSheetArg;
		createRope(pointA, pointB);
	}
	
	public void createRope(CGPoint pointA, CGPoint pointB) 
	{
		vPoints = new ArrayList<VPoint >();
		vSticks = new ArrayList<VStick >();
		ropeSprites = new ArrayList<CCSprite>();
	    
		float distance = CGPoint.ccpDistance(pointA,pointB);
	    
		int segmentFactor = (int)(G.getX(12.0f)) ; //increase value to have less segments per rope, decrease to have more segments
		numPoints = (int)(distance/segmentFactor);
		CGPoint diffVector = CGPoint.ccpSub(pointB,pointA);
		float multiplier = distance / (numPoints - 1);
		antiSagHack = 0.1f; //HACK: scale down rope points to cheat sag. set to 0 to disable, max suggested value 0.1
	
		for(int i = 0;i < numPoints; i ++) 
	    {
			CGPoint tmpVector = CGPoint.ccpAdd(pointA, CGPoint.ccpMult(CGPoint.ccpNormalize(diffVector),multiplier*i*(1-antiSagHack)));
			VPoint tmpPoint = new VPoint();
			tmpPoint.setPos(tmpVector.x, tmpVector.y);
			vPoints.add(tmpPoint);
		}
		
	    for(int i = 0; i < numPoints - 1; i ++) 
	    {
			VStick tmpStick = new VStick(vPoints.get(i),vPoints.get(i+1));
			vSticks.add(tmpStick);
		}
		
	    if(spriteSheet != null) 
	    {
			for(int i = 0;i < numPoints - 1; i ++) 
	        {
				VPoint point1 = vSticks.get(i).getPointA();
				VPoint point2 = vSticks.get(i).getPointB();
	            
				CGPoint stickVector = CGPoint.ccpSub(CGPoint.make(point1.x,point1.y),CGPoint.make(point2.x,point2.y));
				float stickAngle = CGPoint.ccpToAngle(stickVector);
	         
				CCSprite tmpSprite = CCSprite.sprite(spriteSheet, CGRect.make(0,0,multiplier,spriteSheet.getTextureAtlas().getTexture().pixelsHigh()));
	
				CCTexParams params = new CCTexParams(GL10.GL_LINEAR,GL10.GL_LINEAR,GL10.GL_REPEAT,GL10.GL_REPEAT);
					
				tmpSprite.getTexture().setTexParameters(params);
				tmpSprite.setPosition(CGPoint.ccpMidpoint(CGPoint.make(point1.x,point1.y),CGPoint.make(point2.x,point2.y)));
				tmpSprite.setRotation(-1 *  ccMacros.CC_RADIANS_TO_DEGREES(stickAngle));
	            
				spriteSheet.addChild(tmpSprite);
				ropeSprites.add(tmpSprite);
			}
		}
	}
	
	public void resetWithPoints(CGPoint pointA, CGPoint pointB) 
	{
		float distance = CGPoint.ccpDistance(pointA,pointB);
		CGPoint diffVector = CGPoint.ccpSub(pointB,pointA);
	    
		float multiplier = distance / (numPoints - 1);
	    
	    for(int i = 0; i < numPoints; i ++) 
	    {
			CGPoint tmpVector = CGPoint.ccpAdd(pointA, CGPoint.ccpMult(CGPoint.ccpNormalize(diffVector),multiplier*i*(1-antiSagHack)));
			VPoint tmpPoint = vPoints.get(i);
			tmpPoint.setPos(tmpVector.x,tmpVector.y);		
		}
	}
	
	public void removeSprites() 
	{
		for(int i = 0;i < numPoints - 1;i ++) 
	    {
			CCSprite tmpSprite = ropeSprites.get(i);
			spriteSheet.removeChild(tmpSprite, true);
		}
	    
		ropeSprites.removeAll(ropeSprites);
		
	}
	
	public boolean cutRope(CGPoint cutPoint, Body bodA, Body bodB)
	{
	 	for(int i = 0; i < numPoints - 1;i ++) 
	    {		
	        CCSprite tmpSprite = ropeSprites.get(i);
	        
	        float fDistance = (float)Math.sqrt(Math.pow(cutPoint.x-tmpSprite.getPosition().x, 2) + Math.pow(cutPoint.y-tmpSprite.getPosition().y, 2));
	        
	        if (fDistance < G.get(10) && bodA == bodyA && bodB == bodyB) 
	            return true;
		}
	    
	    return false;
	}
	
	public void updateWithPoints(CGPoint pointA, CGPoint pointB,  float dt )
	{
		//manually set position for first and last point of rope
		vPoints.get(0).setPos(pointA.x, pointA.y);
		vPoints.get(numPoints-1).setPos(pointB.x, pointB.y);
		
		//update points, apply gravity
		for(int i=1;i<numPoints-1;i++) 
	    {
			vPoints.get(i).applyGravity(dt);
			vPoints.get(i).update();
		}
		
		//contract sticks
		int iterations = 4;
		for(int j=0;j<iterations;j++) 
	    {
			for(int i=0;i<numPoints-1;i++) 
	        {
				vSticks.get(i).contract();
			}
		}
	}
	
	public void updateSprites() 
	{
		if(spriteSheet!=null) 
	    {
			for(int i=0;i<numPoints-1;i++) 
	        {
				VPoint point1 = vSticks.get(i).getPointA();
				VPoint point2 = vSticks.get(i).getPointB();
	            
				CGPoint point1_ = CGPoint.make(point1.x,point1.y);
				CGPoint point2_ = CGPoint.make(point2.x,point2.y);
	            
				float stickAngle = CGPoint.ccpToAngle(CGPoint.ccpSub(point1_,point2_));
	            
				CCSprite tmpSprite = ropeSprites.get(i);
	            
				tmpSprite.setPosition(CGPoint.ccpMidpoint(point1_,point2_));
				tmpSprite.setRotation(-ccMacros.CC_RADIANS_TO_DEGREES(stickAngle));
			}
		}	
	}
	/*
	public void debugDraw() 
	{
		//Depending on scenario, you might need to have different Disable/Enable of Client States
		//glDisableClientState(GL_TEXTURE_2D);
		//glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		//glDisableClientState(GL_COLOR_ARRAY);
		//set color and line width for ccDrawLine
		glColor4f(0.0f,0.0f,1.0f,1.0f);
		glLineWidth(5.0f);
		for(int i=0;i<numPoints-1;i++) {
			//"debug" draw
			VPoint pointA = vSticks.get(i).getPointA();
			VPoint pointB = vSticks.get(i).getPointB();
			ccDrawPoint(ccp(pointA.x,pointA.y));
			ccDrawPoint(ccp(pointB.x,pointB.y));
			//ccDrawLine(ccp(pointA.x,pointA.y),ccp(pointB.x,pointB.y));
		}
		//restore to white and default thickness
		glColor4f(1.0f,1.0f,1.0f,1.0f);
		glLineWidth(1);
		//glEnableClientState(GL_TEXTURE_2D);
		//glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		//glEnableClientState(GL_COLOR_ARRAY);
	}
	*/
	public Body getBodyA()
	{
	    return bodyA;
	}
	
	public Body getBodyB()
	{
	    return bodyB;
	}
	
	public boolean isValidGameOverState()
	{
	    SpriteInfo info1 = (SpriteInfo )bodyA.getUserData();
	    SpriteInfo info2 = (SpriteInfo )bodyB.getUserData();
	    
	    if ((info1.nTagName >= GameConfig.GameSpriteTag.BALL1 && info1.nTagName <= GameConfig.GameSpriteTag.BALL10) && (info2.nTagName >= GameConfig.GameSpriteTag.BALL1 && info2.nTagName <= GameConfig.GameSpriteTag.BALL10))
	        return true;
	    else 
	        return false;
	}

}
