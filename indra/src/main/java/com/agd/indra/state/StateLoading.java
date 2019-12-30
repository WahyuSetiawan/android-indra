package com.agd.indra.state;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import com.agd.indra.state.value.StateDefine;

import android.view.KeyEvent;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameSprite;
import lib.elementgame.Mfx;
import lib.elementgame.Sfx;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateLoading extends GameState implements StateDefine
{
	private GameSprite splash;
	private GameSprite miniBar;

	public StateLoading(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent() 
	{
		splash 		= new GameSprite(SPLASH, engine);
		miniBar		= new GameSprite(LOAD_BAR_MINI, engine);	
	}

	@Override
	protected void init() 
	{
		miniBar.setHeight(36);
		miniBar.setWidth(0);	
	}

	@Override
	protected void attach() 
	{
		engine.scene.attachChild(splash);
		engine.scene.attachChild(miniBar);	
	}

	@Override
	protected void detach() 
	{
		splash.detachSelf();
		miniBar.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		splash.setPosition(0,0);
		miniBar.setPosition(GameEngineConfiguration.masterWidth / 6 , 412 - 5);
	}

	@Override
	protected void registerTouch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void unregisterTouch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onUpdate() 
	{
		if(miniBar.getX() + miniBar.getWidth() > engine.camera.getWidth() - (engine.camera.getWidth() / 6))
		{
			exitState(STATE_MENU);
		}
		else
		{
			miniBar.setWidth(miniBar.getWidth() + 2);
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
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		return false;
	}

}
