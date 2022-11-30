package kh.study.NF.emp.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//by수경 학적신청 대비 승인 KPI 차트생성을 위하여 새롭게 만든 VO입니다.
@Getter
@Setter
@ToString
public class ChartVO {
	private int applyNoCnt;
	private int approvalDateCnt;
	private String day;
}
