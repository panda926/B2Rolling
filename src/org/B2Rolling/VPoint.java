package org.B2Rolling;


public class VPoint extends Object {
		float x,y,oldx,oldy;

		public void setPos(float argX, float argY) 
		{
			x = oldx = argX;
			y = oldy = argY;
		}
		
		public  void update() 
		{
			float tempx = x;
			float tempy = y;
		    
			x += x - oldx;
			y += y - oldy;
		    
			oldx = tempx;
			oldy = tempy;
		}
		
		public void applyGravity(float dt) 
		{
			y -= 10.0f*dt; //gravity magic number
		}
		
		public void setX(float argX) 
		{
			x = argX;
		}
		
		public void setY(float argY)
		{
			y = argY;
		}
		
		public float getX() 
		{
			return x;
		}
		
		public float getY() 
		{
			return y;
		}

}
