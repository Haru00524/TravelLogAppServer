package zarazio.travel.android;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import zarazio.travel.android.bean.Friend;
import zarazio.travel.android.bean.Member;
import zarazio.travel.android.bean.TravelStory;
import zarazio.travel.android.dao.MemberDAO;
import zarazio.travel.android.service.MemberService;
import zarazio.travel.android.service.TravelStoryService;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@Inject
	private MemberService service;

	@Inject
	private JavaMailSender mailSender;
	@Inject
	private TravelStoryService mservice;

	@RequestMapping(value="/expensive")
	public void expenseInsert(HttpServletRequest request, TravelStory travelstory) throws Exception {
		System.out.println("ddsadas");
		mservice.expenseInsert(travelstory);
		travelstory.setExpense_Code(mservice.fineMaxExpenseCode());
		// System.out.println("그룹코드 : "+travelstory.getGroup_Code());
		// System.out.println("사용자아이디 : "+travelstory.getUser_id());
		// System.out.println("지출코드 : "+travelstory.getExpense_Code());
		mservice.expenseInsertTravel(travelstory);

	}
	
	@RequestMapping(value="/register")
	public void androidTest2(HttpServletRequest request, Member member) throws Exception {

		service.insert(member);

	}
	
	@RequestMapping("firend")
	public ResponseEntity<String> friendState(Friend friend) throws Exception{
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=EUC_KR");

		System.out.println(friend.getFriend_id() +" / " + friend.getUser_id());
		Friend result = new Friend();
		result = service.friendState(friend);

		Gson gson = new Gson();
		String data =  gson.toJson(result);
		System.out.println(data);
		return new ResponseEntity<String>(data,resHeaders,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/firendADD")
	public ResponseEntity<String> friend(Friend friend) throws Exception{
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=EUC_KR");
		
		System.out.println(friend.getFriend_State());
		if(friend.getFriend_State().equals("친구신청")){
			service.friendADD(friend);
		}else if(friend.getFriend_State().equals("친구하기")){
			service.friendUpdate(friend);
		}else if(friend.getFriend_State().equals("친구")){
			service.friendDelete(friend);
		}else if(friend.getFriend_State().equals("친구신청중")){
			service.friendDelete(friend);
		}
		return new ResponseEntity<String>("success",resHeaders,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/idCheck")
	@ResponseBody
	public String androidIdCheck(HttpServletRequest request, Member member) throws Exception {

		String count = service.idCheck(member) + "";
		System.out.println(count);
		return count;
	}

	@RequestMapping("change_profile")
	public ResponseEntity<String> changeProfile(HttpServletRequest request) throws Exception {
		System.out.println("들어옴");
		
		String result="Failed";
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=EUC_KR");
		
		String this_id = null;
		String c_user_id = null;
		String c_user_email = null;
		String c_user_phone = null;
		String c_user_gender = null;
		String originalName = "";
		
		HttpStatus a=HttpStatus.BAD_REQUEST;
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items= null;
		
		try {
			items = upload.parseRequest(request);
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
	          if(item!=null && item.getFieldName().equals("this_id")) {
	        	  this_id = item.getString("EUC_KR");//form field 안에 입력한 데이터를 가져옴
	        	  System.out.println("로그 제목:"+this_id+"<br>"); 
	          }else if(item!=null && item.getFieldName().equals("c_user_id")) {
	        	  c_user_id = item.getString("EUC_KR");
	        	  System.out.println("로그 내용:"+c_user_id+"<br>");
	          }else if(item!=null && item.getFieldName().equals("c_user_email")) {
	        	  c_user_email = item.getString("EUC_KR");
		          System.out.println("해시태그 내용:"+c_user_email+"<br>");
		      }else if(item!=null && item.getFieldName().equals("c_user_phone")) {
		    	  c_user_phone = item.getString("EUC_KR");
		          System.out.println("경도 내용:"+c_user_phone+"<br>");
		      }else if(item!=null && item.getFieldName().equals("c_user_gender")) {
		    	  c_user_gender = item.getString("EUC_KR");
		          System.out.println("위도 내용:"+c_user_gender+"<br>");
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
	                originalName = System.currentTimeMillis()+"Travel_log_";
	                File savedFile = new File("C:/Returns/src/main/webapp/resources/upload/logs/"+ originalName+fileName);

	                item.write(savedFile);// 지정 경로에 파일을 저장함
	        		String uploadedFileName = null;
	        		 // 이미지 파일은 썸네일 사용
	        			// 썸네일생성 
	        		uploadedFileName = makeThumbnail("C:/Returns/src/main/webapp/resources/upload/logs/", originalName+fileName);
	                originalName += fileName;
	                System.out.println("<tr><td><b>파일저장 경로:</b></td></tr><tr><td><b>"+savedFile+"</td></tr>");
	                System.out.println("<tr><td><b><a href=\"DownloadServlet?file="+fileName+"\">"+originalName+"</a></td></tr>");
	             } catch (Exception e) {
	            	 System.out.println("서버에 파일 저장중 에러: "+e);
	               }
	        }
	    }
	    
	    Member member = new Member();
	    if(this_id != null)
	    member.setThis_id(this_id);
	    if(c_user_email != null)
	    member.setUser_email(c_user_email);
	    if(c_user_gender != null)
	    member.setUser_gender(c_user_gender);
	    if(c_user_phone != null)
	    member.setUser_phone(c_user_phone);
	    member.setUser_id(c_user_id);
	    
		String count = service.idCheck(member) + "";
		/*if(count.equals("1")){
			result = "idcheck";
			a = HttpStatus.CREATED;
		}else if(!count.equals("1")){
			service.user_update(member);
			a = HttpStatus.OK;
		}else{
			result = "Failed";
			a = HttpStatus.BAD_REQUEST;
		}*/

		return new ResponseEntity<String>("success", resHeaders, a);
	}
	
	@RequestMapping("change_pass")
	@ResponseBody
	public String changePassWord(HttpServletRequest request ,Member member) throws Exception {
		
		String result="Failed";
		service.passUpdate(member);
		result="success";
		
		return result;
	}
	
	@RequestMapping("login")
	@ResponseBody
	public ResponseEntity<String> androidLogin(HttpServletRequest request, Member member) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		Member List = new Member();
		List = service.loginCheck(member);
		
		Gson gson = new Gson();
		String data =  gson.toJson(List);
		

		System.out.println(request.getParameter("user_id"));
		System.out.println(request.getParameter("user_pass"));


		return new ResponseEntity<String>(data,resHeaders,HttpStatus.CREATED);
	}

	@RequestMapping("findId")
	@ResponseBody
	public String idFind(Member member) throws Exception {

		String user_id = service.idFind(member);
		if (user_id == null) {
			user_id = "NOT FOUND";
		}
		return user_id;
	}

	@RequestMapping("findPass")
	@ResponseBody
	public String passFind(Member member) throws Exception {

		System.out.println(member.getUser_id());
		Member mem = service.passFind(member);
		String result = "FAILED";

		if (mem != null) {
			result = "SUCCESS";
			
			String buf = getRandomPassword(15);
			
			Member mem2 = new Member();
			
			mem2.setUser_id(member.getUser_id());
			mem2.setUser_pass(buf);
			service.lostpass(mem2);
			// mailSending 코드

			String setfrom = "travellogproject@gmail.com";
			String tomail = mem.getUser_email(); // 받는 사람 이메일
			String title = "[Travel_log] 비밀번호 찾기 문의"; // 보내는 사람 이메일
			String content = "임시 비밀번호는 "+buf + "입니다. 로그인시 비밀번호를 바꿔주세요"; // 보내는 사람 이메일
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(tomail); // 받는사람 이메일
				messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
				messageHelper.setText(content); // 메일 내용
				mailSender.send(message);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return result;
	}
	public String getRandomPassword( int length ){
        char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();
        Random rn = new Random();
        for( int i = 0 ; i < length ; i++ ){
            sb.append( charaters[ rn.nextInt( charaters.length ) ] );
        }
        return sb.toString();
    }
	// 썸네일 생성 
			private static String makeThumbnail(String uploadPath, String fileName) throws Exception{
				// 이미지를 읽어들이기 위한 버퍼
				BufferedImage sourceImg = 
						ImageIO.read(new File(uploadPath, fileName));
				// 100 픽셀단위 썸네일 생성
				BufferedImage destImg = 
						Scalr.resize(sourceImg, 600, null, null);
//						Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
				// 썸네일의 이름생성 "s_"를 붙임
				String thumbnailName = 
						uploadPath +"s_"+ fileName;
				File newFile = new File(thumbnailName);
				// 썸네일 생성
				ImageIO.write(destImg, "jpg", newFile);
				
				// 썸네일의 이름을 리턴 
				return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
			}
}
