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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
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
						List<String> tittles = api.getTitleList(response);
						LinearLayout searchList = findViewById(R.id.search_item_test);

						if(tittles.size() >= 1) {
							TextView title0 = searchList.findViewById(R.id.search_article_title0);
							title0.setText(tittles.get(0));
							TextView summary0 = searchList.findViewById(R.id.search_article_summary0);
							summary0.setText(Html.fromHtml(api.getSnippetList(response).get(0)));
						}
						else{
							TextView title0 = searchList.findViewById(R.id.search_article_title0);
							title0.setText(null);
							TextView summary0 = searchList.findViewById(R.id.search_article_summary0);
							summary0.setText(null);
						}

						if(tittles.size() >= 2) {
							TextView title1 = searchList.findViewById(R.id.search_article_title1);
							title1.setText(tittles.get(1));
							TextView summary1 = searchList.findViewById(R.id.search_article_summary1);
							summary1.setText(Html.fromHtml(api.getSnippetList(response).get(1)));
						}
						else{
							TextView title1 = searchList.findViewById(R.id.search_article_title1);
							title1.setText(null);
							TextView summary1 = searchList.findViewById(R.id.search_article_summary1);
							summary1.setText(null);
						}

						if(tittles.size() >= 3) {
							TextView title2 = searchList.findViewById(R.id.search_article_title2);
							title2.setText(tittles.get(2));
							TextView summary2 = searchList.findViewById(R.id.search_article_summary2);
							summary2.setText(Html.fromHtml(api.getSnippetList(response).get(2)));
						}
						else{
							TextView title2 = searchList.findViewById(R.id.search_article_title2);
							title2.setText(null);
							TextView summary2 = searchList.findViewById(R.id.search_article_summary2);
							summary2.setText(null);
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
