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
	private String moreResultsUrl;
	
	public ImageSearcher(Activity parentActivity) {
		this.parentActivity = parentActivity;
	}
	
	public void executeRemoteQuery(String queryUrl)
	{
	
		AsyncHttpClient client = new AsyncHttpClient();
	//Toast.makeText(parentActivity, "Searching using " + queryUrl, Toast.LENGTH_SHORT).show();
		client.get(queryUrl, 
					new JsonHttpResponseHandler() {
						public void onSuccess(JSONObject response) {
							JSONArray imageJsonResults = null;
							JSONObject cursorJsonResults = null;

							try {
								imageJsonResults = 
										response.getJSONObject("responseData").getJSONArray("results");
								imageResults.addAll(ImageResult.fromJSONArray(imageJsonResults));
								
								SearchActivity searchActivity = (SearchActivity) parentActivity;
								searchActivity.getImageArrayAdapter().notifyDataSetChanged();
								cursorJsonResults = 
										response.getJSONObject("responseData").getJSONObject("cursor");
								moreResultsUrl = cursorJsonResults.getString("moreResultsUrl");
								
							//	Toast.makeText(parentActivity, "Cursor string " + moreResultsUrl, Toast.LENGTH_SHORT).show();
								Log.d("DEBUG", imageResults.toString());
								Log.d("DEBUG", "moreResults" + moreResultsUrl);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
	}
	
	public void executeImageSearchQuery(String baseUrl, String keyword, int startArg)
	{
		String queryUrl = baseUrl + "?rsz=8&" + "start=" + startArg + "&v=1.0&q=" + Uri.encode(keyword);
		executeRemoteQuery(queryUrl);
	}

	public void fetchNextSet()
	{
		executeRemoteQuery(moreResultsUrl);
	}

	
	public ArrayList<ImageResult> getImageResults() {
		return imageResults;
	}

	public void setImageResults(ArrayList<ImageResult> imageResults) {
		this.imageResults = imageResults;
	}
	
	
}
