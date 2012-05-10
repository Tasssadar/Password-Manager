package com.frca.passmgr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TextPasswordActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_password);
        
        Intent intent = getIntent();
        if (intent.getBooleanExtra("isRepeate", false))	// is repeated
        {
        	TextView text = (TextView)findViewById(R.id.textpass_title);
        	text.setText(this.getString(R.string.pass_input_again));
        }
    }
    
    public void confirmCall(View v)
    {
    	EditText mEdit = (EditText)findViewById(R.id.textpass_pass);
    	if (mEdit.getText().toString().equals(""))
    	{
    		ShowAlert(this);
    		return;
    	}
    	Intent intent = getIntent();    	
    	intent.putExtra("inputPassword", C.hashText(mEdit.getText().toString()));
    	setResult(RESULT_OK, intent);
    	finish();
    }
    
    public static void ShowAlert(Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.textpass_alert);
        //builder.setTitle(R.string.error);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
   
}
