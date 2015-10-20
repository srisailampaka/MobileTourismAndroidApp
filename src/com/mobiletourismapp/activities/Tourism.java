package com.mobiletourismapp.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tourism {

	private List<Object> htmlAttributions = new ArrayList<Object>();
	private String nextPageToken;
	private List<Result> results = new ArrayList<Result>();
	private String status;

	/**
	 * 
	 * @return The htmlAttributions
	 */
	public List<Object> getHtmlAttributions() {
		return htmlAttributions;
	}

	/**
	 * 
	 * @param htmlAttributions
	 *            The html_attributions
	 */
	public void setHtmlAttributions(List<Object> htmlAttributions) {
		this.htmlAttributions = htmlAttributions;
	}

	/**
	 * 
	 * @return The nextPageToken
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * 
	 * @param nextPageToken
	 *            The next_page_token
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	/**
	 * 
	 * @return The results
	 */
	public List<Result> getResults() {
		return results;
	}

	/**
	 * 
	 * @param results
	 *            The results
	 */
	public void setResults(List<Result> results) {
		this.results = results;
	}

	/**
	 * 
	 * @return The status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public class Geometry {

		private Location location;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		/**
		 * 
		 * @return The location
		 */
		public Location getLocation() {
			return location;
		}

		/**
		 * 
		 * @param location
		 *            The location
		 */
		public void setLocation(Location location) {
			this.location = location;
		}

	}

	public class Location {

		private Double lat;
		private Double lng;

		/**
		 * 
		 * @return The lat
		 */
		public Double getLat() {
			return lat;
		}

		/**
		 * 
		 * @param lat
		 *            The lat
		 */
		public void setLat(Double lat) {
			this.lat = lat;
		}

		/**
		 * 
		 * @return The lng
		 */
		public Double getLng() {
			return lng;
		}

		/**
		 * 
		 * @param lng
		 *            The lng
		 */
		public void setLng(Double lng) {
			this.lng = lng;
		}

	}

	public class OpeningHours {

		private Boolean openNow;
		private List<Object> weekdayText = new ArrayList<Object>();

		/**
		 * 
		 * @return The openNow
		 */
		public Boolean getOpenNow() {
			return openNow;
		}

		/**
		 * 
		 * @param openNow
		 *            The open_now
		 */
		public void setOpenNow(Boolean openNow) {
			this.openNow = openNow;
		}

		/**
		 * 
		 * @return The weekdayText
		 */
		public List<Object> getWeekdayText() {
			return weekdayText;
		}

		/**
		 * 
		 * @param weekdayText
		 *            The weekday_text
		 */
		public void setWeekdayText(List<Object> weekdayText) {
			this.weekdayText = weekdayText;
		}

	}

	public class Photo {

		private Integer height;
		private List<String> htmlAttributions = new ArrayList<String>();
		private String photoReference;
		private Integer width;

		/**
		 * 
		 * @return The height
		 */
		public Integer getHeight() {
			return height;
		}

		/**
		 * 
		 * @param height
		 *            The height
		 */
		public void setHeight(Integer height) {
			this.height = height;
		}

		/**
		 * 
		 * @return The htmlAttributions
		 */
		public List<String> getHtmlAttributions() {
			return htmlAttributions;
		}

		/**
		 * 
		 * @param htmlAttributions
		 *            The html_attributions
		 */
		public void setHtmlAttributions(List<String> htmlAttributions) {
			this.htmlAttributions = htmlAttributions;
		}

		/**
		 * 
		 * @return The photoReference
		 */
		public String getPhotoReference() {
			return photoReference;
		}

		/**
		 * 
		 * @param photoReference
		 *            The photo_reference
		 */
		public void setPhotoReference(String photoReference) {
			this.photoReference = photoReference;
		}

		/**
		 * 
		 * @return The width
		 */
		public Integer getWidth() {
			return width;
		}

		/**
		 * 
		 * @param width
		 *            The width
		 */
		public void setWidth(Integer width) {
			this.width = width;
		}

	}

	public class Result {

		private Geometry geometry;
		private String icon;
		private String id;
		private String name;
		private OpeningHours openingHours;
		private String placeId;
		private String reference;
		private String scope;
		private List<String> types = new ArrayList<String>();
		private String vicinity;
		private List<Photo> photos = new ArrayList<Photo>();
		private Double rating;

		/**
		 * 
		 * @return The geometry
		 */
		public Geometry getGeometry() {
			return geometry;
		}

		/**
		 * 
		 * @param geometry
		 *            The geometry
		 */
		public void setGeometry(Geometry geometry) {
			this.geometry = geometry;
		}

		/**
		 * 
		 * @return The icon
		 */
		public String getIcon() {
			return icon;
		}

		/**
		 * 
		 * @param icon
		 *            The icon
		 */
		public void setIcon(String icon) {
			this.icon = icon;
		}

		/**
		 * 
		 * @return The id
		 */
		public String getId() {
			return id;
		}

		/**
		 * 
		 * @param id
		 *            The id
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * 
		 * @return The name
		 */
		public String getName() {
			return name;
		}

		/**
		 * 
		 * @param name
		 *            The name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 
		 * @return The openingHours
		 */
		public OpeningHours getOpeningHours() {
			return openingHours;
		}

		/**
		 * 
		 * @param openingHours
		 *            The opening_hours
		 */
		public void setOpeningHours(OpeningHours openingHours) {
			this.openingHours = openingHours;
		}

		/**
		 * 
		 * @return The placeId
		 */
		public String getPlaceId() {
			return placeId;
		}

		/**
		 * 
		 * @param placeId
		 *            The place_id
		 */
		public void setPlaceId(String placeId) {
			this.placeId = placeId;
		}

		/**
		 * 
		 * @return The reference
		 */
		public String getReference() {
			return reference;
		}

		/**
		 * 
		 * @param reference
		 *            The reference
		 */
		public void setReference(String reference) {
			this.reference = reference;
		}

		/**
		 * 
		 * @return The scope
		 */
		public String getScope() {
			return scope;
		}

		/**
		 * 
		 * @param scope
		 *            The scope
		 */
		public void setScope(String scope) {
			this.scope = scope;
		}

		/**
		 * 
		 * @return The types
		 */
		public List<String> getTypes() {
			return types;
		}

		/**
		 * 
		 * @param types
		 *            The types
		 */
		public void setTypes(List<String> types) {
			this.types = types;
		}

		/**
		 * 
		 * @return The vicinity
		 */
		public String getVicinity() {
			return vicinity;
		}

		/**
		 * 
		 * @param vicinity
		 *            The vicinity
		 */
		public void setVicinity(String vicinity) {
			this.vicinity = vicinity;
		}

		/**
		 * 
		 * @return The photos
		 */
		public List<Photo> getPhotos() {
			return photos;
		}

		/**
		 * 
		 * @param photos
		 *            The photos
		 */
		public void setPhotos(List<Photo> photos) {
			this.photos = photos;
		}

		/**
		 * 
		 * @return The rating
		 */
		public Double getRating() {
			return rating;
		}

		/**
		 * 
		 * @param rating
		 *            The rating
		 */
		public void setRating(Double rating) {
			this.rating = rating;
		}

	}
}
