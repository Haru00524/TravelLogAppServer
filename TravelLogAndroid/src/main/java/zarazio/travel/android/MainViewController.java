package zarazio.travel.android;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;
import zarazio.travel.android.service.ListViewService;
import zarazio.travel.android.service.StepService;
import zarazio.travel.android.service.boardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainViewController {

	@Inject
	private ListViewService service;
	@Inject
	private StepService stepService;
	@Inject
	private boardService bservice;

	@RequestMapping(value = "/main_View_DB")
	public ResponseEntity<String> mainViewDB(String user_id) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<boardLIstDTO> mainDB = service.mainList(user_id);

		Gson gson = new Gson();
		String data = gson.toJson(mainDB);

		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);

	}

	@RequestMapping("all_list_View")
	@ResponseBody
	public ResponseEntity<String> allListView() throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<boardLIstDTO> search = service.serachList();

		Gson gson = new Gson();

		String data = gson.toJson(search);

		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);
	}

	@RequestMapping("search_View")
	@ResponseBody
	public ResponseEntity<String> searchListView(String hash_tag) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<boardLIstDTO> search = service.serachListHashList(hash_tag);

		Gson gson = new Gson();

		String data = gson.toJson(search);
		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping("myLog")
	@ResponseBody
	public ResponseEntity<String> myLogList(String user_id) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<boardLIstDTO> search = service.myLogs(user_id);

		Gson gson = new Gson();

		String data = gson.toJson(search);
		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);
	}
	@RequestMapping("myLikeBoard")
	@ResponseBody
	public ResponseEntity<String> myLikeBoard(String user_id) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<boardLIstDTO> search = service.MyLikeboard(user_id);

		Gson gson = new Gson();

		String data = gson.toJson(search);
		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping("mypageCount")
	@ResponseBody
	public ResponseEntity<String> mypageCount(String user_id) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<boardLIstDTO> search = service.myLogs(user_id);

		Gson gson = new Gson();

		String data = gson.toJson(search);
		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);
	}
	@RequestMapping("like")
	public ResponseEntity<String> boardLike(boardDTO board) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		System.out.println(board.getUser_id());
		System.out.println("라이크 누름");
		service.boardLike(board);
		return new ResponseEntity<String>("success", resHeaders, HttpStatus.CREATED);
	}
	@RequestMapping("count_profile")
	public ResponseEntity<String> countProfile(String user_id) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		String data = service.countProfile(user_id);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);
	}
	@RequestMapping("likeDelete")
	public ResponseEntity<String> LikeDelete(boardDTO board) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		System.out.println(board.getUser_id());
		service.likedelete(board);
		System.out.println("라이크 지움");
		return new ResponseEntity<String>("success", resHeaders, HttpStatus.CREATED);
	}

	@RequestMapping("writeComment")
	public ResponseEntity<String> boardComment(boardDTO board) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		board.setShare_type(1);
		board.setBoard_type_code(6);
		board.setWrite_type(1);
		service.commentWrite(board);

		return new ResponseEntity<String>("success", resHeaders, HttpStatus.CREATED);
	}

	@RequestMapping("writeReplyList")
	public ResponseEntity<String> replyList(String reply_code) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<boardDTO> replyList = service.replyList(reply_code);

		Gson gson = new Gson();
		String data = gson.toJson(replyList);

		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);

	}

	@RequestMapping("hashTagList")
	public ResponseEntity<String> hashTagList(String board_code) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<hashTagDTO> hashTagList = service.hashTagSel(Integer.parseInt(board_code));

		Gson gson = new Gson();
		String data = gson.toJson(hashTagList);

		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);

	}

	@RequestMapping("liketure")
	public ResponseEntity<String> liketure(boardDTO board) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		String data = service.liketure(board) + "";

		if (!data.equals("-1")) {
			data = "1";
			System.out.println("라이크함");
		} else {
			data = "-1";
			System.out.println("라이크안함");
		}

		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);
	}

	@RequestMapping("deleteBoard")
	public ResponseEntity<String> board_delete(boardDTO board) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/String;charset=UTF-8");

		service.deleteLog(board);

		return new ResponseEntity<String>("삭제성공", resHeaders, HttpStatus.CREATED);
	}

	@RequestMapping("updateBoard")
	public ResponseEntity<String> board_update(HttpServletRequest request) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/String;charset=UTF-8");
		int board_Type_Code = 0;
		int share_Type = 0;
		String board_Title = null;
		String board_Content = null;
		String hash_Tag = null;
		double log_longtitude = 0;
		double log_latitude = 0;
		String user_id = null;
		String originalName = "";
		int file_Type = 0;
		int step_code = 0;
		int write_type = 1;
		String board_code = null;

		HttpStatus a = HttpStatus.BAD_REQUEST;

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = null;

		try {
			items = upload.parseRequest(request);
			a = HttpStatus.OK;
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		Iterator itr = null;
		if (items != null) {
			itr = items.iterator();
		}
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			System.out.println(item.isFormField());
			if (item.isFormField()) { // 파일이 아닌 폼필드에 입력한 내용을 가져옴.

				if (item != null && item.getFieldName().equals("board_code")) {
					board_code = item.getString("EUC_KR");// form field 안에 입력한
															// 데이터를 가져옴
					System.out.println("로그 제목:" + board_code + "<br>");
				} else if (item != null && item.getFieldName().equals("log_Title")) {
					board_Title = item.getString("EUC_KR");// form field 안에 입력한
															// 데이터를 가져옴
					System.out.println("로그 제목:" + board_Title + "<br>");
				} else if (item != null && item.getFieldName().equals("log_Content")) {
					board_Content = item.getString("EUC_KR");
					System.out.println("로그 내용:" + board_Content + "<br>");
				} else if (item != null && item.getFieldName().equals("hash_tag")) {
					hash_Tag = item.getString("EUC_KR");
					System.out.println("해시태그 내용:" + hash_Tag + "<br>");
				} else if (item != null && item.getFieldName().equals("share_type")) {
					share_Type = Integer.parseInt(item.getString("EUC_KR"));
					System.out.println("공유 여부 :" + share_Type + "<br>");
				} else if (item != null && item.getFieldName().equals("file_Type")) {
					file_Type = Integer.parseInt(item.getString("EUC_KR"));
					System.out.println("파일 타입:" + file_Type + "<br>");
				}
			} else { // 폼 필드가 아니고 파일인 경우
				try {
					String itemName = item.getName();// 로컬 시스템 상의 파일경로 및 파일 이름
														// 포함
					if (itemName == null || itemName.equals(""))
						continue;
					String fileName = FilenameUtils.getName(itemName);// 경로없이
																		// 파일이름만
																		// 추출함
					if (fileName.equals("null"))
						continue;
					// 전송된 파일을 서버에 저장하기 위한 절차
					// String rootPath = getServletContext().getRealPath("/");
					originalName = System.currentTimeMillis() + "Travel_log_";
					File savedFile = new File(
							"C:/Returns/src/main/webapp/resources/upload/logs/" + originalName + fileName);

					item.write(savedFile);// 지정 경로에 파일을 저장함
					String uploadedFileName = null;
					// 이미지 파일은 썸네일 사용
					// 썸네일생성
					uploadedFileName = makeThumbnail("C:/Returns/src/main/webapp/resources/upload/logs/",
							originalName + fileName);
					originalName += fileName;
					System.out.println("<tr><td><b>파일저장 경로:</b></td></tr><tr><td><b>" + savedFile + "</td></tr>");
					System.out.println("<tr><td><b><a href=\"DownloadServlet?file=" + fileName + "\">" + originalName
							+ "</a></td></tr>");
				} catch (Exception e) {
					System.out.println("서버에 파일 저장중 에러: " + e);
				}
			}
		}

		boardDTO board = new boardDTO();

		System.out.println(board_Content);
		int randomViewY = (int) (Math.random() * 1500) - 700;

		board.setBoard_code(Integer.parseInt(board_code));
		board.setBoard_title(board_Title);
		board.setBoard_content(board_Content);
		board.setShare_type(share_Type);

		hashTagDTO hash = new hashTagDTO();
		hash.setBoard_code(Integer.parseInt(board_code));
		hash.setHash_tag_content(hash_Tag);

		attachedFileDTO file = new attachedFileDTO();
		file.setBoard_code(Integer.parseInt(board_code));
		file.setFile_content("/s_"+originalName);
		file.setFile_type(file_Type);

		try {
			service.deleteHashFile(board);
			service.updateLog(board);
			if (!hash_Tag.equals("")) {
				bservice.insertHash(hash);
			}
			if (!originalName.equals("")) {
				bservice.insertFile(file);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		service.updateLog(board);

		String result = "success";

		System.out.println(result);
		return new ResponseEntity<String>(result, resHeaders, HttpStatus.OK);
	}

	// 썸네일 생성
	private static String makeThumbnail(String uploadPath, String fileName) throws Exception {
		// 이미지를 읽어들이기 위한 버퍼
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath, fileName));
		// 100 픽셀단위 썸네일 생성
		BufferedImage destImg = Scalr.resize(sourceImg, 600, null, null);
		// Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC,
		// Scalr.Mode.FIT_TO_HEIGHT, 100);
		// 썸네일의 이름생성 "s_"를 붙임
		String thumbnailName = uploadPath + "s_" + fileName;
		File newFile = new File(thumbnailName);
		// 썸네일 생성
		ImageIO.write(destImg, "jpg", newFile);

		// 썸네일의 이름을 리턴
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
