package mobile.rama.filmkita.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionUtil {
    public static boolean isConnected (Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return  activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
