package com.we.pmp;

import com.we.pmp.common.utils.CommonUtil;

/**
 * @author we
 * @date 2021-05-06 15:32
 **/
public class CommonUtilTest {

    public static void main(String[] args) {
        String params="1,2,3,4,5";
        System.out.println(CommonUtil.concatStrToInt(params,","));

        params="R1010,R1020,R1030";
        System.out.println(CommonUtil.concatStrToChar(params,","));
    }
}
