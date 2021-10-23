package com.zjazn.common.baseUtils;

import com.alibaba.excel.util.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FDL {
    public static ResponseEntity<byte[]> download(String path,String filename,String userAgent) {
        /*

           @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename,
                                           @RequestHeader("User-Agent") String userAgent) throws IOException {
                                           ....
        * */

        // 下载文件的路径 path
        // 构建File
        File file = new File(path + File.separator + filename);
        // ok表示http请求中状态码200
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        // 内容长度
        builder.contentLength(file.length());
        // application/octet-stream 二进制数据流（最常见的文件下载）
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        // 使用URLEncoding.decode对文件名进行解码
        try {
            filename = URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 根据浏览器类型，决定处理方式
        if (userAgent.indexOf("MSIE") > 0) {
            builder.header("Content-Disposition", "attachment; filename=" + filename);
        } else {
            builder.header("Content-Disposition", "attacher; filename*=UTF-8''" + filename);
        }
        try {
            return builder.body(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
