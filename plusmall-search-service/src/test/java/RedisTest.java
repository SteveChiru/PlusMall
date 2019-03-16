import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext-redis.xml")
public class RedisTest {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void setValue(){
		redisTemplate.boundValueOps("name").set("firstuse");
	}

	@Test
	public void getValue(){
		String str = (String) redisTemplate.boundValueOps("name").get();
		System.out.println(str);
	}

	@Test
	public void deleteValueFromSet(){
		redisTemplate.delete("allBrand");
	}

	@Test
	public void getValueFromHash(){
		Set members = redisTemplate.opsForSet().members("allBrand");
		System.out.println(members);
	}
}
