package com.t2t.top.base.utils;

/**
 * 经纬度坐标点 ,用于坐标转换类CoordinateConversion使用
 * User: guizhou
 * Date: 15-5-4
 * Time: 下午5:29
 */
public class GeoPointUtils {

    private double lat;// 纬度
    private double lng;// 经度

    public GeoPointUtils() {
    }

    public GeoPointUtils(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GeoPointUtils) {
            GeoPointUtils bmapGeoPointUtils = (GeoPointUtils) obj;
            return (bmapGeoPointUtils.getLng() == lng && bmapGeoPointUtils.getLat() == lat) ? true : false;
        } else {
            return false;
        }
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "GeoPoint [lat=" + lat + ", lng=" + lng + "]";
    }

}