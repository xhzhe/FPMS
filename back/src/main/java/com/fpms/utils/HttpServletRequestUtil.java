package com.fpms.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/18 16:57
 * @description: 获取前端传递参数的工具类
 * @modified :
 */
public class HttpServletRequestUtil {

    public static int getInt(HttpServletRequest request, String name){

        try {
            return Integer.valueOf(request.getParameter(name));
        }catch (Exception e){
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String name){

        try {
            return Long.valueOf(request.getParameter(name));
        }catch (Exception e){
            return -1;
        }
    }

    public static double getDouble(HttpServletRequest request, String name){

        try {
            return Double.valueOf(request.getParameter(name));
        }catch (Exception e){
            return -1d;
        }
    }

    public static String getString(HttpServletRequest request, String name){

        try {
            String result = request.getParameter(name);
            if(result != null){
                result.trim();
            }
            if("".equals(result)){
                result = null;
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }
}
