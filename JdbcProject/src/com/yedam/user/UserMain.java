package com.yedam.user;

import java.util.List;
import java.util.Scanner;

public class UserMain {

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		

		while (true) {
			System.out.println("1.추가 2.조회 3.수정 4.삭제 5.목록 6.종료");
			System.out.print("선택> ");
			menu = Integer.parseInt(sc.nextLine());
			if (menu == 1) {
				System.out.print("id> ");
				String id = sc.nextLine();
				System.out.print("pw> ");
				String pw = sc.nextLine();
				System.out.print("name> ");
				String name = sc.nextLine();
				UserVo user = new UserVo();
				user.setUserId(id);
				user.setUserPw(pw);
				user.setUserName(name);
				if (dao.add(user)) {
					System.out.println("처리성공");
				} else {
					System.out.println("처리실패");
				}
			} 
			else if (menu == 2) {
				System.out.print("id> ");
				String id = sc.nextLine();
				UserVo user = dao.search(id);
				if(user==null) {
					System.out.println("조회된정보가없습니다");
				}
				else {
					System.out.println(user.toString());
				}
			} 
			else if (menu == 3) {//수정
				System.out.print("id> ");
				String id = sc.nextLine();
				System.out.print("addrress> ");
				String addrress = sc.nextLine();
				System.out.print("phone> ");
				String phone = sc.nextLine();
				UserVo user = new UserVo();
				user.setUserId(id);
				user.setUserAddr(addrress);
				user.setUserPhone(phone);
				
				if(dao.modify(user)) {
					System.out.println("정상수정");
				} else {
					System.out.println("수정할대상이없습니다");
				}
			} 
			else if (menu == 4) {
				System.out.print("id> ");
				String id = sc.nextLine();
				
				if(dao.delete(id)){
					System.out.println("정상삭제");
				}
				else {
					System.out.println("삭제할대상없음");
				}
				
				
			} 
			else if (menu == 5) {
				List<UserVo>list = dao.list();
				
				if(list.size()==0) {
					System.out.println("조회결과가 없습니다");
				}else {
					for(UserVo user4:list) {
						System.out.println(user4);
					}
				}
			}
			else if(menu==6) {
				break;
			}
		}
		System.out.println("end of prog.");
	}

}
