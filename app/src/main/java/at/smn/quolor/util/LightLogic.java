package at.smn.quolor.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.requests.GetTask;
import at.smn.quolor.requests.PostTask;
import at.smn.quolor.requests.PutTask;

public class LightLogic {

    public static void loadLights(){

    }
    public static Response generateAuthUser(){
        PostTask task = new PostTask();

        task.execute("http://" + MainActivity.bridgeIP + "/api", "{\"devicetype\":\"quolor#android simon\"}");
        try {
            String response = task.get();
            System.out.println(response);
            JSONObject object = new JSONArray(response).getJSONObject(0);
            if(object.has("error")){
                Response res = Response.BUTTON_NOT_PRESSED;
                res.setResponse(response);
                return res;
            }else if(object.has("success")){
                Response res = Response.SUCCESS;
                res.setResponse(response);
                return res;
            }
            System.out.println(response);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String getLights(){
        GetTask task = new GetTask();
        task.execute("http://" + MainActivity.bridgeIP + "/api/" + MainActivity.userAuthentification + "/lights");
        try {
            return task.get();
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
    public static String getLightInfo(int lightID){
        GetTask task = new GetTask();
        task.execute("http://" + MainActivity.bridgeIP + "/api/" + MainActivity.userAuthentification + "/lights/" + lightID);
        try {
            return task.get();
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
    public static void setLightState(int lightID, boolean state){
        PutTask task = new PutTask();
        task.execute("http://" + MainActivity.bridgeIP + "/api/" + MainActivity.userAuthentification + "/lights/" + lightID + "/state", "{\"on\":" + state + "}");
        try {
            task.get();
        } catch (Exception e) {e.printStackTrace();}
    }
    public static void setLightColor(int lightID, int saturation, int brightness, int hue){
        PutTask task = new PutTask();
        task.execute("http://" + MainActivity.bridgeIP + "/api/" + MainActivity.userAuthentification + "/lights/" + lightID + "/state", "{\"on\":true, \"sat\":" + saturation + ", \"bri\":" + brightness + ",\"hue\":" + hue + "}");
        try {
            task.get();
        } catch (Exception e) {e.printStackTrace();}
    }

    public enum Response {
        SUCCESS, BUTTON_NOT_PRESSED, FAILURE;
        String response = null;

        public void setResponse(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }
    }

}
