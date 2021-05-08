package com.we.pmp.server.support.excel;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 通用的Web操作服务
 * @author we
 * @date 2021-05-08 10:47
 **/
@Service
@Slf4j
public class WebOperationService {

    /**
     * 通用下载附件
     * @param response
     * @param is
     * @param fileName
     * @throws Exception
     */
    public void downloadFile(HttpServletResponse response, InputStream is, String fileName)
            throws Exception{
        if(is == null || Strings.isNullOrEmpty(fileName)){
            return;
        }
        BufferedInputStream bis = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;
        try{
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            bos = new BufferedOutputStream(os);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes(
                    "utf-8"),"iso-8859-1"));
            byte[] buffer = new byte[10240];
            int len = bis.read(buffer);
            while(len != -1){
                bos.write(buffer, 0, len);
                len = bis.read(buffer);
            }
            bos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(bis != null){
                try{
                    bis.close();
                }catch(IOException e){}
            }
            if(is != null){
                try{
                    is.close();
                }catch(IOException e){}
            }
        }
    }


    /**
     * 下载Excel
     * @param response
     * @param wb
     * @param fileName
     * @throws Exception
     */
    public void downloadExcel(HttpServletResponse response, Workbook wb, String fileName) throws Exception{
        response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out=response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
}
