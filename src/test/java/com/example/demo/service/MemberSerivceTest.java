package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.MemberDTO;

@SpringBootTest
public class MemberSerivceTest {
	
	@Autowired
	MemberService service;
	
	
	@Test
	public void 회원목록조회() {
		Page<MemberDTO> page = service.getList(1);
		List<MemberDTO> list= page.getContent();
		for (MemberDTO memberDTO : list) {
			System.out.println(memberDTO);
		}
	}
	
	@Test
	public void 회원등록() {
		MemberDTO dto = MemberDTO.builder()
						.id("user")
						.password("1234")
						.name("또치")
						.build();
		
		boolean result = service.register(dto);
		if(result == true) {
			
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
			
	}
	
	@Test
	public void 회원상세조회() {
		MemberDTO dto = service.read("user");
		
		System.out.println(dto);
	}
}
