package com.example.musicplayer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private MyPlayer myPlayer;
    private IBinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ServiceDemo", "Đã gọi onCreate()");

        myPlayer = new MyPlayer(this);
        binder = new MyBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ServiceDemo", "Đã gọi onBind()");
        myPlayer.play();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("ServiceDemo", "Đã gọi onBind()");
        myPlayer.stop();
        return super.onUnbind(intent);
    }

    public void fastForward(){

        myPlayer.fastForward(3000);
    }
    public void fastStart(){

        myPlayer.fastStart();
    }
    public void downtime(){
        myPlayer.fastForward(-3000);
    }
    public class MyBinder extends Binder {

        // phương thức này trả về đối tượng MyService
        public MyService getService() {

            return MyService.this;
        }
    }


}
    class MyPlayer {
        private MediaPlayer mediaPlayer;

        public MyPlayer(Context context) {
            mediaPlayer = MediaPlayer.create(
                    context, null);
            mediaPlayer.setLooping(true);
        }

        public void fastForward(int pos){
            mediaPlayer.seekTo(pos);

        }
        public void fastStart(){
            mediaPlayer.start();
        }
        public void play() {
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        }

        public void stop() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        }
}
