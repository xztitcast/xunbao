package com.bfox.xunbao.common.core.utils;


/**
 * 迁移经纬度距离计算工具类
 *
 * @author clj
 * @date 2023/3/22 09:59:28
 */
public final class DistanceUtils {

    /**
     * 地球半径,单位 km
     */
    private static final double EARTH_RADIUS = 6378.137;

    /**
     * 根据经纬度，计算两点间的距离
     *
     * @param longitude1 第一个点的经度
     * @param latitude1  第一个点的纬度
     * @param longitude2 第二个点的经度
     * @param latitude2  第二个点的纬度
     * @return 返回距离 单位千米
     */
    public static double getDistance(String longitude1, String latitude1, String longitude2, String latitude2) {
        // 纬度
        double lat1 = Math.toRadians(Double.parseDouble(latitude1));
        double lat2 = Math.toRadians(Double.parseDouble(latitude2));
        // 经度
        double lng1 = Math.toRadians(Double.parseDouble(longitude1));
        double lng2 = Math.toRadians(Double.parseDouble(longitude2));
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s = s * EARTH_RADIUS;
        return s;
    }

}
