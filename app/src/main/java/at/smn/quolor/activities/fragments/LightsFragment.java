package at.smn.quolor.activities.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import at.smn.quolor.R;
import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.activities.adapters.LightViewAdapter;
import at.smn.quolor.requests.GetTask;
import at.smn.quolor.util.Light;
import at.smn.quolor.util.LightLogic;
import top.defaults.colorpicker.ColorPickerPopup;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LightsFragment extends Fragment {

    public static List<Light> lightList = new ArrayList<>();
    public static ListView lightView = null;
    public static LightViewAdapter lightViewAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lights, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment loadingFragment = new LoadingFragment();
        FragmentTransaction transaction = MainActivity.getMain().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.loading_framelayout, loadingFragment);
        transaction.commit();
        new Thread(() -> {
            if(lightList.isEmpty()) {
                try {
                    String jsonString = LightLogic.getLights();
                    System.out.println("jsonString: " + jsonString);
                    JSONObject obj = new JSONObject(jsonString);
                    Iterator<String> iterator = obj.keys();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        JSONObject light = obj.getJSONObject(key);
                        JSONObject state = light.getJSONObject("state");

                        boolean on = state.getBoolean("on");
                        int id = Integer.parseInt(key);
                        System.out.println(key);
                        int bri = state.getInt("bri");
                        int hue = state.getInt("hue");
                        int sat = state.getInt("sat");
                        String name = light.getString("name");
                        Light.LightType type = Light.LightType.getType(light.getJSONObject("config").getString("archetype"));
                        Light lightObject = new Light(id, type, name, on, bri, hue, sat);
                        lightList.add(lightObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            MainActivity.getMain().runOnUiThread(() -> {
                lightView = view.findViewById(R.id.fl_lightsView);
                lightViewAdapter = new LightViewAdapter(MainActivity.getMain(), R.layout.activity_light_list_view, lightList);
                lightView.setAdapter(lightViewAdapter);
                FragmentTransaction transaction2 = MainActivity.getMain().getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.loading_framelayout, new Fragment());
                transaction2.commit();
            });
        }).start();
    }
}