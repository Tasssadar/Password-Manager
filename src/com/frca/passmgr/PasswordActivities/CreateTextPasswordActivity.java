package com.frca.passmgr.PasswordActivities;

import com.frca.passmgr.R;
import com.frca.passmgr.Constants.C;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateTextPasswordActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_password_create);
    }
    
    public void confirmCall(View v)
    {
    	EditText mEdit1 = (EditText)findViewById(R.id.textpass_c_pass1);
    	EditText mEdit2 = (EditText)findViewById(R.id.textpass_c_pass2);
    	if (mEdit1.getText().toString().equals("") ||
    		mEdit2.getText().toString().equals(""))
    	{
    		C.ShowAlert(this, R.string.textpass_alert, R.string.ok, 0);
    		return;
    	}
    	
    	Intent intent = getIntent();
    	String hash1 = C.hashText(mEdit1.getText().toString());
    	String hash2 = C.hashText(mEdit2.getText().toString());

    	if (hash1.equals(hash2))
    	{
    		intent.putExtra(C.TEXT_PASS_HASH, hash1);
    		setResult(RESULT_OK, intent);
    	}
    	else
    		setResult(RESULT_CANCELED, intent);
    	
    	finish();
    }
}
