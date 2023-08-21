package com.example.sohelvpn;

import android.net.Uri;

public class Utiles {
    public static String getImageUrl(int resourceId) {
        // Generate the image URL using the correct format
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }
}
