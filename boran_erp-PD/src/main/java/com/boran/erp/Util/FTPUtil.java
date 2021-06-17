package com.boran.erp.Util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author LT
 * @create 2020-12-2 15:00
 */
public class FTPUtil {
    //ip地址
    private static final String FTP_ADDRESS = "81.70.180.73";
    //端口号
    private static final int FTP_PORT = 21;

    //ftp用户名
    private static final String FTP_USERNAME = "ftp_boran_rz_com";
    //ftp密码
    private static final String FTP_PASSWORD = "boran123";
    //文件夹
    private static final String FTP_BASEPATH = "/LOG";

    //文件上传                                   //文件名       io输入流
    private static boolean uploadFile(String fileName, InputStream input) {
        boolean success = false;
        //new有个客户端
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);
            ftp.login(FTP_USERNAME, FTP_PASSWORD);
            reply = ftp.getReplyCode();
            //如果没有连上，那么就自动断开
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
            ftp.setControlEncoding("GBK");
            //设置文件类型                二进制
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            //创建文件夹
            ftp.makeDirectory(FTP_BASEPATH);
            //改变目录
            ftp.changeWorkingDirectory(FTP_BASEPATH);
            ftp.enterLocalPassiveMode();
            //传文件
            ftp.storeFile(fileName, input);
            //输入流关闭
            input.close();
            //ftp注销
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    //实现
    public static AjaxJson addFtp(MultipartFile file) throws IOException {
        //@RequestParam("file") MultipartFile file
        //File files = new File(url);
        //FileInputStream inputStreams = new FileInputStream(files);
        //MultipartFile file = new MockMultipartFile("file", files.getName(), "*/plain", inputStreams);

        //获取上传的文件流
        InputStream inputStream = file.getInputStream();
        //获取上传的文件名  xxxx.xx
        String filename = file.getOriginalFilename();
        //截取后缀  .oo
        String suffix = filename.substring(filename.lastIndexOf("."));
        //截取文件名字   ooo.    存在乱码待解决
        String name = filename.substring(0, filename.lastIndexOf("."));
        //文件名+使用时间戳拼接后缀，生成一个不重复的文件名
        String finalName = System.currentTimeMillis() + suffix;
        //调用自定义的FTP工具类上传文件
        boolean fileBool = FTPUtil.uploadFile(finalName, inputStream);
        if (fileBool) {
            return AjaxJson.getSuccess(finalName);
        } else {
            return AjaxJson.getError();
        }
    }

    /**
     * 删除
     */
    public static boolean deleteFile(String filename) {
        boolean flag = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);
            ftp.login(FTP_USERNAME, FTP_PASSWORD);
            ftp.changeWorkingDirectory(FTP_BASEPATH);
            ftp.enterLocalPassiveMode();
            reply = ftp.getReplyCode();
            ftp.setControlEncoding("GBK");
            //如果没有连上，那么就自动断开
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
            ftp.dele(filename);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


  /*  public static boolean addFtp(String url) throws IOException {
        //@RequestParam("file") MultipartFile file
        File files = new File(url);
        FileInputStream inputStreams = new FileInputStream(files);
        MultipartFile file = new MockMultipartFile("file", files.getName(),
                plain", inputStreams);

                //获取上传的文件流
                InputStream inputStream = file.getInputStream();
        //获取上传的文件名  xxxx.xx
        String filename = file.getOriginalFilename();
        //截取后缀  .oo
        String suffix = filename.substring(filename.lastIndexOf("."));
        //截取文件名字   ooo.    存在乱码待解决
        String name = filename.substring(0, filename.lastIndexOf("."));
        //文件名+使用时间戳拼接后缀，生成一个不重复的文件名
        String finalName=System.currentTimeMillis()+suffix;
        //调用自定义的FTP工具类上传文件
        boolean fileBool=FTPUtil.uploadFile(finalName,inputStream);
        return fileBool;
    }*/

}
