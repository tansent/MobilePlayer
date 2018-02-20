package jtli.com.mobileplayer.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jtli.com.mobileplayer.R;
import jtli.com.mobileplayer.app.App;
import jtli.com.mobileplayer.bean.MediaItem;
import jtli.com.mobileplayer.utils.Utils;

/**
 * Created by Jingtian(Tansent).
 */

public class VideoAdapter extends BaseQuickAdapter<MediaItem, BaseViewHolder> {
    private Utils utils;
    public VideoAdapter(List<MediaItem> data) {
        super(R.layout.item_video, data);
        utils = new Utils();
    }

    @Override
    protected void convert(BaseViewHolder helper, final MediaItem item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_size, Formatter.formatFileSize(App.instance, item.getSize()) );
        helper.setText(R.id.tv_time, utils.stringForTime((int) item.getDuration()));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(item.getData());
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClickListener(String data);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
