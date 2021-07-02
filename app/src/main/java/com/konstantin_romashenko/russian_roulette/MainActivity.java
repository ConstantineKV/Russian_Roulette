package com.konstantin_romashenko.russian_roulette;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.Random;

public class MainActivity extends Activity {

    SoundPool sound;
    private int sound_shot;
    private int sound_shot_false;
    private int sound_shot_baraban;
    private int on_shot = 1;
    private int max_number = 10;
    private int random = 0;

    private ImageView image_blood;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSoundPool();
        if(sound != null)
            loadSounds();
        image_blood = findViewById(R.id.imageBlood);

    }

    protected void createSoundPool()
    {
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP)
        {
            createNewSoundPool();
        }
        else
        {
            createOldsoundPool();
        }
    }
    //@TargetApi(Build.VERSION_CODES.LOLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void createNewSoundPool()
    {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sound = new SoundPool.Builder().setAudioAttributes(attributes).build();
    }

    @SuppressWarnings("deprecation")
    protected void createOldsoundPool()
    {
        sound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    }

    private void loadSounds()
    {
        sound_shot = sound.load(this, R.raw.revolver_shot, 1);
        sound_shot_false = sound.load(this, R.raw.gun_false, 1);
        sound_shot_baraban = sound.load(this, R.raw.revolver_baraban, 1);
    }

    public void on_shot(View view)
    {
        if(random == on_shot)
        {
            sound.play(sound_shot, 1, 1, 1, 0, 1);
            image_blood.setVisibility(View.VISIBLE);
        }
        else
        {
            sound.play(sound_shot_false, 1, 1, 1, 0, 1);
        }
    }

    public void on_shot_false(View view)
    {
        sound.play(sound_shot_false, 1, 1, 1, 0, 1);
    }


    public void on_baraban(View view)

    {
        sound.play(sound_shot_baraban, 1, 1, 1, 0, 1);
        random = new Random().nextInt(max_number);
        Log.e("MainActivity","Random number = " + random);
    }

    private void init()
    {

    }
}