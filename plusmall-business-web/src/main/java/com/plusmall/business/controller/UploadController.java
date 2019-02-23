package com.plusmall.business.controller;

import com.plusmall.commons.ActionResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 */
@RestController
public class UploadController {
	private static Logger logger = Logger.getLogger(UploadController.class);
	private static String logStr = "进入UploadController-";
	private static ActionResult actionResult;

	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER_URL;	//文件服务器地址

	@RequestMapping("/upload")
	public ActionResult upload(MultipartFile file){
		logger.info(logStr+"upload方法");
		//1.取文件的扩展名
		String originalFilename = file.getOriginalFilename();
		logger.info(originalFilename);
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		logger.info(extName);
		try {
			//2.创建一个FastDFS的客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:fdfs_client.conf");
			//3.执行上传处理
			String path = fastDFSClient.uploadFile(file.getBytes(), extName);
			//4.拼接返回的url和ip地址，拼装成完整的url
			String url = FILE_SERVER_URL + path;
			actionResult = new ActionResult(true,url);
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"上传失败");
		}
		return actionResult;
	}
}
