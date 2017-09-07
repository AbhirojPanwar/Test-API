package io.github.abhirojp.myfeed_android.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.HashMap;

/**
 * Created by abhiroj on 6/9/17.
 */

public class Constants {

    public static final String positionForData="1";
    public static final String FRAGMENT_TAG="frag_key";
    public static HashMap<String,Fragment> fragtag=new HashMap<>();
    // python manage.py runserver @baseUrl to connect it with mobile device.
    public static final String baseUrl="http://192.168.0.104:8000/";

}
