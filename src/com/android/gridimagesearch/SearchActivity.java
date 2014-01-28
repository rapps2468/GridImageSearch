package com.android.gridimagesearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

public class SearchActivity extends Activity {

	private EditText etKeyword;
	private GridView gvImageResults;
	private Button btnSearch;
	private ImageResultArrayAdapter imageArrayAdapter;
	private ImageSearcher imageSearcher;
	private String curKeyword;
	private ImageSearchQuery currentQuery;
	private SearchFilters searchFilters;

	private final int SET_FILTERS_REQUEST = 1;

	
	
	private EndlessScrollListener scrollListener;
	
	public ImageResultArrayAdapter getImageArrayAdapter() {
		return imageArrayAdapter;
	}





	public void setImageArrayAdapter(ImageResultArrayAdapter imageArrayAdapter) {
		this.imageArrayAdapter = imageArrayAdapter;
	}





	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		setupViews();
		
		searchFilters = new SearchFilters();
		currentQuery = new ImageSearchQuery("");
		
		imageSearcher = new ImageSearcher(this, currentQuery);
		imageArrayAdapter = new ImageResultArrayAdapter(this, 
														imageSearcher.getImageResults());
		gvImageResults.setAdapter(imageArrayAdapter);
		
		gvImageResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long rowId) {
			/*	
				ImageResult imageResult = (ImageResult) imageSearcher.getImageResults().get(position);
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(imageResult.getFullUrl()));
				startActivity(i);
			*/
				Intent i = new Intent(getApplicationContext(), ImageViewer.class);
				ImageResult imageResult = (ImageResult) gvImageResults.getItemAtPosition(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
		});

		scrollListener = new EndlessScrollListener() {
	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
	            customLoadMoreDataFromApi(totalItemsCount); 
	        }
	    };
	        
        gvImageResults.setOnScrollListener(scrollListener);

	}


	
	
	
    // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset) {
    	currentQuery.setStartArg(offset);
    	imageSearcher.executeImageSearchQuery(currentQuery);
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
		if ((resultCode == RESULT_OK) && (requestCode == SET_FILTERS_REQUEST)) {
			searchFilters = (SearchFilters) data.getSerializableExtra("search_filters");
		}
	}
    
    
	public void executeImageSearch(View v)
	{
		scrollListener.reset();
		imageArrayAdapter.clear();
		curKeyword = etKeyword.getText().toString();
		Toast.makeText(getApplicationContext(), "Searching for images...", Toast.LENGTH_SHORT).show();
		currentQuery.setSearchFilters(searchFilters);
		currentQuery.setStartArg(0);
		currentQuery.setKeyword(curKeyword);
        imageSearcher.executeImageSearchQuery(currentQuery);
	}
		


	public void setFiltersAction(MenuItem mi)
	{
		Toast.makeText(getApplicationContext(), "setFiltersAction", Toast.LENGTH_SHORT).show();
        Intent intentSetFilters = new Intent(this, SetFiltersActivity.class);
        intentSetFilters.putExtra("search_filters", searchFilters);
		startActivityForResult(intentSetFilters, SET_FILTERS_REQUEST);
	}
	
	private void setupViews()
	{
		etKeyword = (EditText) findViewById(R.id.etKeyword);
		gvImageResults = (GridView) findViewById(R.id.gvImageResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
	}
}
