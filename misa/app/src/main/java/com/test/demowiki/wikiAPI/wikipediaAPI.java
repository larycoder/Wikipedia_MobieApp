package com.test.demowiki.wikiAPI;

import android.util.Log;
import android.util.Xml;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public List<String> getSnippetList(String jsonObject){
        JSONObject obj;
        List<String> searchList = new ArrayList<>();
        try {
            obj = new JSONObject(jsonObject);
            JSONArray search = obj.getJSONObject("query").getJSONArray("search");
            for(int i=0; i<search.length(); i++){
                searchList.add(search.getJSONObject(i).getString("snippet"));
            }
            return searchList;
        } catch (JSONException e){
            Log.e("Snippet JSON Exception", e.toString());
            return null;
        }
    }

    public String getThumbnailPageUrl(String title){
        return baseAPIUrl+"action=query&titles="+title+"&prop=pageimages&format=json";
    }

    public String getThumbnailUrl(String jsonObject){
        JSONObject obj;
        try {
            obj = new JSONObject(jsonObject);
            JSONObject pages = obj.getJSONObject("query").getJSONObject("pages");
            return pages.getJSONObject(pages.keys().next()).getJSONObject("thumbnail").getString("source");
        } catch (JSONException e){
            Log.e("Thumbnail Exception", e.toString());
            return null;
        }
    }

    public String getArticleUrl(String title){
        return basePageUrl+title;
    }

    public String getOriginalImageArticleUrl(String title){
        return baseAPIUrl+"action=query&prop=pageimages&format=json&piprop=original&titles="+title;
    }

    public List<String> getOriginalImageUrl(String jsonObject){
        JSONObject obj;
        List<String> originImageInfo = new ArrayList<>();
        try {
            obj = new JSONObject(jsonObject);
            JSONObject pages = obj.getJSONObject("query").getJSONObject("pages");
            String key = pages.keys().next();
            if(pages.getJSONObject(key).has("original")) {
                originImageInfo.add(pages.getJSONObject(key).getJSONObject("original").getString("source"));
            }
            else{
                originImageInfo.add("Not Exit Image");
            }
            originImageInfo.add(baseAPIUrl+"format=json&action=query&prop=extracts&exintro&explaintext&titles="+pages.getJSONObject(key).getString("title"));
            return originImageInfo;
        } catch (JSONException e){
            Log.e("Origin Image Exception", e.toString());
            return null;
        }
    }

    public String getImageOfDayPageTitleUrl(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return baseAPIUrl + "format=json&action=query&prop=images&titles=Template:POTD_protected/"+df.format(date);
    }
    public String getImageOfDayPageURL(String jsonObject){
        JSONObject obj;
        try {
            obj = new JSONObject(jsonObject);
            JSONObject pages = obj.getJSONObject("query").getJSONObject("pages");
            JSONArray file = pages.getJSONObject(pages.keys().next()).getJSONArray("images");
            return baseAPIUrl+"format=json&action=query&prop=imageinfo&iiprop=url&titles="+file.getJSONObject(0).getString("title");
        } catch (JSONException e){
            Log.e("IODP Url JSON Exception", e.toString());
            return null;
        }
    }

    public List<String> getImageOfDayInfoUrl(String jsonObject){
        JSONObject obj;
        List<String> PODInfo = new ArrayList<>();
        try{
            obj = new JSONObject(jsonObject);
            JSONObject pages = obj.getJSONObject("query").getJSONObject("pages");
            String key = pages.keys().next();
            String title = pages.getJSONObject(key).getString("title");
            JSONArray imageInfo = pages.getJSONObject(key).getJSONArray("imageinfo");
            PODInfo.add(imageInfo.getJSONObject(0).getString("url")); // image url
            PODInfo.add(baseAPIUrl+"format=json&action=query&prop=revisions&rvprop=content&rvslots=*&titles="+title); // content url
            PODInfo.add(baseAPIUrl+"format=json&action=query&prop=extracts&exintro&explaintext&titles="+title); // intro url
            PODInfo.add(title); // article title
            return PODInfo;
        } catch (JSONException e){
            Log.e("IOD Url JSON Exception", e.toString());
            return null;
        }
    }

    public List<String> getExtract(String jsonObject){
        JSONObject obj;
        List<String> ExtractInfo = new ArrayList<>();
        try{
            obj = new JSONObject(jsonObject);
            JSONObject pages = obj.getJSONObject("query").getJSONObject("pages");
            String key = pages.keys().next();
            ExtractInfo.add(pages.getJSONObject(key).getString("title"));
            ExtractInfo.add(pages.getJSONObject(key).getString("extract"));
            return ExtractInfo;
        } catch (JSONException e){
            Log.e("Extract JSON Exception", e.toString());
            return null;
        }
    }

    public String getArticleContent(String jsonObject){
        JSONObject obj;
        try{
            obj = new JSONObject(jsonObject);
            JSONObject pages = obj.getJSONObject("query").getJSONObject("pages");
            String key = pages.keys().next();
            return pages.getJSONObject(key).getJSONArray("revisions").getJSONObject(0).getJSONObject("slots").getJSONObject("main").getString("*");
        } catch (JSONException e){
            Log.e("Content JSON Exception", e.toString());
            return null;
        }
    }

    public String getFeatureFeedUrl(){
        return baseAPIUrl+"action=featuredfeed&feed=featured&feedformat=rss";
    }

    public List<String> parseFeatureFeed(String xmlObject){
        List<String> featuredInfo = new ArrayList<>();
        XMLParser parser = new XMLParser(xmlObject);
        featuredInfo.add(parser.getContentFromTag("title"));
        featuredInfo.add(parser.getContentFromTag("description"));
        return featuredInfo;
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

class XMLParser{
    private String TAG = "XMLParser";
    private XmlPullParser parser;
    private int Skip = 1;

    public XMLParser(String xmlObject){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();
            parser.setInput( new StringReader( xmlObject ) ); // pass input whatever xml you have
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public String getContentFromTag(String Tag){
        try {
            XmlPullParser xpp = parser;
            int eventType = xpp.getEventType();
            String data = new String();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_DOCUMENT) {
                } else if(eventType == XmlPullParser.START_TAG) {
                    if(xpp.getName().equals(Tag)){
                        Skip = 0;
                    }
                } else if(eventType == XmlPullParser.END_TAG) {
                    if(xpp.getName().equals(Tag)){
                        Skip = 2;
                    }
                } else if(eventType == XmlPullParser.TEXT) {
                    if(Skip == 0) data = data + xpp.getText(); // here you get the text from xml
                }
                eventType = xpp.next();
                if(Skip == 2){
                    return data;
                }
            }
            return null;
        } catch (XmlPullParserException e){
            e.printStackTrace();
            return null;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
