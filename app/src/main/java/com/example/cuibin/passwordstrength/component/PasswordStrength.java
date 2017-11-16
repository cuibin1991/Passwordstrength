package com.example.cuibin.passwordstrength.component;

import android.graphics.Color;
import android.util.Log;

import com.example.cuibin.passwordstrength.R;

/**
 * Created by cuibin on 17/11/16.
 */

public enum PasswordStrength {

    WEAK(R.string.password_strength_weak, Color.RED),
    MEDIUM(R.string.password_strength_medium, 0xFFFFD700),
    STRONG(R.string.password_strength_strong, 0xFF228B22);

    //--------REQUIREMENTS--------
    static int REQUIRED_LENGTH = 8;
    static int MAXIMUM_LENGTH = 15;
    static boolean REQUIRE_SPECIAL_CHARACTERS = true;
    static boolean REQUIRE_DIGITS = true;
    static boolean REQUIRE_LOWER_CASE = true;
    static boolean REQUIRE_UPPER_CASE = false;
    static String TAG = "PasswordStrength";

    int resId;
    int color;

    PasswordStrength(int resId, int color) {
        this.resId = resId;
        this.color = color;
    }

    public CharSequence getText(android.content.Context ctx) {
        return ctx.getText(resId);
    }

    public int getColor() {
        return color;
    }

    public static PasswordStrength calculateStrength(String password) {
        //当前安全等级
        int currentScore = 0;
        //是否包含大写字母
        boolean sawUpper = false;
        //是否包含小写字母
        boolean sawLower = false;
        //是否包含数字
        boolean sawDigit = false;
        //是否包含特殊字符
        boolean sawSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            //如果不是字母或者数字就是特殊字符，不是空字符
            if (!sawSpecial && !Character.isLetterOrDigit(c)) {
                sawSpecial = true;
            } else {
                //判断是否包含数字
                if (!sawDigit && Character.isDigit(c)) {
                    sawDigit = true;
                } else {
                    //判断是否包含大小写字母
                    if (!sawUpper || !sawLower) {
                        if(Character.isUpperCase(c) && !sawUpper)
                            sawUpper = true;
                        if(Character.isLowerCase(c) && !sawLower)
                            sawLower = true;
                    }
                }
            }
        }

        Log.i(TAG , "Digit:" + sawDigit);
        Log.i(TAG , "letter:" + (sawLower || sawUpper));
        Log.i(TAG , "special:" + sawSpecial);

        /** 大于八位并且有特殊字符 */
        if((password.length() >= REQUIRED_LENGTH) && sawSpecial){
            currentScore = 2;
        }
        else{
            /** 小于八位或者仅包含字母或者数字 */
            if((password.length() < REQUIRED_LENGTH) || !((sawDigit && (sawLower || sawUpper)))){
                currentScore = 0;
            }
            /** 其他情况 */
            else{
                currentScore = 1;
            }
        }

        switch (currentScore) {
            case 0:
                return WEAK;
            case 1:
                return MEDIUM;
            case 2:
                return STRONG;
            default:
        }
        return STRONG;
    }

}
