package com.frca.passmgr.PasswordActivities;

import com.frca.passmgr.R;
import com.frca.passmgr.Constants.C;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TextPasswordActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_password);
    }
    
    public void confirmCall(View v)
    {
        EditText mEdit = (EditText)findViewById(R.id.textpass_pass);
        if (mEdit.getText().toString().equals(""))
        {
            C.ShowAlert(this, R.string.textpass_alert, R.string.ok, 0);
            return;
        }
        Intent intent = getIntent();
        intent.putExtra(C.TEXT_PASS_HASH, C.hashText(mEdit.getText().toString()));
        setResult(RESULT_OK, intent);
        finish();
    }
}
