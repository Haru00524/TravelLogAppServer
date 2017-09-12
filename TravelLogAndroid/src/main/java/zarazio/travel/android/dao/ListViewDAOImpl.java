package zarazio.travel.android.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;

@Repository
public class ListViewDAOImpl implements ListViewDAO {

	public static final String namespace = "travel.android.boardMapper";

	@Inject
	private SqlSession sqlSession;

	@Override
	public List<boardLIstDTO> serachList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".share_Log_all_List");
	}

	@Override
	public List<boardLIstDTO> serachListHashList(String hash_tag) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".share_Log_search_List", hash_tag);
	}

	@Override
	public List<boardLIstDTO> mainList(String main_user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".share_Log_main_List", main_user_id);
	}

	@Override
	public void boardLike(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".likeInsert", board);
	}

	@Override
	public void commentWrite(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".CommentInsert",board);
	}

	@Override
	public int liketure(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".liketrue", board);
	}

	@Override
	public void likedelete(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".likedelete", board);
	}

	@Override
	public List<boardDTO> replyList(String reply_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".replyList", reply_code);
	}

	@Override
	public void deleteLog(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".log_delete",board);
		sqlSession.delete(namespace+".deleteHashtag",board);
		sqlSession.delete(namespace+".deleteFile",board);
		sqlSession.delete(namespace+".likedelete",board);
	}

	@Override
	public void updateLog(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".update_board", board);
	}

	@Override
	public void deleteHashFile(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".deleteFile",board);
		sqlSession.delete(namespace+".deleteHashtag",board);
		sqlSession.delete(namespace+".likedelete",board);
	}

	@Override
	public List<hashTagDTO> hashTagSel(int board_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".hashTageList", board_code);
	}

	@Override
	public List<boardLIstDTO> myLogs(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".myLogs", user_id);
	}

	@Override
	public String countProfile(String user_id) throws Exception {
		// TODO Auto-generated method stub
		int boardC = sqlSession.selectOne(namespace+".board_count", user_id);
		int friendC = sqlSession.selectOne(namespace+".friend_count", user_id);
		
		String result = boardC +"," + friendC;
		return result;
	}

	@Override
	public List<boardLIstDTO> MyLikeboard(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".MyLikeBoard", user_id);
	}
}
