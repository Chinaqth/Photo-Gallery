/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fk.photogallery.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.fk.photogallery.base.model.RuntimeData;


public class DisplayHelper {

    public static int dp2px(float dp) {
        DisplayMetrics metrics = RuntimeData.getInstance().getContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    public static int dp2px(int dp) {
        DisplayMetrics metrics = RuntimeData.getInstance().getContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    public static float getDensity(Context context) {
        if (context == null) {
            return 1;
        }
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getDensityDPI(Context context) {
        if (context == null) {
            return 1;
        }
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    /**
     * ??????????????????(????????????{@link #getWindowRealWidth(Context)})
     *
     * @return
     */
    @Deprecated
    public static int getHeightPixels() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * ?????? APP ????????????
     *
     * @param activity ????????????????????? Activity
     * @return AppHeight(include ActionBar) = Screen Height - StatusHeight
     */
    public static int getAppHeight(Activity activity) {
        Rect rect = new Rect();
        if (activity != null) {
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        }
        return rect.height();
    }

    /**
     * ???????????????????????????
     *
     * @param activity ????????????????????? Activity
     * @return ??????????????? = ???????????? - ???????????? - ???????????????,???????????????,?????????787
     */
    public static int getKeyBoardHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //?????????????????????????????????????????????
        int displayHeight = rect.bottom - rect.top;
        //??????????????????
        int availableHeight = getAppHeight(activity);
        //????????????????????????

        return availableHeight - displayHeight - getStatusBarHeight(activity);
    }

    /**
     * ????????????????????????
     */
    public static int getStatusBarHeight(Context context) {
        context = RuntimeData.getInstance().getContext();
        Resources resources = context.getResources();
        int statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelOffset(statusBarHeightId);
    }

    /**
     * Get Display
     *
     * @param context Context for get WindowManager
     * @return Display
     */
    public static Display getDisplay(@NonNull Context context) {
        WindowManager wm;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            wm = activity.getWindowManager();
        } else {
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        if (wm != null) {
            return wm.getDefaultDisplay();
        }
        return null;
    }

    /**
     * @return ????????????????????????????????????"??????"??????????????????0
     */
    public static int getWindowRealHeight(Context context) {
        Display display = getDisplay(context);
        if (display == null) {
            return 0;
        }
        Point outSize = new Point();
        display.getRealSize(outSize);
        return outSize.y;
    }

    /**
     * @return ????????????????????????????????????"??????"??????????????????0
     */
    public static int getWindowRealWidth(@NonNull Context context) {
        Display display = getDisplay(context);
        if (display == null) {
            return 0;
        }
        Point outSize = new Point();
        display.getRealSize(outSize);
        return outSize.x;
    }

    public static boolean isSoftShowing(Activity activity) {
        if (activity == null) return false;
        //?????????????????????????????????
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //??????View???????????????bottom
        Rect rect = new Rect();
        //DecorView??????activity?????????view
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //???????????????????????????????????????????????????????????????screenHeight = rect.bottom + ????????????????????????
        //??????screenHeight*2/3????????????
        return screenHeight * 2 / 3 > rect.bottom;
    }
}
