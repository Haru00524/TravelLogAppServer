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
	
	// ���ε� ����� ���� 
	public static String uploadFile(String uploadPath, 
			String originalName, byte[] fileData) throws Exception {
		// uploadPath : ������ ������
		// originalName : ���������� �̸�
		// fileData : ���� ������ byte[] 
		
		// UUID �߱� 
		UUID uid = UUID.randomUUID();
		
		// ������ ���ϸ� = UUID + �����̸�
		String saveName = uid.toString() + "_" + originalName;
		
		// ���� ���(������ ���ε���+��¥�����), ���ϸ��� �޾� ���� ��ü ����
		File target = new File(uploadPath, saveName);
		
		
		// ������� �����ϱ� ���� ������ Ȯ���� �˻�
        // ���ϸ��� aaa.bbb.ccc.jpg�� ��� ������ ��ħǥ�� ã�� ����
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		
		 // �̹��� ������ ����� ���
			// ����ϻ��� 
		uploadedFileName = makeThumbnail(uploadPath, saveName);
		
		
		return uploadedFileName;
	}
	
	// ����� ���� 
	private static String makeThumbnail(String uploadPath, String fileName) throws Exception{
		// �̹����� �о���̱� ���� ����
		BufferedImage sourceImg = 
				ImageIO.read(new File(uploadPath, fileName));
		// 100 �ȼ����� ����� ����
		BufferedImage destImg = 
				Scalr.resize(sourceImg, 600, null, null);
//				Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// ������� �̸����� "s_"�� ����
		String thumbnailName = 
				uploadPath+ File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = 
				fileName.substring(fileName.lastIndexOf(".")+1);
		// ����� ����
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// ������� �̸��� ���� 
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
}

