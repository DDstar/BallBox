package org.ddstar.ballbox.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.ddstar.ballbox.Base.Contants;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class SPControl {
    public static void saveString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences(Contants.SHAREPRE_KEY, Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    public static String getString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(Contants.SHAREPRE_KEY, Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }




    public static void saveUserID(Context context,String userId){
        saveString(context,Contants.USER_ID_KEY,userId);
    }
}
