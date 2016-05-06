package com.Newawe.ads.AdMobUtils;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

public class AdMobParameters {
    private static final String TAG;
    private Date _birthday;
    private int _gender;
    private Set<String> _keywords;
    private Double _latitude;
    private Double _longtitude;
    private String _publisherId;

    enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    static {
        TAG = AdMobParameters.class.getSimpleName();
    }

    public AdMobParameters(String publisherId, String keywords, String gender, String birthday, String latitude, String longtitude) {
        this._publisherId = StringUtils.EMPTY;
        if (publisherId != null) {
            this._publisherId = publisherId;
        }
        this._keywords = null;
        if (keywords != null) {
            String[] keywordsParts = keywords.split(",");
            this._keywords = new HashSet(keywordsParts.length);
            for (String word : keywordsParts) {
                this._keywords.add(word.trim());
            }
        }
        this._gender = 0;
        if (gender != null) {
            if (gender.compareToIgnoreCase("male") == 0) {
                this._gender = 1;
            } else if (gender.compareToIgnoreCase("female") == 0) {
                this._gender = 2;
            }
        }
        if (birthday != null) {
            try {
                this._birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
            } catch (Exception e) {
                this._birthday = null;
                if (e.getMessage() != null) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        this._latitude = null;
        this._longtitude = null;
        if (latitude != null && longtitude != null) {
            try {
                this._latitude = Double.valueOf(Double.parseDouble(latitude));
                this._longtitude = Double.valueOf(Double.parseDouble(longtitude));
            } catch (NumberFormatException e2) {
                this._latitude = null;
                this._longtitude = null;
                if (e2.getMessage() != null) {
                    Log.e(TAG, e2.getMessage());
                } else {
                    e2.printStackTrace();
                }
            }
        }
    }

    public String getPublisherId() {
        return this._publisherId;
    }

    public void setPublisherId(String publisherId) {
        this._publisherId = publisherId;
    }

    public Set<String> getKeywords() {
        return this._keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this._keywords = keywords;
    }

    public int getGender() {
        return this._gender;
    }

    public void setGender(int gender) {
        this._gender = gender;
    }

    public Date getBirthday() {
        return this._birthday;
    }

    public void setBirthday(Date birthday) {
        this._birthday = birthday;
    }

    public Double getLatitude() {
        return this._latitude;
    }

    public Double getLongtitude() {
        return this._longtitude;
    }

    public void setCoordinates(Double latitude, Double longtitude) {
        this._latitude = latitude;
        this._longtitude = longtitude;
    }
}
