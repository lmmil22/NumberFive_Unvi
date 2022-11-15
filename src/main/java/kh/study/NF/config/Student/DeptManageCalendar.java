package kh.study.NF.config.Student;

import java.time.LocalDate;

//by수경 관리자가 학적관리에서 승인일자/날짜 조회 시 사용할 페이지 입니다.
public class DeptManageCalendar {

	//현재 날짜 구하기(승인일자)
	public static String nowDateToString() {
      LocalDate date = LocalDate.now();
    
      int year = date.getYear();
      int month = date.getMonthValue();
      int day = date.getDayOfMonth(); 
    
      String nowDate = year +"년" + month + "월" + day +"일";
			
		return nowDate;
	}
	
	//현재 날짜 구하기
	public static String nowDate() {
		LocalDate date = LocalDate.now();
		
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth(); 
		
		String nowDate = year + "-"  + month + "-" + day;
		
		return nowDate;
	}
	
	//한달 전 날짜 문자열 리턴
	public static String aMonthAgo() {
		
		LocalDate date = LocalDate.now();
		
		int year = date.minusMonths(1).getYear();
		int month = date.minusMonths(1).getMonthValue();
		int day = date.minusMonths(1).getDayOfMonth();
		
		String amonthAgo = year + "-"  + month + "-" + day;
		
		return amonthAgo;
	}
}
