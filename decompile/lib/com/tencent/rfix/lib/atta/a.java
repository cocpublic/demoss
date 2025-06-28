/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/atta;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.util.ArrayList;

// class: com/tencent/rfix/lib/atta/a
public class a {

    public a(Context context) {
        super(context, "atta.db", null, 3);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table record(_id integer primary key autoincrement, process text,params text)");
    }

    public void onUpgrade(SQLiteDatabase database, int i0, int i1) {
        RFixLog.b("RFix.ATTADatabaseHelper", String.format("onUpgrade oldVersion=%s newVersion=%s", new Object[]{Integer.valueOf(i0), Integer.valueOf(i1)}));
        database.execSQL("drop table if exists record");
        this.onCreate(database);
    }

    public long a(String str0, String str1) {
        long l1 = -1L;
        try {
            ContentValues values = new ContentValues();
            values.put("process", str0);
            values.put("params", str1);
            SQLiteDatabase database = this.getWritableDatabase();
            l1 = database.insert("record", null, values);
        }
        catch (Exception var_5_1) {
            RFixLog.e("RFix.ATTADatabaseHelper", "insertATTARecord fail.", var_5_1);
        }
        return l1;
    }

    public boolean a(int i0) {
        int i3 = false;
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            int i2 = database.delete("record", "_id=?", new String[]{String.valueOf(i0)});
            i3 = i2 > 0 ? 0 : 1;
        }
        catch (Exception var_3_1) {
            RFixLog.e("RFix.ATTADatabaseHelper", "deleteATTARecord fail.", var_3_1);
        }
        return i3;
    }

    public List<b> a(String str0) {
        ArrayList list = new ArrayList();
        try {
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.query("record", new String[]{"_id", "process", "params"}, "process=?", new String[]{str0}, null, null, null);
            int i0 = cursor.getColumnIndex("_id");
            int i1 = cursor.getColumnIndex("params");
            while (cursor.moveToNext()) {
                int i2 = cursor.getInt(i0);
                String str1 = cursor.getString(i1);
                b b = new b(i2, str0, str1);
                list.add(b);
            }
            cursor.close();
        }
        catch (Exception var_3_1) {
            RFixLog.e("RFix.ATTADatabaseHelper", "getAllATTARecord fail.", var_3_1);
        }
        return list;
    }

}
