package com.cosc325.bouncerbuddy;

import android.provider.BaseColumns;

public class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entries";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_ZIP = "zip";
       

    }
}
