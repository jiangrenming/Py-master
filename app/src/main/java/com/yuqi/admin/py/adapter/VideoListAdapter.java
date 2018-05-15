package com.yuqi.admin.py.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.VideoBean;
import com.yuqi.admin.py.utils.ToastUtil;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2018/3/5/005.
 */

public class VideoListAdapter extends BaseAdapter {
    public static final String TAG = "JiaoZiVideoPlayer";
    private Context context;
    private VideoBean videoBean;

    public VideoListAdapter(Context context, VideoBean videoBean ) {
        this.context = context;
        this.videoBean = videoBean;
    }
    @Override
    public int getCount() {
        if(videoBean.getVideos().size()==0){
            ToastUtil.show(context,"视频列表为0");
        }
        return videoBean.getVideos().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_video_list, null);
            viewHolder.video_title_bottom =(TextView) convertView.findViewById(R.id.video_title_bottom);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.jzVideoPlayer = (JZVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
        viewHolder.jzVideoPlayer.titleTextView.setVisibility(View.GONE);
        viewHolder.jzVideoPlayer.setUp(
                videoBean.getVideos().get(position).getVideo_content(), JZVideoPlayer.SCREEN_WINDOW_LIST,
                videoBean.getVideos().get(position).getVideo_title());
        viewHolder.jzVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        viewHolder.video_title_bottom.setText(videoBean.getVideos().get(position).getVideo_title());
        Glide.with(context).load(videoBean.getVideos().get(position).getVideo_ThumbNailImage()).
                placeholder(R.mipmap.icon_stub).error(R.mipmap.icon_error).into(viewHolder.jzVideoPlayer.thumbImageView);
        viewHolder.jzVideoPlayer.positionInList = position;
        return convertView;
    }

    class ViewHolder {
        JZVideoPlayerStandard jzVideoPlayer;
        TextView video_title_bottom;
    }

}
