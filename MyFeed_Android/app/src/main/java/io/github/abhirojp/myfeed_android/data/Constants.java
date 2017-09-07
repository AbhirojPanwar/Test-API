package io.github.abhirojp.myfeed_android.data;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by abhiroj on 6/9/17.
 */

public class Constants {

    public static final String FRAGMENT_TAG="frag_key";
    // python manage.py runserver @BASE_URL to connect it with mobile device.
    public static final String BASE_URL = "http://192.168.0.104:8000/";
    public static HashMap<String,Fragment> fragtag=new HashMap<>();

}
