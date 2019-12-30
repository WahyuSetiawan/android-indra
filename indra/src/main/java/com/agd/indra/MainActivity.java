package com.agd.indra;

import com.agd.indra.state.StateGameMenuIndra;
import com.agd.indra.state.StateGamePlayIndra;
import com.agd.indra.state.StateLoading;
import com.agd.indra.state.StateShop;
import com.agd.indra.state.StateStory;

import lib.engine.GameEngine;
import lib.engine.GameState;

public class MainActivity extends GameEngine 
{

	@Override
	protected void gameInit() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected GameState[] onCreateState() 
	{
		
		return new GameState[] 
				{
					new StateStory(this),
					new StateLoading(this),
					new StateGameMenuIndra(this),
					new StateGamePlayIndra(this),
					new StateShop(this),
				};
	}
	
}
