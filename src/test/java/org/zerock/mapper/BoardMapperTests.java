package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)  // 메서드별 테스트용 JUnit4
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 참고할 파일
@Log4j2 
public class BoardMapperTests { // 테스트용 코드
	
	@Setter(onMethod_ = @Autowired) // 생성자 자동주입
	private BoardMapper mapper;
	
	@Test // 메서드 별로 테스트 Junit
	public void testGetList() {
		
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test // 보드 객체 삽입용 테스트
	public void testInsert() {
		
	BoardVO boardVO = new BoardVO(); // 빈객체 생성	
	boardVO.setTitle("매퍼로 만든제목");
	boardVO.setContent("메퍼로 만든 내용");
	boardVO.setWriter("매퍼사용자"); // 객체의 내용 삽입완료
	
	mapper.insert(boardVO);
	
	log.info("입력된 객체 : " + boardVO);
	}
	
	@Test
	public void testInsertSelectKey() {
	
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("번호생성 먼저 제목");
		boardVO.setContent("번호 생성먼저 내용");
		boardVO.setWriter("번호 생성 나");
		
		mapper.insertSelectKey(boardVO); // 번호 먼저 생성 후 insert
		log.info(boardVO);
	}

	@Test
	public void testRead() {
		
		BoardVO boardVO = mapper.read(21L);
		
		log.info(boardVO);
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(23L); // 찾을 번호
		boardVO.setTitle("수정한 제목");
		boardVO.setContent("수정한 내용");
		boardVO.setWriter("김수정");
		
		int count = mapper.update(boardVO);
		log.info("수정된 갯수 : " + count);
		log.info("수정된 객체 : " + boardVO);
	}
	
	@Test
	public void testDelete() {
		
		log.info("삭제한 개수 : " + mapper.delete(22L));
	}
	}