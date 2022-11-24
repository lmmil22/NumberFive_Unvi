package kh.study.NF.config;
// by 유빈
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kh.study.NF.board.vo.ImgVO;
public class BoardUploadFileUtil {
	// 내(유빈)컴퓨터 버전 경로
	private static final String UPLOAD_PATH = "C:\\workSpaceSTS_TEAM\\NumberFive_Unvi\\src\\main\\resources\\static\\images\\";
	
//------------------------------------------------------------------------------------------------------------------//
	//파일첨부
	// static : 메소드 사용하기위해 접근시 클래스명. 으로 접근하면 가능하다
	public static ImgVO uploadFile(MultipartFile mainImg) {
		
		// <첨부파일 문제점>
		// 하지만 첨부파일을 하지않고 상품등록시 오류가 발생한다.
		
		// 리턴값 인식되도록 위의 방식처럼 반복문 밖에 미리 리스트 선언해주고 리턴으로 던져준다.
		//첨부된 파일명+ 원본 파일명 생성 
		// : if문 밖에 선언해야 리턴가능하다.
		 String fileName = null;
		 String originalFilename = null;
		
		// 실제 첨부파일이 있을 때만 첨부 기능 실행
		// : 실제 첨부파일명이 비어있지않다면~
		if (!mainImg.isEmpty()) {
			//첨부하려는 원본 파일명
			 originalFilename = mainImg.getOriginalFilename();
			//파일이 첨부될 경로
			//찾는 방법: 프로젝트 오른쪽클릭하여 맨 아래 properties -> 계속 타고들어가서 복사하기 -> 마지막에 역슬러쉬\\ 두개 추가로 넣기
			//(if문밖에 선언함)String uploadPath = "D:\\worksapceSTS\\Shop\\src\\main\\resources\\static\\images\\";
			
			// 문제점 해결위한 방법) 랜덤 문자열을 생성해준다.
			// 파일명 중복을 막기위해 랜덤 파일명을 문자열로 생성 
			String uuid = UUID.randomUUID().toString();
			
			// <확장자추출>
			// :확장자파일명 마지막 확장자명만 뺀다. ex) .jpg 추출 
			// String extension = originalFilename.substring(3); // 원본파일명의 글자를 잘라서 4번째 글자부터 보여줘라.(0번째부터 시작함)
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".")); 
			
			// <참고>
			// : 매개변수에 들어온 문자가 몇번째 위치하는지 숫자로 알려줌
			// originalFilename.indexOf(".");//"abcde.jpg" -> 5(0부터시작)
			// originalFilename.lastIndexOf(".");//"abc.cvb.jpg" -> 여러개의 "." 중에서 가장 마지막을 기준으로함
			
			//첨부된 파일명 생성
		    fileName = uuid + extension;
			
			//첨부파일은 무조건 예외처리를 해야한다. 
			// : surrount with try/cath 클릭하여 자동생성
			try {
				
				//파일객체 생성
				//File file = new File("파일경로 + 파일명");
				// 문제점!
				// 이전 첨부한 파일을 다시 상품등록할 때 첨부하면 덮어쓰기가 되어 문제가 된다.
				// 그래서 위에서 원본파일명이 아닌 첨부된 파일명을 생성한 뒤, 넣어준다.
				// 상품등록 할 경우 -> 이번엔 랜덤한 문자열로 저장되어 이미지파일에 업로드된다.
				File file = new File(UPLOAD_PATH + fileName);

				mainImg.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//원본파일명과 첨부된파일명 둘다 한꺼번에 담아서 리턴으로 보내주도록하자.
		//-> 이 때 사용하는 것이 클래스(여러객체들 저장할때 사용하는!) 객체이다!
		//[ 리턴해야하는 데이터를 저장하기 위한 객체 ]
		ImgVO imgVO = new ImgVO();
		imgVO.setAttachedName(fileName);
		imgVO.setOriginName(originalFilename);
		imgVO.setIsMain("Y");//메인이미지는 메인값 Y 넣기
		
		return imgVO;
	}
//--------------------------------------------------------------------------------------------------//
		// 다중 파일첨부
		// static : 메소드 사용하기위해 접근시 클래스명. 으로 접근하면 가능하다
		public static List<ImgVO> MultiUploadFile(List<MultipartFile> subImgs) {
			// 리턴값 인식되도록 위의 방식처럼 반복문 밖에 미리 리스트 선언해주고 리턴으로 던져준다.
			List<ImgVO> list = new ArrayList<>();
			
			//첨부된 파일 갯수만큼 반복하여 첨부시작
			for(MultipartFile subImg : subImgs) {
				ImgVO vo = uploadFile(subImg);
				vo.setIsMain("N"); // 서브이미지는 메인이 아니므로 N 설정해주기
				list.add(vo);
			}
			return list;
		}
		
		
}
