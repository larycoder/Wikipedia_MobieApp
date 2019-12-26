package com.test.demowiki;


import android.util.Log;

import com.android.volley.RequestQueue;


/**
 * Created by imnobody on 7/8/17.
 */

public class VolleySingleton {
	private static RequestQueue queue = null;

	protected VolleySingleton(){};


	public static RequestQueue getQueue(){
		if(queue == null){
			Log.i("Volley Request", "Queue not existed");
			return null;
		}
		return queue;
	}

	public static void setQueue(RequestQueue rqueue){
		if(queue != null){
			Log.i("Volley Request", "Queue already existed");
			return;
		}
		VolleySingleton.queue = rqueue;
	}

}