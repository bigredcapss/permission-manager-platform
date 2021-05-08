package com.we.pmp.server.web.controller;

import com.google.common.collect.Maps;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.model.entity.AttendRecordEntity;
import com.we.pmp.server.support.excel.PoiService;
import com.we.pmp.server.support.excel.WebOperationService;
import com.we.pmp.server.web.sercice.IAttendRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 考勤记录Controller
 * @author we
 * @date 2021-05-08 11:35
 **/
@RestController
@Slf4j
@RequestMapping("/attend/record")
public class AttendRecordController {
    @Autowired
    private IAttendRecordService attendRecordService;
    @Autowired
    private PoiService poiService;
    @Autowired
    private WebOperationService webOperationService;

    @GetMapping("/list")
    @ResponseBody
    public BaseResponse list(@RequestParam Map<String, Object> params){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            log.info("---考勤列表---");
            PageResult page = attendRecordService.queryPage(params);
            resMap.put("page", page);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }

    @GetMapping("/export")
    @ResponseBody
    public String export(@RequestParam Map<String, Object> params, HttpServletResponse response){
        final String[] headers=new String[]{"ID","部门名称","姓名","日期","打卡状态","打卡开始时间","打卡结束时间","工时/小时"};
        try {
            String fileName=new StringBuilder("考勤明细-").append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).toString();
            String excelName=fileName+".xls";

            // 针对于具体的业务模块-查询相应的数据，并做 “行转列映射” 的处理
            List<AttendRecordEntity> list=attendRecordService.selectAll(params);
            List<Map<Integer, Object>> listMap=attendRecordService.manageExport(list);

            // 以下是通用的
            Workbook wb=poiService.fillExcelSheetDataV1(listMap,headers,fileName);
            webOperationService.downloadExcel(response,wb,excelName);

            return excelName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
