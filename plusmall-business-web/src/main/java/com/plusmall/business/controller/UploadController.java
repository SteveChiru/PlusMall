package com.plusmall.business.controller;

import com.plusmall.commons.ActionResult;
import com.plusmall.commons.FastDFSUtil;
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
		try {
			String clientConf = "D:\\IdeaProjects\\GithubProjects\\PlusMall\\plusmall-business-web\\src\\main\\resources\\fdfs_client.conf";
			String path = FastDFSUtil.uploadPic(clientConf, file.getBytes(), file.getOriginalFilename(), file.getSize());
			//拼接返回的 url 和 ip 地址，拼装成完整的 url
			String url = FILE_SERVER_URL + path;

			actionResult = new ActionResult(true,url);
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"上传失败");
		}
		return actionResult;
	}
}
