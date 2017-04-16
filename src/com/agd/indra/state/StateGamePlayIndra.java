package com.agd.indra.state;

import java.util.Random;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import com.agd.indra.state.value.StateDefine;
import com.agd.indra.state.value.ValueActionJump;
import com.agd.indra.state.value.ValueCamera;
import com.agd.indra.state.value.ValueFlagObs;
import com.agd.indra.state.value.ValueInterface;
import com.agd.indra.state.value.ValuePlay;
import com.agd.indra.state.value.ValuePlayer;
import com.agd.indra.state.value.ValueReport;

import android.view.KeyEvent;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameAnim;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.elementgame.Mfx;
import lib.elementgame.Sfx;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGamePlayIndra extends GameState 
implements ValueInterface, StateDefine, ValueCamera, ValuePlayer, ValueActionJump, ValuePlay, ValueFlagObs, ValueReport
{
	private GameSprite bg_belakang;
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite[] bg_tengah		= new GameSprite[2];
	private GameSprite[] bg_depan		= new GameSprite[2];
	
	private GameSprite[][] obstacle 	= new GameSprite[COUNT][COUNTOBS];
	private GameSprite[] bigrock		= new GameSprite[COUNT];
	private GameSprite[] smallrock		= new GameSprite[COUNT];
	private GameSprite[] tree			= new GameSprite[COUNT];
	
	private GameSprite pointer;
	
	private GameSprite pohon;
	private GameSprite pohon2;
	private GameSprite semak;
	private GameSprite semak2;
	
	private GameSprite bg_report;
	private GameSprite button_play;
	private GameSprite button_pocket;
	private GameSprite button_quit;
	
	private GameSprite option_background;
	private GameSprite option_credit;
	private GameSprite option_off_sfx;
	private GameSprite option_off_bgm;
	private GameSprite option_on_sfx;
	private GameSprite option_on_bgm;
	
	private GameSprite pause_background;
	private GameSprite pause_exit;
	private GameSprite pause_option;
	private GameSprite pause_resume;
	
	private GameSprite borderSc_kanan;
	private GameSprite borderSc_tengah;
	private GameSprite borderSc_kiri;
		
	private GameText report_high;
	private GameText distance;
	private GameText status;
	private GameText txtScore;
	private GameText report_score;
	private GameText report_apple;
	/*
	 * apple yang bnayak dipunya
	 * di hiden aja
	 * */
	private GameText appleStat;
	
	private GameSprite appleCon;
	
	private int apple = 0;
	private int finalscore = 0;
	private int pluspoint = 0;
	private Random num = new Random();
	
	private GameAnim[][] point = new GameAnim[COUNT][COUNTOBS];
	
	private GameAnim player_run;
	private GameAnim player_jump;
	private GameAnim player_doublejump;
	private GameAnim player_fall;
	private GameAnim player_accident;
	
	private GameAnim apple_report;
	
	private boolean jump;
	private boolean isstart;
	private boolean collidgerun;
	private boolean collidgejump;
	private boolean run;
	private boolean rise;
	private boolean jungledash;
	
	private int selectobs;
	private int jump_player;
	private int move_player;
	private int obsfirst;
	private int highscore;
	//private int obssecound;
	private int score;
	private int time;
	private int distance_player;
	private int speed_distance;
	private int speed_decrease;
	//private int tilelastaccident;
	
	private float playerstartaccidentx;
	private float speed_jump;
	private float range_up;
	private float flagout;
	private float standinupper;
	private float speedplayer;
	
	//reportBaru try
//	private GameSprite bg_report1;
	
	private GameText[] pocket		= new GameText[4];
	private GameSprite[] shop_buy 	= new GameSprite[4];
	
	private GameText applePoint;
	private GameSprite appleicon;
	
	private GameSprite shop_background;
	private GameSprite shop_back;
	private GameSprite shop_pocket;
	private GameSprite shop_shop;
	private GameSprite shop_apple;
	
	
	public StateGamePlayIndra(GameEngine engine) {
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
		
		for(int loop = 0; loop<bg_depan.length; loop++){
			bg_depan[loop] 	= new GameSprite(BG_DEPAN, engine);
		}
		
		pointer 			= new GameSprite(POINTER, engine);
		pohon				= new GameSprite(BG_POHON, engine);
		pohon2				= new GameSprite(BG_POHON, engine);
		semak				= new GameSprite(BG_SEMAK, engine);
		semak2				= new GameSprite(BG_SEMAK, engine);
		
		for(int loop = 0; loop < obstacle.length;loop++ ){
			bigrock[loop]			= new GameSprite(BIG_ROCK, engine);
			smallrock[loop]			= new GameSprite(SMALL_ROCK, engine);
			tree[loop]				= new GameSprite(TREE, engine);
			
			obstacle[loop][0]		= new GameSprite(POINTER, engine);
			obstacle[loop][1]		= new GameSprite(POINTER, engine);
			obstacle[loop][2]		= new GameSprite(POINTER, engine);
		}
		
		for (int loop = 0; loop < point.length; loop++) {
			for (int i = 0; i < point[loop].length; i++) {
				point[loop][i]		= new GameAnim(ANIM_POINT_APPLE, engine); 
			}
		}
		
		player_run 			= new GameAnim(ANIM_PLAYER_LARI, engine);
		player_accident		= new GameAnim(ANIM_PLAYER_NABRAK, engine);
		player_jump			= new GameAnim(ANIM_PLAYER_LOMPAT, engine);
		player_doublejump	= new GameAnim(ANIM_PLAYER_DOUBLEJUMP, engine);
		player_fall			= new GameAnim(ANIM_PLAYER_LUBANG, engine);
		
		bg_report			= new GameSprite(REPORT_BG, engine);
		button_play			= new GameSprite(PLAY_BUTTON, engine);
		button_pocket		= new GameSprite(POCKET_BUTTON, engine);
		button_quit			= new GameSprite(QUIT_BUTTON, engine);
		apple_report		= new GameAnim(ANIM_POINT_APPLE, engine);
		report_apple		= new GameText("", 28, engine.getFont(FONT_LADYIC2), engine);
		report_score		= new GameText("score : CCCCCCC", 18, engine.getFont(FONT_LADYIC2), engine);
		
		option_background	= new GameSprite(OPTION_BG, engine);
		option_credit		= new GameSprite(OPTION_CREDIT, engine);
		option_off_sfx		= new GameSprite(OPTION_OFF, engine);
		option_off_bgm		= new GameSprite(OPTION_OFF, engine);
		option_on_sfx		= new GameSprite(OPTION_ON, engine);
		option_on_bgm		= new GameSprite(OPTION_ON, engine);
		
		pause_background	= new GameSprite(PAUSE_BG, engine);
		pause_exit			= new GameSprite(PAUSE_EXIT, engine);
		pause_option		= new GameSprite(PAUSE_OPTION, engine);
		pause_resume		= new GameSprite(PAUSE_RESUME, engine);

		distance 			= new GameText("0", 15, engine.getFont(FONT_LADYIC), engine);
		status				= new GameText("0", 10, engine.getFont(FONT_LADYIC), engine);
		txtScore			= new GameText("", 20, engine.getFont(FONT_LADYIC), engine);
		report_high			= new GameText("0", 20, engine.getFont(FONT_LADYIC), engine);
		appleStat			= new GameText("0", 20, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);

		appleCon 			= new GameSprite(STAT_APEL, engine);	
		
		borderSc_kanan		= new GameSprite(BORDER_SCORE, engine);
		borderSc_tengah		= new GameSprite(BORDER_SCTENGAH, engine);
		borderSc_kiri		= new GameSprite(BORDER_SCORE, engine);
				
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
	
	protected void getScore() {
		String strScore = engine.getDatabase().getData(TABLE_SCORE, 0, 1);
		engine.getDatabase().print(TABLE_SCORE);
		engine.getDatabase().insertData(0, new String[]{"0","10"});
		highscore = Integer.parseInt(strScore);
		txtScore.setText("" + highscore);
	}
	
	protected void getPoint() {
		String strApple = engine.getDatabase().getData(TABLE_POINT, 0, 1);
		engine.getDatabase().print(TABLE_POINT);
		engine.getDatabase().insertData(0, new String[]{"0","10"});
		apple = Integer.parseInt(strApple);
		report_apple.setText("" + apple);
	}
	
	protected void getHighScore() {
		String strFinal = engine.getDatabase().getData(TABLE_HIGHSC, 0, 1);
		engine.getDatabase().print(TABLE_HIGHSC);
		engine.getDatabase().insertData(0, new String[]{"0","10"});
		finalscore = Integer.parseInt(strFinal);
		report_high.setText("Top Score : " + String.valueOf(finalscore));
	}
	
	protected void getPP() {
		String strPP = engine.getDatabase().getData(TABLE_PUP, 0, 1);
		engine.getDatabase().print(TABLE_PUP);
		engine.getDatabase().insertData(0, new String[]{"0","10"});
		pluspoint = Integer.parseInt(strPP);
		appleStat.setText("Apple : " + String.valueOf(pluspoint));
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
	
	protected void updateJungleDash(int value) {
		engine.getDatabase().updateData(TABLE_JUNGLE_DASH, new int[]{0}, new String[]{String.valueOf(value)}, "");
		
	}

	@Override
	protected void init() 
	{
		GameEngineConfiguration.useSound = this.getSound();
		GameEngineConfiguration.useMusic = this.getMusic();
		
		getHighScore();
		getPP();
			
		//onScene
		engine.camera.setCenter(CAMERA_CENTER_X, CAMERA_CENTER_Y);
		
		//on sound sfx mfx
		Mfx.Play(MUSIC_GP_JUNGLE);
		Mfx.Pause(MUSIC_MAINMENU);
		Sfx.Play(SOUND_RUN_GRASS);
			
		showOption();
		closeOption();
		showPocket();
		shop_background.setVisible(false);
		for (int i = 0; i < shop_buy.length; i++) {
			shop_buy[i].setScale(1);
		}
		
		pocket[0].setText(String.valueOf(getJungleDash()));
		applePoint.setText(String.valueOf(getApple()));
				
		//onPlayer
		player_run.animate(SPEED_ANIM, true);
		
		player_run.setVisible(true);
		player_fall.setVisible(false);
		player_jump.setVisible(false);
		player_doublejump.setVisible(false);
		player_accident.setVisible(false);
		appleStat.setVisible(false);
				
		//on GamePlay
		highscore = 0;
		
		for (int loop = 0; loop < point.length; loop++) {
			for (int i = 0; i < point[loop].length; i++) {
				point[loop][i].animate(DURATION_APPLE, true); 
			}
		}
		
		borderSc_kanan.setVisible(true);
		borderSc_kiri.setVisible(true);
		borderSc_tengah.setVisible(true);
			
		// on Update
		score			= 0;
		run 			= true;
		rise			= false;
		speedplayer		= SPEED;
		jump 			= false;
		
		jungledash		= getJungleDash() > 0;
		
		if(jungledash)
		{
			updateJungleDash(getJungleDash()-1);
		}
		
		speed_jump 		= SPEED_JUMP;
		collidgerun		= false;
		collidgejump	= false;
		distance_player	= 0;
		standinupper	= ACCIDENTINUPPER;
		playerstartaccidentx = DISTANCESCCIDENT;
				
		// on Area Touched
		isstart			= false;
		showPause();
		showReport();
		bg_report.setVisible(false);
		pause_background.setVisible(false);

		// on Jump
		speed_decrease 	= 0;
		move_player 	= UP;
		time			= 0;
				
		// on Flag Obstacle
		flagout			= STARTOBS;
		jump_player		= NETRAL;
		selectobs		= 0;
		
		apple_report.setVisible(false);
		option_credit.setVisible(false);
		
	}

	@Override
	protected void attach() 
	{
		// attach backgound
		engine.scene.attachChild(bg_belakang);
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_tengah[loop]);
		}
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_depan[loop]);
		}
		
		// attach object gameplay
		engine.scene.attachChild(pohon);
		engine.scene.attachChild(semak);
		engine.scene.attachChild(pohon2);
		engine.scene.attachChild(semak2);
		
		// attach floor
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			engine.scene.attachChild(bg_floor_depan[loop]);
		}
		
		//attach apple point
		
		for (int loop = 0; loop < point.length; loop++) {
			for (int i = 0; i < point[loop].length; i++) {
				engine.scene.attachChild(point[loop][i]);
			}
		}
		
		//attach obstacle
		for(int loop = 0; loop < obstacle.length;loop++ ){
			engine.scene.attachChild(obstacle[loop][0]);
			engine.scene.attachChild(obstacle[loop][1]);
			engine.scene.attachChild(obstacle[loop][2]);
			
			obstacle[loop][0].attachChild(bigrock[loop]);
			obstacle[loop][1].attachChild(smallrock[loop]);
			obstacle[loop][2].attachChild(tree[loop]);
		}
		
		//attach player
		engine.scene.attachChild(pointer);
		pointer.attachChild(player_run);
		pointer.attachChild(player_accident);
		pointer.attachChild(player_fall);
		pointer.attachChild(player_jump);
		pointer.attachChild(player_doublejump);
		//pointer.attachChild(player_rise);
		//engine.scene.attachChild(peri);
		
		engine.hud.attachChild(option_background);
		option_background.attachChild(option_off_sfx);
		option_background.attachChild(option_on_sfx);
		option_background.attachChild(option_off_bgm);
		option_background.attachChild(option_on_bgm);
		option_background.attachChild(option_credit);
		
		//attach pause
		engine.hud.attachChild(pause_background);
		pause_background.attachChild(pause_option);
		pause_background.attachChild(pause_resume);
		pause_background.attachChild(pause_exit);
		
		// attach report
		engine.hud.attachChild(bg_report);
		bg_report.attachChild(button_play);
		bg_report.attachChild(button_pocket);
		bg_report.attachChild(button_quit);
		bg_report.attachChild(apple_report);
		bg_report.attachChild(report_apple);
		bg_report.attachChild(report_score);
		
		//attach menu gameplay
		engine.hud.attachChild(borderSc_kanan);
		engine.hud.attachChild(borderSc_tengah);
		engine.hud.attachChild(borderSc_kiri);
		//engine.hud.attachChild(distance);
		
		borderSc_tengah.attachChild(txtScore);
		borderSc_tengah.attachChild(status);
		borderSc_tengah.attachChild(appleStat);
		borderSc_tengah.attachChild(appleCon);	
		
		borderSc_kiri.attachChild(report_high);
		borderSc_kanan.attachChild(distance);
		
		//pocket
		engine.scene.attachChild(shop_background);
		shop_background.attachChild(shop_back);
		shop_background.attachChild(shop_pocket);
		shop_background.attachChild(shop_shop);
		shop_background.attachChild(shop_apple);
//		engine.scene.attachChild(bg_pocket);
		
		shop_background.attachChild(appleicon);
		shop_background.attachChild(applePoint);
		
		for (int j = 0; j < shop_buy.length; j++) {
			shop_background.attachChild(pocket[j]);
			shop_background.attachChild(shop_buy[j]);
		}
	}

	@Override
	protected void detach() 
	{
		//detach background
		bg_belakang.detachSelf();
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop].detachSelf();
		}
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_depan[loop].detachSelf();
		}
		
		//detach ornamen
		pohon.detachSelf();
		semak.detachSelf();
		pohon2.detachSelf();
		semak2.detachSelf();
		
		//detach point
		for (int loop = 0; loop < point.length; loop++) {
			for (int i = 0; i < point[loop].length; i++) {
				point[loop][i].detachSelf();
			}
		}
		
		//detach obstacle
		for(int loop = 0; loop < obstacle.length;loop++ ){
			obstacle[loop][0].detachSelf();
			obstacle[loop][1].detachSelf();
			obstacle[loop][2].detachSelf();
			
			bigrock[loop].detachSelf();
			smallrock[loop].detachSelf();
			tree[loop].detachSelf();
		}
		
		//detach floor
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop].detachSelf();
		}
		
		//detach player
		pointer.detachSelf();
		pointer.detachChildren();
		//peri.detachSelf();
		
		//detach pause
		pause_background.detachChildren();
		pause_background.detachSelf();
		
		option_background.detachSelf();
		option_background.detachChildren();
		
		//detach report
		bg_report.detachSelf();
		bg_report.detachChildren();
		
		//stop music
		Mfx.PauseAll();
		Sfx.PauseAll();
		
		//detach utility
		borderSc_kanan.detachSelf();
		borderSc_kanan.detachChildren();
		
		borderSc_tengah.detachSelf();
		borderSc_tengah.detachChildren();
		
		borderSc_kiri.detachSelf();
		borderSc_kiri.detachChildren();
		
		distance.detachSelf();
		status.detachSelf();
		/*txtScore.detachSelf();
		report_high.detachSelf();
		appleStat.detachSelf();
		appleCon.detachSelf();*/
		
		/*bg_report1.detachSelf();
		bg_report1.detachChildren();*/
		
		shop_background.detachSelf();
		shop_background.detachChildren();
	}

	@Override
	protected void setPosition() 
	{
		//setposition background
		bg_belakang.setPosition(139,0);
		
		bg_tengah[0].setX(0);
		bg_tengah[1].setX(bg_tengah[0].getWidth());
		
		bg_depan[0].setX(0);
		bg_depan[1].setX(bg_depan[0].getWidth());
		
		//setposition object
		pohon.setX(engine.camera.getXMin());
		pohon2.setX(engine.camera.getXMin());
		semak.setX(engine.camera.getXMin());
		semak2.setX(engine.camera.getXMin());
				
		//setposition  floor
		bg_floor_depan[0].setX(0);
		bg_floor_depan[1].setX(bg_floor_depan[0].getWidth());
		
		//setpositoin point
		for (int loop = 0; loop < point.length; loop++) {
			for (int i = 0; i < point[loop].length; i++) {
				point[loop][i].setPosition(engine.camera.getXMin() + point[loop][i].getWidth(), LOCATIONYPOINT);
			}
		}
		
		//setposition obstacle
		for(int loop = 0; loop < obstacle.length;loop++ ){
			obstacle[loop][0].setPosition(engine.camera.getXMin() - GameEngineConfiguration.masterWidth, 260);
			obstacle[loop][1].setPosition(engine.camera.getXMin() - GameEngineConfiguration.masterWidth, 365);
			obstacle[loop][2].setPosition(engine.camera.getXMin() - GameEngineConfiguration.masterWidth, 386);
			
			obstacle[loop][0].setHeight(161);
			obstacle[loop][0].setWidth(30);
			obstacle[loop][0].setAlpha(0f);
			
			obstacle[loop][1].setHeight(55);
			obstacle[loop][1].setWidth(30);
			obstacle[loop][1].setAlpha(0f);
			
			obstacle[loop][2].setHeight(30);
			obstacle[loop][2].setWidth(190);
			obstacle[loop][2].setAlpha(0f);
			
			bigrock[loop].setPosition(-50,0);
			smallrock[loop].setPosition(-25,0);
			tree[loop].setPosition(0,-51);
		}
		
		//set position player
		pointer.setPosition(PLAYERX, PLAYERY);
		pointer.setWidth(40);
		pointer.setHeight(85);
		pointer.setAlpha(0f);
		
		player_run.setPosition(-42, -18);
		player_fall.setPosition(-42, -18);
		player_jump.setPosition(-42, -18);
		player_doublejump.setPosition(-42, -18);
		player_accident.setPosition(-42, -18);
		//player_rise.setPosition(-42, -18);
		//peri.setPosition(player_accident.getX() + player_accident.getWidth(), -20);
		
		//set position pause
		pause_background.setPosition(Anchor.CENTER);
		pause_resume.setPosition((pause_background.getWidth()/2 + 85)-(pause_resume.getWidth()/2),pause_background.getHeight() - 185);
		pause_option.setPosition(pause_resume.getX(), pause_resume.getY() + pause_resume.getHeight() + 7);
		pause_exit.setPosition(pause_resume.getX(), pause_option.getY() + pause_option.getHeight() + 7);
		
		option_background.setPosition(Anchor.CENTER);
		option_off_sfx.setPosition(350, 120);
		option_on_sfx.setPosition(option_off_sfx.getX(), option_off_sfx.getY() - 5);
		option_off_bgm.setPosition(option_off_sfx.getX(), option_off_sfx.getY() + 55);
		option_on_bgm.setPosition(option_off_bgm.getX(), option_off_bgm.getY());
		option_credit.setPosition(option_off_bgm.getX() - (option_credit.getWidth()+ 200), option_off_bgm.getY() + 23);
		
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
				shop_buy[i].setPosition((GameEngineConfiguration.masterWidth /2) + SHOPBUYWIDTH + 25, (GameEngineConfiguration.masterHeight /2 )- 100 - SHOPBUYHEIGHT);
				pocket[i].setPosition(shop_buy[i].getX() - 50, shop_buy[i].getY() + 10);
			}
			else 
			{
				shop_buy[i].setPosition(shop_buy[i-1].getX(), shop_buy[i-1].getY() + 40);
				pocket[i].setPosition(shop_buy[i].getX(), shop_buy[i].getY());
			}
			
			if(i>0){
				shop_buy[i].setVisible(false);
			}
		}
		
		/*for (int i = 0; i < shop_buy.length; i++) {
			if(i == 0)
			{
				shop_buy[i].setPosition((GameEngineConfiguration.masterWidth /2) + shop_buy[i].getWidth() - 65, (GameEngineConfiguration.masterHeight /2 )- 100 - shop_buy[i].getHeight()+ 50);
				pocket[i].setPosition(shop_buy[i].getX() - 90, shop_buy[i].getY() + 5);
			}
			else 
			{
				shop_buy[i].setPosition(shop_buy[i-1].getX(), shop_buy[i-1].getY() + shop_buy[i].getHeight() - 50);
				pocket[i].setPosition(shop_buy[i].getX(), shop_buy[i].getY());
			}
		}*/
		
		/*appleicon.setPosition(GameEngineConfiguration.masterWidth/5,GameEngineConfiguration.masterHeight -78);
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
		}*/
		
		//set position report
		bg_report.setPosition(Anchor.CENTER);
		button_pocket.setPosition((bg_report.getWidth()/2) - (button_pocket.getWidth()/2), POSITIONPOCKETY);
		button_play.setPosition((float) (button_pocket.getX() + (REPORTPLAYBUTTONWIDTH * 1.5) + 60), POSITIONPLAYY);
		button_quit.setPosition((float) (button_pocket.getX() - (REPORTPLAYBUTTONWIDTH * 1.5) - 60), POSITIONQUITY);
		apple_report.setPosition(button_quit.getX() + (button_quit.getWidth() /2), POSITIONAPPLEY);
		report_apple.setPosition((float) (apple_report.getX() + (apple_report.getWidth() * 1.2) + 122), POSITIONSCOREAPPLEY - 15);
		report_score.setPosition(button_quit.getX() + (button_quit.getWidth() /2) + 170, POSITIONSCOREY - 20);
				
		//setposition untility
		borderSc_kanan.setPosition(570, 0);
		borderSc_tengah.setPosition(Anchor.TOP_CENTER);
		borderSc_kiri.setPosition(Anchor.TOP_LEFT);
		distance.setPosition(30, 15);
		txtScore.setPosition(borderSc_tengah.getX() + 60, borderSc_tengah.getY()+ 70);
		report_high.setPosition(borderSc_kiri.getX() + 20, borderSc_kiri.getY() + 15);
		appleCon.setPosition(borderSc_tengah.getX() - 325, borderSc_tengah.getY()+ 10);
		status.setPosition(appleCon.getX() + 50, appleCon.getY()+ 3);
		
		/*status.setPosition(Anchor.TOP_CENTER);
		distance.setPosition(8 * GameEngineConfiguration.masterWidth /9, 0);
		txtScore.setPosition(Anchor.TOP_CENTER);
		report_high.setPosition(Anchor.TOP_LEFT);
		appleStat.setPosition(600, 0);
		appleCon.setPosition(350, 0);*/
		
		//set Position report1
		/*bg_report1.setPosition(Anchor.CENTER);
		button_pocket.setPosition(Anchor.CENTER_RIGHT);*/
		
	}

	@Override
	protected void registerTouch() 
	{
		engine.scene.registerTouchArea(bg_belakang);
		engine.hud.registerTouchArea(pause_exit);
		engine.hud.registerTouchArea(pause_option);
		engine.hud.registerTouchArea(pause_resume);
		engine.hud.registerTouchArea(button_play);
		engine.hud.registerTouchArea(button_pocket);
		engine.hud.registerTouchArea(button_quit);
		
		engine.hud.registerTouchArea(option_credit);
		engine.hud.registerTouchArea(option_off_bgm);
		engine.hud.registerTouchArea(option_on_bgm);
		engine.hud.registerTouchArea(option_on_sfx);
		engine.hud.registerTouchArea(option_off_sfx);
		engine.scene.registerTouchArea(shop_back);
		engine.scene.registerTouchArea(shop_shop);
		engine.scene.registerTouchArea(shop_pocket);
		
		for (int i = 0; i < shop_buy.length; i++) {
			engine.scene.registerTouchArea(shop_buy[i]);
		}
	}

	@Override
	protected void unregisterTouch() 
	{
		engine.unregisterSceneTouch(bg_belakang);
		engine.unregisterHudTouch(pause_exit);
		engine.unregisterHudTouch(pause_option);
		engine.unregisterHudTouch(pause_resume);
		engine.unregisterHudTouch(button_play);
		engine.unregisterHudTouch(button_pocket);
		engine.unregisterHudTouch(button_quit);
		
		engine.unregisterHudTouch(option_credit);
		engine.unregisterHudTouch(option_off_bgm);
		engine.unregisterHudTouch(option_on_bgm);
		engine.unregisterHudTouch(option_on_sfx);
		engine.unregisterHudTouch(option_off_sfx);	
		engine.unregisterSceneTouch(shop_back);
		engine.unregisterSceneTouch(shop_shop);
		engine.unregisterSceneTouch(shop_pocket);
		
		for (int i = 0; i < shop_buy.length; i++) {
			engine.unregisterSceneTouch(shop_buy[i]);
		}
	}

	@Override
	protected void onUpdate() 
	{
		if(run /*&& !rise*/){
			System.out.println("run");
			/*
			 * set speed jalan untuk player dan background
			 * */
			
			if(jungledash && distance_player < 200){
				speedplayer = (float) (SPEED + ((speedplayer - SPEED) + 0.2));
				distance_player += 1;
			}
			else if(jungledash && distance_player < 300){
				speedplayer = (float) (SPEED + ((speedplayer - SPEED) - 0.2));
				distance_player += 1;
			}
			else if(jungledash && distance_player > 300)
			{				
				flagout += pointer.getX();
				jungledash = false;
				speedplayer = SPEED;
			}
			else if(!jungledash && pointer.getX() < 10000)
			{
				speedplayer = (float) (SPEED + ((speedplayer - SPEED) + 0.0001));
			}
			
			bg_belakang.setX(bg_belakang.getX() + speedplayer);
			
			bg_depan[0].setX(bg_depan[0].getX() - SPEEDBGTENGAH);
			bg_depan[1].setX(bg_depan[1].getX() - SPEEDBGTENGAH);
			
			pointer.setX(pointer.getX() + speedplayer);
			if(distance_player < 3000)
			speedplayer = (float) (SPEED + ((speedplayer - SPEED) + 0.00055));
			
			/*for (int i = 0; i < 7*10; i=+500) 
			{
				if(pointer.getX() > i-500 && pointer.getX() < i)
				{
					speedplayer = speedplayer + (10* i);
					bg_belakang.setX(bg_belakang.getX() - 10);
					
				}
			}*/
			
			/*
			 * pengaturan hitungan score
			 * */
			speed_distance++;
			
			if(speed_distance == SPEED_DISTANCE){
				distance_player++;
				distance.setText(String.valueOf(distance_player));
				speed_distance = 0;
			}
			
			/*
			 * set camera position untuk player
			 * */
			engine.camera.setCenter(pointer.getX() + GameEngine.cameraWidth / 2 - 60, engine.camera.getCenterY());
			
			/*
			 * coding untuk melakukan looping background
			 * */ 
			if (bg_tengah[0].getX() + bg_tengah[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
			{
				bg_tengah[0].setX(bg_tengah[1].getX() + bg_tengah[1].getWidth());
			}else if (bg_tengah[1].getX() + bg_tengah[1].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
			{
				bg_tengah[1].setX(bg_tengah[0].getX() + bg_tengah[0].getWidth());
			}
			
			if (bg_depan[0].getX() + bg_depan[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
			{
				bg_depan[0].setX(bg_depan[1].getX() + bg_depan[1].getWidth());
			}else if (bg_depan[1].getX() + bg_depan[1].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
			{
				bg_depan[1].setX(bg_depan[0].getX() + bg_depan[0].getWidth());
			}
			
			if (bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
			{
				bg_floor_depan[0].setX(bg_floor_depan[1].getX()+ bg_floor_depan[1].getWidth());
			}else if (bg_floor_depan[1].getX() + bg_floor_depan[1].getWidth() < engine.camera.getCenterX()- GameEngine.cameraWidth / 2)
			{
				bg_floor_depan[1].setX(bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth());
			}
			
			/*
			 * pengaturan untuk collide dengan point
			 * */
			
			for (int loop = 0; loop < point.length; loop++) {
				for (int i = 0; i < point[loop].length; i++) {
					if(pointer.collidesWith(point[loop][i]))
					{
						Sfx.Play(SOUND_BITE);
						GameEngineConfiguration.useSound = true;
						point[loop][i].setPosition(0,LOCATIONYPOINT);
						score++;
						status.setText(String.valueOf(score));
					}
				}
			}
			
			/*
			 * event collusion player ddengan obstacle
			 * */
			for(int loop = 0; loop < obstacle.length;loop++ ){
				for (int i = 0; i < obstacle[loop].length; i++) {
					if(pointer.collidesWith(obstacle[loop][i]) && !rise){
						System.out.println("masukan");
						Sfx.Play(SOUND_COLLISION);
						if(pointer.getY() < PLAYERY){
							if(player_doublejump.isVisible())
							{
								player_doublejump.stopAnimation();
								player_doublejump.setVisible(false);
								
								player_accident.setVisible(true);
								player_accident.animate(SPEEDACCIDENT, false);
								
								playerstartaccidentx += pointer.getX();
								standinupper += pointer.getX();
								
								run = false;
								collidgejump = true;
							}else 
							if(player_jump.isVisible())
							{
								player_jump.stopAnimation();
								player_jump.setVisible(false);
								
								player_accident.setVisible(true);
								player_accident.animate(SPEEDACCIDENT, false);
								
								playerstartaccidentx += pointer.getX();
								standinupper += pointer.getX();
								
								run = false;
								collidgejump = true;
							}
						}else{
							player_run.stopAnimation();
							player_run.setVisible(false);
							
							player_accident.setVisible(true);
							player_accident.animate(SPEEDACCIDENT, false);
							
							playerstartaccidentx += pointer.getX();
							
							run = false;
							collidgerun = true;
						}
					}
				}
			}
			
			/*
			 * menentukan object yang akan keluar
			 * */
			switch (num.nextInt(75)) {
			case 1:
				if(!(engine.camera.getXMin() - pohon.getWidth()< pohon.getX())&& !(pohon.getX()>engine.camera.getXMax()))
				{
					pohon.setPosition(engine.camera.getXMax(), 0, Anchor.CENTER_LEFT);
				}
				break;
			case 2:
				if(!(engine.camera.getXMin() - semak.getWidth()< semak.getX())&& !(semak.getX()>engine.camera.getXMax()))
				{
					semak.setPosition(engine.camera.getXMax(), 25, Anchor.BOTTOM_LEFT);
				}
				break;
			case 3:
				if(!(engine.camera.getXMin() - pohon2.getWidth()< pohon2.getX())&& !(pohon2.getX()>engine.camera.getXMax()))
				{
					pohon2.setPosition(engine.camera.getXMax(), 0, Anchor.CENTER_RIGHT);
				}
				break;
			case 4:
				if(!(engine.camera.getXMin() - semak2.getWidth()< semak2.getX())&& !(semak2.getX()>engine.camera.getXMax()))
				{
					semak2.setPosition(engine.camera.getXMax(), 25, Anchor.BOTTOM_CENTER);
				}
				break;
			}
			
			/*
			 * mengeset flag player obstacle
			 * */
			if(engine.camera.getXMax() > flagout && !jungledash)
			{
				flagout = flagout + setFlag(obstacle, num.nextInt(FLAGOBS[0].length - 1), FLAGOBS);
			}
			
			/*
			 * pemangilan method jump
			 * action jump player
			 * */
			if(jump)
			{
				doubleJump(pointer, PLAYERY, speed_jump, 13);
			}
		}
		else if(!run && !shop_background.isVisible())
		{
			System.out.println("collidge");
			if(collidgerun){
				if(pointer.getX() < playerstartaccidentx)
				{
					Sfx.PauseAll();
					pointer.setX(pointer.getX() + SPEED);
				}
				else if(pointer.getX() >= playerstartaccidentx)
				{
					report_apple.setText("	 	: " + score);
					report_score.setText("Score : " + distance_player);
					if(!bg_report.isVisible())showReport();
				}
			}
			else if (collidgejump)
			{
				if(pointer.getX() < playerstartaccidentx)
				{
					Sfx.PauseAll();
					if(pointer.getY() < PLAYERY && pointer.getX() > standinupper)
					{
						pointer.setY(pointer.getY() + speed_jump);
					}
					pointer.setX(pointer.getX() + SPEED);
				}
				else if(pointer.getX() >= playerstartaccidentx)
				{
					report_apple.setText("		: " + score);
					report_score.setText("Score : " + distance_player);
					if(!bg_report.isVisible())showReport();
				}
			}
		}
	}

	@Override
	protected void onPaused() 
	{
		showPause();
		player_run.stopAnimation();
		Mfx.Pause(MUSIC_GP_JUNGLE);
		Sfx.PauseAll();
	}

	@Override
	protected void onResumed() 
	{
		closePause();
		
		this.player_run.animate(SPEED_ANIM, true);
		Mfx.Play(MUSIC_GP_JUNGLE);
		
		if(pointer.getY() >= PLAYERY){
			Sfx.Play(SOUND_RUN_GRASS);
		}	
	}

	@Override
	public void onKeyUp(int keyCode, KeyEvent event) 
	{
		engine.getDatabase().updateData(TABLE_SCORE, new int[]{1}, new String[]{"" + highscore}, "WHERE Id_score = 0");
		engine.getDatabase().updateData(TABLE_HIGHSC, new int[]{1}, new String[]{"" + finalscore}, "WHERE Id_HighScore = 0");
		engine.getDatabase().updateData(TABLE_PUP, new int[]{1}, new String[]{"" + pluspoint}, "WHERE Id_PP = 0");
		
		if(keyCode == KeyEvent.KEYCODE_BACK && !collidgejump && !collidgerun) 
		{
			if(isPaused())
			{
				if(isOption()){
					closeOption();
				}

				resume();
			}
			else if(!isPaused())
			{ 	
				pause();
			}
		}
		
		if (keyCode == KeyEvent.KEYCODE_HOME) {
			Mfx.PauseAll();
			Sfx.PauseAll();
		}
	}
	
	private boolean isOption() {
		return option_background.isVisible();
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) 
	{
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_DOWN:
			{
				if(pTouchArea == bg_belakang 
						&& !isPaused() 
						&& engine.getCurrState() == STATE_PLAY 
						&& !bg_report.isVisible() 
						&& !collidgejump 
						&& !collidgerun
						&& !player_accident.isVisible() 
						&& !pause_background.isVisible()
						)
				{	
					jump = true;
					++jump_player;
					
					switch (jump_player) {
					case 0:
						break;
					case SINGLE_JUMP:
						Sfx.Pause(SOUND_RUN_GRASS);
						Sfx.Play(SOUND_JUMP);
						
						player_run.setVisible(false);
						player_jump.animate(DURATION_SINGLE, false);
						player_jump.setVisible(true);
						range_up = GameEngine.cameraHeight - SINGLE_JUMP_RANGE;	
						break;
					case DOUBLE_JUMP:
							if(player_jump.isVisible() && move_player == UP){
								Sfx.Pause(SOUND_JUMP);
								Sfx.Play(SOUND_DBL_JUMP);
								
								player_jump.setVisible(false);
								player_doublejump.animate(DURATION_DOUBLE, false);
								player_doublejump.setVisible(true);								
							}
							range_up = GameEngine.cameraHeight - DOUBLE_JUMP_RANGE;
						break;
					default:
						jump_player=SINGLE_JUMP;
						break;
					} 					
				}
				else if(pTouchArea == bg_belakang && !isPaused() && !isstart){
					isstart = true;
				}
				
				if(this.bg_report.isVisible())
				{
					if(pTouchArea == button_play)
					{
						button_play.setScale(PUSHSCALE);
					}
					else if(pTouchArea == button_pocket)
					{
						button_pocket.setScale(PUSHSCALE);
					}
					else if(pTouchArea == button_quit)
					{
						button_quit.setScale(PUSHSCALE);
					}
				}
				else if (shop_background.isVisible()) 
				{
					if(pTouchArea == shop_back){
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
									engine.getDatabase().updateData(TABLE_PUP, new int[]{i + 1}, new String[]{String.valueOf(getApple() - 1)}, "");
									
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
				}
				else if(pause_background.isVisible()){
					if(pTouchArea == pause_exit)
					{
						pause_exit.setScale(PUSHSCALE);
					}
					else if(pTouchArea == pause_option)
					{
						pause_option.setScale(PUSHSCALE);
					}
					else if(pTouchArea == pause_resume)
					{
						pause_resume.setScale(PUSHSCALE);
					}
				}
				else if (option_background.isVisible())
				{
					if (pTouchArea == option_credit) {
						option_credit.setScale(PUSHSCALE);
					}
					else if (pTouchArea == option_off_bgm) 
					{
						if(option_on_bgm.isVisible()){
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
				if(bg_report.isVisible())
				{
					if(pTouchArea == button_pocket)
					{
						button_pocket.setScale(DEFAULTSCALE);
						shop_background.setPosition(engine.camera.getXMin(), 0);
						shop_background.setVisible(true);
						
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
						
						/*
						 * Tempat data distance
						 */
						highscore = distance_player;
						engine.getDatabase().updateData(TABLE_SCORE, new int[]{1}, new String[]{"" + highscore}, "WHERE id_score = 0");
						
						/**
						 *  Penambahan serta penyimpanan data Apple yang dimiliki
						 */
						if (pluspoint <= score) 
						{
							pluspoint = score;
						} 
						else if (pluspoint >= score) 
						{
							pluspoint = pluspoint+score;
						}
						engine.getDatabase().updateData(TABLE_PUP, new int[]{1}, new String[]{"" + pluspoint}, "WHERE Id_PP = 0");
						status.setText(String.valueOf(apple));
						
						/*
						 * Penambahan serta penyimpanan data Top Score
						 */
						if (finalscore <= highscore) 
						{
							finalscore = highscore;
						} 
						else if (finalscore > highscore) 
						{
							finalscore = finalscore;
						}
						engine.getDatabase().updateData(TABLE_HIGHSC, new int[]{1}, new String[]{"" + finalscore}, "WHERE Id_HighScore = 0");
						
						this.bg_report.setVisible(false);
						setPocket();
					}
					else if(pTouchArea == button_play)
					{
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
						
						highscore = distance_player;
						engine.getDatabase().updateData(TABLE_SCORE, new int[]{1}, new String[]{"" + highscore}, "WHERE id_score = 0");
						
						/*
						 * Tempat data apple
						 * Penambahan serta penyimpanan data Apple yang dimiliki
						 */
						if (pluspoint <= score) 
						{
							pluspoint = score;
						} 
						else if (pluspoint >= score) 
						{
							pluspoint = pluspoint+score;
						}
						engine.getDatabase().updateData(TABLE_PUP, new int[]{1}, new String[]{"" + pluspoint}, "WHERE Id_PP = 0");
						status.setText(String.valueOf(apple));
						/*
						 * Penambahan serta penyimpanan data Top Score
						 */
						if (finalscore <= highscore) 
						{
							finalscore = highscore;
						} 
						else if (finalscore > highscore) 
						{
							finalscore = finalscore;
						}
						engine.getDatabase().updateData(TABLE_HIGHSC, new int[]{1}, new String[]{"" + finalscore}, "WHERE Id_HighScore = 0");
						
						button_play.setScale(DEFAULTSCALE);
						status.setText(String.valueOf(apple));
						reset();
					}
					else if(pTouchArea == button_quit)
					{

						button_quit.setScale(DEFAULTSCALE);
						
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
						
						/*
						 * Tempat data distance
						 */
						highscore = distance_player;
						engine.getDatabase().updateData(TABLE_SCORE, new int[]{1}, new String[]{"" + highscore}, "WHERE id_score = 0");
						
						/*
						 * Tempat data apple
						 */
						//apple = score;
						//engine.getDatabase().updateData(TABLE_POINT, new int[]{1}, new String[]{"" + apple}, "WHERE Id_Point = 0");
						
						/**
						 *  Penambahan serta penyimpanan data Apple yang dimiliki
						 */
						if (pluspoint <= score) 
						{
							pluspoint = score;
						} 
						else if (pluspoint >= score) 
						{
							pluspoint = pluspoint+score;
						}
						engine.getDatabase().updateData(TABLE_PUP, new int[]{1}, new String[]{"" + pluspoint}, "WHERE Id_PP = 0");
						status.setText(String.valueOf(apple));
						/*
						 * Penambahan serta penyimpanan data Top Score
						 */
						if (finalscore <= highscore) 
						{
							finalscore = highscore;
						} 
						else if (finalscore > highscore) 
						{
							finalscore = finalscore;
						}
						engine.getDatabase().updateData(TABLE_HIGHSC, new int[]{1}, new String[]{"" + finalscore}, "WHERE Id_HighScore = 0");
						
						exitState(STATE_MENU);
					}
				}
				else if(pause_background.isVisible()){
					if(pTouchArea == pause_exit)
					{
						this.pause_exit.setScale(DEFAULTSCALE);
						
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
						
						exitState(STATE_MENU);
					}
					else if(pTouchArea == pause_option)
					{
						this.pause_option.setScale(DEFAULTSCALE);	
						closePause();
						showOption();
					}
					else if(pTouchArea == pause_resume)
					{
						this.pause_resume.setScale(DEFAULTSCALE);
						
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
						
						resume();
					}
				}
				else if (shop_background.isVisible()) 
				{
					if(pTouchArea == shop_back){
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
				}				
				else if(option_background.isVisible())
				{
					if (pTouchArea == option_credit) {
						option_credit.setScale(DEFAULTSCALE);
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
				
			}
			
			break;
		}
		return false;
	}
	
	protected void showReport()
	{
		this.bg_report.setVisible(true);
		
		this.button_play.setHeight(REPORTPLAYBUTTONHEIGHT);
		this.button_play.setWidth(REPORTPLAYBUTTONWIDTH);
		
		this.button_pocket.setHeight(REPORTPLAYBUTTONHEIGHT);
		this.button_pocket.setWidth(REPORTPLAYBUTTONWIDTH);
		
		this.button_quit.setHeight(REPORTPLAYBUTTONHEIGHT);
		this.button_quit.setWidth(REPORTPLAYBUTTONWIDTH);
		
		this.apple_report.animate(DURATION_APPLE, true);
	}

	protected void closePause()
	{
		this.pause_background.setVisible(false);
	}
	
	protected void showPause()
	{
		this.pause_background.setVisible(true);
		
		this.pause_background.setHeight(PAUSEBACKGROUNDHEIGHT);
		this.pause_background.setWidth(PAUSEBACKGROUNDWIDTH);
		
		this.pause_option.setHeight(PAUSEOPTIONBUTTONHEIGHT);
		this.pause_option.setWidth(PAUSEOPTIONBUTTONWIDTH);
		
		this.pause_resume.setHeight(PAUSEPOCKETBUTTONHEIGHT);
		this.pause_resume.setWidth(PAUSEPOCKETBUTTONWIDTH);
		
		this.pause_exit.setHeight(PAUSEEXITBUTTONHEIGHT);
		this.pause_exit.setWidth(PAUSEEXITBUTTONWIDTH);
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
	
	protected void showPocket() {
		closeReport();
		
		for (int i = 0; i < pocket.length; i++) {
			shop_apple.setVisible(false);
			pocket[i].setVisible(true);
			shop_buy[i].setVisible(false);
		}
		
		shop_background.setHeight(SHOPBACKGROUNDHEIGHT);
		shop_background.setWidth(SHOPBACKGROUNDWIDTH);
		
		shop_back.setHeight(SHOPBACKHEIGHT);
		shop_back.setWidth(SHOPBACKWIDTH);
		
		shop_pocket.setHeight(SHOPPOCKETHEIGHT);
		shop_pocket.setWidth(SHOPPOCKETWIDTH);
		
		shop_shop.setWidth(SHOPPOCKETWIDTH);
		shop_shop.setHeight(SHOPPOCKETHEIGHT);
		
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
	}
	
	protected void setPocket() {
		borderSc_kanan.setVisible(false);
		borderSc_kiri.setVisible(false);
		borderSc_tengah.setVisible(false);
		
		pocket[0].setText(String.valueOf(getJungleDash()) + " X");
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
	
	private float setFlag(GameSprite[][] sprites, int flag, int[][] flagobs)
  	{
		int i = 0;float j = 0, r = 0;
		
		if(this.obsfirst == flagobs[0].length){
			flag--;
		}
		else 
		if(this.obsfirst == flag){
				
			flag++;
		}
		
		this.obsfirst = flag;
		
		while(i < flagobs[flag].length)
		{
			r = (RANGEOBSTACLE * (i + 1)) + num.nextInt(75);
			
			if(!(flagobs[flag][i] == DISABLE))
			{
				sprites[selectobs][flagobs[flag][i]].setX(flagout + r);
				
				switch (num.nextInt(4)) 
				{
				case 1:
					this.point[selectobs][i].setPosition(flagout + r, sprites[selectobs][flagobs[flag][i]].getY() - (point[selectobs][i].getHeight() * 2));
					break;
				case 3:
				case 2:
					this.point[selectobs][i].setPosition(flagout + r + tree[0].getWidth(), LOCATIONYPOINT);
					break;
				}
			} 
			else
			{
				switch (num.nextInt(2)) 
				{
				case 1:
					this.point[selectobs][i].setPosition(flagout  + r, LOCATIONYPOINT);
					break;
				}
			}
			j =+ r;
			i++;
		}
		++this.selectobs;
		
		if(this.selectobs >= this.obstacle.length)this.selectobs=0;
		return j;
	}
	
	private void doubleJump(GameSprite spritejump, float rangedown, float speed, int timeup)
	{		
		switch (this.move_player) {
		case UP:	
			if (this.speed_decrease == 0){
				this.speed_decrease = (int) speed;
				this.speed_decrease--;
			}
			else
			{
				this.speed_decrease--;
			}
			if(spritejump.getY() <= this.range_up)
			{
				if(this.time == timeup){
					this.time=0;
					this.move_player = DOWN;
				}
				this.time++;
			}else {
				spritejump.setY((float) (spritejump.getY() - speed));
			}
			break;
		case DOWN:
			this.speed_decrease++;
			
			spritejump.setY((float) (spritejump.getY() + speed));
			
			if (spritejump.getY() >= rangedown) {
				if(player_jump.isVisible()){
					this.player_jump.setVisible(false);
				}else{
					this.player_doublejump.setVisible(false);
				}
				
				if(pointer.getY() > PLAYERY - 50){
					this.jump_player = NETRAL;
				}
								
				Sfx.Play(SOUND_RUN_GRASS);
				spritejump.setY(PLAYERY);
				
				this.player_run.setVisible(true);
				this.jump =  false;
				this.move_player = UP;
			}
			break;
		}
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
	
	protected void closeOption() {
		engine.getDatabase().updateData(TABLE_OPTION_GAME, new int[]{0, 1},
				new String[]{String.valueOf(
						GameEngineConfiguration.useSound), 
						String.valueOf(GameEngineConfiguration.useMusic)
						}, "");
		
		option_background.setVisible(false);
	}
	
	protected void closeReport() {
		bg_report.setVisible(false);
	}
}
