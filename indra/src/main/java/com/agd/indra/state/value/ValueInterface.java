package com.agd.indra.state.value;

import android.R.integer;
import lib.defines.GameEngineConfiguration;

public interface ValueInterface 
{
	static final float PUSHSCALE				= 0.9f;
	static final float DEFAULTSCALE				= 1.0f;
	
	//value background interface
	static final int OPTIONBACKGROUNDHEIGHT 	= 260;
	static final int OPTIONBACKGROUNDWIDTH		= 500;
	
	static final int CLOSINGBACKGROUNDHEIGHT	= 260;
	static final int CLOSINGBACKGROUNDWIDTH		= 500;
	
	static final int PAUSEBACKGROUNDHEIGHT		= 260;
	static final int PAUSEBACKGROUNDWIDTH		= 450;
	
	static final int SHOPBACKGROUNDHEIGHT		= GameEngineConfiguration.masterHeight;
	static final int SHOPBACKGROUNDWIDTH		= GameEngineConfiguration.masterWidth;
	
	static final int BGSHOPHEIGHT				= GameEngineConfiguration.masterHeight;
	static final int BGSHOPWIDTH				= GameEngineConfiguration.masterWidth;

	//value shop
	
	static final int SHOPBUTTONSHOPHEIGH		= 50;
	static final int SHOPBUTTONSHOPWIDTH		= 60;
	
	static final int SHOPBACKHEIGHT				= SHOPBUTTONSHOPHEIGH + 20;
	static final int SHOPBACKWIDTH				= SHOPBUTTONSHOPWIDTH + 70;
	
	static final int SHOPAPPLEHEIGHT			= 40;
	static final int SHOPAPPLEWIDTH				= SHOPAPPLEHEIGHT;
	
	static final int SHOPBUYHEIGHT				= SHOPAPPLEHEIGHT + 5;
	static final int SHOPBUYWIDTH				= SHOPAPPLEWIDTH + 50; 
	
	static final int SHOPPOCKETHEIGHT			= SHOPBUTTONSHOPHEIGH ;
	static final int SHOPPOCKETWIDTH			= SHOPBUTTONSHOPWIDTH + 50;
	
	static final int SHOPREVIVEICONHEIGHT		= 65;
	static final int SHOPREVIVEICONWIDTH		= 65;
	
	static final int SHOPDASHICONHEIGHT			= 65;
	static final int SHOPDASHICONWIDTH			= 65;
	
	//value option closing
	static final int CLOSINGYESBUTTONHEIGHT 	= 50;
	static final int CLOSINGYESBUTTONWIDTH		= 117;
	
	static final int CLOSINGNOBUTTONHEIGHT 		= 50;
	static final int CLOSINGNOBUTTONWIDTH		= 117;
	
	//value option Report
	static final int REPORTPLAYBUTTONHEIGHT		= 69;
	static final int REPORTPLAYBUTTONWIDTH		= 69;
	
	//value option BUTTON
	static final int GEARBUTTONHEIGHT 			= 65;
	static final int GEARBUTTONWIDTH 			= 63;
	
	static final int ONBUTTONHEIGHT				= 50;
	static final int ONBUTTONWIDTH				= 117;
	
	static final int OFFBUTTONHEIGHT			= 50;
	static final int OFFBUTTONWIDTH				= 117;
	
	static final int OPTIONCREDITBUTTONHEIGHT	= 38;
	static final int OPTIONCREDITBUTTONWIDTH	= 88;
	
	//value pause interface button
	
	static final int PAUSEPOCKETBUTTONHEIGHT	= 50;
	static final int PAUSEPOCKETBUTTONWIDTH 	= 117;
	
	static final int PAUSEOPTIONBUTTONHEIGHT	= 50;
	static final int PAUSEOPTIONBUTTONWIDTH 	= 117;
	
	static final int PAUSEEXITBUTTONHEIGHT		= 48;
	static final int PAUSEEXITBUTTONWIDTH  		= 117;
}
