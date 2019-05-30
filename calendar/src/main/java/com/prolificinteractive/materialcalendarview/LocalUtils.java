package com.prolificinteractive.materialcalendarview;

import android.content.Context;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;

import java.util.Locale;

class LocalUtils {

  private LocalUtils() { }

  static boolean isRTL() {
    return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault())
        == ViewCompat.LAYOUT_DIRECTION_RTL;
  }

  public static int convertDpToPixel(int dp, Context context){
    return dp * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
  }
}
