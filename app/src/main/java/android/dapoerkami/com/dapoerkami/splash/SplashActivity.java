package android.dapoerkami.com.dapoerkami.splash;

import android.content.Intent;
import android.dapoerkami.com.dapoerkami.login.LoginActivity;
import android.dapoerkami.com.dapoerkami.R;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer yang digunakan untuk menampilkan berapa lama waktu splash screen akan diinisialisasikan
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // method ini akan dijalankan jika timer yang ada sudah habis
                // menjalankan main activity yang ada
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}