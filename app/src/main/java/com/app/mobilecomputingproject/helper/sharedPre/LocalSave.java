package com.app.mobilecomputingproject.helper.sharedPre;

import android.content.Context;


public class LocalSave {
    private static final String IS_FIRST      = "isFirstTime";
    private static final String LANG = "lang";
    private static LocalSave instance = null;
    private Context mContext;

    private LocalSave(Context mContext) {
        this.mContext = mContext;
    }

    public static LocalSave getInstance(Context context) {
        if (instance == null) {
            instance = new LocalSave(context);
        }
        return instance;
    }

    private Context getmContext() {
        return mContext;
    }

    public void setFirstTime(boolean isFirstTime){
        SharedPrefs.save(getmContext(),IS_FIRST,isFirstTime);
    }
    public boolean isFirstTime(){
        return SharedPrefs.getBoolean(getmContext(),IS_FIRST,true);
    }


    public void setLang(String lang){
        SharedPrefs.save(getmContext(),LANG,lang);
    }
    public String getLang(){
        return SharedPrefs.getString(getmContext(),LANG,"en");
    }


/*
    public void seveUserInformation(EditProfile editProfile){
        SharedPrefs.save(getmContext(),KEY_USER_INFO,new Gson().toJson(editProfile));
    }
    public EditProfile editProfile(){
        return new Gson().fromJson(SharedPrefs.getString(getmContext(),KEY_USER_INFO),EditProfile.class);
    }
*/
    public boolean isFirst(){
        return SharedPrefs.getBoolean(getmContext(),IS_FIRST,false);
    }

    public void setIsFirst(boolean isFirst){
        SharedPrefs.save(getmContext(),IS_FIRST,isFirst);
    }

}
