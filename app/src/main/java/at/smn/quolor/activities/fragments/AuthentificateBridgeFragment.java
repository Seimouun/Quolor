package at.smn.quolor.activities.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.smn.quolor.R;
import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.util.LightLogic;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthentificateBridgeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthentificateBridgeFragment extends Fragment {

    private static Button authenticate = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authentificate_bridge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authenticate = view.findViewById(R.id.abf_authenticate_button);
        TextView authentificationStatusText = view.findViewById(R.id.abf_authentification_status);
        ProgressBar progressBar = view.findViewById(R.id.abf_progressBar);
        authenticate.setOnClickListener(v -> {
            authentificationStatusText.setText("");
            progressBar.setVisibility(View.VISIBLE);
            @SuppressLint("ResourceAsColor") Thread t = new Thread(() -> {
                LightLogic.Response res = LightLogic.generateAuthUser();
                if(res == LightLogic.Response.SUCCESS){
                    try {
                        JSONArray jsonArray = new JSONArray(res.getResponse());
                        JSONObject object = jsonArray.getJSONObject(0);
                        MainActivity.userAuthentification = object.getJSONObject("success").getString("username");
                        MainActivity.getMain().runOnUiThread(() -> {
                            view.findViewById(R.id.abf_authInfoH).setVisibility(View.INVISIBLE);
                            view.findViewById(R.id.abf_authInfoP).setVisibility(View.INVISIBLE);
                            authenticate.setText("Authenticated!");
                            authenticate.setBackgroundColor(R.color.app_success_color);
                            progressBar.setVisibility(View.INVISIBLE);
                        });
                        LightLogic.saveAuthToken(MainActivity.userAuthentification);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if(res == LightLogic.Response.BUTTON_NOT_PRESSED){
                    progressBar.setVisibility(View.INVISIBLE);
                    authentificationStatusText.setText("Link button not pressed. Try again!");
                    MainActivity.getMain().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            YoYo.with(Techniques.Tada)
                                    .duration(700)
                                    .playOn(authentificationStatusText);
                        }
                    });
                }
            });
            t.start();
        });
    }
}