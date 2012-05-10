package com.frca.passmgr.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;


public class DbVariables
{
    // Database fields
    private SQLiteDatabase database;
    private SqlLiteHelper dbHelper;
    private Map<String, String> values;

    public DbVariables(Context context)
    {
        dbHelper = new SqlLiteHelper(context);
        values = new HashMap<String, String>();
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public String getVariable(String key)
    {
        // if loaded to locale map return its content
        if (values.containsKey(key))
            return (String)values.get(key);

        // otherwise load from DB
        Cursor cur = database.query(SqlLiteHelper.TABLE_VARIABLES, new String[] {SqlLiteHelper.VARIABLES_VALUE}, SqlLiteHelper.VARIABLES_KEY + " = \"" + key + "\"", null, null, null, null);

        if (!cur.moveToFirst() || cur.getString(0) == null ||cur.getString(0).equals(""))
            return null;

        String value = cur.getString(0);
        values.put(key, value);
        return value;
    }

    public int getIntVariable(String key)
    {
        String strVar = getVariable(key);
        try
        {
            return Integer.parseInt(strVar);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public float getFloatVariable(String key)
    {
        String strVar = getVariable(key);
        try
        {
            return Float.parseFloat(strVar);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public void setVariable(String key, String value)
    {
        // save in locale map
        values.put(key, value);

        // save in DB
        ContentValues data = new ContentValues();
        data.put(SqlLiteHelper.VARIABLES_KEY, key);
        data.put(SqlLiteHelper.VARIABLES_VALUE, value);
        database.replace(SqlLiteHelper.TABLE_VARIABLES, null, data);
    }

    public void setVariable(String key, int value)
    {
        setVariable(key, Integer.toString(value));
    }
    
    public void setVariable(String key, float value)
    {
        setVariable(key, Float.toString(value));
    }
    
    public void deleteVariable(String key)
    {
        values.remove(key);
        database.delete(SqlLiteHelper.TABLE_VARIABLES, SqlLiteHelper.VARIABLES_KEY + " = \"" + key + "\"", null);
    }
}
