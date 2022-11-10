package kh.study.NF.professor.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.InputStreamResource;
import javax.annotation.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.study.NF.config.UploadFileUtil;
import kh.study.NF.dept.service.DeptService;
import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.professor.service.ProfessorService;
import kh.study.NF.professor.vo.EnrollmentVO;
import kh.study.NF.professor.vo.LecturePdfVO;
import kh.study.NF.professor.vo.LectureTimeVO;
import kh.study.NF.professor.vo.LectureVO;

//by지아
@Controller
@RequestMapping("/proF")
public class ProfessorController {

	@Resource(name = "deptService")
	private DeptService deptService;
	
	@Resource(name = "professorService")
	private ProfessorService professorService;
	
	
	//강의 등록 페이지로 이동 
	@GetMapping("/regProfLec")
	public String regProfLecture(Model model) {
		//by지아
		
		 model.addAttribute("deptList",deptService.selectDeptList());
		 model.addAttribute("collList",deptService.selectCollList());
		model.addAttribute("semesterList",professorService.selectSemesterList());
		model.addAttribute("empList",professorService.selectEmpList());
		
		//return "content/professor/professorRegLecture";
		return "content/professor/regproFLecture";
	}
	//by지아
	//coll 목록을 누르면 dept 가 변경된다 
	@ResponseBody
	@PostMapping("/getDeptListAjax")
	public List<DeptVO> getDeptListAjax(String collNo, Model model) {
		//System.out.println("!!!!!!");
		List<DeptVO> list = deptService.getDeptList(collNo);
		
		return list;
	}
	
	//강의등록페이지에서 강의등록을 눌렀을 때 //by지아
	@PostMapping("/regProfLecForm")
	public String regProfLecForm(LecturePdfVO lecturePdfVO,
			LectureTimeVO lectureTimeVO ,LectureVO lectureVO ,MultipartFile PdfName ) {
		String nextLecNo = professorService.getNextLecNo();
		lectureVO.setLecNo(nextLecNo);
		
		LecturePdfVO uploadPdf =UploadFileUtil.uploadFile(PdfName);
		
		lecturePdfVO.setAttachedPdfName(uploadPdf.getAttachedPdfName());
		lecturePdfVO.setOriginPdfName(uploadPdf.getOriginPdfName());
		lecturePdfVO.setLecNo(nextLecNo);
		lectureTimeVO.setLecNo(nextLecNo);
		
		professorService.insertLecture(lectureVO, lecturePdfVO, lectureTimeVO);
		
		//lec 는 nextNo로 셀렉트해서 가져와야한다 
		//return "redirect:/stu/main";
		return "redirect:/proF/viewLecList";
	}
	
	//강의 등록후 리스트
	@GetMapping("/viewLecList")
	public String lectureList(Model model) {
		
		model.addAttribute( "lecList", professorService.selectLecList());
		
		
		return "content/professor/lectureList";
	}
	
	//ajax로 이동해서 강의수정목록을 상세를 띄움
	@ResponseBody
	@PostMapping("/lecListAjax")
	public LectureVO lecListAjax(String lecNo , Model model) {
		
		LectureVO list=  professorService.selectLecture(lecNo);
		
		return list;
	}
	
	//강의 수정 버튼 클릭 시
	@PostMapping("/updateLec")
	public String updateLec(LectureVO lectureVO , LectureTimeVO lectureTimeVO) {
		
		
		professorService.updateLec(lectureVO ,lectureTimeVO );
		
		return "redirect:/proF/viewLecList";
	}
	
	//강의 삭제 버튼 클릭 시
	@ResponseBody
	@PostMapping("/deleteLecAjax")
	public void deleteLecAjax(String lecNo) {
		
		professorService.deleteLec(lecNo);
		
		//ajax와 redirect 를 같이 사용할 수 없다 
	}
	
	//강의 이름 클릭시 다운진행됨 
	//다운로드
	@GetMapping("/download")
	public ResponseEntity<Object> download(String lecNo) {
		LecturePdfVO attachedInfo = professorService.selectLecPdf(lecNo);
		String path = "D:\\workspaceSTS\\NumberFive_Unvi\\src\\main\\resources\\static\\pdf\\" + attachedInfo.getAttachedPdfName();
		
		
		//디비로 조회해온 pdf원본 파일명 이름을 가져온다
		
		try {
			Path filePath = Paths.get(path);
			org.springframework.core.io.Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
			
			File file = new File(path);
			
			HttpHeaders headers = new HttpHeaders();
			System.out.println(attachedInfo.getOriginPdfName()+"!!!!!!");
			
			String downloadName =  new String(attachedInfo.getOriginPdfName().getBytes("UTF-8"), "ISO-8859-1");
			
//			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(downloadName).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
//			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
	
	/*//교수가 강의 이름 눌렀을 때 보여주는 pdf (다운x)
	@PostMapping("/viewLecPdf")
	public ResponseEntity<Object> viewLecPdf(String lecNo) {
		LecturePdfVO attachedInfo = professorService.selectLecPdf(lecNo);
		String path = "D:\\workspaceSTS\\NumberFive_Unvi\\src\\main\\resources\\static\\pdf\\" + attachedInfo.getAttachedPdfName();
		
		try {
			Path filePath = Paths.get(path);
			org.springframework.core.io.Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
			
			File file = new File(path);
			
			HttpHeaders headers = new HttpHeaders();
			System.out.println(attachedInfo.getOriginPdfName()+"!!!!!!");
			
			String downloadName =  new String(attachedInfo.getOriginPdfName().getBytes("UTF-8"), "ISO-8859-1");
			
//			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(downloadName).build());  
			
			return ResponseEntity.ok()
			        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + downloadName + "\"")
			        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(downloadName.length()))
			        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF.toString())
			        .body(resource);
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
		*/
		
	//}
	
	
	//수강신청시 진행 
	@GetMapping("/enrollList")
	public String enrollList(Model model, EnrollmentVO enrollmentVO) {
		//로그인 임시 코드, 로그인 구현 시 소스 변경 필요
		enrollmentVO.setStuNo("STU_001");
		
		//이미 수강신청한 lec_no 목록 조회
		List<String> emrolledList = professorService.selectEnrollmentLecNoList(enrollmentVO.getStuNo());
		
		//이미 수강신청한 lec_no 목록을 쿼리 실행시 전달
		enrollmentVO.setEmrolledList(emrolledList);
		
		//수강 신청 가능한 목록 리스트
		model.addAttribute( "lecList",professorService.selectLecListEnroll(enrollmentVO));
		//학생이 신청한 수강목록
		model.addAttribute( "enrollList",professorService.selectStuLectureList(enrollmentVO));
		
		return "content/professor/enrollClassList";
		
	}
	
	@ResponseBody
	@PostMapping("/insertEnrollAjax")
	public void insertEnrollAjax(EnrollmentVO enrollmentVO , String lecNo) {
		
		
		
		professorService.insertEnroll(enrollmentVO, lecNo);
		
		
		//return "";
	}
	
	
	
}
