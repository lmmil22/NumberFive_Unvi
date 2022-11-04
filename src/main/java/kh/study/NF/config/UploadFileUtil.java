package kh.study.NF.config;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kh.study.NF.professor.vo.LecturePdfVO;

//by 지아 //11/03
public class UploadFileUtil {
	private static final String UPLOAD_PATH = "D:\\workspaceSTS\\NumberFive_Unvi\\src\\main\\resources\\static\\pdf\\";

	public static LecturePdfVO uploadFile(MultipartFile PdfName) {
		System.out.println("!!!!!!!!!!!!!!!");
		String fileName = null; // 널값으로 초기화 해준다.
		String originFileName = null;

		// 넘어오는 파일이 있을때만 실행
		if (!PdfName.isEmpty()) {

			originFileName = PdfName.getOriginalFilename();

			// 파일 중복을 막기 위해 랜덤한 파일명을 문자열로 생성
			String uuid = UUID.randomUUID().toString();
			// 확장자 추출
			String extension = originFileName.substring(originFileName.lastIndexOf("."));

			// 첨부될 파일명 생성
			fileName = uuid + extension; // "asxvge" + ".jpg"

			// 예외처리
			try {
				File file = new File(UPLOAD_PATH + fileName);

				PdfName.transferTo(file);

			} catch (Exception e) { // 모든 예외가 발생하면 모두 처리해주겟다
				e.printStackTrace();
			}
		}

		LecturePdfVO lecturePdfVO = new LecturePdfVO();
		lecturePdfVO.setOriginPdfName(originFileName);
		lecturePdfVO.setAttachedPdfName(fileName);

		return lecturePdfVO;

	}
}