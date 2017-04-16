package lib.defines;

import lib.element.ElementFont;

import org.andengine.opengl.font.Font;
import org.andengine.util.color.Color;

import android.R.integer;

public interface FontDefines 
{	
	int FONT_ANIMEACE2_ITAL 	= 0;
	int FONT_ANIMEACE2_ITAL2 	= FONT_ANIMEACE2_ITAL + 1;
	int FONT_ANIMEACE_WHITE		= FONT_ANIMEACE2_ITAL2 + 1;
	int FONT_ANIMACE_GREEN_19 	= FONT_ANIMEACE_WHITE +1;
	int FONT_IMPACT				= FONT_ANIMACE_GREEN_19 + 1;
	int FONT_JAPAN_STYLE		= FONT_IMPACT + 1;
	int FONT_JASMINE			= FONT_JAPAN_STYLE + 1;
	int FONT_FRAHV				= FONT_JASMINE + 1;
	int FONT_LADYIC				= FONT_FRAHV + 1;
	int FONT_LADYIC2			= FONT_LADYIC + 1;
	int FONT_LADYISCB			= FONT_LADYIC2 + 1;

	final static ElementFont CONTAINER[] = 
	{	
		new ElementFont("font/animeace2_ital.ttf", 61, Color.RED),
		new ElementFont("font/animeace2_ital.ttf", 23, Color.GREEN),
		new ElementFont("font/animeace2_ital.ttf", 20, Color.WHITE),
		new ElementFont("font/animeace2_ital.ttf", 21, Color.GREEN),
		new ElementFont("font/impact.ttf", 21, Color.BLACK),
		new ElementFont("font/japanstyle.ttf", 19, Color.WHITE),
		new ElementFont("font/UPCJL.ttf", 40, Color.WHITE),
		new ElementFont("font/FRAHV.ttf", 23, Color.BLACK),
		new ElementFont("font/LADYIC.ttf", 23, Color.WHITE),
		new ElementFont("font/LADYIC.ttf", 37, Color.WHITE),
		new ElementFont("font/LADYISCB.ttf", 23, Color.WHITE),
	};
}
