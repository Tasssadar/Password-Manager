package com.frca.passmgr;

import android.widget.PopupWindow;
import com.frca.passmgr.PasswordPopupType;

public class PasswordPopupWindow extends PopupWindow
{
	private PasswordPopupType pass_type = null;
	private int stage = 0;
	
	PasswordPopupWindow(PasswordPopupType _type)
	{
		pass_type = _type;
		stage = 0;
		constructNewStage();
	}
	
	void nextStage()
	{
		++stage;
		constructNewStage();
	}
	
	void constructNewStage()
	{
		switch(stage) 
		{
			case 0:
			{
				switch(pass_type)
				{
					case PASSWORD_POPUP_TEXT:
					case PASSWORD_POPUP_BOTH:
						constructTextStage();
						return;
					case PASSWORD_POPUP_GRAPHIC:
						constructGraphicStage();
						return;
					default:
						break;
				}
				break;
			}
			case 1:
			{
				switch(pass_type)
				{
					case PASSWORD_POPUP_BOTH:
					case PASSWORD_POPUP_GRAPHIC:
						constructGraphicStage();
						return;
					default:
						break;
				}
				break;
			}
			case 2:
			{
				if (pass_type == PasswordPopupType.PASSWORD_POPUP_BOTH)
				{
					constructGraphicStage();
					return;
				}
				break;
			}
			default:
				break;
		}
		
		// if no action made, pop-up window should close
		dismiss();
	}
	
	void constructTextStage()
	{
		
	}
	
	void constructGraphicStage() 
	{
		
	}
	

}
