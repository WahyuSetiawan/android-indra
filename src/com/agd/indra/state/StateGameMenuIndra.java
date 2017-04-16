package com.agd.indra.state;

import javax.security.auth.PrivateCredentialPermission;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import com.agd.indra.state.value.StateDefine;
import com.agd.indra.state.value.ValueCamera;
import com.agd.indra.state.value.ValueInterface;
import com.agd.indra.state.value.ValueMenu;
import com.agd.indra.state.value.ValuePlayer;

import android.R.menu;
import android.view.KeyEvent;
import android.view.Menu;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameAnim;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.elementgame.Mfx;
import lib.elementgame.Sfx;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGameMenuIndra extends GameState implements ValueCamera, ValuePlayer, StateDefine, ValueMenu, ValueInterface
{
	private GameSprite bg_belakang;
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite[] bg_tengah		= new GameSprite[2];
	private GameSprite[] bg_depan		= new GameSprite[2];
	
	private GameSprite closing_background;
	private GameSprite closing_yes;
	private GameSprite closing_no;
	
	private GameAnim player;
	
	private GameSprite option_background;
	private GameSprite option_gear;
	private GameSprite option_credit;
	private GameSprite option_off_sfx;
	private GameSprite option_off_bgm;
	private GameSprite option_on_sfx;
	private GameSprite option_on_bgm;
	private GameSprite credit;
	private GameSprite bg_credit;
	
	private GameSprite applesprite;
	
	private GameSprite shop;
	private GameSprite tap_to_play;
	
	private GameSprite title;
	
	private GameText text_highscore;
	private GameText text_apple;
	private GameText frontHS;

	public StateGameMenuIndra(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initComponent() 
	{
		bg_belakang		= new GameSprite(BG_BELAKANG, engine);
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop] 	= new GameSprite(BG_TENGAH, engine);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop] = new GameSprite(BG_FLOOR_DEPAN, engine);
		}
		
		for (int loop = 0; loop < bg_depan.length; loop++)
		{
			bg_depan[loop] 		= new GameSprite(BG_DEPAN, engine);
		}
		
		closing_background 	= new GameSprite(CLOSING_BG, engine);
		closing_yes			= new GameSprite(CLOSING_YES, engine);
		closing_no 			= new GameSprite(CLOSING_NO, engine);
		
		option_gear			= new GameSprite(OPTION_GEAR, engine);
		option_background	= new GameSprite(OPTION_BG, engine);
		option_credit		= new GameSprite(OPTION_CREDIT, engine);
		option_off_sfx		= new GameSprite(OPTION_OFF, engine);
		option_off_bgm		= new GameSprite(OPTION_OFF, engine);
		option_on_sfx		= new GameSprite(OPTION_ON, engine);
		option_on_bgm		= new GameSprite(OPTION_ON, engine);
		
		title 				= new GameSprite(TITLE, engine);
		
		credit				= new GameSprite(CREDIT, engine);
		bg_credit			= new GameSprite(BG_CREDIT, engine);
		
		shop				= new GameSprite(SHOP_SHOP, engine);
		tap_to_play			= new GameSprite(TAP_TO_PLAY, engine);
		
		player 				= new GameAnim(ANIM_PLAYER_LARI, engine);
		
		String strscore 	= engine.getDatabase().getData(TABLE_HIGHSC, 0, 1);
		
		applesprite 		= new GameSprite(STAT_APEL, engine);
		text_apple			= new GameText("",  12,engine.getFont(FONT_LADYIC2), engine);
		text_highscore		= new GameText("", 12, engine.getFont(FONT_LADYIC2), engine);
		frontHS 			= new GameText("Highscore : ", 12, engine.getFont(FONT_LADYIC2), engine);
	}

	@Override
	protected void init() 
	{
		GameEngineConfiguration.useSound = getSound();
		GameEngineConfiguration.useMusic = getMusic();
		
		engine.camera.setCenter(CAMERA_CENTER_X, CAMERA_CENTER_Y);
		
		this.option_gear.setHeight(SHOPBUTTONSHOPHEIGH + 10);
		this.option_gear.setWidth(SHOPBUTTONSHOPWIDTH + 10);
		
		this.shop.setHeight(SHOPBUTTONSHOPHEIGH + 10);
		this.shop.setWidth(SHOPBUTTONSHOPWIDTH + 66);
		
		text_highscore.setText(String.valueOf(getHighScore()));
		text_apple.setText(String.valueOf(getApple()));
		
		bg_credit.setHeight(GameEngineConfiguration.masterHeight);
		bg_credit.setWidth(GameEngineConfiguration.masterWidth);
	
		bg_credit.setVisible(false);
		
		applesprite.setHeight(35);
		applesprite.setWidth(35);
		
		this.title.setWidth(475);
		this.title.setHeight(190);
		
		player.animate(200, true);
		player.setVisible(false);
		
		showClosing();
		showOption();
		closeClosing();
		closeOption();
		
		Mfx.Resume(MUSIC_MAINMENU);
		
		
		
	}
	
	protected int getHighScore() {
		int strscore;
		try {
			strscore = Integer.parseInt(engine.getDatabase().getData(TABLE_HIGHSC, 0, 1));
		} catch (NullPointerException s) {
			engine.getDatabase().dropTable(TABLE_HIGHSC);
			strscore = Integer.parseInt(engine.getDatabase().getData(TABLE_HIGHSC, 0, 1));
		}
		return strscore;
	}
	
	protected int getApple() {
		int appleint;
		try{
			appleint =  Integer.parseInt(engine.getDatabase().getData(TABLE_PUP, 0, 1));
		}catch(NullPointerException p){
			engine.getDatabase().dropTable(TABLE_PUP);
			appleint =  Integer.parseInt(engine.getDatabase().getData(TABLE_PUP, 0, 1));
		}
		
		return appleint;
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

	@Override
	protected void attach() 
	{
		engine.scene.attachChild(bg_belakang);
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_tengah[loop]);
		}
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_depan[loop]);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			engine.scene.attachChild(bg_floor_depan[loop]);
		}
		engine.scene.attachChild(player);
		
		engine.scene.attachChild(title);
		engine.scene.attachChild(tap_to_play);
		
		engine.hud.attachChild(text_highscore);
		engine.hud.attachChild(frontHS);
		engine.hud.attachChild(option_gear);
		
		engine.hud.attachChild(option_background);
		option_background.attachChild(option_off_sfx);
		option_background.attachChild(option_on_sfx);
		option_background.attachChild(option_off_bgm);
		option_background.attachChild(option_on_bgm);
		option_background.attachChild(option_credit);
		
		engine.hud.attachChild(applesprite);
		engine.hud.attachChild(text_apple);
		
		engine.hud.attachChild(closing_background);
		closing_background.attachChild(closing_yes);
		closing_background.attachChild(closing_no);
		
		engine.scene.attachChild(shop);
		engine.scene.attachChild(bg_credit);
		bg_credit.attachChild(credit);
		
	}

	@Override
	protected void detach() 
	{
		bg_belakang.detachSelf();
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop].detachSelf();
		}
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_depan[loop].detachSelf();
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop].detachSelf();
		}
		
		text_highscore.detachSelf();
		frontHS.detachSelf();
		tap_to_play.detachSelf();
		
		option_gear.detachSelf();
		option_background.detachSelf();
		option_background.detachChildren();
		
		closing_background.detachSelf();
		closing_no.detachSelf();
		closing_yes.detachSelf();
		
		text_apple.detachSelf();
		applesprite.detachSelf();
		
		player.detachSelf();
		
		title.detachSelf();
		credit.detachSelf();
		bg_credit.detachSelf();
		shop.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		bg_belakang.setPosition(0,0);
		
		bg_tengah[0].setX(0);
		bg_tengah[1].setX(bg_tengah[0].getWidth());
		
		bg_depan[0].setX(0);
		bg_depan[1].setX(bg_depan[0].getWidth());
		
		bg_floor_depan[0].setX(0);
		bg_floor_depan[1].setX(bg_floor_depan[0].getWidth());
		
		frontHS.setPosition(Anchor.TOP_LEFT);
		text_highscore.setPosition(frontHS.getX() + 125, frontHS.getY());
		applesprite.setPosition(frontHS.getX(), frontHS.getY() + 50);
		text_apple.setPosition(applesprite.getX() + applesprite.getWidth() + 10, applesprite.getY() - 5);
		
		option_gear.setPosition(GameEngineConfiguration.masterWidth - 30 - option_gear.getWidth() , 5);
		option_background.setPosition(Anchor.CENTER);
		option_off_sfx.setPosition(350, 120);
		option_on_sfx.setPosition(option_off_sfx.getX(), option_off_sfx.getY());
		option_off_bgm.setPosition(option_off_sfx.getX(), option_off_sfx.getY() + 55);
		option_on_bgm.setPosition(option_off_bgm.getX(), option_off_bgm.getY());
		option_credit.setPosition(option_off_bgm.getX() - (option_credit.getWidth()+ 200), option_off_bgm.getY() + 23);
		
		title.setPosition((GameEngineConfiguration.masterWidth/2)-(title.getWidth()/2),-25);
		
		closing_background.setPosition(Anchor.CENTER);
		closing_yes.setPosition(215, 160);
		closing_no.setPosition(closing_yes.getX() + closing_yes.getWidth() + 19, 160);
		
		bg_credit.setPosition(0,0);
		
		shop.setPosition((option_gear.getX() + (option_gear.getWidth()/2))-(shop.getWidth()/2 + 20), GameEngineConfiguration.masterHeight - 100);
		
		tap_to_play.setPosition((GameEngineConfiguration.masterWidth /2) - (tap_to_play.getWidth()/2), shop.getY() - (tap_to_play.getHeight() /2));
		credit.setPosition(Anchor.BOTTOM_CENTER);
	}

	@Override
	protected void registerTouch() 
	{
		// register touch closing
		engine.hud.registerTouchArea(bg_belakang);
		engine.hud.registerTouchArea(closing_no);
		engine.hud.registerTouchArea(closing_yes);				
		
		engine.hud.registerTouchArea(shop);
		
		//register touch option
		engine.hud.registerTouchArea(option_gear);
		engine.hud.registerTouchArea(option_credit);
		engine.hud.registerTouchArea(option_off_bgm);
		engine.hud.registerTouchArea(option_on_bgm);
		engine.hud.registerTouchArea(option_on_sfx);
		engine.hud.registerTouchArea(option_off_sfx);	
	}

	@Override
	protected void unregisterTouch() 
	{
		engine.unregisterHudTouch(bg_belakang);
		// unregister touch closing
		engine.unregisterHudTouch(closing_no);
		engine.unregisterHudTouch(closing_yes);
		
		engine.unregisterHudTouch(shop);
		
		// unregister touch option
		engine.unregisterHudTouch(option_gear);
		engine.unregisterHudTouch(option_credit);
		engine.unregisterHudTouch(option_off_bgm);
		engine.unregisterHudTouch(option_on_bgm);
		engine.unregisterHudTouch(option_on_sfx);
		engine.unregisterHudTouch(option_off_sfx);
	}

	@Override
	protected void onUpdate() 
	{
		
		
		bg_tengah[0].setX(bg_tengah[0].getX()- SPEEDBGBELAKANG);
		bg_tengah[1].setX(bg_tengah[1].getX()- SPEEDBGBELAKANG);
		
		bg_depan[0].setX(bg_depan[0].getX()- SPEEDBGDEPAN);
		bg_depan[1].setX(bg_depan[1].getX()- SPEEDBGDEPAN);
		
		bg_floor_depan[0].setX(bg_floor_depan[0].getX() + SPEEDBFLOOR);
		bg_floor_depan[1].setX(bg_floor_depan[1].getX() + SPEEDBFLOOR);
		
		if(bg_tengah[0].getX() < engine.camera.getXMin()){
			bg_tengah[1].setX(bg_tengah[0].getX() + bg_tengah[0].getWidth());
		}
		
		if(bg_tengah[1].getX() < engine.camera.getXMin()){
			bg_tengah[0].setX(bg_tengah[1].getX() + bg_tengah[1].getWidth());
		}
		
		if(bg_depan[0].getX() < engine.camera.getXMin()){
			bg_depan[1].setX(bg_depan[0].getX() + bg_depan[0].getWidth());
		}
		
		if(bg_depan[1].getX() < engine.camera.getXMin()){
			bg_depan[0].setX(bg_depan[1].getX() + bg_depan[1].getWidth());
		}
		
		if(bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth()> engine.camera.getXMax()){
			bg_floor_depan[1].setX(bg_floor_depan[0].getX() - bg_floor_depan[0].getWidth());
		}
		
		if(bg_floor_depan[1].getX() + bg_floor_depan[1].getWidth() > engine.camera.getXMax()){
			bg_floor_depan[0].setX(bg_floor_depan[1].getX() - bg_floor_depan[1].getWidth());
		}
		
		/*if(credit.isVisible()){
			credit.setY(credit.getY()-1);
		}*/
		
		if(credit.isVisible()){ if(credit.getY() + credit.getHeight() < 0)
		{ 
			credit.setY(GameEngineConfiguration.masterHeight); 
		} 
			credit.setY(credit.getY()-1); 
		}
	}

	@Override
	protected void onPaused() 
	{
		Mfx.Pause(MUSIC_MAINMENU);
		Sfx.PauseAll();
	}

	@Override
	protected void onResumed() 
	{
		Mfx.Play(MUSIC_GP_JUNGLE);
	}

	@Override
	public void onKeyUp(int keyCode, KeyEvent event) 
	{
		
		if(keyCode == KeyEvent.KEYCODE_BACK  && !bg_credit.isVisible() && !closing_background.isVisible() && !option_background.isVisible() /*&& !shop_background.isVisible()*/) 
		{	
			showClosing();
		}
		else if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			if(bg_credit.isVisible()){
				closeCredit();
				option_gear.setVisible(true);
			}
			else if(closing_background.isVisible()){
				closeClosing();
			}else if(option_background.isVisible()){
				closeOption();
			}else if (credit.isVisible()){
				credit.setVisible(false);
			}
		}		
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) 
	{
		switch (pSceneTouchEvent.getAction()) 
		{
			case TouchEvent.ACTION_DOWN:
				{
					if (pTouchArea == option_gear && !closing_background.isVisible()) {
						option_gear.setScale(PUSHSCALE);
					}
					else if (pTouchArea == shop) {
						shop.setScale(PUSHSCALE);
					}
					else if(closing_background.isVisible()){
						if(pTouchArea == closing_no)
						{
							closing_no.setScale(PUSHSCALE);
						}
						else if(pTouchArea == closing_yes)
						{
							closing_yes.setScale(PUSHSCALE);
						}
					}
					else if (option_background.isVisible())
					{
						if (pTouchArea == option_credit) {
							option_credit.setScale(PUSHSCALE);
						}
						else if (pTouchArea == option_off_bgm) 
						{
							if(option_on_bgm.isVisible())
							{
								option_on_bgm.setScale(PUSHSCALE);
							}
							else if(option_off_bgm.isVisible()) 
							{
								option_off_bgm.setScale(PUSHSCALE);
							}
						}
						else if (pTouchArea == option_off_sfx) 
						{
							if(option_on_sfx.isVisible()){
								option_on_sfx.setScale(PUSHSCALE);
							}
							else if(option_off_sfx.isVisible()) 
							{
								option_off_sfx.setScale(PUSHSCALE);
							}
						}
					}
					
				}
				break;
			case TouchEvent.ACTION_UP:
				{
					if(pTouchArea == closing_no && closing_background.isVisible())
					{
						closing_no.setScale(DEFAULTSCALE);
						
						closeClosing();
					}
					else if (pTouchArea == shop) {
						shop.setScale(DEFAULTSCALE);
						exitState(STATE_SHOP);
					}
					else if(closing_background.isVisible())
					{
						if(pTouchArea == closing_yes && closing_background.isVisible())
						{
							closing_yes.setScale(DEFAULTSCALE);
							
							engine.getDatabase().updateData(TABLE_OPTION_GAME, new int[]{0, 1}, new String[]{String.valueOf(GameEngineConfiguration.useSound), String.valueOf(GameEngineConfiguration.useMusic)}, "");
							
							engine.finish();
						}
						else if(pTouchArea == closing_yes)
						{
							closing_yes.setScale(DEFAULTSCALE);
						}
						
					}
					else if (pTouchArea == option_gear && !closing_background.isVisible())
					{
						option_gear.setScale(DEFAULTSCALE);
						
						
						if(option_background.isVisible())
						{
							closeOption();
						}
						else if(!option_background.isVisible()) 
						{
							showOption();
						}
					}
					else if(option_background.isVisible())
					{
						if (pTouchArea == option_credit) {
							option_credit.setScale(DEFAULTSCALE);
							
							option_gear.setVisible(false);
							showCredit();
							closeOption();
						}
						else if (pTouchArea == option_off_bgm) 
						{
							if(option_on_bgm.isVisible()){
								option_on_bgm.setScale(DEFAULTSCALE);
								
								option_on_bgm.setVisible(false);
								option_off_bgm.setVisible(true);
								
								GameEngineConfiguration.useMusic = false;
							}
							else if(option_off_bgm.isVisible()) 
							{
								option_off_bgm.setScale(DEFAULTSCALE);
								
								option_off_bgm.setVisible(false);
								option_on_bgm.setVisible(true);
								
								GameEngineConfiguration.useMusic = true;
							}
						}
						else if (pTouchArea == option_off_sfx) 
						{
							if(option_on_sfx.isVisible()){
								option_on_sfx.setScale(DEFAULTSCALE);
								
								option_on_sfx.setVisible(false);
								option_off_sfx.setVisible(true);
								
								GameEngineConfiguration.useSound = false;
							}
							else if(option_off_sfx.isVisible()) 
							{
								option_off_sfx.setScale(DEFAULTSCALE);
								
								option_off_sfx.setVisible(false);
								option_on_sfx.setVisible(true);
								
								GameEngineConfiguration.useSound = true;
							}
						}
					}
					else if(
							engine.getCurrState() == STATE_MENU 
							&& pTouchArea == bg_belakang 
							&& shop.getScaleX() != PUSHSCALE
							&& option_gear.getScaleX() != PUSHSCALE
							&& option_credit.getScaleX() != PUSHSCALE
							&& !bg_credit.isVisible()
							)
					{ 
						engine.getDatabase().updateData(TABLE_OPTION_GAME, new int[]{0, 1},
								new String[]{String.valueOf(
										GameEngineConfiguration.useSound), 
										String.valueOf(GameEngineConfiguration.useMusic)
										}, "");
						exitState(STATE_PLAY);
					}
				}
				break;
		}
		return false;
	}
	
	protected void showCredit() {
		credit.setVisible(true);
		title.setVisible(false);
		
		text_apple.setVisible(false);
		text_highscore.setVisible(false);
		applesprite.setVisible(false);
		
		credit.setY(480);
		bg_credit.setVisible(true);
	}
	
	protected void closeCredit() {
		text_apple.setVisible(true);
		text_highscore.setVisible(true);
		applesprite.setVisible(true);
		
		title.setVisible(true);
		bg_credit.setVisible(false);
	}
	
	protected void closeClosing()
	{
		
		closing_background.setVisible(false);
	}
	
	protected void closeOption() {
		try {
			engine.getDatabase().updateData(TABLE_OPTION_GAME, new int[]{0, 1},
					new String[]{String.valueOf(
							GameEngineConfiguration.useSound), 
							String.valueOf(GameEngineConfiguration.useMusic)
							}, "");
		} catch (NullPointerException e) {
			engine.getDatabase().dropTable(TABLE_OPTION_GAME);
			engine.getDatabase().updateData(TABLE_OPTION_GAME, new int[]{0, 1}, new String[]{String.valueOf(GameEngineConfiguration.useSound), String.valueOf(GameEngineConfiguration.useMusic)}, "");
		}
		
		option_background.setVisible(false);
	}
	
	protected void showClosing(){
		this.closing_background.setVisible(true);
		
		this.closing_background.setHeight(CLOSINGBACKGROUNDHEIGHT);
		this.closing_background.setWidth(CLOSINGBACKGROUNDWIDTH);
		
		this.closing_yes.setHeight(CLOSINGYESBUTTONHEIGHT);
		this.closing_yes.setWidth(CLOSINGYESBUTTONWIDTH);
		
		this.closing_no.setHeight(CLOSINGNOBUTTONHEIGHT);
		this.closing_no.setWidth(CLOSINGNOBUTTONWIDTH);
	}
	
	protected void showOption(){
		GameEngineConfiguration.useSound = this.getSound();
		GameEngineConfiguration.useMusic = this.getMusic();
		
		this.option_background.setVisible(true);
		
		this.option_background.setWidth(OPTIONBACKGROUNDWIDTH);
		this.option_background.setHeight(OPTIONBACKGROUNDHEIGHT);
		
		this.option_off_sfx.setHeight(OFFBUTTONHEIGHT);
		this.option_off_sfx.setWidth(OFFBUTTONWIDTH);
		
		this.option_off_bgm.setHeight(OFFBUTTONHEIGHT);
		this.option_off_bgm.setWidth(OFFBUTTONWIDTH);
		
		this.option_on_sfx.setHeight(OFFBUTTONHEIGHT);
		this.option_on_sfx.setWidth(OFFBUTTONWIDTH);
		
		this.option_on_bgm.setHeight(OFFBUTTONHEIGHT);
		this.option_on_bgm.setWidth(OFFBUTTONWIDTH);
		
		this.option_credit.setHeight(OPTIONCREDITBUTTONHEIGHT);
		this.option_credit.setWidth(OPTIONCREDITBUTTONWIDTH);
		
		
		if(GameEngineConfiguration.useSound)
		{
			this.option_on_sfx.setVisible(true);
			this.option_off_sfx.setVisible(false);
		}
		else if(!GameEngineConfiguration.useSound)
		{
			this.option_on_sfx.setVisible(false);
			this.option_off_sfx.setVisible(true);
		}
		
		if(GameEngineConfiguration.useMusic)
		{
			this.option_on_bgm.setVisible(true);
			this.option_off_bgm.setVisible(false);
		}
		else if(!GameEngineConfiguration.useMusic)
		{
			this.option_on_bgm.setVisible(false);
			this.option_off_bgm.setVisible(true);
		}
	}	
	
	protected boolean getSound() {
		String entitysound;
		try {
			entitysound = engine.getDatabase().getData(TABLE_OPTION_GAME, 0, 0);
		} catch (NullPointerException e) {
			engine.getDatabase().dropTable(TABLE_OPTION_GAME);
			entitysound = engine.getDatabase().getData(TABLE_OPTION_GAME, 0, 0);
		}		
		
		boolean getsound = entitysound.equals("true");

		return getsound;
	}
	
	protected boolean getMusic() {
		String entitymusic;
		engine.getDatabase().getData(TABLE_OPTION_GAME, 0, 1);
		
		try {
			entitymusic = engine.getDatabase().getData(TABLE_OPTION_GAME, 0, 1);
		} catch (NullPointerException e) {
			engine.getDatabase().dropTable(TABLE_OPTION_GAME);
			entitymusic = engine.getDatabase().getData(TABLE_OPTION_GAME, 0, 1);
		}	
		boolean getmusic = entitymusic.equals("true");

		return getmusic;
	}
}
