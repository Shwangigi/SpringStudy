package net.yshcloud.sample;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component   // 스프링이 관리 -> 필수 root-context.xml에 가서 component-scan에 패키지추가 
@Data   // lombok이 dto처럼 관리해주세요
public class Restaurant {

	// 필드
	@Setter(onMethod_ = @Autowired) // 자동으로 setChef() 을 컴파일 시 생성된다.
	private Chef chef;                // setChef(chef)
	private String RestaurantName;
	private Date openTime;
	private Date closeTime;
	
	// 생성자
	
	
	// 메서드
}
