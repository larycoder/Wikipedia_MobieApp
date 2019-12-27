package com.test.demowiki.SearchActivity;

import android.graphics.Bitmap;

public class ItemSearch {
	String header;
	String subHeader;
	Bitmap img;

//    public Item(String header, String subHeader, int img) {
//        this.header = header;
//        this.subHeader = subHeader;
//        this.img = img;
//    }

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getSubHeader() {
		return subHeader;
	}

	public void setSubHeader(String subHeader) {
		this.subHeader = subHeader;
	}

	public Bitmap getImg() {
		return img;
	}

	public void setImg(Bitmap img) {
		this.img = img;
	}

}
