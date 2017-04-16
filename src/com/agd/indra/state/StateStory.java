package com.agd.indra.state;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import com.agd.indra.state.value.StateDefine;

import android.view.KeyEvent;
import lib.elementgame.GameSprite;
import lib.elementgame.Mfx;
import lib.elementgame.Sfx;
import lib.engine.GameEngine;
import lib.engine.GameState;
import lib.engine.TimeCapturer;

public class StateStory extends GameState implements StateDefine
{
	private GameSprite[] stories;
	private TimeCapturer capturer = new TimeCapturer();
	private final long DURATION = 4000;
	private int curr;

	public StateStory(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent() 
	{
		stories = new GameSprite[]
				{
				new GameSprite(STORY_FIVE, engine),
				new GameSprite(STORY_FOURTH, engine),
				new GameSprite(STORY_THIRD, engine),
				new GameSprite(STORY_SECOND, engine),
				new GameSprite(STORY_FIRST, engine),
				new GameSprite(AGD_SPLASH, engine),
				};
	}

	@Override
	protected void init() 
	{
		capturer.captureTime();
		curr = stories.length - 1;	
	}

	@Override
	protected void attach() 
	{
		for (GameSprite story : stories) 
		{
			engine.hud.attachChild(story);
		}	
	}

	@Override
	protected void detach() 
	{
		for(GameSprite story : stories)
		{
			story.detachSelf();
		}	
	}

	@Override
	protected void setPosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void registerTouch() 
	{
		engine.hud.registerTouchArea(stories[0]);
	}

	@Override
	protected void unregisterTouch() 
	{
		engine.hud.unregisterTouchArea(stories[0]);
	}

	@Override
	protected void onUpdate() 
	{
		if(capturer.isTimePassed(DURATION))
		{
			if(curr == 0)
			{
				exitState(STATE_LOADING); // next state
				return;
			}
			
			System.out.println("curr : " + curr);
			stories[curr].setVisible(false);
			curr--;
			
			capturer.captureTime();
		}	
	}

	@Override
	protected void onPaused() 
	{
		Mfx.PauseAll();
		Sfx.PauseAll();
	}

	@Override
	protected void onResumed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) 
	{
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_UP:
			if(pTouchArea == stories[0] && engine.getCurrState() == STATE_STORY){
				exitState(STATE_LOADING);
			}
		break;
		}
		return false;
	}

}
