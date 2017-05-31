package id.co.imastudio.latihanmultimedia;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

public class RadioActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button play, stop;
    MediaPlayer player;
    Animation animFade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        play=(Button) findViewById(R.id.btnPlay);
        stop=(Button) findViewById(R.id.btnStop);

        animFade= AnimationUtils.loadAnimation(RadioActivity.this,R.anim.anim_fade);

        progressBar.setMax(100);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setIndeterminate(true);

        player=new MediaPlayer();
        try {
            player.setDataSource("http://103.16.198.36:9160/stream");
        } catch (IOException e) {
            e.printStackTrace();
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        } catch(IllegalStateException e){
            e.printStackTrace();
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    player.prepareAsync();
                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            player.start();
                        }
                    });
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }

                progressBar.setVisibility(View.VISIBLE);
                play.startAnimation(animFade);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (player==null) return;
                    if (player.isPlaying()){
                        player.stop();
                    }
                } catch(IllegalStateException e) {
                    e.printStackTrace();
                }

                progressBar.setVisibility(View.INVISIBLE);
                stop.startAnimation(animFade);
            }

        });
    }
}
