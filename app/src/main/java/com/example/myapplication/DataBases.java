package com.example.myapplication;

import android.provider.BaseColumns;

public class DataBases {
    public static final class CreateDB implements BaseColumns{
        public static final String FOODNO = "foodNo";
        public static final String FNAME ="fName";
        public static final String FPRICE = "fPrice";
        public static final String _TABLENAME0 = "food";
        public static final String _CREATE0 =
                "create table if not exists "+_TABLENAME0+"("+_ID+" integer primary key autoincrement, "
                        +FOODNO+" integer not null ,"
                        +FNAME+" text not null , "
                        +FPRICE+" integer not null)";
        public static final String ORDERFOOD = "orderFood";
        public static final String COUNTFOOD = "countFood";
        public static final String TOTALPRICE = "totalPrice";
        public static final String _TABLENAME1 = "sales";
        public static final String _CREATE1 = "create table if not exists "+_TABLENAME1+"("+_ID+" integer primary key autoincrement, " +ORDERFOOD+" text not null ," +COUNTFOOD+" integer not null , " +TOTALPRICE+" integer not null)";

    }
}
