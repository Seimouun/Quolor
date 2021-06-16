package at.smn.quolor.util;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    String sceneName;
    int[] idList;
    int[] colorArr;

    public Scene(String sceneName, int[] idList, int[] colorArr) {
        this.sceneName = sceneName;
        this.idList = idList;
        this.colorArr = colorArr;
    }

    public String getSceneName() {
        return sceneName;
    }

    public int[] getLightIDList() {
        return idList;
    }

    public int[] getColorArr() {
        return colorArr;
    }
}
