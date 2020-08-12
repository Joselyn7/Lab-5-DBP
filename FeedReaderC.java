package com.example.jos.carteraclientes.BaseDatos;
import android.provider.BaseColumns;

public final class FeedReaderC {
    private FeedReaderC(){}

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "CLIENTE";
        public static final String COLUMN_NAME ="NOMBRE";
        public static final String COLUMN_ADDRESS ="DIRECCION";
        public static final String COLUMN_EMAIL ="EMAIL";
        public static final String COLUMN_PHONE ="TELEFONO";
    }
}
