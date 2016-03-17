package org.B2Rolling;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;



class CrashDetectUtils 
{
	
	public static int CRASH_NO = -1;
	public static int CRASH_LEFT = 0;
	public static int CRASH_RIGHT = 1;
	public static int CRASH_TOP = 2;
	public static int CRASH_BOTTOM = 3;
	public static int CRASH_YES = 4;
	
    

	
	
public int DetectRectAndRect(CGRect rtOrg, CGRect rtDest) 
{
	float fSX = rtOrg.origin.x + (rtOrg.size.width)/2;
	float fSY = rtOrg.origin.y + (rtOrg.size.height)/2;
    
	float fSXD = (rtOrg.size.width)/2;
	float fSYD = (rtOrg.size.height)/2;

	float fTX = rtDest.origin.x + (rtDest.size.width)/2;
	float fTY = rtDest.origin.y + (rtDest.size.height)/2;
    
	float fTXD = (rtDest.size.width)/2;
	float fTYD = (rtDest.size.height)/2;
	
	if ((Math.abs(fSX-fTX)-fSXD-fTXD) < 0 && (Math.abs(fSY-fTY)-fSYD-fTYD) < 0)
		return CRASH_YES;
	
	return CRASH_NO;
}

boolean DetectRectAndXY(CGRect rect, float x, float y)
{
	float left = rect.origin.x;
	float top = rect.origin.y;
	float right = rect.origin.x + rect.size.width;
	float bottom = rect.origin.y + rect.size.height;
	
	if (x > left && x < right &&
		y < top && y > bottom)
		return true;
	
	return false;
}

float CalcDistancePointAndPoint(CGPoint ptOrg, CGPoint ptDst)
{
	float fDistance = (float)Math.sqrt((double)(Math.pow(ptOrg.x-ptDst.x, 2) + Math.pow(ptOrg.y-ptDst.y, 2)));
	return fDistance;
}
 
static float CalcDistanceWithLinePoint(float x1, float y1, float x2, float y2, float x3, float y3)
{
	float px = x2-x1;
    float py = y2-y1;
	
    float something = px*px + py*py;
	
    float u =  ((x3 - x1) * px + (y3 - y1) * py) / something;
	
    if ( u > 1 )
        u = 1;
	else if (u < 0)
        u = 0;
		
	float x = x1 + u * px;
	float y = y1 + u * py;
		
	float dx = x - x3;
	float dy = y - y3;
		
	return (float)Math.sqrt(dx*dx + dy*dy);
}

static int DetectCircleAndRect(CGPoint ptOrg, float r, CGRect rtDest)
{
	float left = rtDest.origin.x;
	float top = rtDest.origin.y;
	float right = rtDest.origin.x + rtDest.size.width;
	float bottom = rtDest.origin.y + rtDest.size.height;
	
	float x = ptOrg.x;
	float y = ptOrg.y;
	
    float rTempR = r + (float)G.get(1);
    
	//TOP LINE DETECT
	float fDistance = CalcDistanceWithLinePoint(left, top, right, top, x, y);
	if (fDistance <= rTempR)
		return CRASH_TOP;
		
	//LEFT LINE DETECT
	fDistance = CalcDistanceWithLinePoint(left, top, left, bottom, x, y);
	if (fDistance <= rTempR)
		return CRASH_LEFT;

	//BOTTOM LINE DETECT
	fDistance = CalcDistanceWithLinePoint(left, bottom, right, bottom, x, y);
	if (fDistance <= rTempR)
		return CRASH_BOTTOM;
	
	//RIGHT LINE DETECT
	fDistance = CalcDistanceWithLinePoint(right, top, right, bottom, x, y);
	if (fDistance <= rTempR)
		return CRASH_RIGHT;
	
	return CRASH_NO;
}

int DetectCircleAndCircle(CGPoint ptOrg, float nOrgR,  CGRect ptDest, float nDestR)
{
	return CRASH_NO;
}
}

