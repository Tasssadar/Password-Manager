package com.frca.passmgr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity
{
	private DbVariables vars;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        vars = new DbVariables(this);
        vars.open();
        
        vars.setVariable("text_password_hash", 124322243);
        vars.deleteVariable("graphic_password_hash");
        vars.deleteVariable("text_password_hash");
        // check if we have password_hash saved
        if (vars.getVariable("text_password_hash") == null &&
        	vars.getVariable("graphic_password_hash") == null)
        {
        	Intent fuIntent = new Intent(this, FirstUseActivity.class);
        	startActivityForResult(fuIntent, 10);
        	// start first use activity
        }
        else
        {
        	if (vars.getVariable("text_password_hash") != null)
        	{
        		Intent textIntent = new Intent(this, TextPasswordActivity.class);
        		startActivityForResult(textIntent, 1);        		
        	}
        	else	// graphic must be set
        	{
    			Intent graphicIntent = new Intent(this, GraphicPasswordActivity.class);
    			startActivityForResult(graphicIntent, 2);        		
        	}        	
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode == RESULT_OK)
    	{
    		if (requestCode == 1) // text finished
    		{
    			// authenticate hash
    			setContentView(R.layout.main);
    	    	TextView txtView = (TextView)findViewById(R.id.main_midText);
    	    	txtView.setText(data.getStringExtra("inputPassword"));
    			// if graphical also set, new intent
    			if (vars.getVariable("graphic_password_hash") != null)
    			{
    				Intent graphicIntent = new Intent(this, GraphicPasswordActivity.class);
    				startActivityForResult(graphicIntent, 2);
    			}
    			else
    			{
    				// main menu
    			}
    		}
    		else if (requestCode == 2) // graphic finished
    		{
    			// authenticate hash
    			
    			// main menu    			
    		}
    	}
    }
}