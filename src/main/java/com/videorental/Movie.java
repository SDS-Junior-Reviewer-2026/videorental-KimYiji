package com.videorental;

public abstract class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	
	private String title;
	private int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}

//	public void setPriceCode(int arg) {
//		priceCode = arg;
//	}

	public Movie setPriceCode(int priceCode) {
		switch (priceCode) {
			case 0:
				return new RegularMovie(title);
			case 1:
				return new NewReleaseMovie(title);
			case 2:
				return new ChildrenMovie(title);
			default:
				throw new IllegalArgumentException("Invalid movie code");
		}
	}


	public String getTitle() {
		return title;
	}

	abstract double getChargeFor(int daysRented);

	abstract int getFrequentRenterPointsFor(int daysRented);

}