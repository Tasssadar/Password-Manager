package com.frca.passmgr.PasswordActivities;

import java.util.ArrayList;

import com.frca.passmgr.*;
import com.frca.passmgr.Constants.C;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GraphicPasswordActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic_password);
        
        LinearLayout ll = (LinearLayout)findViewById(R.id.grappass_field);
        
        ll.setOnTouchListener(new OnTouchListener()
        {

            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == android.view.MotionEvent.ACTION_UP)
                {
                    GraphicPasswordActivity.this.removeTouchListener();
                }
                else
                {
                    GraphicPasswordActivity.this.drawnIn(event.getX(), event.getY());
                }
                return true;
            }
            
        });
    }
    
    public void drawnIn(float x, float y)
    {
        //View v = findViewById(R.id.Y3X3);
        //String str = "top: "+v.getTop()+" bot: "+v.getBottom()+" left: "+v.getLeft()+" right: "+v.getRight();
        //C.ShowAlert(this, str, R.string.ok, 0);
        for (int buttId : buttonIds)
        {
            View v = findViewById(buttId);
            if (isMotionEventInView(v, x, y))
            {
                ImageView iv = (ImageView)v;
                iv.setImageState(new int[] { android.R.attr.state_checked }, false);
            }
        } 
    }
    
    public void removeTouchListener()
    {
        LinearLayout ll = (LinearLayout)findViewById(R.id.grappass_field);
        
        ll.setOnTouchListener(null);        
    }

    public boolean isMotionEventInView(View v, float x, float y)
    {
        View parent = (View)v.getParent(); 
        if (y < parent.getTop() || y > parent.getBottom())
            return false;
        
        if (x < v.getLeft() || x > v.getRight())
            return false;

        return true;
    }
    
    private int[] buttonIds = 
    {
            R.id.Y1X1, R.id.Y1X2, R.id.Y1X3, R.id.Y1X4, R.id.Y1X5,
            R.id.Y2X1, R.id.Y2X2, R.id.Y2X3, R.id.Y2X4, R.id.Y2X5,
            R.id.Y3X1, R.id.Y3X2, R.id.Y3X3, R.id.Y3X4, R.id.Y3X5,
            R.id.Y4X1, R.id.Y4X2, R.id.Y4X3, R.id.Y4X4, R.id.Y4X5,
            R.id.Y5X1, R.id.Y5X2, R.id.Y5X3, R.id.Y5X4, R.id.Y5X5
    };
}
