package at.smn.quolor.activities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authentificate_bridge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button authenticate = view.findViewById(R.id.abf_authenticate_button);
        TextView authentificationStatusText = view.findViewById(R.id.abf_authentification_status);
        authenticate.setOnClickListener(v -> {
            LightLogic.Response res = LightLogic.generateAuthUser(MainActivity.bridgeIP);
            if(res == LightLogic.Response.SUCCESS){
                try {
                    JSONArray jsonArray = new JSONArray(res.getResponse());
                    JSONObject object = jsonArray.getJSONObject(0);
                    MainActivity.userAuthentification = object.getJSONObject("success").getString("username");
                    System.out.println(MainActivity.userAuthentification);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(res == LightLogic.Response.BUTTON_NOT_PRESSED){
                authentificationStatusText.setText("Button not pressed");
            }
        });
    }
}