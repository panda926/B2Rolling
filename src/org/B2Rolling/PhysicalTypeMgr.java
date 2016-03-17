package org.B2Rolling;

import java.util.ArrayList;

import org.cocos2d.nodes.CCNode;

public class PhysicalTypeMgr extends CCNode {
	
	public ArrayList<PhysicalTypeInfo> m_physicalInfoArray;

	public static void createPhysicalTypeInfo() {
		G.g_physicalTypeMgr = new PhysicalTypeMgr();
	}
	
	private PhysicalTypeMgr() {
		m_physicalInfoArray = new ArrayList<PhysicalTypeInfo>();
		makePhysicsInfo();
	}
	
	private void makePhysicsInfo() {
	    PhysicalTypeInfo info;
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.STEELBALL_TYPE, 0.1f, 0.1f, 5.0f);
	  
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.STEEL_TYPE, 0.2f, 0.1f, 5.0f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.WOOD_TYPE, 0.1f, 0.1f, 0.5f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.STATIC_WOOD_TYPE, 0.8f, 0.1f, 0.5f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.CEMENT_TYPE, 0.2f, 0.1f, 1.8f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.STONE_TYPE, 0.2f, 0.1f, 1.8f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.ZOMBIE_TYPE, 1.0f, 0.0f, 0.05f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.ZOMBIEBOY_TYPE, 1.0f, 0.0f, 0.1f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.ZOMBIEMAN_TYPE, 1.0f, 0.0f, 0.1f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();    
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.ZOMBIEGIRL_TYPE, 1.0f, 0.0f, 0.1f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.ZOMBIEWOMAN_TYPE, 1.0f, 0.0f, 0.1f);
	    m_physicalInfoArray.add(info);
	//    
	    info = new PhysicalTypeInfo();
	    info.setPhysicalTypeInfo(GameConfig.PhysicalType.ZOMBIEPART_TYPE, 0.1f, 0.0f, 0.1f);
	    m_physicalInfoArray.add(info);
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		m_physicalInfoArray.removeAll(m_physicalInfoArray);
		super.onExit();
	}
	
	
}
