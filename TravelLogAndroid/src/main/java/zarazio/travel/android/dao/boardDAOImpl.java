package zarazio.travel.android.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;
import zarazio.travel.android.bean.selectTravel;

@Repository 
public class boardDAOImpl implements boardDAO {

public static final String namespace = "travel.android.boardMapper" ;
	
	@Inject
	private SqlSession sqlSession ;
	
	@Override
	public void insertBoard(boardDTO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".Log_inset", board);
	}

	@Override
	public void insertHash(hashTagDTO hash) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".hash_Tag_insert", hash);
	}

	@Override
	public int maxboard_Code() throws Exception {
		// TODO Auto-generated method stub
		int number=0;
		if(sqlSession.selectOne(namespace+".max_board_code") == null){
			number = 0;
		}else{
			number = sqlSession.selectOne(namespace+".max_board_code");
		}
		return number;
	}

	@Override
	public void insertFile(attachedFileDTO files) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".attachecFile", files);
	}

	@Override
	public List<boardDTO> boardList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".share_Log_List");
	}

	@Override
	public List<attachedFileDTO> selPicture(int board_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".addFile", board_code);
	}

	@Override
	public List<boardDTO> boardHashList(String hash_tag) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(hash_tag);
		return sqlSession.selectList(namespace+".share_Log_hash_List", hash_tag);
	}

	@Override
	public boardLIstDTO pushBoard(int board_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".pushBoard", board_code);
	}

	@Override
	public List<selectTravel> Travel(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".selectboard", user_id);
	}

	

}
