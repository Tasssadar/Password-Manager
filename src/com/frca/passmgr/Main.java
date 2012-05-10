package com.frca.passmgr;

import com.frca.passmgr.Constants.C;
import com.frca.passmgr.Database.DbVariables;
import com.frca.passmgr.PasswordActivities.GraphicPasswordActivity;
import com.frca.passmgr.PasswordActivities.TextPasswordActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Main extends Activity
{
	private DbVariables vars;
	private boolean isLoggedIn = false;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        if (isLoggedIn)
        {
        	C.ShowAlert(this, R.string.logged, R.string.confirm, 0);
        	return;
        }
        
        vars = new DbVariables(this);
        vars.open();
        
        
        if (vars.getVariable(C.TEXT_PASS_HASH) == null && vars.getVariable(C.GRAP_PASS_HASH) == null)
        {
        	Intent fuIntent = new Intent(this, FirstUseActivity.class);
        	startActivityForResult(fuIntent, 10);
        }
        else
        {
        	if (vars.getVariable(C.TEXT_PASS_HASH) != null)
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
    	if (resultCode != RESULT_OK)
    		return;
    	
    	switch (requestCode)
    	{
    		case 1:		// text finished
    		{
    			// authenticate hash
    			String inputHash = data.getStringExtra(C.TEXT_PASS_HASH); 
    			if (inputHash != null && inputHash.equals(vars.getVariable(C.TEXT_PASS_HASH)))
    			{
        	    	// if graphical also set, new intent
        			if (vars.getVariable(C.GRAP_PASS_HASH) != null)
        			{
        				Intent graphicIntent = new Intent(this, GraphicPasswordActivity.class);
        				startActivityForResult(graphicIntent, 2);
        			}
        			else
        				isLoggedIn = true;
    			}
    			else
    				C.ShowAlert(this, R.string.textpass_notmatch, R.string.ok, 0);

    			break;
    		}
    		case 2: // graphic finished
    		{
    			// authenticate hash
    			
    			isLoggedIn = true;
    			break;
    		}
    		case 10:	// first use finished
    		{
    			isLoggedIn = true;
    			break;
    		}
    		default:
    			break;
    	}
    	
    	if (isLoggedIn)
    	{
    		onCreate(new Bundle());    		
    	}
    }
}