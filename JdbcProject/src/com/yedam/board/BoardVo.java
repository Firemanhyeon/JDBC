package com.yedam.board;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVo {
	private int number;
	private String title;
	private String writer;
	private String content;
	private Date date;
	private int count;
}
