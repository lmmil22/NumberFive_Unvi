package kh.study.NF.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.study.NF.board.service.BoardService;
import kh.study.NF.board.vo.BoardVO;
import kh.study.NF.board.vo.ImgVO;
import kh.study.NF.board.vo.SearchVO;
import kh.study.NF.config.BoardUploadFileUtil;
import kh.study.NF.config.MemRole;
import kh.study.NF.mail.service.MailService;
import kh.study.NF.member.service.MemberService;
import kh.study.NF.member.vo.MemberVO;

// by 유빈

@Controller
@RequestMapping("/member")
public class MemberController {
   
   @Resource(name = "memberService")
   private MemberService memberService;
   @Resource(name = "boardService")
   private BoardService boardService;
   @Autowired
   private MailService mailService;
   //암호화 작업 1-주석풀기 
   @Autowired
   private PasswordEncoder encoder;
//-------------------------------------------------------------------------------------------///   
   // 실제 홈화면(시큐리티 사용)
   @GetMapping("/homeLogin")
   public String homeLoginTest(MemberVO memberVO, boolean isLoginFail, Model model,BoardVO boardVO,String boardNo,SearchVO searchVO ) {
			System.out.println("SearchKeyword=" + boardVO.getSearchKeyword());
			System.out.println("searchValue=" + boardVO.getSearchValue());
			boardVO.setTotalDataCnt(boardService.selectBoardCnt(searchVO));
			System.out.println("_________________게시판 총 갯수 조회 쿼리문 실행성공_______________");
			boardVO.setPageInfo();
			System.out.println("_________________게시판 페이징 정보 실행 성공_______________");
			System.out.println("_____boardVO 추출_____" + boardVO);
			model.addAttribute("boardList",boardService.selectBoardList(boardVO));
			System.out.println("_________________게시판 목록 조회 성공_______________");
			
      return "content/common/home_Login"; 
      //by 유빈 :로그인페이지는 공통이라 common폴더 아래 login으로 파일 만들었음
      // 언니 수정부분 return "content/common/home_Login_test"; //by 유빈 :로그인페이지는 공통이라 common폴더 아래 login으로 파일 만들었음
      //by수경 최종 사용을 위하여 페이지 이름 변경 및 페이지 경로 수정(11/30)
   }
 //---------------------------------------------------------------------------------------------//   
   // 미사용 [홈화면 모달창] 회원가입
	/*
	 * @PostMapping("/join") public String join(@Valid MemberVO memberVO,
	 * BindingResult bindingResult, Model model) { // !! validation 체크 (데이터 유효성 검증)
	 * if (bindingResult.hasErrors()) {// 바인딩하는데 오류가 생겼니?: 결과는 true/false
	 * model.addAttribute("memberVO", memberVO);//회원가입 실패시 입력 데이터 값 유지. System.out.
	 * println("@@@@@@@@@@@@@@@@@@@@ 회원가입 유효성체크 >>> error발생   @@@@@@@@@@@@@@@@@@@");
	 * System.out.println("지금 바인딩 오류의 상태는? " + bindingResult.hasErrors());//true
	 * //어떤 오류인지 확인 List<ObjectError> list = bindingResult.getAllErrors();
	 * for(ObjectError e : list) { System.out.println(e.getDefaultMessage()); } } //
	 * 기본값을 student로 주기. memberVO.setMemRole(MemRole.STUDENT.toString());
	 * 
	 * //암호화 작업2 -주석풀기 memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
	 * 
	 * //회원가입 memberService.join(memberVO);
	 * 
	 * return "content/common/join_result"; }
	 */
//-------------------------------------------------------------------------------------------///   
   // by 유빈 : 학생정보시스템의 첫 화면 로그인 페이지입니다.
   // --> stu컨트롤러에서 학생,교수,교직원 모두 로그인해야해서 공통사항은 (common폴더)여기로 옮겼어!!)
   // 첫 화면 경로 : http://localhost:8081/member/homeLogin
	/* 미사용 페이지????
	 * @GetMapping("/homeLoginTest") public String homeLogin(MemberVO memberVO,
	 * boolean isLoginFail, Model model ) { //-----로그인 성공 및 실패 여부를 html에 데이터
	 * 전달하기-------// System.out.println("____________지금 로그인 실패니???_________" +
	 * isLoginFail); model.addAttribute("isLoginFail",isLoginFail); return
	 * "content/common/home_Login_Test"; //by 유빈 :로그인페이지는 공통이라 common폴더 아래 login으로
	 * 파일 만들었어!! }
	 */
   
//----------------[ 시큐리티 로그인 성공 실패 여부 결과 - 페이지이동 ]------------------------------------------///   
  
   //--로그인성공시 (시큐리티config 설정한 경로)
   @GetMapping("/loginResultSuccess")
   public String loginResultSuccess() {
      System.out.println("로그인 성공!!!!!!!!!!!!!");
      return "content/common/login_success";
   }   
   
   //--로그인실패시 (시큐리티config 설정한 경로)
   @GetMapping("/loginResultFail")
   public String loginResultFail() {
	   System.out.println("로그인 실패!!!!!!!!!!!!!!");
	   return "content/common/login_fail";
   }   
  
//-------------------------------------------------------------------------------------------///   
   // 이메일로 비밀번호 찾기 (ajax.ver) -> 모달 ajax사용 (ajaxlogin이라는 버튼을 클릭시)
   //@ResponseBody //ajax사용할때(단,리턴값은 필요한 데이터만! html페이지가 아님!)
   @PostMapping("/checkAndSendEmail")
   public String ajaxLogin( MemberVO memberVO ,String memEmail) {
      //비밀번호 찾기 쿼리문이용해서 회원존재확인하기.
      String loginInfo = memberService.selectIsValidMem(memberVO);
      System.out.println(loginInfo+"_________________<----- 로그인정보__________________");
      // 로그인 정보 시큐리티로(암호화) 이용
      if(loginInfo != null) {
         System.out.println("____________현재 조회된 정보가 있나?______________ "+ loginInfo);
         
         // 수신 대상을 담을 arrayList 생성
          ArrayList<String> toUerList = new ArrayList<>();
          //수신 대상 추가
          toUerList.add(memEmail);
          //메일 발송:비밀번호암호화까지 o
          mailService.sendMdail(memEmail,memberVO);
          System.out.println("________________ 이메일 발송 성공_________________");
          
          return "redirect:/member/homeLogin";//이메일로 임시비밀번호받고 다시 재로그인해야함
      } 
      System.out.println("  ________________  현재 조회된 정보가 있나?__________   " + loginInfo);//null
      return "redirect:/member/homeLogin";//login상태가 null일때, alert으로 올바르지않은 이메일과 학번/교번이라 뜨면서 다시 입력하세요라고한다ㅏ
   }
   
//-------------------------------------------------------------------------------------------///   
   // 비밀번호변경 페이지 (임시발급 비밀번호로 로그인후)
   @GetMapping("/updatePw")
   public String updatePwForm() {
	   System.out.println("비밀번호변경 페이지 이동 성공!!!!");
	   return "content/common/update_pw";
   }    
//-------------------------------------------------------------------------------------------///   
   //실제 비밀번호 변경 페이지 
   @PostMapping("/updatePw")
   public String updatePw(MemberVO memberVO, Authentication authentication) {
	   System.out.println("이메일로 발급된 임시비밀번호에서 다시 비밀번호수정해서 변경시키면 form태그로 넘어오기 성공!!!!");

	   User user = (User)authentication.getPrincipal();
	   memberVO.setMemNo(user.getUsername());
	   System.out.println("시큐리티 유저 아이디(학번/교번) 불러와서 memNo pk 넣어주기 null오류해결!!");
	   
	   memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
	   memberService.updatePw(memberVO);
	   System.out.println("<><><><><>암호화시켜서 비밀번호 업데이트되었는지 확인------->"+ encoder.encode(memberVO.getMemPw()));
	   
	   return "redirect:/member/homeLogin";
   }    
//-------------------------------------------------------------------------------------------///   
   // ajax로 이메일 임시비밀번호 발급 후 이동 페이지 ???
   @PostMapping("/afterLogin")
   public String afterLogin1(boolean isLoginFail, Model model) {
      //-----로그인 성공 및 실패 여부를 html에 데이터 전달하기-------//
      System.out.println("_______________로그인 성공시 false!!! -->" + isLoginFail);
      model.addAttribute("isLoginFail",isLoginFail);
      
      return "redirect:/member/homeLogin";
   }
//-------------------------------------------------------------------------------------------///   
   // 로그인 후 첫 화면 > 다시 홈화면
   @GetMapping("/afterLogin")
   public String afterLogin(boolean isLoginFail, Model model) {
      //-----로그인 성공 및 실패 여부를 html에 데이터 전달하기-------//
      System.out.println("_______________로그인 성공시 false!!! -->" + isLoginFail);
      model.addAttribute("isLoginFail",isLoginFail);
      
      return "redirect:/member/homeLogin";
   }
//----------------------------------------------------------------------------------------------//   
   // 미사용// 관리자 로그인 후 페이지 이동
   @GetMapping("/adminLogin")
   public String adminLogin(boolean isLoginFail) {
      //-----로그인 성공 및 실패 여부를 html에 데이터 전달하기-------//
      System.out.println("_______________로그인 성공시 false!!! -->" + isLoginFail);
      
      return "redirect:/member/homeLogin";
   }
//-------------------------------------------------------------------------------------------///   
   
   // 미사용 //로그인
   @PostMapping("/stuLogin")
   public String afterLogin2(boolean isLoginFail, Model model) {
      //-----로그인 성공 및 실패 여부를 html에 데이터 전달하기-------//
      //false면 로그인성공-fail이면 로그인 실패
      System.out.println("@@@@@@@@@@@@@@@@@@@" + isLoginFail);
      model.addAttribute("isLoginFail",isLoginFail);
      
      return "content/common/afterLogin";
   }
//-------------------------------------------------------------------------------------------//   
   //미사용..? 탑메뉴에서 나의 정보관리 클릭시,
	/*
	 * @GetMapping("/myinfo") public String myinfo(String memNo, Model
	 * model,Authentication authentication) { //시큐리티 사용했으므로 로그인한 유저의 정보를 가져온다. User
	 * user = (User)authentication.getPrincipal(); //로그인한 사람의 아이디,비밀번호,권한정보 데이터만
	 * 가져올수있다. System.out.println("id = " + user.getUsername());
	 * //System.out.println("pw = " + encoder.encode(user.getPassword()));
	 * 
	 * //회원상세정보조회 model.addAttribute("memInfo",
	 * memberService.selectMemberDetail(memNo));
	 * 
	 * return "content/common/my_info"; }
	 */
//-------------------------------------------------------------------------------------------//

   // 미사용 상세조회 후 회원정보수정
	/*
	 * @PostMapping("/updateMemInfo") public String updateMemInfo() { return
	 * "redirect:/member/myInfo"; }
	 */
/////////////////////////////////// [ 관리자 모드 ] //////////////////////////////////////////////////////
//-------------------------------------------------------------------------------------------//
   // [관리자모드] - 회원등록양식페이지이동
   @GetMapping("/regMemForm")
   public String regMemForm(MemberVO memberVO, boolean isLoginFail, Model model ) {
      //-----로그인 성공 및 실패 여부를 html에 데이터 전달하기-------//
      System.out.println("____________false 면, 로그인 성공!_________" + isLoginFail);
      model.addAttribute("isLoginFail",isLoginFail);
      return "content/admin/reg_mem";
   }
//-------------------------------------------------------------------------------------------//
   
   // [관리자모드] - 회원등록 실제 페이지 이동
   @PostMapping("/regMem")
   public String regMem(@Valid MemberVO memberVO, BindingResult bindingResult, Model model ) {
      
      //암호화 작업2 -주석풀기 
      memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
     
      //회원가입 
      memberService.join(memberVO);
      
      // !! validation 체크 (데이터 유효성 검증)
      if (bindingResult.hasErrors()) {// 바인딩하는데 오류가 생겼니?: 결과는 true/false
         
    	 model.addAttribute("memberVO", memberVO);//회원가입 실패시 입력 데이터 값 유지.
         System.out.println("@@@@@@@@@@@@@@@@@@@@ 회원가입 유효성체크 >>> error발생   @@@@@@@@@@@@@@@@@@@");
         System.out.println("지금 바인딩 오류의 상태는? " + bindingResult.hasErrors());//true
          
          //어떤 오류인지 확인
          List<ObjectError> list =  bindingResult.getAllErrors();
             
          for(ObjectError e : list) {
              System.out.println(e.getDefaultMessage());
        	}
             return"content/admin/reg_mem";
      }
      return "redirect:/member/afterRegMem";
   }   
//-------------------------------------------------------------------------------------------//
   // 미사용 //로그인 후 첫 화면 > 다시 홈화면
   @GetMapping("/afterRegMem")
   public String afterRegMem(boolean isLoginFail, Model model) {
      
      return "content/common/after_Reg_Mem";
   }
//-------------------------------------------------------------------------------------------//
   // (미사용)탑메뉴클릭시, '학교소개'에서 나오는 페이지로 사용??
   @GetMapping("/intro")
   public String intro() {
      return "content/common/intro";
   }
//-------------------------------------------------------------------------------------------//
   // 로그인 실패시(시큐리티 설정시 혹시몰라 주석처리함)
   /*
    * @GetMapping("/loginFail") public String loginFail( ) {
    * 
    * return "content/member/login"; }
    */
//-------------------------------------------------------------------------------------------//
   
   // 접근거부시(시큐리티 설정시 혹시몰라 주석처리함)
   /*
    * @GetMapping("/accessDenied") public String accessDenied( ) {
    * 
    * return "content/member/accessDenied"; }
    */
}