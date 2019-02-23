package com.plusmall.commons;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

/**
 * @Description:
 * 	连接fastdfs，上传图片
 * 	返回上传的路径, 例如: group1/M00/00/00/wKisFFpBG9eAHaQvAAAWKd1hQR4158_big.jpg
 */
public class FastDFSUtil {

	//上传图片
	public static String uploadPic(String clientConf,byte[] pic,String name,long size) throws Exception{
		//读取配置文件
		ClientGlobal.init(clientConf);

		//连接tracker的客户端
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();

		//连接storage的客户端
		StorageServer storageServer = null;
		StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);

		//上传图片
		String ext = FilenameUtils.getExtension(name);  //获取扩展名
		NameValuePair[] metaArr = new NameValuePair[3]; //描述信息
		metaArr[0] = new NameValuePair("fileName", name);
		metaArr[1] = new NameValuePair("fileExt", ext);
		metaArr[2] = new NameValuePair("fileSize", String.valueOf(size));

		String path = storageClient1.upload_file1(pic, ext, metaArr); //返回存储的文件路径
		return path;
	}
}
