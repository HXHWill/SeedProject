package com.carlisle.seed.support;

import android.content.Context;

import com.meituan.android.walle.ChannelInfo;
import com.meituan.android.walle.WalleChannelReader;

/**
 * Creator      : carlisle
 * Date         : 31/08/2017
 * Description  :
 */

public class ChannelUtils {
    public static String getChannel(Context context) {
        ChannelInfo channelInfo = WalleChannelReader.getChannelInfo(context);
        if (channelInfo != null) {
            return channelInfo.getChannel();
        } else {
            return "default";
        }
    }
}
