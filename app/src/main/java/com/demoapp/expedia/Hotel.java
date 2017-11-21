package com.demoapp.expedia;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel implements Parcelable {

    @SerializedName("starRating")
    @Expose
    private float starRating;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("loyaltyPointsEarned")
    @Expose
    private int loyaltyPointsEarned;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("hotelImageURL")
    @Expose
    private String hotelImageURL;
    @SerializedName("guestRating")
    @Expose
    private double guestRating;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("hotelName")
    @Expose
    private String hotelName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("discountMessage")
    @Expose
    private String discountMessage;

    /**
     * No args constructor for use in serialization
     *
     */
    public Hotel() {
    }

    /**
     *
     * @param starRating
     * @param price
     * @param discountMessage
     * @param hotelName
     * @param description
     * @param guestRating
     * @param hotelImageURL
     * @param longitude
     * @param latitude
     * @param loyaltyPointsEarned
     */
    public Hotel(float starRating, double latitude, int loyaltyPointsEarned, String description,
                 String hotelImageURL, double guestRating, double longitude, String hotelName,
                 String price, String discountMessage) {
        super();
        this.starRating = starRating;
        this.latitude = latitude;
        this.loyaltyPointsEarned = loyaltyPointsEarned;
        this.description = description;
        this.hotelImageURL = hotelImageURL;
        this.guestRating = guestRating;
        this.longitude = longitude;
        this.hotelName = hotelName;
        this.price = price;
        this.discountMessage = discountMessage;
    }

    public Hotel(Parcel in) {
    }

    public float getStarRating() {
        return starRating;
    }

    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getLoyaltyPointsEarned() {
        return loyaltyPointsEarned;
    }

    public void setLoyaltyPointsEarned(int loyaltyPointsEarned) {
        this.loyaltyPointsEarned = loyaltyPointsEarned;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHotelImageURL() {
        return hotelImageURL;
    }

    public void setHotelImageURL(String hotelImageURL) {
        this.hotelImageURL = hotelImageURL;
    }

    public Double getGuestRating() {
        return guestRating;
    }

    public void setGuestRating(double guestRating) {
        this.guestRating = guestRating;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountMessage() {
        return discountMessage;
    }

    public void setDiscountMessage(String discountMessage) {
        this.discountMessage = discountMessage;
    }

    @Override
    public String toString() {
        return "Hotel [starRating=" + starRating + ", latitude=" + latitude + ", loyaltyPointsEarned=" + loyaltyPointsEarned + ", description=" + description
                + ", hotelImageURL=" + hotelImageURL + ", guestRating=" + guestRating + ", longitude=" + longitude + ", hotelName=" + hotelName + ", " +
                "price=" + price + ", discountMessage=" + discountMessage + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(String.valueOf(getLatitude()));
        dest.writeString(String.valueOf(getLongitude()));
        dest.writeString(getHotelName());
    }

    public static final Parcelable.Creator<Hotel> CREATOR = new Parcelable.Creator<Hotel>() {
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        public Hotel[] newArray(int size) {
            return new Hotel[size];

        }
    };
}