import com.plusmall.model.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class SolrTest {

	@Autowired
	private SolrTemplate solrTemplate;

	//增加
	@Test
	public void testAdd(){
		TbItem item=new TbItem();
		item.setId(1L);
		item.setBrand("华为");
		item.setCategory("手机");
		item.setGoodsId(1L);
		item.setSeller("华为2号专卖店");
		item.setTitle("华为Mate9");
		item.setPrice(new BigDecimal(2000));
		solrTemplate.saveBean(item);
		solrTemplate.commit();
	}

	//按主键查询
	@Test
	public void testFindOne(){
		TbItem item = solrTemplate.getById(1, TbItem.class);
		System.out.println(item.getTitle());
	}

	//按主键删除
	@Test
	public void testDelete(){
		solrTemplate.deleteById("1");
		solrTemplate.commit();
	}

	//分页查询
	//1.循环插入100条测试数据
	@Test
	public void testList(){
		List<TbItem> list = new ArrayList<>();

		for (int i=0;i<100;i++){
			TbItem item=new TbItem();
			item.setId(i+1L);
			item.setBrand("华为");
			item.setCategory("手机");
			item.setGoodsId(1L);
			item.setSeller("华为2号专卖店");
			item.setTitle("华为Mate"+i);
			item.setPrice(new BigDecimal(2000+i));
			list.add(item);
		}

		solrTemplate.saveBeans(list);
		solrTemplate.commit();
	}

	//2.编写分页查询测试代码
	@Test
	public void testPageQuery(){
		Query query=new SimpleQuery("*:*");
		query.setOffset(20);//开始索引（默认0）
		query.setRows(20);//每页记录数(默认10)
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		System.out.println("总记录数："+page.getTotalElements());
		List<TbItem> list = page.getContent();
		showList(list);
	}

	private void showList(List<TbItem> list) {
		for(TbItem item:list){
			System.out.println(item.getTitle()+": "+item.getPrice());
		}
	}

	//按条件查询
	@Test
	public void testPageQueryMutil(){
		Query query=new SimpleQuery("*:*");
		Criteria criteria=new Criteria("item_title").contains("2");
		criteria=criteria.and("item_title").contains("5");
		query.addCriteria(criteria);
		//query.setOffset(20);//开始索引（默认0）
		//query.setRows(20);//每页记录数(默认10)
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		System.out.println("总记录数："+page.getTotalElements());
		List<TbItem> list = page.getContent();
		showList(list);
	}

	//删除全部数据
	@Test
	public void testDeleteAll(){
		Query query=new SimpleQuery("*:*");
		solrTemplate.delete(query);
		solrTemplate.commit();
	}

}
