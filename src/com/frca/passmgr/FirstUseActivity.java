package com.frca.passmgr;

import com.frca.passmgr.R;
import com.frca.passmgr.PasswordPopupWindow;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.view.LayoutInflater;
import android.widget.PopupWindow;

public class FirstUseActivity extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    private int selectedType;
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode != RESULT_OK)
    		return;
    	
    	if (requestCode == 1)
    	{
			Intent tempIntent = new Intent(this, TextPasswordActivity.class);
			tempIntent.putExtra("isRepeate", true);
			startActivityForResult(tempIntent, 2);
    	}

    }

    public void buttonClicked(View v)
    {
    	switch(v.getId())
    	{
	    	case R.id.main_textPass:
	    	{
	    		selectedType = 1;
	    		Intent tempIntent = new Intent(this, TextPasswordActivity.class);
	    		tempIntent.putExtra("isRepeate", false);
	    		startActivityForResult(tempIntent, 1);
	    		break;
	    	}
	    	case R.id.main_graphPass:
	    		selectedType = 2;
	    		//popupText.setText(this.getString(R.string.info_graph_pass));
	    		break;
	    	case R.id.main_bothPass:
	    		selectedType = 3;
	    		//popupText.setText(this.getString(R.string.info_both_pass));
	    		break;
	  		default:
	  			//popupText.setText(this.getString(R.string.error));
	   			break;
    	}
    }
    
    private PopupWindow pw;
    public void infoClicked(View v) {
    	LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	View layout = inflater.inflate(R.layout.popup_info, null, false);
    	pw = new PopupWindow(layout, 500, 500, true);
    	TextView popupText = (TextView)layout.findViewById(R.id.popup_text);
    	switch(v.getId())
    	{
    	case R.id.main_textPassInfo:
    		popupText.setText(this.getString(R.string.info_text_pass));
    		break;
    	case R.id.main_graphPassInfo:
    		popupText.setText(this.getString(R.string.info_graph_pass));
    		break;
    	case R.id.main_bothPassInfo:
    		popupText.setText(this.getString(R.string.info_both_pass));
    		break;
  		default:
  			popupText.setText(this.getString(R.string.error));
   			break;
    	}
    	pw.showAtLocation(this.findViewById(R.id.main_midText), Gravity.CENTER, 0, 0); 
    }
    
    public void popupDismiss(View v) {
    	if (pw != null)
    		pw.dismiss();
    }
    
    public void popupConfirm(View v) {
    	if (pw == null)
    		return;
    	
    	PasswordPopupWindow ppw = (PasswordPopupWindow)pw;
    	ppw.nextStage();
    }
}