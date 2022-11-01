package kh.study.NF.professor.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LecturePdfVO {
	//by지아 LecturePdfVO table로 만든 VO
	private String pdfNo;
	private String originPdfName;
	private String attachedPdfName;
	private String lecNo;
}
