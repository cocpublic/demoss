package com.tencent.rfix.lib.atta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库辅助类，用于管理 ATTA 记录。
 */
public class ATTARecordDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "atta.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "record";

    public ATTARecordDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, process TEXT, params TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        RFixLog.b("RFix.ATTADatabaseHelper", String.format("onUpgrade oldVersion=%s newVersion=%s", oldVersion, newVersion));
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * 插入一条 ATTA 记录。
     * @param process 进程名
     * @param params 参数
     * @return 插入行的ID，失败返回-1
     */
    public long insertRecord(String process, String params) {
        long result = -1L;
        try {
            ContentValues values = new ContentValues();
            values.put("process", process);
            values.put("params", params);
            SQLiteDatabase db = getWritableDatabase();
            result = db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            RFixLog.e("RFix.ATTADatabaseHelper", "insertATTARecord fail.", e);
        }
        return result;
    }

    /**
     * 删除指定ID的 ATTA 记录。
     * @param id 记录ID
     * @return 删除成功返回true，否则false
     */
    public boolean deleteRecord(int id) {
        boolean success = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            int rows = db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});
            success = rows > 0;
        } catch (Exception e) {
            RFixLog.e("RFix.ATTADatabaseHelper", "deleteATTARecord fail.", e);
        }
        return success;
    }

    /**
     * 查询指定进程的所有 ATTA 记录。
     * @param process 进程名
     * @return 记录列表
     */
    public List<ATTARecord> getAllRecords(String process) {
        List<ATTARecord> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(TABLE_NAME, new String[]{"_id", "process", "params"}, "process=?", new String[]{process}, null, null, null);
            int idIndex = cursor.getColumnIndex("_id");
            int paramsIndex = cursor.getColumnIndex("params");
            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                String params = cursor.getString(paramsIndex);
                list.add(new ATTARecord(id, process, params));
            }
        } catch (Exception e) {
            RFixLog.e("RFix.ATTADatabaseHelper", "getAllATTARecord fail.", e);
        } finally {
            if (cursor != null) cursor.close();
        }
        return list;
    }

    /**
     * ATTA 记录实体类
     */
    public static class ATTARecord {
        public final int id;
        public final String process;
        public final String params;
        public ATTARecord(int id, String process, String params) {
            this.id = id;
            this.process = process;
            this.params = params;
        }
    }
}
