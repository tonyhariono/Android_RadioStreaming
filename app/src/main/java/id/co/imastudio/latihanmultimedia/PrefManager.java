package id.co.imastudio.latihanmultimedia;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tOnY-ROG on 5/17/2017.
 */

public class PrefManager {
    private static final String MASUKPERTAMA ="MASUKPERTAMA" ;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context mcontext;

    public PrefManager(Context mcontext) {
        this.mcontext = mcontext;
        pref=mcontext.getSharedPreferences("DATAMASUK",0);
        editor=pref.edit();

    }

    // masukin data ke pref
    public void setFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(MASUKPERTAMA,isFirstTime);
        editor.commit();
    }

    // ngambil data dari pref
    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(MASUKPERTAMA,true);
    }
}
