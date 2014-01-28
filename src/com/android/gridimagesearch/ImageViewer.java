package com.android.gridimagesearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;

import com.loopj.android.image.SmartImageView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ImageViewer extends Activity {


	private ShareActionProvider miShareAction;


	public void onShareItem(View v) {
	    // Get access to bitmap image from view
	    SmartImageView ivImage = (SmartImageView) findViewById(R.id.ivResult);
	    Bitmap bitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
	    // Write image to default external storage directory   
	    Uri bmpUri = null;
	    try {
	        File file =  new File(Environment.getExternalStoragePublicDirectory(  
	           Environment.DIRECTORY_DOWNLOADS), "share_image.png");  
	    FileOutputStream out = new FileOutputStream(file);
	    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
	    out.close();
	    bmpUri = Uri.fromFile(file);
	    } catch (IOException e) {
	    e.printStackTrace();
	    }

	    if (bmpUri != null) {
	        // Construct a ShareIntent with link to image
	    Intent shareIntent = new Intent();
	    shareIntent.setAction(Intent.ACTION_SEND);
	    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
	    shareIntent.setType("image/*");
	    // Launch sharing dialog for image
	    startActivity(Intent.createChooser(shareIntent, "Share Content"));  
	    } else {
	    // ...sharing failed, handle error
	    }
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_viewer);
		
		ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra("result");
		SmartImageView ivImage = (SmartImageView) findViewById(R.id.ivResult);
		ivImage.setImageUrl(imageResult.getFullUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_viewer, menu);
		
	    MenuItem item = menu.findItem(R.id.menu_item_share);
	    // Fetch and store ShareActionProvider
	    miShareAction = (ShareActionProvider) item.getActionProvider();
	    // Return true to display menu
	    

		return true;
	}

	public void shareImage(MenuItem mi)
	{
		 // Create share intent as described above
	    Intent shareIntent = new Intent();
	    shareIntent.setAction(Intent.ACTION_SEND);
	    // ...
	    // Attach share event to the menu item provider
	    miShareAction.setShareIntent(shareIntent);
	}
}
