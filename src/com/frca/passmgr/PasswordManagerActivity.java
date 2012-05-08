package com.frca.passmgr;

import com.frca.passmgr.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class PasswordManagerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void buttonClicked(View v) {
    	TextView txtView = (TextView)findViewById(R.id.textView1);
    	Button but = (Button)v;
    	txtView.setText(but.getText());
    }
}