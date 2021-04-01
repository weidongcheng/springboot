package com.wdc;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

@SpringBootTest
class SpringBootFileUploadApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String args[]) {

		try {
			//加载客户端配置文件
			String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
			ClientGlobal.init(filePath);
			System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
			System.out.println("charset=" + ClientGlobal.g_charset);
			System.out.println("tracker_http_port= "+ClientGlobal.getG_tracker_http_port());
			//构建tracker客户端
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			//构建storage客户端
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);

			//文件原始信息
			NameValuePair[] metaList = new NameValuePair[1];
			metaList[0] = new NameValuePair("fileName", "scenery1.jpg");
			//执行上传
			String fileId = client.upload_file1("C:\\Users\\18621\\Pictures\\Camera Roll\\scenery1.jpg", "jpg", metaList);
			System.out.println("upload success. file id is: " + fileId);

//			int i = 0;
//			while (i++ < 10) {
//				byte[] result = client.download_file1(fileId);
//				System.out.println(i + ", download result is: " + result.length);
//			}
			//关闭tracker服务连接
			trackerServer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


}
