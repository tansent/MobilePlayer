package jtli.com.mobileplayer.pager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import jtli.com.mobileplayer.base.BasePager;
import jtli.com.mobileplayer.utils.LogUtil;

/**
 * Created by Jingtian(Tansent).
 */

public class AudioPager extends BasePager {

    private TextView textView;
    public AudioPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        LogUtil.e("Audio Pager initialized");
        textView = new TextView(context);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

}
