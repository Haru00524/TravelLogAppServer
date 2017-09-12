package zarazio.travel.android.service;

import java.util.List;

import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;

public interface ListViewService {
	public List<boardLIstDTO> serachList()throws Exception;
	public List<boardLIstDTO> mainList(String user_id)throws Exception;
	public List<boardLIstDTO> serachListHashList(String hash_tag)throws Exception;
	public void boardLike(boardDTO board) throws Exception;
	public void commentWrite(boardDTO board) throws Exception;
	public int liketure(boardDTO board) throws Exception;
	public void likedelete(boardDTO board) throws Exception;
	public List<boardDTO> replyList(String reply_code) throws Exception;
	public void deleteLog(boardDTO board) throws Exception;
	public void updateLog(boardDTO board) throws Exception;
	public void deleteHashFile(boardDTO board)throws Exception;
	public List<hashTagDTO> hashTagSel(int board_code) throws Exception;
	public List<boardLIstDTO> myLogs(String user_id)throws Exception;
	public String countProfile(String user_id)throws Exception;
	public List<boardLIstDTO> MyLikeboard(String user_id) throws Exception;
}
