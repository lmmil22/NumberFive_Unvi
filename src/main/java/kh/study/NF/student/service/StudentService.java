package kh.study.NF.student.service;

import org.springframework.stereotype.Service;

import kh.study.NF.student.vo.StudentVO;

public interface StudentService {

	//by수경 휴학/복학/전과신청 시 학생정보 나타내는 테이블 데이터
	StudentVO studentInfo();
}
