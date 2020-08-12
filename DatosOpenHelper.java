package com.example.jos.carteraclientes.BaseDatos;
import com.example.jos.carteraclientes.BaseDatos.FeedReaderC;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatosOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DATOS";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES  =
            "CREATE TABLE IF NOT EXISTS " + FeedReaderC.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderC.FeedEntry.COLUMN_NAME + " VARCHAR(250), " +
                    FeedReaderC.FeedEntry.COLUMN_ADDRESS + " VARCHAR(250), " +
                    FeedReaderC.FeedEntry.COLUMN_EMAIL + " VARCHAR(200), " +
                    FeedReaderC.FeedEntry.COLUMN_PHONE + " VARCHAR(20))";

    public DatosOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {

    }
}
