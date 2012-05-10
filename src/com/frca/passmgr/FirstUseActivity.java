package com.frca.passmgr;

import com.frca.passmgr.R;
import com.frca.passmgr.Constants.C;
import com.frca.passmgr.Database.DbVariables;
import com.frca.passmgr.PasswordActivities.CreateTextPasswordActivity;
import com.frca.passmgr.PasswordActivities.GraphicPasswordActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstUseActivity extends Activity
{
    private int selectedType = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_use_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        boolean doFinish = false;
        Intent intent = getIntent();
        if (requestCode == 1)        // text password
        {
            if (resultCode == RESULT_OK)
            {
                DbVariables vars = new DbVariables(this);
                vars.open();

                vars.setVariable(C.TEXT_PASS_HASH, data.getStringExtra(C.TEXT_PASS_HASH));              

                if (selectedType == 3)
                {
                    Intent tempIntent = new Intent(this, GraphicPasswordActivity.class);
                    startActivityForResult(tempIntent, 2);
                }
                else
                    doFinish = true;
            }
            else if (resultCode == RESULT_CANCELED)
                C.ShowAlert(this, R.string.textpass_notmatch, R.string.ok, 0);
        }
        else if (requestCode == 2)    // graphic password
        {
            if (resultCode == RESULT_OK)
            {
                DbVariables vars = new DbVariables(this);
                vars.open();
                vars.setVariable(C.GRAP_PASS_HASH, intent.getStringExtra("passwordHash"));
                doFinish = true;
            }
        }

        if (doFinish)
        {
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void buttonClicked(View v)
    {
        switch(v.getId())
        {
            case R.id.main_textPass:
            {
                selectedType = 1;
                Intent tempIntent = new Intent(this, CreateTextPasswordActivity.class);
                startActivityForResult(tempIntent, 1);
                break;
            }
            case R.id.main_graphPass:
            {
                selectedType = 2;
                Intent tempIntent = new Intent(this, GraphicPasswordActivity.class);
                startActivityForResult(tempIntent, 2);
                break;
            }
            case R.id.main_bothPass:
            {
                selectedType = 3;
                Intent tempIntent = new Intent(this, CreateTextPasswordActivity.class);
                startActivityForResult(tempIntent, 1);
                break;
            }
              default:
                   break;
        }
    }

    public void infoClicked(View v)
    {
        int messageId;
        switch(v.getId())
        {
            case R.id.main_textPassInfo:
                messageId = R.string.info_text_pass;
                break;
            case R.id.main_graphPassInfo:
                messageId = R.string.info_graph_pass;
                break;
            case R.id.main_bothPassInfo:
                messageId = R.string.info_both_pass;
                break;
              default:
                  messageId = 0;
                   break;
        }

        C.ShowAlert(this, messageId, R.string.ok, 0);
    }
}