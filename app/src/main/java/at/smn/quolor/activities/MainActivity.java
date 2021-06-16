package at.smn.quolor.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import at.smn.quolor.R;
import at.smn.quolor.activities.fragments.AuthentificateBridgeFragment;
import at.smn.quolor.activities.fragments.LightsFragment;
import at.smn.quolor.activities.fragments.LoadingFragment;
import at.smn.quolor.activities.fragments.ScenesFragment;
import at.smn.quolor.util.LightLogic;

public class MainActivity extends AppCompatActivity {

    public static String bridgeIP = "192.168.10.100";
    public static String userAuthentification = null;
    public static MainActivity main;
    public static LightsFragment lightsFragment = null;
    public static ScenesFragment scenesFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = this;

        Fragment loadingFragment = new LoadingFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_framelayout, loadingFragment);
        transaction.commit();

        boolean userAuth = LightLogic.loadAuthToken();

        if(userAuth){
            transaction = getSupportFragmentManager().beginTransaction();
            lightsFragment = new LightsFragment();
            transaction.replace(R.id.main_framelayout, lightsFragment);
            transaction.commit();
            System.out.println(userAuthentification);
        }else {
            transaction = getSupportFragmentManager().beginTransaction();
            Fragment authFragment = new AuthentificateBridgeFragment();
            transaction.replace(R.id.main_framelayout, authFragment);
            transaction.commit();
        }
        ImageButton lightsButton = findViewById(R.id.main_lights_button);
        ImageButton scenesButton = findViewById(R.id.main_scenes_button);
        ImageButton audioButton = findViewById(R.id.main_audio_button);
        ImageButton automationButton = findViewById(R.id.main_automation_button);
        ImageButton settingsButton = findViewById(R.id.main_settings_button);

        lightsButton.setOnClickListener(v -> {
            FragmentTransaction transactionAnim = getSupportFragmentManager().beginTransaction();
            if(lightsFragment == null) {
                lightsFragment = new LightsFragment();
            }
            transactionAnim.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            transactionAnim.replace(R.id.main_framelayout, lightsFragment);
            transactionAnim.commit();
        });
        scenesButton.setOnClickListener(v -> {
            FragmentTransaction transactionAnim = getSupportFragmentManager().beginTransaction();
            if(scenesFragment == null) {
                scenesFragment = new ScenesFragment();
            }
            transactionAnim.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            transactionAnim.replace(R.id.main_framelayout, scenesFragment);
            transactionAnim.commit();
        });

    }
    public static MainActivity getMain(){
        return main;
    }
}