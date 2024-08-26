package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j2;

@RestController // REST 방식의 컨트롤러로 동작
@RequestMapping("/sample") // http://loacalhost:80/sample/???
@Log4j2 // Log4j2.xml 필수
public class SampleController {
	// Rest(Representational State Transfer) -> URI를 통해 METHOD가 반응함
	// Method는 get(select) / post(insert) / put(update) / delete(delete) 로 세분화됨 
	// @RequestBody : 요청에 대한 처리(json 데이터를 원하는 타입으로 바인딩 처리)
	// @ResponseBody : 응답에 대한 처리(jsp로 view 처리가 아님 -> xml, json으로 보냄)
	// @PathVariable : URI(주소, 경로)에 있는 값을 파라미터로 추출
	// @CrossOrigin : AJAX(프론트)의 크로스 도메인 문제를 해결
	
	@GetMapping(value="/getText" , produces="text/plain; charset=UTF-8") // http://localhost:80/sample/getText
	// produces(생산하다 : 데이터타입반환용)
	public String getText() {
		
		log.info("SampleController.getText() 메서드 실행");
		
		return "안뇽하세요. 레스트 컨트롤러 입니다.";
	}
	
	@GetMapping(value="/getSampleVO", produces = {MediaType.APPLICATION_JSON_VALUE, // json으로 출력
			                                    MediaType.APPLICATION_XML_VALUE}) // xml 출력
	public SampleVO getSample() {
		
		return new SampleVO(123, "김" , "기원");
	}
	
	// http://localhost:80/sample/getSample -> xml(기본값)
	// http://localhost:80/sample/getSample.json -> json
	@GetMapping(value="/getSample") // produces 생략 가능
	public SampleVO getSample2() {
		
		return new SampleVO(312, "MBC", "아카데미");
	}
	
	@GetMapping(value="/getList") // http://localhost:80/sample/getList
	public List<SampleVO> getlist(){
		
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+"FirstName", i+"LastName"))
				.collect(Collectors.toList());
		// IntStream.range(1, 10) -> 정수타입의 숫자를 1~10까지 반복생성용
		// [{"mno":1,"firstName":"1FirstName","lastName":"1LastName"},
		// {"mno":2,"firstName":"2FirstName","lastName":"2LastName"},
		// {"mno":3,"firstName":"3FirstName","lastName":"3LastName"},
		// {"mno":4,"firstName":"4FirstName","lastName":"4LastName"},
		// {"mno":5,"firstName":"5FirstName","lastName":"5LastName"},
		// {"mno":6,"firstName":"6FirstName","lastName":"6LastName"},
		// {"mno":7,"firstName":"7FirstName","lastName":"7LastName"},
		// {"mno":8,"firstName":"8FirstName","lastName":"8LastName"},
		// {"mno":9,"firstName":"9FirstName","lastName":"9LastName"}]
	}
	
	@GetMapping(value = "/getMap") // http://localhost:80/sample/getMap
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("kkw",  new SampleVO(312, "엠비씨 아카데미","클라우드풀스택-교사"));
		map.put("mgs",  new SampleVO(312, "엠비씨 아카데미","클라우드풀스택-반장"));
		map.put("ysy",  new SampleVO(312, "엠비씨 아카데미","클라우드풀스택-1조장"));
		map.put("ysh",  new SampleVO(312, "엠비씨 아카데미","클라우드풀스택-2조장"));
		
		return map;
		// {"kkw":{"mno":312,"firstName":"엠비씨 아카데미","lastName":"클라우드풀스택-교사"},
		// "mgs":{"mno":312,"firstName":"엠비씨 아카데미","lastName":"클라우드풀스택-반장"},
		// "ysy":{"mno":312,"firstName":"엠비씨 아카데미","lastName":"클라우드풀스택-1조장"},
		// "ysh":{"mno":312,"firstName":"엠비씨 아카데미","lastName":"클라우드풀스택-2조장"}}
	}  
	
	// Rest 컨트롤러의 단점 : 넘어온 값이 정상(200) 값인지 비정상 인지를 알려줄 필요가 있다.
	// ResponseEntity : 데이터와 함께 http 헤더의 상태값(200,400,500) 등을 같이 전달
	
	@GetMapping(value="/check", params= {"height", "weight"}) 
	// http://localhost:80/sample/check.json?height=140&weight=80 -> 502에러
	// http://localhost:80/sample/check.json?height=160&weight=80 -> 200 OK
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		// height 150보다 작으면 오류로 판단 502오류 값을 보낼 것 !
		
		SampleVO vo = new SampleVO(312, "" + height, "" + weight); // ""+는 String 처리
		
		ResponseEntity<SampleVO> result = null;
		if(height < 150) { // url을 통해서 넘어온 height 값이 150보다 작으면
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo); // BAD_GATEWAY(502오류)
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo); // HttpSatus.OK(200 상태 : 정상)
		}
		
		return result;
	}
	
	// @Pathvariable -> REST 컨트롤러에는 최대한 많은 정보를 가지고 데이터를 처리하려고 한다.
	// http://localhost:80/sample/{sno}/page/{pno}/ampunt/{amount}
	
	@GetMapping("/product/{cat}/{pid}") // http://localhost:80/sample/product/happy/312
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		//                                              happy                             312
		
		return new String[] {"category : " + cat, "produuctid : " + pid}; 
		// ["category : happy","produuctid : 312"]
	}
	
	// @RequesrBody -> 전달된 요청(Request)의 내용(Body)를 이용해서 해당 파라미터의 타입으로 변환 요구함
	// 외부 API가 json을 이용해서 값을 달라고 요청을 하면 (들어오는 값이 json) 처리해야 함
	
	@PostMapping("/ticket") // http://localhost:80/sample/ticket
	// PostMapping은 url을 통해서 테스트가 불가능함 (junit에서 하던지 , postman을 이용함)
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("SampleController.convert() 메서드 실행" + ticket); // toString 처리됨
		
		return ticket;
	}
}
