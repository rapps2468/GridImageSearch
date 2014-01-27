package com.android.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {

	private EditText etKeyword;
	private GridView gvImageResults;
	private Button btnSearch;
	private ImageResultArrayAdapter imageArrayAdapter;
	private ImageSearcher imageSearcher;
	private String curKeyword;
	
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
		
		
		imageSearcher = new ImageSearcher(this);
		imageArrayAdapter = new ImageResultArrayAdapter(this, 
														imageSearcher.getImageResults());
		gvImageResults.setAdapter(imageArrayAdapter);

		
		
		gvImageResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long rowId) {
				
				ImageResult imageResult = (ImageResult) imageSearcher.getImageResults().get(position);
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(imageResult.getFullUrl()));
				startActivity(i);
			
			}
		});

		scrollListener = new EndlessScrollListener() {
	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
	            customLoadMoreDataFromApi(page * 8); 
	        }
	    };
	        
        gvImageResults.setOnScrollListener(scrollListener);

	}


	
	
	
    // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset) {
    	imageSearcher.executeImageSearchQuery("https://ajax.googleapis.com/ajax/services/search/images", 
    			curKeyword, offset);
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public void executeImageSearch(View v)
	{
		scrollListener.reset();
		imageSearcher.getImageResults().clear();
		imageArrayAdapter.notifyDataSetChanged();
		curKeyword = etKeyword.getText().toString();
		Toast.makeText(getApplicationContext(), "Searching for images.", Toast.LENGTH_SHORT).show();
        imageSearcher.executeImageSearchQuery("https://ajax.googleapis.com/ajax/services/search/images", curKeyword, 0);
	}
		


	public void setFiltersAction(MenuItem mi)
	{
		Toast.makeText(getApplicationContext(), "setFiltersAction", Toast.LENGTH_SHORT).show();
	}
	
	private void setupViews()
	{
		etKeyword = (EditText) findViewById(R.id.etKeyword);
		gvImageResults = (GridView) findViewById(R.id.gvImageResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
	}
}
