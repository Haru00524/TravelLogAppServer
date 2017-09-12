package zarazio.travel.android.util;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	// 업로드 기능의 설계 
	public static String uploadFile(String uploadPath, 
			String originalName, byte[] fileData) throws Exception {
		// uploadPath : 파일의 저장경로
		// originalName : 원본파일의 이름
		// fileData : 파일 데이터 byte[] 
		
		// UUID 발급 
		UUID uid = UUID.randomUUID();
		
		// 저장할 파일명 = UUID + 원본이름
		String saveName = uid.toString() + "_" + originalName;
		
		// 파일 경로(기존의 업로드경로+날짜별경로), 파일명을 받아 파일 객체 생성
		File target = new File(uploadPath, saveName);
		
		
		// 썸네일을 생성하기 위한 파일의 확장자 검사
        // 파일명이 aaa.bbb.ccc.jpg일 경우 마지막 마침표를 찾기 위해
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		
		 // 이미지 파일은 썸네일 사용
			// 썸네일생성 
		uploadedFileName = makeThumbnail(uploadPath, saveName);
		
		
		return uploadedFileName;
	}
	
	// 썸네일 생성 
	private static String makeThumbnail(String uploadPath, String fileName) throws Exception{
		// 이미지를 읽어들이기 위한 버퍼
		BufferedImage sourceImg = 
				ImageIO.read(new File(uploadPath, fileName));
		// 100 픽셀단위 썸네일 생성
		BufferedImage destImg = 
				Scalr.resize(sourceImg, 600, null, null);
//				Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// 썸네일의 이름생성 "s_"를 붙임
		String thumbnailName = 
				uploadPath+ File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = 
				fileName.substring(fileName.lastIndexOf(".")+1);
		// 썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// 썸네일의 이름을 리턴 
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
}

