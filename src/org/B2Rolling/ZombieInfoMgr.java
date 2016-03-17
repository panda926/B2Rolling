package org.B2Rolling;

import java.util.ArrayList;

import org.cocos2d.nodes.CCNode;

public class ZombieInfoMgr extends CCNode {
	
	private final int MAX_ZOMBIE_NUM = 12;
	
	public ArrayList<ZombieInfo> m_zombieArray;

	private ZombieInfoMgr() {
		m_zombieArray = new ArrayList<ZombieInfo>();
		makeZombies();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		m_zombieArray.removeAll(m_zombieArray);
		super.onExit();
	}

	private void makeZombies()
	{
	    for (int i = 0; i < MAX_ZOMBIE_NUM; i ++)
	        setZombieInfo(i);
	}    

	private void setZombieInfo(int nType)
	{
	    ZombieInfo info = new ZombieInfo();
	    
	    info.makeZombieInfo(nType);
	    info.makeZombieJointInfo(nType);
	    m_zombieArray.add(info);
	}

	public static void createZombieInfoMgr() {
		G.g_zombieInfoMgr = new ZombieInfoMgr();
	}
}
