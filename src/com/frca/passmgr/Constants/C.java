package com.frca.passmgr.Constants;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

// class with constants and global methods
public final class C
{
    public static final String settingsFilename="settings.xml";
    public static final String TEXT_PASS_HASH="text_password_hash";
    public static final String GRAP_PASS_HASH="graphic_password_hash";

    public static final String hashText(String text)
    {
        MessageDigest m;
        try
        {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        m.reset();
        try
        {
            m.update(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);

        while(hashtext.length() < 32 )
            hashtext = "0"+hashtext;
        return hashtext;
    }

    public static void ShowAlert(Context context, int messageId, int buttonId, int titleId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (messageId != 0)
            builder.setMessage(messageId);
        if (titleId != 0)
            builder.setTitle(titleId);
        
        builder.setPositiveButton(buttonId, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    
    public static void ShowAlert(Context context, String message, int buttonId, int titleId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (message != null)
            builder.setMessage(message);
        if (titleId != 0)
            builder.setTitle(titleId);
        
        builder.setPositiveButton(buttonId, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}