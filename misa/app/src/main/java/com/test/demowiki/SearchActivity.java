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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.demowiki.ui.explore.trending_card.Item;
import com.test.demowiki.ui.history.ItemListAdapter;

import java.util.ArrayList;

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
				Toast.makeText(SearchActivity.this, query, Toast.LENGTH_LONG).show();
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
