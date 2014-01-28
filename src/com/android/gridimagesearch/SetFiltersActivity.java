package com.android.gridimagesearch;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class SetFiltersActivity extends Activity {

	private Spinner spImageSize;
	private Spinner spImageType;
	private Spinner spColor;
	private EditText etSite;
	private SearchFilters searchFilters;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_filters);
		// Show the Up button in the action bar.
		setupActionBar();
		
		setupViews();
		
		searchFilters = (SearchFilters) getIntent().getSerializableExtra("search_filters");
		
		if (searchFilters.getDomain() != null) {
			etSite.setText(searchFilters.getDomain());
		}
		
		setSpinnerToValue(spImageSize, searchFilters.getImageSize().toSpinnerValue());
		setSpinnerToValue(spImageType, searchFilters.getImageType().toSpinnerValue());
		setSpinnerToValue(spColor, searchFilters.getImageColor().toSpinnerValue());
		
	}

	/*
	@Override
	protected void onPause() {
		super.onPause();

		String tvImageTypeSelection = (String) spImageType.getSelectedItem();
		String tvImageSizeSelection = (String) spImageSize.getSelectedItem();
		String tvColorSelection = (String) spColor.getSelectedItem();
		searchFilters.setImageType(SearchFilters.spinnerValueToImageType(tvImageTypeSelection));
		searchFilters.setImageSize(SearchFilters.spinnerValueToImageSize(tvImageSizeSelection));
		searchFilters.setImageColor(SearchFilters.spinnerValueToImageColor(tvColorSelection));
		searchFilters.setDomain(etSite.getText().toString());
        Intent filtersData = new Intent();
        filtersData.putExtra("search_filters", searchFilters);
        setResult(RESULT_OK, filtersData);
        finish();
	}
	*/

	public void setSpinnerToValue(Spinner spinner, String value) {
	    int index = 0;
	    SpinnerAdapter adapter = spinner.getAdapter();
	    for (int i = 0; i < adapter.getCount(); i++) {
	        if (adapter.getItem(i).equals(value)) {
	            index = i;
	        }
	    }
	    spinner.setSelection(index);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_filters, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void saveFilters(View v) {
		String tvImageTypeSelection = (String) spImageType.getSelectedItem();
		String tvImageSizeSelection = (String) spImageSize.getSelectedItem();
		String tvColorSelection = (String) spColor.getSelectedItem();
		searchFilters.setImageType(SearchFilters.spinnerValueToImageType(tvImageTypeSelection));
		searchFilters.setImageSize(SearchFilters.spinnerValueToImageSize(tvImageSizeSelection));
		searchFilters.setImageColor(SearchFilters.spinnerValueToImageColor(tvColorSelection));
		searchFilters.setDomain(etSite.getText().toString());
        Intent filtersData = new Intent();
        filtersData.putExtra("search_filters", searchFilters);
        setResult(RESULT_OK, filtersData);
        finish();
	}

	
	private void setupViews()
	{
		etSite = (EditText) findViewById(R.id.etSite);
		spColor = (Spinner) findViewById(R.id.spColor);
		spImageSize = (Spinner) findViewById(R.id.spImageSize);
		spImageType = (Spinner) findViewById(R.id.spImageType);
	}
	
}
