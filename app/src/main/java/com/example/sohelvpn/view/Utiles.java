package com.example.sohelvpn.view;

import android.net.Uri;

import com.example.sohelvpn.R;

public class Utiles {
    public static String getImageUrl(int resourceId) {
        // Generate the image URL using the correct format
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }
}
