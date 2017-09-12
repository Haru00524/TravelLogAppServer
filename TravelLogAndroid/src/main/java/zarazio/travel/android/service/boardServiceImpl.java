package zarazio.travel.android.service;

import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;
import zarazio.travel.android.bean.selectTravel;
import zarazio.travel.android.dao.boardDAO;

@Service
public class boardServiceImpl implements boardService {

	@Inject
	private boardDAO dao;
	
	@Override
	public void insertBoard(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		dao.insertBoard(board);
	}

	@Override
	public void insertHash(hashTagDTO hash) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(hash.getHash_tag_content()," ");
		while(st.hasMoreTokens()){
			hash.setHash_tag_content("#"+st.nextToken());
			dao.insertHash(hash);
		}
	}

	@Override
	public int maxboard_Code() throws Exception {
		// TODO Auto-generated method stub
		return dao.maxboard_Code();
	}

	@Override
	public void insertFile(attachedFileDTO files) throws Exception {
		// TODO Auto-generated method stub
		dao.insertFile(files);
	}

	@Override
	public List<boardDTO> boardList() throws Exception {
		// TODO Auto-generated method stub
		return dao.boardList();
	}

	@Override
	public List<attachedFileDTO> selPicture(int board_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.selPicture(board_code);
	}

	@Override
	public List<boardDTO> boardList(String Hash) throws Exception {
		// TODO Auto-generated method stub
		return dao.boardHashList(Hash);
	}

	@Override
	public boardLIstDTO pushBoard(int board_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.pushBoard(board_code);
	}

	@Override
	public List<selectTravel> Travel(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.Travel(user_id);
	}

}
