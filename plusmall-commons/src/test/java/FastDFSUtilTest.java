import com.plusmall.commons.FastDFSUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;

/**
 * @Description:
 */
public class FastDFSUtilTest {

	public static void main(String[] args) {
		File f = new File("C:\\Users\\cqr15\\Desktop\\RJ.jpg");
		FileItem fileItem = null;
		try {
			InputStream input = new FileInputStream(f);
			fileItem = new DiskFileItem("mainFile", Files.probeContentType(f.toPath()), false, f.getName(), (int) f.length(), f.getParentFile());
			OutputStream os = fileItem.getOutputStream();

			IOUtils.copy(input, os);
			MultipartFile mulFile = new CommonsMultipartFile(fileItem);

			String clientConf = "D:\\IdeaProjects\\GithubProjects\\PlusMall\\plusmall-commons\\src\\main\\resources\\fdfs_client.conf";
			String s = FastDFSUtil.uploadPic(clientConf,mulFile.getBytes(), mulFile.getOriginalFilename(), mulFile.getSize());
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
