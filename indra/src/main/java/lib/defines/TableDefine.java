package lib.defines;

import lib.element.ElementTable;

public interface TableDefine 
{
	int TABLE_SCORE  = 0;
	int TABLE_POINT  = TABLE_SCORE + 1;
	int TABLE_HIGHSC = TABLE_POINT + 1;
	int TABLE_PUP	 = TABLE_HIGHSC + 1;
	int TABLE_OPTION_GAME = TABLE_PUP +1;
	int TABLE_ITEM	= TABLE_OPTION_GAME +1;
	int TABLE_JUNGLE_DASH	= TABLE_ITEM +1;
	
	String scoreString = "score";
	
	ElementTable[] CONTAINER =
	{ 
		new ElementTable(scoreString, new String[]{"Id_Score", "Score"}, new String[]{"0","0"}),
		new ElementTable("Point", new String[]{"Id_Point", "Score_Point"}, new String[]{"0","0"}),
		new ElementTable("HighScore", new String[]{"Id_HighScore", "HighScore"}, new String[]{"0","0"}),
		new ElementTable("PP", new String[]{"Id_PP","Point_Up"}, new String[]{"0","0"}),
		new ElementTable("OptionGame", new String[]{"sound", "music"}, new String[]{"true","true"}),
		new ElementTable("itemGame", new String[]{"revive"}, new String[]{"0"}),
		new ElementTable("jungledash", new String[]{"jungle_dash"}, new String[]{"2"}),
	};
}
