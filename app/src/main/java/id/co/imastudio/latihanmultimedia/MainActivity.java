package id.co.imastudio.latihanmultimedia;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        video = (VideoView) findViewById(R.id.videoView);
        //video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.photograph));
        video.setVideoURI(Uri.parse("http://idn.id/semarang/tes/tatacara.mp4"));
        video.setMediaController(new MediaController(this));
        video.requestFocus();
        video.start();


    }
}
