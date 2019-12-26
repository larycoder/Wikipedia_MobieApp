package com.test.demowiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.test.demowiki.ui.article_detail.ScrollingActivity;
import com.test.demowiki.ui.explore.trending_card.Item;
import com.test.demowiki.ui.history.ItemListAdapter;
import com.test.demowiki.wikiAPI.wikipediaAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class SearchActivity extends AppCompatActivity {
	RecyclerView recyclerView;
	ArrayList<Item> listItem;
	ItemListAdapter adapter;
	SearchView searchView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		final Toolbar mToolbar = findViewById(R.id.search_toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
		recyclerView = findViewById(R.id.search_list_item);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		Drawable mDivider = ContextCompat.getDrawable(this, R.drawable.divider);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
						LinearLayoutManager.VERTICAL);
		dividerItemDecoration.setDrawable(mDivider);
		recyclerView.addItemDecoration(dividerItemDecoration);
		listItem= new ArrayList<>();
		adapter = new ItemListAdapter(this,listItem);
		recyclerView.setAdapter(adapter);

		Intent intent = getIntent();
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doMySearch(query);
		}

	}

	public void doMySearch(String query){
		Toast.makeText(this, query, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_search, menu);
		MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
		myActionMenuItem.expandActionView();
		myActionMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
			@Override
			public boolean onMenuItemActionExpand(MenuItem menuItem) {
				return false;
			}

			@Override
			public boolean onMenuItemActionCollapse(MenuItem menuItem) {
				Intent intent = new Intent(SearchActivity.this, MainActivity.class);
				startActivity(intent);
				return false;
			}
		});
		searchView = (SearchView) myActionMenuItem.getActionView();
		searchView.setQueryHint("Search Wikipedia");
		searchView.setIconifiedByDefault(true);
		searchView.setIconified(false);

		searchView.setOnCloseListener(new SearchView.OnCloseListener() {
			@Override
			public boolean onClose() {
				Intent intent = new Intent(SearchActivity.this, MainActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
				return false;
			}
		});
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				final wikipediaAPI api = new wikipediaAPI();
				String searchUrl = api.getTitleSearchUrl(10, query);
				Response.Listener<String> response =  new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						final List<String> tittles = api.getTitleList(response);
						LinearLayout searchList = findViewById(R.id.search_item_test);

						TextView title2 = searchList.findViewById(R.id.search_article_title2);
						title2.setText(null);
						TextView summary2 = searchList.findViewById(R.id.search_article_summary2);
						summary2.setText(null);
						ImageView image2 = searchList.findViewById(R.id.search_article_image2);
						image2.setImageDrawable(null);

						TextView title1 = searchList.findViewById(R.id.search_article_title1);
						title1.setText(null);
						TextView summary1 = searchList.findViewById(R.id.search_article_summary1);
						summary1.setText(null);
						ImageView image1 = searchList.findViewById(R.id.search_article_image1);
						image1.setImageDrawable(null);

						TextView title0 = searchList.findViewById(R.id.search_article_title0);
						title0.setText(null);
						TextView summary0 = searchList.findViewById(R.id.search_article_summary0);
						summary0.setText(null);
						ImageView image0 = searchList.findViewById(R.id.search_article_image0);
						image0.setImageDrawable(null);

						if(tittles.size() >= 1) {
							title0.setText(tittles.get(0));
							summary0.setText(Html.fromHtml(api.getSnippetList(response).get(0)));
							VolleySingleton.getQueue().add(new StringRequest(Request.Method.GET, api.getThumbnailPageUrl(tittles.get(0)), new Response.Listener<String>() {
								@Override
								public void onResponse(String response) {
									VolleySingleton.getQueue().add(new ImageRequest(api.getThumbnailUrl(response), new Response.Listener<Bitmap>() {
										@Override
										public void onResponse(Bitmap response) {
											((ImageView) findViewById(R.id.search_article_image0)).setImageBitmap(response);
										}
									}, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null));
								}
							}, null));

						}

						if(tittles.size() >= 2) {
							title1.setText(tittles.get(1));
							summary1.setText(Html.fromHtml(api.getSnippetList(response).get(1)));
							VolleySingleton.getQueue().add(new StringRequest(Request.Method.GET, api.getThumbnailPageUrl(tittles.get(1)), new Response.Listener<String>() {
								@Override
								public void onResponse(String response) {
									VolleySingleton.getQueue().add(new ImageRequest(api.getThumbnailUrl(response), new Response.Listener<Bitmap>() {
										@Override
										public void onResponse(Bitmap response) {
											((ImageView) findViewById(R.id.search_article_image1)).setImageBitmap(response);
										}
									}, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null));
								}
							}, null));
						}

						if(tittles.size() >= 3) {
							title2.setText(tittles.get(2));
							summary2.setText(Html.fromHtml(api.getSnippetList(response).get(2)));
							VolleySingleton.getQueue().add(new StringRequest(Request.Method.GET, api.getThumbnailPageUrl(tittles.get(2)), new Response.Listener<String>() {
								@Override
								public void onResponse(String response) {
									VolleySingleton.getQueue().add(new ImageRequest(api.getThumbnailUrl(response), new Response.Listener<Bitmap>() {
										@Override
										public void onResponse(Bitmap response) {
											((ImageView) findViewById(R.id.search_article_image2)).setImageBitmap(response);
										}
									}, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null));
								}
							}, null));

//							searchList.findViewById(R.id.search_item_test0).setOnClickListener(new View.OnClickListener(){
//								@Override
//								public void onClick(View v){
//									Intent startScroll = new Intent(getApplicationContext(), ScrollingActivity.class);
//									//startScroll.putExtra("articleImageUrl", PODInfo.get(0));
//									//startScroll.putExtra("articleDescriptionUrl", PODInfo.get(2));
//									startActivity(startScroll);
//								}
//							});
						}
					}
				};
				StringRequest stringRequest = new StringRequest(Request.Method.GET, searchUrl, response, null);
				VolleySingleton.getQueue().add(stringRequest);
				return false;
			}
			@Override
			public boolean onQueryTextChange(String s) {
				// UserFeedback.show( "SearchOnQueryTextChanged: " + s);
				return false;
			}
		});
		return true;
	}



}
