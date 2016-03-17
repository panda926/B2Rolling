package org.B2Rolling;

import java.util.ArrayList;

import org.cocos2d.nodes.CCNode;

public class LevelMgr extends CCNode {

	public ArrayList<LevelScore> m_levelInfo;
	public int nTotalScore;
	
	private LevelMgr() {
		m_levelInfo = new ArrayList<LevelScore>();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		if( m_levelInfo != null ) {
			m_levelInfo.removeAll(m_levelInfo);
			m_levelInfo = null;
		}
		super.onExit();
	}

	public void calcTotalScore()
	{
	    nTotalScore = 0;
	    LevelScore score;
	    
	    for (int i = 0; i < m_levelInfo.size(); i ++)
	    {
	        score = m_levelInfo.get(i);
	        nTotalScore += score.getScore();
	    }
	    
	    G.g_nTotalScore = nTotalScore;
	}

	public void resetScore(int nIdx, int nScore)
	{
	    LevelScore score;
	    
	    score = m_levelInfo.get(nIdx);    
	    score.setScore(nScore);
	}
	
	public static void createLevelMgr() {
	    G.g_levelMgr = new LevelMgr();
	}
}
