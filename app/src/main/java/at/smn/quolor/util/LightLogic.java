package at.smn.quolor.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import at.smn.quolor.requests.PostTask;

public class LightLogic {

    public static void loadLights(){

    }
    public static Response generateAuthUser(String bridgeIP){
        PostTask task = new PostTask();

        task.execute("http://" + bridgeIP + "/api", "{\"devicetype\":\"quolor#android simon\"}");
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
