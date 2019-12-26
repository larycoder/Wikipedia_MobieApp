package com.test.demowiki.wikiAPI;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
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
}

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