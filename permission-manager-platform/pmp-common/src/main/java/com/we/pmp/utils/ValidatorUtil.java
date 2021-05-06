package com.we.pmp.utils;

import org.springframework.validation.BindingResult;

/**
 * 参数校验工具
 * @author we
 * @date 2021-05-06 16:42
 **/
public class ValidatorUtil {
    /**
     * 统一处理校验结果
     * @param result
     * @return
     */
    public static String checkResult(BindingResult result){
        StringBuilder sb=new StringBuilder("");
        if (result!=null && result.hasErrors()){
            result.getAllErrors().stream().forEach(error -> sb.append(error.getDefaultMessage()).append("\n"));
        }
        return sb.toString();
    }
}
