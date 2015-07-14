package br.ufpe.cin.if1001.chatoffline.model.user;

import android.content.Context;
import android.content.SharedPreferences;

import br.ufpe.cin.if1001.chatoffline.model.data.communication.User;

public class UserPreferences {

        private static final String PREFS_NAME = "Preferences";

        private static final String PREFS_USER = "USER";
        private static final String PREFS_INITIAL = "INITIAL";


        private UserPreferences () {}

        private static SharedPreferences getSharedPreferences(Context context) {
            return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }

        public static User getUser(Context context) {
            User user = new User(0, getSharedPreferences(context).getString(PREFS_USER , ""), "");
            return user;
        }

        public static void setUser(Context context, String newValue) {
            final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.putString(PREFS_USER , newValue);
            editor.commit();
        }

        public static void setInitial(Context context, boolean newValue) {
            final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.putBoolean(PREFS_INITIAL, newValue);
            editor.commit();
        }

        public static boolean getInitial(Context context) {
            return getSharedPreferences(context).getBoolean(PREFS_INITIAL, false);
        }

    }
