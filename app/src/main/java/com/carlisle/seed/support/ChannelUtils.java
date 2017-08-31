package com.carlisle.seed.support;

import android.content.Context;
import android.text.TextUtils;

import com.meituan.android.walle.WalleChannelReader;

/**
 * Creator      : carlisle
 * Date         : 31/08/2017
 * Description  :
 */

public class ChannelUtils {
    public static int getChannel(Context context) {
        String channel = WalleChannelReader.getChannel(context);
        return Integer.parseInt(TextUtils.isEmpty(channel) ? "0" : channel);
    }
}
