package zarazio.travel.android.dao;

import java.util.List;

import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;
import zarazio.travel.android.bean.selectTravel;

public interface boardDAO {
	public int maxboard_Code()throws Exception;
	public void insertBoard(boardDTO board) throws Exception;
	public void insertHash(hashTagDTO hash)throws Exception;
	public void insertFile(attachedFileDTO files)throws Exception;
	public List<boardDTO> boardList()throws Exception;
	public List<boardDTO> boardHashList(String hash_tag)throws Exception;
	public List<attachedFileDTO> selPicture(int board_code)throws Exception;
	public boardLIstDTO pushBoard(int board_code) throws Exception;
	public List<selectTravel> Travel(String user_id) throws Exception;
}
