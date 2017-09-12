package zarazio.travel.android;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import zarazio.travel.android.bean.ARFilterDTo;
import zarazio.travel.android.bean.StepLogDTO;
import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;
import zarazio.travel.android.service.StepService;
import zarazio.travel.android.service.boardService;

@Controller
public class StepLogController {


	@Inject
	private StepService service;

	@Inject
	private boardService boardservice;
	
	@RequestMapping(value="/stepinsert")
	@ResponseBody
	public String stepInsert(String user_id) throws Exception {
		
		System.out.println("dd");
		service.StepInsert(user_id);
		
		return "success";
		
	}
	
	@RequestMapping(value="/stepCodeSelect")
	@ResponseBody
	public String codeSelect(String user_id) throws Exception {
		
		System.out.println("dd");
		return service.stepCodeSelect(user_id)+"";
		
	}
	
	@RequestMapping(value="/stepdelete")
	@ResponseBody
	public String stepDelete(String user_id) throws Exception {
		
		int board_code = service.stepCodeSelect(user_id);
		
		System.out.println(board_code);
		service.StepLogDelete(board_code);
		service.StepDelete(board_code);
		
		return "success";
		
	}
	@RequestMapping(value="/step_log_select")
	public ResponseEntity<String> StepLog(String step_log_code) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		List<boardLIstDTO> List = null;
		
		List = service.stepList(step_log_code);
		
		Gson gson = new Gson();
		String data =  gson.toJson(List);

		System.out.println(data);
		return new ResponseEntity<String>(data,resHeaders,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/stepUpdate")
	@ResponseBody
	public ResponseEntity<String> inserLog(HttpServletRequest request) throws UnsupportedEncodingException{

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=EUC_KR");
		
		int share_Type = 0;
		String step_title = null;
		String user_id = null;
	    String originalName = null;
	    int file_Type = 3;
		HttpStatus a=HttpStatus.BAD_REQUEST;
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items= null;
		
		try {
			items = upload.parseRequest(request);
			a = HttpStatus.OK;
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		Iterator itr = null;
		if(items!=null){
		 itr= items.iterator();
		}
		while (itr.hasNext()) {
	        FileItem item = (FileItem) itr.next();
	        System.out.println(item.isFormField());
	        if (item.isFormField()) { // 파일이 아닌 폼필드에 입력한 내용을 가져옴.
	          if(item!=null && item.getFieldName().equals("step_Title")) {
	        	  step_title = item.getString("EUC_KR");//form field 안에 입력한 데이터를 가져옴
	        	  System.out.println("로그 제목:"+step_title+"<br>"); 
		      }else if(item!=null && item.getFieldName().equals("share_type")) {
		    	  share_Type = Integer.parseInt(item.getString("EUC_KR"));
		          System.out.println("공유 여부 :"+share_Type+"<br>");
		      }else if(item!=null && item.getFieldName().equals("user_id")) {
		    	  user_id = item.getString("EUC_KR");
		          System.out.println("공유 여부 :"+user_id+"<br>");
		      }
	        }
	        else{ // 폼 필드가 아니고 파일인 경우
	            try {
	                String itemName = item.getName();//로컬 시스템 상의 파일경로 및 파일 이름 포함
	                if(itemName==null || itemName.equals("") ) continue;
	                String fileName = FilenameUtils.getName(itemName);// 경로없이 파일이름만 추출함
	                if(fileName.equals("null")) continue;
	                // 전송된 파일을 서버에 저장하기 위한 절차
	                //String rootPath = getServletContext().getRealPath("/");
	                originalName = System.currentTimeMillis()+"Travel_log_"+fileName;
	                File savedFile = new File("C:/Returns/src/main/webapp/resources/upload/step_Log/"+ originalName);

	                item.write(savedFile);// 지정 경로에 파일을 저장함
	                System.out.println("<tr><td><b>파일저장 경로:</b></td></tr><tr><td><b>"+savedFile+"</td></tr>");
	                System.out.println("<tr><td><b><a href=\"DownloadServlet?file="+fileName+"\">"+originalName+"</a></td></tr>");
	             } catch (Exception e) {
	            	 System.out.println("서버에 파일 저장중 에러: "+e);
	               }
	        }
	    }

		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StepLogDTO step_log = new StepLogDTO();
       	
       	step_log.setBoard_title(step_title);
        step_log.setShare_type(share_Type);
        step_log.setUser_id(user_id);
        step_log.setFile_content(originalName);
        
        try {

			int step_code = service.stepCodeSelect(user_id);
			System.out.println(step_code);
			step_log.setBoard_code(step_code);
			service.StepFileInsert(step_log);
			
			attachedFileDTO file = new attachedFileDTO();
	        file.setBoard_code(step_code);
	        file.setFile_content(originalName);
	        file.setFile_type(file_Type);
	        
	        boardservice.insertFile(file);
	        
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return new ResponseEntity<String>("success", resHeaders, a);
	}

}