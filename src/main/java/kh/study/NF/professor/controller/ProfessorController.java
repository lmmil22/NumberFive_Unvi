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
	
	//강의 이름 클릭시 ekdns진행됨 
	
	@GetMapping("/download")
	public ResponseEntity<Object> download() {
		String path = "D:\\workspaceSTS\\NumberFive_Unvi\\src\\main\\resources\\static\\pdf\\";
		
		//디비로 조회해온 pdf원본 파일명 이름을 가져온다
		
		try {
			Path filePath = Paths.get(path);
			org.springframework.core.io.Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
			
			File file = new File(path);
			
			HttpHeaders headers = new HttpHeaders();
//			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename("test.pdf").build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
	
	
	
	
}
