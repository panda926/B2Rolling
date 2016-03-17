package org.B2Rolling;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class B2Rolling extends Activity {
    private CCGLSurfaceView mGLSurfaceView;
	static{ 
		System.loadLibrary("gdx");
	};
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		G.saveScoreInfoXML();
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SoundManager.sharedSoundManager().pauseBackgroundMusic();

		CCDirector.sharedDirector().onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SoundManager.sharedSoundManager().resumeBackgroundMusic();

		CCDirector.sharedDirector().onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();	 	       
        
		CCDirector.sharedDirector().setDisplayFPS(false);
        getScaledCoordinate();    	
		ResourceManager.sharedResourceManager();
		GameSetting.initialize();
		G.loadScoreInfoXML();
		 		
		CCScene scene = CCScene.node();		
		scene.addChild(new MenuScene());
		CCDirector.sharedDirector().runWithScene(scene);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		G.saveScoreInfoXML();
	}
	


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        System.loadLibrary("gdx");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       
		mGLSurfaceView = new CCGLSurfaceView(this);
		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(mGLSurfaceView);
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		setContentView(mGLSurfaceView); 
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 60.0f);
		       
    }    
    private void getScaledCoordinate() {
    	
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		
		G.width = displayMetrics.widthPixels; G.height = displayMetrics.heightPixels;
		G.scale_x = G.width / 480.0f; G.scale_y = G.height / 320.0f;
    }
}