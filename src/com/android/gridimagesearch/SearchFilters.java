package com.android.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class SearchFilters implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4696071137368006390L;


	public enum ImageColor {
		NONE("none", "All"),
		BLACK("black", "Black"),
		BLUE("blue", "Blue"),
		BROWN("brown", "Brown"),
		GRAY("gray", "Gray"),
		GREEN("green", "Green"),
		ORANGE("orange", "Orange"),
		PINK("pink", "Pink"),
		PURPLE("purple", "Purple"),
		RED("red", "Red"),
		TEAL("teal", "Teal"),
		WHITE("white", "White"),
		YELLOW("yellow", "Yellow");
		 
		private String text;
		private String spinnerValue;
		
		ImageColor(String queryText, String spinnerValue) {
			this.text = queryText;
			this.spinnerValue = spinnerValue;
		}
		
		public String toQueryString() {
			return text;
		}
		
		public String toSpinnerValue() {
			return spinnerValue;
		}
	}
	
	public enum ImageSize {
		NONE("none", "All"),
		ICON("icon", "Icon"),
		SMALL("small", "Small"),
		MEDIUM("medium", "Medium"),
		LARGE("large", "Large"),
		XLARGE("xlarge", "XLarge"),
		XXLARGE("xxlarge", "XXLarge"),
		HUGE("huge", "Huge");
		

		private String text;
		private String spinnerValue;
		
		ImageSize(String queryText, String spinnerValue) {
			this.text = queryText;
			this.spinnerValue = spinnerValue;
		}
		
		public String toQueryString() {
			return text;
		}
		
		public String toSpinnerValue() {
			return spinnerValue;
		}
	}
	
	public enum ImageType {
		NONE("none", "All"),
		FACE("face", "Face"),
		PHOTO("photo", "Photo"),
		CLIPART("clipart", "ClipArt"),
		LINEART("lineart", "LineArt");
	
		private String text;
		private String spinnerValue;
		
		ImageType(String queryText, String spinnerValue) {
			this.text = queryText;
			this.spinnerValue = spinnerValue;
		}
		
		public String toQueryString() {
			return text;
		}
		
		public String toSpinnerValue() {
			return spinnerValue;
		}
	}

	
	private ImageColor imageColor = ImageColor.NONE;
	private ImageSize imageSize = ImageSize.NONE;
	private ImageType imageType = ImageType.NONE;
	
	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	
	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	public ImageColor getImageColor() {
		return imageColor;
	}

	public void setImageColor(ImageColor imageColor) {
		this.imageColor = imageColor;
	}

	public ImageSize getImageSize() {
		return imageSize;
	}

	public void setImageSize(ImageSize imageSize) {
		this.imageSize = imageSize;
	}

	public ImageType getImageType() {
		return imageType;
	}
	
	
	public String toQueryString() {
		String extraFilters = "";
		extraFilters += (imageType != ImageType.NONE) ? "&imgtype=" + imageType.toQueryString() : "";
		extraFilters += (imageColor != ImageColor.NONE) ? "&imgcolor=" + imageColor.toQueryString() : "";
		extraFilters += (imageSize != ImageSize.NONE) ? "&imgsz=" + imageSize.toQueryString() : "";
		extraFilters += (domain != null) ? "&as_sitesearch=" + domain : "";
		
		return extraFilters;
	}


	public static SearchFilters.ImageType spinnerValueToImageType(String spinnerValue) {
		if (spinnerValue.equalsIgnoreCase("Face")) {
			return SearchFilters.ImageType.FACE;
		}
		else if (spinnerValue.equalsIgnoreCase("Photo")) {
			return SearchFilters.ImageType.PHOTO;
		}
		else if (spinnerValue.equalsIgnoreCase("ClipArt")) {
			return SearchFilters.ImageType.CLIPART;
		}
		else if (spinnerValue.equalsIgnoreCase("LineArt")) {
			return SearchFilters.ImageType.LINEART;
		}
		else {
			return SearchFilters.ImageType.NONE;
		}
	}
	
	public static SearchFilters.ImageColor spinnerValueToImageColor(String spinnerValue) {
		if (spinnerValue.equalsIgnoreCase("Black")) {
			return SearchFilters.ImageColor.BLACK;
		}
		else if (spinnerValue.equalsIgnoreCase("Blue")) {
			return SearchFilters.ImageColor.BLUE;
		}
		else if (spinnerValue.equalsIgnoreCase("Brown")) {
			return SearchFilters.ImageColor.BROWN;
		}
		else if (spinnerValue.equalsIgnoreCase("Gray")) {
			return SearchFilters.ImageColor.GRAY;
		}
		else if (spinnerValue.equalsIgnoreCase("Green")) {
			return SearchFilters.ImageColor.GREEN;
		}
		else if (spinnerValue.equalsIgnoreCase("Orange")) {
			return SearchFilters.ImageColor.ORANGE;
		}
		else if (spinnerValue.equalsIgnoreCase("Pink")) {
			return SearchFilters.ImageColor.PINK;
		}
		else if (spinnerValue.equalsIgnoreCase("Purple")) {
			return SearchFilters.ImageColor.PURPLE;
		}
		else if (spinnerValue.equalsIgnoreCase("Red")) {
			return SearchFilters.ImageColor.RED;
		}
		else if (spinnerValue.equalsIgnoreCase("Teal")) {
			return SearchFilters.ImageColor.TEAL;
		}
		else if (spinnerValue.equalsIgnoreCase("White")) {
			return SearchFilters.ImageColor.WHITE;
		}
		else if (spinnerValue.equalsIgnoreCase("Yellow")) {
			return SearchFilters.ImageColor.YELLOW;
		}
		else {
			return SearchFilters.ImageColor.NONE;
		}
	}
	
	
	public static SearchFilters.ImageSize spinnerValueToImageSize(String spinnerValue) {
		if (spinnerValue.equalsIgnoreCase("Icon")) {
			return SearchFilters.ImageSize.ICON;
		}
		else if (spinnerValue.equalsIgnoreCase("Small")) {
			return SearchFilters.ImageSize.SMALL;
		}
		else if (spinnerValue.equalsIgnoreCase("Medium")) {
			return SearchFilters.ImageSize.MEDIUM;
		}
		else if (spinnerValue.equalsIgnoreCase("Large")) {
			return SearchFilters.ImageSize.LARGE;
		}
		else if (spinnerValue.equalsIgnoreCase("XLarge")) {
			return SearchFilters.ImageSize.XLARGE;
		}
		else if (spinnerValue.equalsIgnoreCase("XXLarge")) {
			return SearchFilters.ImageSize.XXLARGE;
		}
		else if (spinnerValue.equalsIgnoreCase("Huge")) {
			return SearchFilters.ImageSize.HUGE;
		}
		else {
			return SearchFilters.ImageSize.NONE;
		}
	}
	
	
	
}
