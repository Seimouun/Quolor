package at.smn.quolor.util;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    String sceneName;
    List<Light> lightList;
    int[] colorArr;

    public Scene(String sceneName, List<Light> lightList, int[] colorArr) {
        this.sceneName = sceneName;
        this.lightList = lightList;
        this.colorArr = colorArr;
    }

    public String getSceneName() {
        return sceneName;
    }

    public List<Light> getLightList() {
        return lightList;
    }

    public int[] getColorArr() {
        return colorArr;
    }
}
