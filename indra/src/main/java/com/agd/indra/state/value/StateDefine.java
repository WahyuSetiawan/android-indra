package com.agd.indra.state.value;

public interface StateDefine {
	int STATE_STORY		= 0;
	int STATE_LOADING	= STATE_STORY +1;
	int STATE_MENU 		= STATE_LOADING + 1;
	int STATE_PLAY 		= STATE_MENU + 1;
	int STATE_SHOP		= STATE_PLAY + 1;
}
