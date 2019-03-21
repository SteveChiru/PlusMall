import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 */
public class FreemarkerTest {

	public static void main(String[] args) throws Exception {
		Configuration configuration= new Configuration();
		configuration.setDirectoryForTemplateLoading(new File("D:\\IdeaProjects\\GithubProjects\\PlusMall\\plusmall-page-service\\src\\main\\webapp\\WEB-INF\\ftl\\"));
		configuration.setDefaultEncoding("utf8");
		Template template = configuration.getTemplate("ftlTest.ftl");
		Map map = new HashMap<String,String>();
		map.put("name","张三");
		map.put("message","欢迎使用Freemarker");
		Writer out = new FileWriter(new File("D:\\IdeaProjects\\GithubProjects\\PlusMall\\plusmall-page-web\\src\\main\\webapp\\itempages\\test.html"));
		template.process(map,out);
		out.close();
	}
}
