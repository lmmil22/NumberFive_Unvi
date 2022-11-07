package kh.study.NF.professor.controller;

import java.util.List;

import javax.annotation.Resource;

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
		return "redirect:/stu/main";
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
	
	
}
