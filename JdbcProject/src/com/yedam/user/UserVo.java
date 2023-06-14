package com.yedam.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//게터세터 , 생성자 , 등등 전부 들어가잇음
@AllArgsConstructor//필드에있는생성자 모두 생성해줌(기본생성자는 없음)
@NoArgsConstructor//기본생성자생성
public class UserVo {
	private String userId;
	private String userPw;
	private String userName;
	private String UserBirth;
	private String UserPhone;
	private String UserAddr;
	
}
