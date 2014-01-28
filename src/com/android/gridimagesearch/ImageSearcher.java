package com.android.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ImageSearcher {
	private ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	private Activity parentActivity;
	
	ImageSearchQuery searchQuery;
	public ImageSearcher(Activity parentActivity,
						ImageSearchQuery searchQuery) {
		this.parentActivity = parentActivity;
		this.searchQuery = searchQuery;
	}
	
	public void executeRemoteQuery(String queryUrl)
	{
	
		AsyncHttpClient client = new AsyncHttpClient();
		Log.d("DEBUG", "QUERY: " + queryUrl);
		client.get(queryUrl, 
					new JsonHttpResponseHandler() {
						public void onSuccess(JSONObject response) {
							JSONArray imageJsonResults = null;

							try {
								imageJsonResults = 
										response.getJSONObject("responseData").getJSONArray("results");
								imageResults.addAll(ImageResult.fromJSONArray(imageJsonResults));
								
								SearchActivity searchActivity = (SearchActivity) parentActivity;
								searchActivity.getImageArrayAdapter().notifyDataSetChanged();
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						public void onFailure(JSONObject response) {
							Toast.makeText(parentActivity, "Failed to connect to server.", Toast.LENGTH_SHORT).show();
						}
					});
	}
	
	public void executeImageSearchQuery(ImageSearchQuery searchQuery)
	{
		this.searchQuery = searchQuery;
		executeRemoteQuery(searchQuery.toString());
	}

	
	public ArrayList<ImageResult> getImageResults() {
		return imageResults;
	}

	public void setImageResults(ArrayList<ImageResult> imageResults) {
		this.imageResults = imageResults;
	}
	
	
}
