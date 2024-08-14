package com.example.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.example.demo.vo.EmpVO;

@Component
public class EmpUtil {
	public String getHtmlEmp(EmpVO vo) {
		LocalDate now = LocalDate.now();
		int month = now.getMonth().getValue();
		String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String text = "<html>";
		text += "<h2>급여명세서</h2><hr>";
		text += vo.getEname()+"님의 "+month+"월달 급여 명세서를 전달드립니다.";
		text += "<br><br>";
		text += "<table border='1' width='80%'><thead><tr>";
		text += "<th>입사일</th>";
		text += "<th>직급</th>";
		text += "<th>계약월급</th>";
		text += "<th>커미션</th>";
		text += "<th>총 지급액</th>";
		text += "</tr></thead>";
		text += "<tbody><tr>";
		text += "<td>"+vo.getHiredate()+"</td>";
		text += "<td>"+vo.getJob()+"</td>";
		text += "<td>"+vo.getSalary()+"만원</td>";
		text += "<td>"+vo.getComm()+"만원</td>";
		text += "<td>"+(vo.getSalary()+vo.getComm())+"만원</td>";
		text += "</tr></tbody></table><hr>";
		text += "귀하의 노고에 감사드립니다.";
		text += "</html>";
		return text;
	}
}