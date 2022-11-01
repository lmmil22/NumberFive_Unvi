package kh.study.NF.professor.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LecturePdfVO {
	private String pdfNo;
	private String originPdfName;
	private String attachedPdfName;
	private String lecNo;
}
