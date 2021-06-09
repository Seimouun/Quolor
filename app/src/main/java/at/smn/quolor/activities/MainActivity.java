package at.smn.quolor.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import at.smn.quolor.R;
import at.smn.quolor.activities.fragments.AuthentificateBridgeFragment;
import at.smn.quolor.activities.fragments.LightsFragment;
import at.smn.quolor.activities.fragments.LoadingFragment;
import at.smn.quolor.util.LightLogic;

public class MainActivity extends AppCompatActivity {

    public static String bridgeIP = "192.168.10.100";
    public static String userAuthentification = null;
    public static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = this;

        View frame = findViewById(R.id.main_framelayout);
        Fragment loadingFragment = new LoadingFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_framelayout, loadingFragment);
        transaction.commit();

        boolean userAuth = LightLogic.loadAuthToken();

        if(userAuth){
            transaction = getSupportFragmentManager().beginTransaction();
            Fragment lightsFragment = new LightsFragment();
            transaction.replace(R.id.main_framelayout, lightsFragment);
            transaction.commit();
            System.out.println(userAuthentification);
        }else{
            transaction = getSupportFragmentManager().beginTransaction();
            Fragment authFragment = new AuthentificateBridgeFragment();
            transaction.replace(R.id.main_framelayout, authFragment);
            transaction.commit();
        }
    }
    public static MainActivity getMain(){
        return main;
    }
}