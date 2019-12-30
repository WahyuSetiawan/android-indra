package com.agd.indra.state;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.bounds.IFloatBounds;

import com.agd.indra.state.value.StateDefine;
import com.agd.indra.state.value.ValueInterface;
import com.agd.indra.state.value.ValueShop;

import android.R.integer;
import android.R.layout;
import android.R.string;
import android.view.KeyEvent;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.elementgame.Mfx;
import lib.elementgame.Sfx;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateShop extends GameState implements ValueInterface, StateDefine, ValueShop{
	
	private GameText[] pocket		= new GameText[4];
	private GameSprite apple;
	private GameSprite[] shop_buy 	= new GameSprite[4];
	
	private GameText applePoint;
	private GameSprite appleicon;
	
	private GameSprite shop_background;
	private GameSprite shop_back;
	private GameSprite shop_pocket;
	private GameSprite shop_shop;
	private GameSprite shop_apple;
	
	public StateShop(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent() {
		shop_background		= new GameSprite(BG_POCKET, engine);
		shop_back			= new GameSprite(SHOP_BACK, engine);
		shop_pocket			= new GameSprite(SHOP_POCKET, engine);
		shop_shop 			= new GameSprite(SHOP_SHOP, engine);
		shop_apple			= new GameSprite(SHOP_APPLE, engine);
		
		appleicon			= new GameSprite(STAT_APEL,engine);
		applePoint			= new GameText("",12, engine.getFont(FONT_FRAHV), engine);
		
		for (int i = 0; i < shop_buy.length; i++) {
			pocket[i] = new GameText("", 15, engine.getFont(FONT_FRAHV), engine);
			shop_buy[i] = new GameSprite(SHOP_BUY, engine);
		}
	}

	@Override
	protected void init() {
		showConfig();
		showShop();
		
		for (int i = 0; i < shop_buy.length; i++) {
			shop_buy[i].setScale(1);
		}
		
		pocket[0].setText(String.valueOf(getJungleDash()));
		applePoint.setText(String.valueOf(getApple()));
		
		
	}

	@Override
	protected void attach() {
		engine.scene.attachChild(shop_background);
		shop_background.attachChild(shop_back);
		shop_background.attachChild(shop_pocket);
		shop_background.attachChild(shop_shop);
		shop_background.attachChild(shop_apple);
		
		shop_background.attachChild(appleicon);
		shop_background.attachChild(applePoint);
		
		for (int j = 0; j < shop_buy.length; j++) {
			shop_background.attachChild(pocket[j]);
			shop_background.attachChild(shop_buy[j]);
			
		}
	}

	@Override
	protected void detach() {
		shop_background.detachSelf();
		
		shop_back.detachSelf();
		shop_pocket.detachSelf();
		shop_shop.detachSelf();
		shop_apple.detachSelf();
		
		applePoint.detachSelf();
		appleicon.detachSelf();
		
		for (int j = 0; j < shop_buy.length; j++) {
			pocket[j].detachSelf();
			shop_buy[j].detachSelf();
		}
	}

	@Override
	protected void setPosition() {
		shop_background.setPosition(Anchor.CENTER);
		shop_pocket.setPosition(GameEngineConfiguration.masterWidth /17 , 2 * GameEngineConfiguration.masterHeight / 8 - 30);
		shop_shop.setPosition(shop_pocket.getX(), shop_pocket.getY() + 3*shop_pocket.getHeight() - 30);
		shop_apple.setPosition(shop_pocket.getX() + 400, shop_pocket.getY());
		shop_back.setPosition(GameEngineConfiguration.masterWidth - shop_back.getWidth() - 85, GameEngineConfiguration.masterHeight - shop_back.getHeight() - 60);
		
		appleicon.setPosition(GameEngineConfiguration.masterWidth/5 + 150,GameEngineConfiguration.masterHeight - 175);
		applePoint.setPosition(appleicon.getX() + appleicon.getWidth(), appleicon.getY());
		
		for (int i = 0; i < shop_buy.length; i++) {
			if(i == 0)
			{
				shop_buy[i].setPosition((GameEngineConfiguration.masterWidth /2) + shop_buy[i].getWidth() + 35, (GameEngineConfiguration.masterHeight /2 )- 100 - shop_buy[i].getHeight());
				pocket[i].setPosition(shop_buy[i].getX() - 50, shop_buy[i].getY() + 10);
			}
			else 
			{
				shop_buy[i].setPosition(shop_buy[i-1].getX(), shop_buy[i-1].getY() + shop_buy[i].getHeight());
				pocket[i].setPosition(shop_buy[i].getX(), shop_buy[i].getY());
			}
			
			if(i>0){
				shop_buy[i].setVisible(false);
			}
		}
	}

	@Override
	protected void registerTouch() {
		engine.hud.registerTouchArea(shop_back);
		engine.hud.registerTouchArea(shop_pocket);
		engine.hud.registerTouchArea(shop_shop);
		
		for (int i = 0; i < shop_buy.length; i++) {
			engine.hud.registerTouchArea(shop_buy[i]);
		}
	}

	@Override
	protected void unregisterTouch() {
		engine.unregisterSceneTouch(shop_back);
		engine.unregisterSceneTouch(shop_pocket);
		engine.unregisterSceneTouch(shop_shop);
		
		for (int i = 0; i < shop_buy.length; i++) {
			engine.unregisterHudTouch(shop_buy[i]);
		}
	}

	@Override
	protected void onUpdate() {
		

	}

	@Override
	protected void onPaused() {
		Mfx.PauseAll();
		Sfx.PauseAll();

	}

	@Override
	protected void onResumed() {
		

	}

	@Override
	public void onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			exitState(STATE_MENU);
		}
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_DOWN:
			if(pTouchArea == shop_back && engine.getCurrState() == STATE_SHOP){
				shop_back.setScale(PUSHSCALE);
			}
			else if(pTouchArea == shop_shop){
				shop_shop.setScale(PUSHSCALE);
			}
			else if(pTouchArea == shop_pocket){
				shop_pocket.setScale(PUSHSCALE);
			}
			
			for (int i = 0; i < shop_buy.length; i++) {
				if (pTouchArea == shop_buy[i]) {
					if (pTouchArea == shop_buy[i] && shop_buy[i].getScaleX() != PUSHSCALE && i==0 && getApple() >= 100) {
						try{
							engine.getDatabase().updateData(TABLE_PUP, new int[]{i + 1}, new String[]{String.valueOf(getApple() - 100)}, "");
							
							engine.getDatabase().updateData(TABLE_JUNGLE_DASH, new int[]{i}, 
									new String[] {String.valueOf(getJungleDash() + 1)} , "");
							
							applePoint.setText(String.valueOf(getApple()));
						}catch(NullPointerException p){
							System.out.println("salah memasukkan data terjadi interuption");
						}
					}
					shop_buy[i].setScale(PUSHSCALE);
				}
			}
			break;
		case TouchEvent.ACTION_UP:
			if(pTouchArea == shop_back && engine.getCurrState() == STATE_SHOP){
				shop_back.setScale(DEFAULTSCALE);
				
				exitState(STATE_MENU);
			}
			else if(pTouchArea == shop_shop){
				shop_shop.setScale(1);
				
				showShop();
			}
			else if(pTouchArea == shop_pocket){
				shop_pocket.setScale(1);
				showPocket();
			}
			
			for (int i = 0; i < shop_buy.length; i++) {
				if (pTouchArea == shop_buy[i]) {
					shop_buy[i].setScale(DEFAULTSCALE);
				}
			}
			break;
		}
		
		return false;
	}
	
	protected void showPocket() {
		for (int i = 0; i < pocket.length; i++) {
			shop_apple.setVisible(false);
			pocket[i].setVisible(true);
			shop_buy[i].setVisible(false);
		}
		
		pocket[0].setText(String.valueOf(getJungleDash()) + " X");
	}
	
	protected void showShop() {
		for (int i = 0; i < shop_buy.length; i++) {
			shop_buy[i].setHeight(SHOPBUYHEIGHT);
			shop_buy[i].setWidth(SHOPBUYWIDTH);
			
			pocket[i].setVisible(false);
			shop_buy[i].setVisible(true);
			shop_apple.setVisible(true);
			
			if(i>0){
				shop_buy[i].setVisible(false);
			}
		}
		
		pocket[0].setText(String.valueOf(getItem(0)) + " X");
	}
	
	protected void showConfig() 
	{		
		shop_background.setHeight(SHOPBACKGROUNDHEIGHT);
		shop_background.setWidth(SHOPBACKGROUNDWIDTH);
		
		shop_back.setHeight(SHOPBACKHEIGHT);
		shop_back.setWidth(SHOPBACKWIDTH);
		
		shop_pocket.setHeight(SHOPPOCKETHEIGHT);
		shop_pocket.setWidth(SHOPPOCKETWIDTH);
		
		shop_shop.setWidth(SHOPPOCKETWIDTH);
		shop_shop.setHeight(SHOPPOCKETHEIGHT);
	}
	
	protected int getApple(){
		int apple = 0;
		try{
			apple = Integer.parseInt(engine.getDatabase().getData(TABLE_PUP, 0, 1));
		}catch(NullPointerException p){
			engine.getDatabase().dropTable(TABLE_PUP);
			apple = Integer.parseInt(engine.getDatabase().getData(TABLE_PUP, 0, 1));
		}
		engine.getDatabase().print(TABLE_PUP);
		return apple;
	}
	
	protected int getItem(int index) {
		int item;
		
		try{
			item = Integer.parseInt(engine.getDatabase().getData(TABLE_ITEM, 0, index));
		}catch(NullPointerException e){
			engine.getDatabase().dropTable(TABLE_ITEM);
			item = Integer.parseInt(engine.getDatabase().getData(TABLE_ITEM, 0, index));
		}
		
		engine.getDatabase().print(TABLE_ITEM);
		
		return item;
	}
	
	protected int getJungleDash() {
		int item;
		
		try {
			item = Integer.parseInt(engine.getDatabase().getData(TABLE_JUNGLE_DASH, 0, 0));
		} catch (Exception e) {
			engine.getDatabase().dropTable(TABLE_JUNGLE_DASH);
			item = Integer.parseInt(engine.getDatabase().getData(TABLE_JUNGLE_DASH, 0, 0));
		}
		
		engine.getDatabase().print(TABLE_JUNGLE_DASH);
		
		return item;
	}
}
