package com.example.yesterday.yesterday;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class LoginSharedPreference {

    public static void setLogin(Activity ctx, ClientLoginInfo client) {
        SharedPreferences.Editor editor = ctx.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString("name", client.getName());
        editor.putString("id", client.getId());
        editor.putString("pass", client.getPass());
        editor.commit();
    }

    public static void logout(Activity ctx) {
        SharedPreferences.Editor editor = ctx.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString("name", "");
        editor.putString("id", "");
        editor.putString("pass", "");
        editor.commit();
    }
}
