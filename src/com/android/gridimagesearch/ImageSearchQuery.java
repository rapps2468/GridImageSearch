package com.android.gridimagesearch;



public class ImageSearchQuery {

	public ImageSearchQuery(String keyword) {
		this.keyword = keyword;
		this.searchFilters = new SearchFilters();
	}

	private String fullQueryUrl;
	private String keyword;
	private int startArg = 0;
	private int resultSize = 8;

	private SearchFilters searchFilters;

	public SearchFilters getSearchFilters() {
		return searchFilters;
	}

	public void setSearchFilters(SearchFilters searchFilters) {
		this.searchFilters = searchFilters;
	}

	public String getFullQueryUrl() {
		return fullQueryUrl;
	}

	public void setFullQueryUrl(String fullQueryUrl) {
		this.fullQueryUrl = fullQueryUrl;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getStartArg() {
		return startArg;
	}

	public void setStartArg(int startArg) {
		this.startArg = startArg;
	}



	@Override
	public String toString() {

		String queryString;
		queryString = 
				String.format("https://ajax.googleapis.com/ajax/services/search/images?rsz=%d&start=%d&v=1.0&q=%s%s",
									resultSize, startArg, keyword, searchFilters.toQueryString());
		
		return queryString;
	}

}

	
	

