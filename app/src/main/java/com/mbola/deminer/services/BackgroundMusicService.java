package com.mbola.deminer.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.mbola.deminer.R;

public class BackgroundMusicService extends Service{
    private MediaPlayer player;
    private static final String TAG = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    public void onCreate(){
        player = MediaPlayer.create(getApplicationContext(), R.raw.music);
        player.setVolume(1,1);
        player.setLooping(true);
    }

    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (player.isPlaying()){
            stopSelf();
        }
        else {
            player.start();
        }
        return START_STICKY;
    }

    public void onDestroy(Intent intent, int startId)
    {
        player.stop();
    }
}
