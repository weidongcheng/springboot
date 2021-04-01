package com.wdc.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wdc on 2021/3/31 13:10
 */
public class FastDFSClient {
    private static Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    static{
        try {
            //读取资源文件
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!",e);
        }
    }

    /**
     * 上传文件的工具方法
     * @param file 要上传的文件
     * @return
     */
    public static String[] upload(FastDFSFile file) {
        logger.info("File name: " + file.getName() + "File Lenght: " + file.getContent().length);

        //实例化一个作者数组
        NameValuePair[] meta_list = new NameValuePair[1];
        //赋值
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        //获取当前系统时间
        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        StorageClient storageClient = null;
        try {
            //获取客户端
            storageClient = getTrackerClient();
            //调用上传图片我的API
            uploadResults = storageClient.upload_file(file.getContent(),file.getExt(),meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        } catch (Exception e){
            logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
        }
        logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

        //如果uploadResults对象为null,则上传失败
        if(uploadResults == null && storageClient!=null){
            logger.error("upload file fail, error code:" + storageClient.getErrorCode());
        }
        //获取组名称
        String groupName = uploadResults[0];
        //获取远端文件名称
        String remoteFileName = uploadResults[1];

        logger.info("upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
        return uploadResults;
    }

    /**
     * 获取文件信息
     * @param groupName 组名称
     * @param remoteFileName 远端文件地址名称
     * @return
     */
    public FileInfo getFile(String groupName,String remoteFileName){
        try {
            //获取trackerClient
            StorageClient storageClient = getTrackerClient();
            return storageClient.get_file_info(groupName,remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e){
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    /**
     * 下载文件
     * @param groupName 组名称
     * @param remoteFileName 远端文件地址名称
     * @return
     */
    public InputStream downFile(String groupName,String remoteFileName){
        try {
            //获取trackerClient
            StorageClient storageClient =getTrackerClient();
            //调用下载API
            byte[] fileByte = storageClient.download_file(groupName,remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e){
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    /**
     * 删除文件
     * @param groupName 组名称
     * @param remoteFileName 远端文件地址名称
     * @throws Exception
     */
    public static int deleteFile(String groupName,String remoteFileName) throws Exception{
        //获取trackerClient
        StorageClient storageClient = getTrackerClient();
        //调用删除文件API
        int i =storageClient.delete_file(groupName,remoteFileName);
        logger.info("delete file successfully!!!" + i);
        return i;
    }

    /**
     * 获取所有的storage
     * @param groupName 组名称
     * @return
     * @throws IOException
     */
    public static StorageServer[] getStoreStorages(String groupName) throws IOException{
        //实例化trackerClient
        TrackerClient trackerClient = new TrackerClient();
        //连接trackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer,groupName);
    }

    /**
     * 获取所有的storage
     * @param groupName 组名称
     * @param remoteFileName
     * @return
     * @throws IOException
     */
    public static ServerInfo[] getFetchStorages(String groupName,String remoteFileName) throws IOException{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer,groupName,remoteFileName);
    }

    /**
     * 获取上传成功后图片的完整地址
     * @return
     * @throws IOException
     */
    public static String getTrackerUrl() throws IOException{
        return "http://"+getTrackerServer().getInetSocketAddress().
                getHostString()+":"+ClientGlobal.getG_tracker_http_port()+"/";
    }

    /**
     * 生成客户端
     * @return
     * @throws IOException
     */
    private static StorageClient getTrackerClient() throws IOException {
        //获取trackerServer服务
        TrackerServer trackerServer = getTrackerServer();
        //实例化客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);
        return storageClient;
    }

    /**
     * 获取trackerServer服务器
     * @return
     * @throws IOException
     */
    private static TrackerServer getTrackerServer() throws IOException{
        //实例化tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        //连接trackerServer服务器
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }
}
