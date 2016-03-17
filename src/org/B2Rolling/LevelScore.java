package org.B2Rolling;

import org.cocos2d.nodes.CCNode;

public class LevelScore extends CCNode {

	public int nScore;

	public LevelScore() {
		nScore = 0;
	}

	public void setScore(int score)
	{
	    if (score >= nScore)
	        nScore = score;
	}

	public int getScore()
	{
	    return nScore;
	}
}
