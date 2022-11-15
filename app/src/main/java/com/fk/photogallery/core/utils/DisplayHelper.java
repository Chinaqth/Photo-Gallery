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
     * 获取屏幕高度(推荐使用{@link #getWindowRealWidth(Context)})
     *
     * @return
     */
    @Deprecated
    public static int getHeightPixels() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取 APP 显示高度
     *
     * @param activity 当前活动状态的 Activity
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
     * 获取软键盘显示高度
     *
     * @param activity 当前活动状态的 Activity
     * @return 软键盘高度 = 分辨率高 - 状态栏高 - 应用可视高,第一次获取,该值为787
     */
    public static int getKeyBoardHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //屏幕当前可见高度，不包括状态栏
        int displayHeight = rect.bottom - rect.top;
        //屏幕可用高度
        int availableHeight = getAppHeight(activity);
        //用于计算键盘高度

        return availableHeight - displayHeight - getStatusBarHeight(activity);
    }

    /**
     * 获取状态栏的高度
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
     * @return 任何情况下都是返回屏幕的"最大"像素值！或者0
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
     * @return 任何情况下都是返回屏幕的"最大"像素值！或者0
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
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }
}
