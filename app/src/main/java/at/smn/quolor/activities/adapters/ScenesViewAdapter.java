package at.smn.quolor.activities.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import at.smn.quolor.R;
import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.activities.fragments.LightsFragment;
import at.smn.quolor.util.Light;
import at.smn.quolor.util.LightLogic;
import at.smn.quolor.util.Scene;
import top.defaults.colorpicker.ColorPickerPopup;

public class ScenesViewAdapter extends ArrayAdapter<Scene> {

    Context ctx;
    int resource;
    List<Scene> sceneList;

    public ScenesViewAdapter(Context ctx, int resource, List<Scene> sceneList) {
        super(ctx, resource, sceneList);
        this.ctx = ctx;
        this.resource = resource;
        this.sceneList = sceneList;
    }

    public void add(Scene scene) {
        sceneList.add(scene);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.activity_scenes_view_adapter, null);
        if (position < sceneList.size()) {
            Scene scene = sceneList.get(position);
            Button button = view.findViewById(R.id.asva_set_scene_button);
            TextView sceneName = view.findViewById(R.id.asva_scene_name);
            sceneName.setText(scene.getSceneName());
            button.setOnClickListener(v -> {
                new Thread(() -> {
                    ProgressBar circle = view.findViewById(R.id.asva_loading_circle);
                    MainActivity.getMain().runOnUiThread(() -> {
                        circle.setVisibility(View.VISIBLE);
                        button.setVisibility(View.INVISIBLE);
                    });
                    for (int i = 0; i < scene.getLightIDList().length; i++) {
                        Light light = Light.getLight(scene.getLightIDList()[i]);
                        light.setColor(scene.getColorArr()[i]);
                    }
                    MainActivity.getMain().runOnUiThread(() -> {
                        circle.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.VISIBLE);
                    });
                }).start();
            });
        }
        return view;
    }
}