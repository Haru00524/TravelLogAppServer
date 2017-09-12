package zarazio.travel.android.service;

import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;
import zarazio.travel.android.dao.ListViewDAO;
import zarazio.travel.android.dao.boardDAO;

@Service
public class LisetViewServiceImpl implements ListViewService {

	@Inject
	private ListViewDAO dao;

	@Override
	public List<boardLIstDTO> serachList() throws Exception {
		// TODO Auto-generated method stub
		return dao.serachList();
	}

	@Override
	public List<boardLIstDTO> serachListHashList(String hash_tag) throws Exception {
		// TODO Auto-generated method stub
		return dao.serachListHashList(hash_tag);
	}

	@Override
	public List<boardLIstDTO> mainList(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.mainList(user_id);
	}

	@Override
	public void boardLike(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		dao.boardLike(board);
	}

	@Override
	public void commentWrite(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		dao.commentWrite(board);
	}

	@Override
	public int liketure(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		return dao.liketure(board);
	}

	@Override
	public void likedelete(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		dao.likedelete(board);
	}

	@Override
	public List<boardDTO> replyList(String reply_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.replyList(reply_code);
	}

	@Override
	public void deleteLog(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteLog(board);
	}

	@Override
	public void updateLog(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		dao.updateLog(board);
	}

	@Override
	public void deleteHashFile(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteHashFile(board);
	}

	@Override
	public List<hashTagDTO> hashTagSel(int board_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.hashTagSel(board_code);
	}

	@Override
	public List<boardLIstDTO> myLogs(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.myLogs(user_id);
	}

	@Override
	public String countProfile(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.countProfile(user_id);
	}

	@Override
	public List<boardLIstDTO> MyLikeboard(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.MyLikeboard(user_id);
	}
	
	

}
