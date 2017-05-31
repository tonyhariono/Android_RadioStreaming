package id.co.imastudio.latihanmultimedia;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;

public class Radio2Activity extends AppCompatActivity {

    ProgressBar progressBar;
    Button playstop;
    MediaPlayer player;
    AVLoadingIndicatorView avl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio2);

        progressBar=(ProgressBar) findViewById(R.id.progressBar2);
        playstop=(Button) findViewById(R.id.btnPlayStop);

        progressBar.setMax(100);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setIndeterminate(true);

        avl=(AVLoadingIndicatorView) findViewById(R.id.avloading);
        avl.setVisibility(View.INVISIBLE);

        player=new MediaPlayer();
        try {
            //player.setDataSource("http://103.16.198.36:9160/stream");
            player.setDataSource("http://jogjastreamers.com/index.php?play=7");
        } catch (IOException e) {
            e.printStackTrace();
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        } catch(IllegalStateException e){
            e.printStackTrace();
        }

        playstop.setText("PLAY");

        playstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playstop.getText()=="PLAY"){
                    jalankan();
                    playstop.setText("STOP");
                } else if(playstop.getText()=="STOP"){
                    berhenti();
                    playstop.setText("PLAY");
                }
            }
        });

    }

    private void jalankan() {
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
        avl.setVisibility(View.VISIBLE);

    }

    private void berhenti() {
        try{
            if (player==null) return;
            if (player.isPlaying()){
                player.stop();
            }
        } catch(IllegalStateException e) {
            e.printStackTrace();
        }

        progressBar.setVisibility(View.INVISIBLE);
        avl.setVisibility(View.INVISIBLE);
    }

}
