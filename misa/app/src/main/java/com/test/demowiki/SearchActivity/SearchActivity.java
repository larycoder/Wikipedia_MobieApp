package com.test.demowiki.SearchActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.test.demowiki.MainActivity;
import com.test.demowiki.R;
import com.test.demowiki.VolleySingleton;
import com.test.demowiki.ui.article_detail.ScrollingActivity;
import com.test.demowiki.SearchActivity.ItemSearch;
import com.test.demowiki.SearchActivity.ItemListAdapter;
import com.test.demowiki.wikiAPI.wikipediaAPI;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
	RecyclerView recyclerView;
	ArrayList<ItemSearch> listItem;
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
						List<String> tittles = api.getTitleList(response);
//						Toast.makeText(SearchActivity.this, tittles.get(9), Toast.LENGTH_SHORT).show();
						for(int i=0;i<tittles.size();i++){
							final ItemSearch item = new ItemSearch();
							item.setHeader(tittles.get(i));
							item.setSubHeader(Html.fromHtml(api.getSnippetList(response).get(i)).toString());
							VolleySingleton.getQueue().add(new StringRequest(StringRequest.Method.GET, api.getThumbnailPageUrl(tittles.get(i)), new Response.Listener<String>() {
								@Override
								public void onResponse(String response) {
									VolleySingleton.getQueue().add(new ImageRequest(api.getThumbnailUrl(response), new Response.Listener<Bitmap>() {
										@Override
										public void onResponse(Bitmap response) {
											item.setImg(response);
										}
									}, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null));
								}
							}, null));
							listItem.add(item);
						}
						adapter.notifyDataSetChanged();
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
