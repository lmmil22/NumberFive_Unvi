/*
 * package kh.study.NF.professor.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.core.io.InputStreamResource; import
 * org.springframework.core.io.Resource; import
 * org.springframework.core.io.ResourceLoader; import
 * org.springframework.http.ContentDisposition; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import kh.study.NF.professor.service.ProfessorService; import
 * kh.study.NF.professor.vo.LecturePdfVO;
 * 
 * import java.io.File; import java.io.FileNotFoundException; import
 * java.io.IOException; import java.nio.file.Files; import java.nio.file.Path;
 * import java.nio.file.Paths;
 */
/*@Controller
@RequestMapping("/download")
public class PdfController {

    ResourceLoader resourceLoader;
	
	@javax.annotation.Resource(name = "professorService")
	private ProfessorService professorService;
	
    @Autowired
    public PdfController (ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping("/{fileName}")
    public ResponseEntity<Resource> resourceFileDownload(@PathVariable String fileName) {
    	LecturePdfVO attachedInfo = professorService.selectLecPdf(lecNo);
    	
    	try {
    		
            Resource resource = resourceLoader.getResource("D:\\workspaceSTS\\NumberFive_Unvi\\src\\main\\resources\\static\\pdf\\" + fileName);
            File file = resource.getFile();
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF.toString())
                    .body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
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
}*/