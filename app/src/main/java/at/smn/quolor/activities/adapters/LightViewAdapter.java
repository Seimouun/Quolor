package at.smn.quolor.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import at.smn.quolor.R;
import at.smn.quolor.util.Light;

public class LightViewAdapter extends ArrayAdapter<Light> {

    Context ctx;
    int resource;
    List<Light> lightList;

    public LightViewAdapter(Context ctx, int resource, List<Light> lightList){
        super(ctx, resource, lightList);
        this.ctx = ctx;
        this.resource = resource;
        this.lightList = lightList;
    }

    public void add(Light todo) {
        lightList.add(todo);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.activity_light_list_view, null);
        if(position < lightList.size()) {
            Light light = lightList.get(position);
            TextView lightNameTextView = view.findViewById(R.id.allv_light_name);
            ImageView lightIconType = view.findViewById(R.id.allv_light_icon);
            switch (light.getType()){
                case BULB:
                    break;
                case STRIP:
                    break;
                default:
                    break;
            }
        }
        return view;
    }


}
