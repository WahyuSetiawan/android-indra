package lib.defines;

import android.R.integer;
import lib.element.ElementSprite;

public interface SpriteDefines
{
	//index backgound
	int BG_BELAKANG		= 0;
	int BG_TENGAH		= BG_BELAKANG + 1;
	int BG_DEPAN		= BG_TENGAH +1;
	int BG_FLOOR_DEPAN	= BG_DEPAN +1;
	
	//index object
	int BG_POHON		= BG_FLOOR_DEPAN + 1;
	int BG_SEMAK		= BG_POHON + 1;
	
	//index pointer
	int POINTER			= BG_SEMAK +1;
	
	//index obstacle
	int BIG_ROCK		= POINTER + 1;
	int SMALL_ROCK		= BIG_ROCK + 1;
	int HOLE			= SMALL_ROCK + 1;
	int TREE			= HOLE + 1;
	
	//index menu utama
	int MENU			= TREE + 1;
	int BUTTON_MENU		= MENU +1;
	
	//index story
	int AGD_SPLASH		= BUTTON_MENU + 1;
	int STORY_FIRST		= AGD_SPLASH + 1;	
	int STORY_SECOND	= STORY_FIRST + 1;
	int STORY_THIRD		= STORY_SECOND + 1;
	int STORY_FOURTH	= STORY_THIRD + 1;
	int STORY_FIVE		= STORY_FOURTH + 1;
	
	//index splash
	int SPLASH			= STORY_FIVE +1;
	int LOADINGBAR		= SPLASH +1;
	
	// index report
	int REPORT_BG		= LOADINGBAR +1;
	int PLAY_BUTTON		= REPORT_BG +1;
	int POCKET_BUTTON	= PLAY_BUTTON +1;
	int QUIT_BUTTON		= POCKET_BUTTON+1;
	
	//index closing
	int CLOSING_BG		= QUIT_BUTTON +1;
	int CLOSING_YES		= CLOSING_BG +1;
	int CLOSING_NO		= CLOSING_YES +1;
	
	//index option
	int OPTION_BG 		= CLOSING_NO +1;
	int OPTION_GEAR		= OPTION_BG +1;
	int OPTION_CREDIT 	= OPTION_GEAR +1;
	int OPTION_OFF		= OPTION_CREDIT +1;
	int OPTION_ON 		= OPTION_OFF+1;
	
	//index pause
	int PAUSE_BG 		= OPTION_ON +1;
	int PAUSE_EXIT		= PAUSE_BG+1;
	int PAUSE_OPTION	= PAUSE_EXIT+1;
	int PAUSE_RESUME	= PAUSE_OPTION +1;

	//index shop
	int SHOP_BG 		= PAUSE_RESUME +1;
	int SHOP_BACK		= SHOP_BG +1;
	int SHOP_BUY		= SHOP_BACK +1;
	int SHOP_BUY_ICON	= SHOP_BUY + 1;
	int SHOP_SHOP		= SHOP_BUY_ICON +1;
	int SHOP_POCKET		= SHOP_SHOP + 1;
	
	// INDEX UMUM
	int TITLE			= SHOP_POCKET +1;
	
	//define status int 
	int STAT_APEL		= TITLE + 1;
	
	//index Splash2
	int LOAD_BAR_MINI	= STAT_APEL + 1;
	
	//index Credit
	int CREDIT			= LOAD_BAR_MINI + 1;
	int BG_CREDIT		= CREDIT +1;
	int TAP_TO_PLAY		= BG_CREDIT + 1;
	
	//index report1
	int BG_REPORT_1		= TAP_TO_PLAY + 1;
	
	//border
	int BORDER_SCORE	= BG_REPORT_1 + 1;
	int BORDER_SCTENGAH	= BORDER_SCORE + 1;
	
	//pocket
	int BG_POCKET		= BORDER_SCTENGAH + 1;
	int SHOP_APPLE		= BG_POCKET + 1;
	
	
	/*
	 * Batu besar : 115x161
	 * Batu kecil : 80x55
	 * Pohon jatuh : 207x81
	 * Lebar lubang : 107
	 * */
	
	int backgroundHeight 	= GameEngineConfiguration.masterHeight;
	int backgroundWidth 	= GameEngineConfiguration.masterWidth;	
	
	public final static ElementSprite CONTAINER[] = 
	{
		//background define
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/background/bg_belakang.png"),
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/background/bg_tengah.png"),
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/background/bg_depan.png"),
		new ElementSprite("gfx/gameplay/background/bg_floor_depan.png"),
		//object define
		new ElementSprite(250, 400, "gfx/gameplay/object/bg_pohon.png"),
		new ElementSprite(300, 200, "gfx/gameplay/object/bg_semak.png"),
		//define pointer
		new ElementSprite( 60, 106, "gfx/gameplay/player/pointer.png"),
		//obstacle define
		new ElementSprite(115, 161, "gfx/gameplay/obstacle/BATU BESAR.png"),
		new ElementSprite(80, 55, "gfx/gameplay/obstacle/BATU KECIL.png"),
		new ElementSprite("gfx/gameplay/obstacle/LOBANG.png"),
		new ElementSprite(207, 81, "gfx/gameplay/obstacle/POHON AMBRUK.png"),
		//menu define
		new ElementSprite("gfx/gameplay/menu/menu.png"),
		new ElementSprite( 55, 40,"gfx/gameplay/menu/button_menu.png"),
		//index Story
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/Story/AGD.jpg"),
		new ElementSprite(backgroundWidth, backgroundHeight,"gfx/gameplay/Story/STORY 1.jpg"),
		new ElementSprite(backgroundWidth, backgroundHeight,"gfx/gameplay/Story/STORY 2.jpg"),
		new ElementSprite(backgroundWidth, backgroundHeight,"gfx/gameplay/Story/STORY 3.jpg"),
		new ElementSprite(backgroundWidth, backgroundHeight,"gfx/gameplay/Story/STORY 4.jpg"),
		new ElementSprite(backgroundWidth, backgroundHeight,"gfx/gameplay/Story/STORY 5.jpg"),
		//splash define spalsh
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/loading/splash.png"),
		new ElementSprite("gfx/loading/loading_bar.png"),
		//report define
		new ElementSprite(450, 300, "gfx/gameplay/report/bg_score.png"),
		new ElementSprite("gfx/gameplay/report/restart_button.png"),
		new ElementSprite("gfx/gameplay/report/pocket_button.png"),
		new ElementSprite("gfx/gameplay/report/close_button.png"),
		//handling closing
		new ElementSprite("gfx/gameplay/closing/bg_closing.png"),
		new ElementSprite("gfx/gameplay/closing/yes.png"),
		new ElementSprite("gfx/gameplay/closing/no.png"),
		//option define
		new ElementSprite("gfx/gameplay/option/bg_option.png"),
		new ElementSprite("gfx/gameplay/option/gear.png"),
		new ElementSprite("gfx/gameplay/option/credit.png"),
		new ElementSprite("gfx/gameplay/option/off.png"),
		new ElementSprite("gfx/gameplay/option/on.png"),
		//pause Define
		new ElementSprite(600,300,"gfx/gameplay/pause/bg_pause.png"),
		new ElementSprite("gfx/gameplay/pause/exit.png"),
		new ElementSprite("gfx/gameplay/pause/options.png"),
		new ElementSprite("gfx/gameplay/pause/resume.png"),
		//shop
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/shop/bg.png"),
		new ElementSprite("gfx/gameplay/shop/back.png"),
		new ElementSprite("gfx/gameplay/shop/buy.png"),
		new ElementSprite("gfx/gameplay/shop/buy_icon.png"),
		new ElementSprite("gfx/gameplay/shop/shop.png"),
		new ElementSprite("gfx/gameplay/shop/pocket.png"),
		//shop umum
		new ElementSprite("gfx/gameplay/umum/title.png"),
		//index stat
		new ElementSprite(35, 35, "gfx/gameplay/object/apel.png"),
		//index loading2
		new ElementSprite("gfx/loading/load bar.png"),
		//index credit
		new ElementSprite("gfx/gameplay/credit/new_credit.png"),
		new ElementSprite("gfx/gameplay/credit/bg_chibi.png"),
		new ElementSprite("gfx/gameplay/menu/tap_to_play.png"),
		//index bg report1
		new ElementSprite("gfx/gameplay/report/report fix 1.png"),
		//border
		new ElementSprite(224, 62, "gfx/gameplay/border/bgscore.png"),
		new ElementSprite(115, 62, "gfx/gameplay/border/bgscoretengah.png"),
		//pocket
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/shop/bg_pocket.png"),
		new ElementSprite("gfx/gameplay/shop/shop_apple.png"),
	};
}