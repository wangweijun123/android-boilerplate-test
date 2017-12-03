package uk.co.ribot.androidboilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import uk.co.ribot.androidboilerplate.data.model.Contributor;

public class Db {

    public Db() { }

    public abstract static class RibotProfileTable {
        public static final String TABLE_NAME = "ribot_profile";
        public static final String TABLE_NAME_CONTRIBUTOR = "contributor";


        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_HEX_COLOR = "hex_color";
        public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
        public static final String COLUMN_AVATAR = "avatar";
        public static final String COLUMN_BIO = "bio";

        public static final String COLUMN_AVATAR_URL = "avatar_url";
        public static final String COLUMN_CONTRIBUTIONS = "contributions";



        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                        COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                        COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                        COLUMN_HEX_COLOR + " TEXT NOT NULL, " +
                        COLUMN_DATE_OF_BIRTH + " INTEGER NOT NULL, " +
                        COLUMN_AVATAR + " TEXT, " +
                        COLUMN_BIO + " TEXT" +
                " ); ";

        public static final String CREATE_CONTRIBUTOR =
                "CREATE TABLE " + TABLE_NAME_CONTRIBUTOR + " (" +
                        COLUMN_AVATAR_URL + " TEXT, " +
                        COLUMN_CONTRIBUTIONS + " TEXT" +
                        " ); ";

        public static ContentValues toContentValues(Contributor contributor) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_AVATAR_URL, contributor.avatar_url);
            values.put(COLUMN_CONTRIBUTIONS, contributor.contributions+"");
            return values;
        }


        public static Contributor parseCursor2(Cursor cursor) {
            String avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR_URL));
            String contri = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTRIBUTIONS));
            Contributor contributor = new Contributor();
            contributor.avatar_url = avatarUrl;
            contributor.contributions = Integer.parseInt(contri);
            return contributor;
        }
    }
}
