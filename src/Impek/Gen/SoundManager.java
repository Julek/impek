package Impek.Gen;
import java.util.HashSet;
import java.io.IOException;

import android.content.Context;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Config;
import android.util.Log;

final class SoundManager {
    private static HashSet<MediaPlayer> mpSet = new HashSet<MediaPlayer>();

    
    // the sound Manager class:
    
    /*
     * selects the track to read from the Hashset mpSet after accessing resources (TODO:)
     * add new Sounds to be chosen from music library of user (From settings menu) (DONE)
     * Plays the sound as a ringtone (volume controls are better that way) (DONE)
     */
    static void play(Context context, int resId) {
        try {
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(context, Uri.parse("android.resource://" + "Impek.Gen" + "/" + resId));
            mp.setAudioStreamType(AudioManager.STREAM_RING);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mpSet.remove(mp);
                    mp.stop();
                    mp.release();
                }
            });
            mp.prepare();
            mpSet.add(mp);
            mp.start();
        } catch (IOException e) {
            if (Config.DEBUG) Log.e("Error playing audio resource", e.toString());
        }
    }

    static void stop() {
        for (MediaPlayer mp : mpSet) {
            if (mp != null) {
                mp.stop();
                mp.release();
            }
        }
        mpSet.clear();
    }
}