package com.findu.chattest.tools;

/**
 * Created by Administrator on 2016/5/21.
 */

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;

import com.findu.chattest.R;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmileyParser {
    private Context mContext;
    private String[] mSmileyTexts;
    private Pattern mPattern;
    private HashMap<String, Integer> mSmileyToRes;
    public static final int[] DEFAULT_SMILEY_RES_IDS = {
            R.mipmap.tiny_01,
            R.mipmap.tiny_02,
            R.mipmap.tiny_03,
            R.mipmap.tiny_04,
            R.mipmap.tiny_05,
            R.mipmap.tiny_06,
            R.mipmap.tiny_07,
            R.mipmap.tiny_08,
            R.mipmap.tiny_09,
            R.mipmap.tiny_10,
            R.mipmap.tiny_11,
    };

    public void SmileyParser(Context context) {
        mContext = context;
        mSmileyTexts = mContext.getResources().getStringArray(DEFAULT_SMILEY_TEXTS);
        mSmileyToRes = buildSmileyToRes();
        mPattern = buildPattern();
    }

    public static final int DEFAULT_SMILEY_TEXTS = R.array.default_smiley_texts;

    private HashMap<String, Integer> buildSmileyToRes() {
        if (DEFAULT_SMILEY_RES_IDS.length != mSmileyTexts.length) {
//        	Log.w("SmileyParser", "Smiley resource ID/text mismatch");
            //表情的数量需要和数组定义的长度一致！
            throw new IllegalStateException("Smiley resource ID/text mismatch");
        }

        HashMap<String, Integer> smileyToRes = new HashMap<String, Integer>(mSmileyTexts.length);
        for (int i = 0; i < mSmileyTexts.length; i++) {
            smileyToRes.put(mSmileyTexts[i], DEFAULT_SMILEY_RES_IDS[i]);
        }

        return smileyToRes;
    }

    //构建正则表达式
    private Pattern buildPattern() {
        StringBuilder patternString = new StringBuilder(mSmileyTexts.length * 3);
        patternString.append('(');
        for (String s : mSmileyTexts) {
            patternString.append(Pattern.quote(s));
            patternString.append('|');
        }
        patternString.replace(patternString.length() - 1, patternString.length(), ")");

        return Pattern.compile(patternString.toString());
    }

    //根据文本替换成图片
    public CharSequence replace(CharSequence text) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        Matcher matcher = mPattern.matcher(text);
        while (matcher.find()) {
            int resId = mSmileyToRes.get(matcher.group());
            builder.setSpan(new ImageSpan(mContext, resId), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }
}
