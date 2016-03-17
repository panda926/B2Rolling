package org.B2Rolling;

public class ResourceManager {
	
	private static ResourceManager _shared = null;

	public static ResourceManager sharedResourceManager() {
		if( _shared == null )
			_shared = new ResourceManager();
		return _shared;
	}
	
	private ResourceManager() {
		PhysicalTypeMgr.createPhysicalTypeInfo();
		TemplateDefMgr.createTmpDefMgr();
		ZombieInfoMgr.createZombieInfoMgr();
		LevelMgr.createLevelMgr();
		SoundListMgr.createSoundListMgr();
		
		SoundManager.sharedSoundManager();
	}
}
