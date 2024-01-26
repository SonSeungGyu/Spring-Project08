package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repo;
	
	@Test
	public void 게시물30개추가() {
		for(int i=1;i<=30;i++) {
			Board board = Board
							.builder()
							.title(i+"번글")
							.content("안녕")
							.writer("둘리")
							.build();
			repo.save(board);
		}
	}//빌더 패턴은 no,regDate,modDate등 정보를 생략 가능
	
	@Test
	public void 페이지테스트() {
		//1페이지 10개
		Pageable pageable = PageRequest.of(2,10);//인덱스라서 0번부터 시작, 뒤는 갯수(10)
		//게시물 리스트 + 페이지정보
		Page<Board> result = repo.findAll(pageable);
		
		System.out.println(result);
		
		//게시물 리스트
		List<Board> list = result.getContent();
		
		System.out.println(list);
	}
	
	@Test
	public void 정렬조건추가하기() {
		
		//no필드값을 기준으로 역정렬
		Sort sort = Sort.by("no").descending();
		
		//정렬정보 추가
		Pageable pageable = PageRequest.of(2,10,sort);
		Page<Board> result = repo.findAll(pageable);
		List<Board> list = result.getContent();
		System.out.println(list);
	}
}
