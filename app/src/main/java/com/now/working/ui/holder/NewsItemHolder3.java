package com.now.working.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.now.working.R;
import com.now.working.WorkApplication;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

/**
 * Created by Cyj on 17/12/19.
 */

public class NewsItemHolder3 extends NewsItemHolder {

    public NiceVideoPlayer newsVideo;

    public NewsItemHolder3(View itemView) {
        super(itemView);

        newsVideo = itemView.findViewById(R.id.news_video);
        newsVideo.setPlayerType(NiceVideoPlayer.TYPE_IJK);

        TxVideoPlayerController controller = new TxVideoPlayerController(WorkApplication.getApplication());
        newsVideo.setController(controller);
    }
}