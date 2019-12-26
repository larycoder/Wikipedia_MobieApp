package com.test.demowiki.wikiAPI;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class wikipediaAPI {
    private String baseAPIUrl = "https://en.wikipedia.org/w/api.php?";
    private String basePageUrl = "https://en.wikipedia.org/wiki/";

    public String getTitleSearchUrl(int numResult, String searchString){
        return baseAPIUrl+"format=json&action=query&prop=info&list=search&srlimit="+Integer.toString(numResult)+"&srsearch="+searchString;
    }

    public List<String> getTitleList(String jsonObject){
        Gson gson = new Gson();
        wikipediaTitleData wikiData = gson.fromJson(jsonObject, wikipediaTitleData.class);
        return wikiData.getListTitle();
    }

    public String getArticleUrl(String title){
        return basePageUrl+title;
    }

    public String getImageOfDayPageTitleUrl(){
        Date date = new Date();
        return baseAPIUrl + "format=json&action=query&prop=images&titles=Template:POTD_protected/"+date.toString();
    }
    public String getImageOfDayPageURL(String jsonObject){
        JSONObject obj;
        try {
            obj = new JSONObject(jsonObject);
            JSONObject pages = obj.getJSONObject("query").getJSONObject("pages");
            Iterator<String> keys = pages.keys();
            String key = keys.next();
            JSONArray file = pages.getJSONObject(key).getJSONArray("images");
            return baseAPIUrl+"format=json&action=query&prop=imageinfo&iiprop=url&titles="+file.getJSONObject(0).getString("title");
        } catch (JSONException e){
            Log.e("JSON Exception", e.toString());
            return null;
        }
    }
    public String getImageOfDayUrl(String jsonObject){
        JSONObject obj;
        try{
            obj = new JSONObject(jsonObject);
            JSONObject pages = obj.getJSONObject("query").getJSONObject("pages");
            Iterator<String> keys = pages.keys();
            String key = keys.next();
            JSONArray imageInfo = pages.getJSONObject(key).getJSONArray("imageInfo");
            return imageInfo.getJSONObject(0).getString("url");
        } catch (JSONException e){
            Log.e("JSON Exception", e.toString());
            return null;
        }
    }
}

// Title data
class wikipediaTitleData{
    public query query = new query();

    public List<String> getListTitle(){
        List<String> arrayTitle = new ArrayList<>();
        for(int i=0; i<query.search.size(); i++){
            arrayTitle.add(query.search.get(i).title);
        }
        return arrayTitle;
    }
}

class query{
    public List<search> search = new ArrayList<>();
}

class search{
    public String title;
}

// Image of the day data
