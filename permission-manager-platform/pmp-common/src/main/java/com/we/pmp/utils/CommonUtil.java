package com.we.pmp.utils;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * @author we
 * @date 2021-05-06 15:28
 **/
public class CommonUtil {
    /**
     * 获取随机的UUID
     * @return
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 分隔后拼接成用于sql查询的字符串~'a','b','c'  "a,b,c"
     * @param param
     * @param separatorChars
     * @return
     */
    public static String concatStrToChar(String param, String separatorChars){
        StringBuilder sb=new StringBuilder();
        String[] arr= StringUtils.split(param,separatorChars);
        for (int i=0;i<arr.length;i++){
            if (arr.length-1 != i){
                sb.append("'").append(arr[i]).append("'").append(",");
            }else{
                sb.append("'").append(arr[i]).append("'");
            }
        }
        return sb.toString();
    }

    /**
     * 分隔后拼接成用于sql查询的字符串~a,b,c
     * @param param
     * @param separatorChars
     * @return
     */
    public static String concatStrToInt(String param, String separatorChars){
        StringBuilder sb=new StringBuilder();
        String[] arr= StringUtils.split(param,separatorChars);
        int i=0;
        for (;i<arr.length;i++){
            if (arr.length-1 != i){
                sb.append(arr[i]).append(",");
            }else{
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }



}
