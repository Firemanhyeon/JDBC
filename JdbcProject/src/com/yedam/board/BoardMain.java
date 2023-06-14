package com.yedam.board;

import java.util.List;
import java.util.Scanner;

public class BoardMain {

	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		while(true) {
			System.out.println("1.글등록 2.삭제 3.글내용수정 4. 글목록보기 5.상세보기 6.종료");
			System.out.print("선택> ");
			menu = Integer.parseInt(sc.nextLine());
			if(menu ==1 ) {
				System.out.print("제목을 입력하세요> ");
				String title = sc.nextLine();
				System.out.print("작성자를 입력하세요> ");
				String writer = sc.nextLine();
				System.out.print("내용을 입력하세요> ");
				String content = sc.nextLine();
				BoardVo user = new BoardVo();
				user.setTitle(title);
				user.setWriter(writer);
				user.setContent(content);
				if(dao.add(user)) {
					System.out.println("등록완료");
				}
				else {
					System.out.println("등록실패");
				}
			}
			else if(menu ==2 ) {
				System.out.println("글 번호를 입력하세요");
				int num = Integer.parseInt(sc.nextLine());
				if(dao.delete(num)) {
					System.out.println("정상 삭제 되었습니다");
				}
				else {
					System.out.println("번호가 일치하지않습니다");
				}
				
			}
			else if(menu ==3) {
				System.out.println("수정할 글번호를 입력하세요");
				int id = Integer.parseInt(sc.nextLine());
				System.out.println("수정할 내용을 입력하세요");
				String content = sc.nextLine();
				BoardVo user = new BoardVo();
				user.setNumber(id);
				user.setContent(content);
				
				if(dao.modify(user)) {
					System.out.println("정상수정되었습니다");
				}
				else {
					System.out.println("수정할 대상이 없습니다");
				}
			}
			else if( menu ==4) {
				List<BoardVo>list = dao.list();
				if(list.size()==0) {
					System.out.println("조회결과가 없습니다");
				}
				else {
					for(BoardVo user1 : list) {
						System.out.println(user1);
					}
				}
			}
			else if(menu==5) {
				System.out.println("상세보기할 번호입력하세요");
				int num = Integer.parseInt(sc.nextLine());
				System.out.println(dao.search(num));
			}
			else if(menu ==6) {
				break;
			}
		}
	}

}
