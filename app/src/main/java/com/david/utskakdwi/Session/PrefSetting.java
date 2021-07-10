package com.david.utskakdwi.Session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.david.utskakdwi.Admin.homeAdmin;
import com.david.utskakdwi.Pembeli.HomeUser;

public class PrefSetting {
    public static String _id;
    public static String userName;
    public static String password;
    public static String namaLengkap;
    public static String alamatLengkap;
    public static String role;
    Activity activity;

    public PrefSetting(Activity activity){
        this.activity = activity;
    }

    public SharedPreferences getSharePreferances(){
        SharedPreferences preferences = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return preferences;
    }

    public void isLogin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        if(session.isLoggedIn()){
            pref = getSharePreferances();
            _id  = pref.getString("_id", "");
            userName = pref.getString("userName", "");
            password = pref.getString("password", "");
            namaLengkap = pref.getString("namaLengkap", "");
            alamatLengkap = pref.getString("alamatLengkap", "");
            role = pref.getString("role", "");
        }else {
            session.setLogin(false);
            session.setSessid(0);
            Intent i = new Intent(activity, activity.getClass());
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void checkLogin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        _id  = pref.getString("_id", "");
        userName = pref.getString("userName", "");
        password = pref.getString("password", "");
        namaLengkap = pref.getString("namaLengkap", "");
        alamatLengkap = pref.getString("alamatLengkap", "");
        role = pref.getString("role", "");
        if(session.isLoggedIn()){
            if (role.equals("1")){
                Intent i = new Intent(activity, homeAdmin.class);
                activity.startActivity(i);
                activity.finish();
            }else {
                Intent i = new Intent(activity, HomeUser.class);
                activity.startActivity(i);
                activity.finish();
            }
        }
    }


    public void storeRegIdSharedPreferences(Context context, String _id, String userName, String password,
                                            String namaLengkap, String alamatLengkap, String role, SharedPreferences prefs){

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("_id", _id);
        editor.putString("userName", userName);
        editor.putString("password", password);
        editor.putString("namaLengkap", namaLengkap);
        editor.putString("alamatLengkap", alamatLengkap);
        editor.putString("role", role);
        editor.commit();

    }
}
