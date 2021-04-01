package com.wdc.controller;

import com.wdc.fastdfs.FastDFSClient;
import com.wdc.fastdfs.FastDFSFile;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wdc on 2021/3/31 10:38
 */
@Controller
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @GetMapping("/")
    public String index(){
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
           String path = saveFile(file);
           redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
           redirectAttributes.addFlashAttribute("path",
                    "file path url '" + path + "'");
        } catch (Exception e) {
            logger.error("upload file failed",e);
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(){
        return "uploadStatus";
    }

    /**
     *  上传图片并返回图片路径
     * @param file
     * @return
     * @throws IOException
     */
    private String saveFile(MultipartFile file) throws IOException{
        String fileAbsolutePath[]={};
        //获取图片原始路径
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        byte[] file_buff = null;
        InputStream inputStream = file.getInputStream();
        if(inputStream!=null){
            int len1= inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile fastDFSFile = new FastDFSFile(fileName,file_buff,ext);

        try {
            //调用上传文件的工具方法
            fileAbsolutePath = FastDFSClient.upload(fastDFSFile);
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }

        if(fileAbsolutePath==null){
            logger.error("upload file failed,please upload again!");
        }
        //返回可访问的文件地址
        String path = FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+"/"+fileAbsolutePath[1];
        return path;
    }

    /**
     * 删除服务的文件
     * @param groupName 组名称
     * @param remoteFileName 远端文件地址名称
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/delete")
    public String deleteFile(@RequestParam("groupName") String groupName,
                             @RequestParam("remoteFileName") String remoteFileName,
                             RedirectAttributes redirectAttributes){
        try {
            int i = FastDFSClient.deleteFile(groupName,remoteFileName);
            if(i==0){
                redirectAttributes.addFlashAttribute("message","File deleted successfully!");
            }
            if(i==2){
                redirectAttributes.addFlashAttribute("message","File does not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    /**
     * 获取文件的信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    @GetMapping("/getFileInfo")
    public String getFileInfo(@RequestParam("groupName") String groupName,
                              @RequestParam("remoteFileName") String remoteFileName,
                              RedirectAttributes redirectAttributes){
        FastDFSClient fastDFSClient = new FastDFSClient();
        FileInfo fileInfo = fastDFSClient.getFile(groupName,remoteFileName);
        redirectAttributes.addFlashAttribute("message","The size of the file is: "
                + fileInfo.getFileSize()+"\n The IP address of the file is: "+fileInfo.getSourceIpAddr()
                +"\n");
        return "redirect:/uploadStatus";
    }

    /**
     * 下载文件
     * @param groupName 组名称
     * @param remoteFileName 远端文件路径
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/download")
    public String download(@RequestParam("groupName") String groupName,
                           @RequestParam("remoteFileName") String remoteFileName,
                           RedirectAttributes redirectAttributes){
        FastDFSClient fastDFSClient = new FastDFSClient();
        fastDFSClient.downFile(groupName,remoteFileName);
        return "redirect:/uploadStatus";
    }

    /**
     * 获取storage信息
     * @param groupName
     * @return
     */
    @GetMapping("/getStoreStorages")
    public String getStoreStorages(@RequestParam("groupName") String groupName,RedirectAttributes redirectAttributes){
        try {
           StorageServer[] storageServers = FastDFSClient.getStoreStorages(groupName);
           int pathIndex=0;
            if(storageServers!=null){
                for(StorageServer s:storageServers){
                   pathIndex = s.getStorePathIndex();
                }
                redirectAttributes.addFlashAttribute("message","pathIndex: "+pathIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    /**
     * 获取storage信息
     * @param groupName
     * @param remoteFileName
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/getFetchStorages")
    public String getFetchStorages(@RequestParam("groupName") String groupName,
                            @RequestParam("remoteFileName") String remoteFileName,
                                   RedirectAttributes redirectAttributes){
        try {
           ServerInfo[] serverInfos= FastDFSClient.getFetchStorages(groupName,remoteFileName);
            String ip = "";
            int port=0;
           if(serverInfos!=null){
               for(ServerInfo s:serverInfos){
                   ip = s.getIpAddr();
                   port = s.getPort();
               }
               redirectAttributes.addFlashAttribute("message","ip address: "+ip+"\n"+"storage port :"+port);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

}
