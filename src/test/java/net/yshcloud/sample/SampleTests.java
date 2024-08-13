package net.yshcloud.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

// 필수사항 3가지
@RunWith(SpringJUnit4ClassRunner.class) // 메서드 별로 테스트 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 참조할 파일
@Log4j2 // log 출력용
public class SampleTests {
	
	@Setter(onMethod_ = @Autowired)
	private Restaurant restaurant;
	
	@Test // import org.junit.test; 메서드 별로 테스트가 진행됨 (메서드명 클릭 -> 우클릭 -> runas -> junit)
	public void testExist() {
		assertNotNull(restaurant); // assertNotNull() 객체가 null 인지 여부
		
		log.info(restaurant);
		log.info("-----------------");
		log.info(restaurant.getChef()); // restaurant 객체에 있는 Chef 필드를 가져와 출력
		// INFO  net.yshcloud.sample.SampleTests(testExist27) - Restaurant(chef=Chef(name=null, age=0, regDate=null), RestaurantName=null, openTime=null, closeTime=null)
		// INFO  net.yshcloud.sample.SampleTests(testExist28) - -----------------
		// INFO  net.yshcloud.sample.SampleTests(testExist29) - Chef(name=null, age=0, regDate=null)
	}
}
