package com.now.working.listener;

import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import com.now.working.WorkApplication;
import com.now.working.data.bean.News;
import com.now.working.ui.adapter.RecyclerViewAdapter;

/**
 * Created by Cyj on 17/12/20.
 */

public class NewsTextureListener implements TextureView.SurfaceTextureListener {
    private News mNews;
    private Surface surface;

    public NewsTextureListener(News news) {
        this.mNews = news;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        surface = new Surface(surfaceTexture);
        new PlayerVideo().start();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    private class PlayerVideo extends Thread{
        @Override
        public void run(){
            try {

                final MediaPlayer mMediaPlayer= new MediaPlayer();
                mMediaPlayer.setDataSource(WorkApplication.getApplication(),
                        Uri.parse(mNews.getVideoUrl()));
                mMediaPlayer.setSurface(surface);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp){
                        mMediaPlayer.start();
                    }
                });
                mMediaPlayer.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
