package com.example.podcast.tools;

import java.util.Formatter;
import java.util.Locale;

public class test {
    /*private StringBuilder sFormatBuilder = new StringBuilder();
    private Formatter sFormatter = new Formatter(sFormatBuilder, Locale.getDefault());
    private final Object[] sTimeArgs = new Object[3];

    private String makeTimeString(int secs) {
        *//**
         * %[argument_index$][flags][width]conversion 可選的
         * argument_index 是一個十進位制整數，用於表明引數在引數列表中的位置。第一個引數由 "1$"
         * 引用，第二個引數由 "2$" 引用，依此類推。 可選 flags
         * 是修改輸出格式的字符集。有效標誌集取決於轉換型別。 可選 width
         * 是一個非負十進位制整數，表明要向輸出中寫入的最少字元數。 可選 precision
         * 是一個非負十進位制整數，通常用來限制字元數。特定行為取決於轉換型別。 所需 conversion
         * 是一個表明應該如何格式化引數的字元。給定引數的有效轉換集取決於引數的資料型別。
         *//*
        String durationformat = getString(R.string.durationformat);// <xliff:g
        // id="format">%1$02d:%2$02d:%3$02d</xliff:g>
        sFormatBuilder.setLength(0);
        secs = secs / 1000;
        Object[] timeArgs = sTimeArgs;
        timeArgs[0] = secs / 3600; // 秒
        timeArgs[1] = (secs % 3600) / 60; // 分
        timeArgs[2] = (secs % 3600 % 60) % 60; // 時
        return sFormatter.format(durationformat, timeArgs).toString().trim();
    }*/
}
