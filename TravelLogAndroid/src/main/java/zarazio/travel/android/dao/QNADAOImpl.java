package zarazio.travel.android.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import zarazio.travel.android.bean.QnaBean;
import zarazio.travel.android.bean.StepLogDTO;
import zarazio.travel.android.bean.TravelMemoPushData;
import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;
import zarazio.travel.android.bean.myPlaceDTO;

@Repository 
public class QNADAOImpl implements QNADAO {
	public final String namespace = "zara.zio.qnaMapper" ;
	
	@Inject
	private SqlSession sqlSession ;

	@Override
	public int boardserch(myPlaceDTO myplace) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".boardserch", myplace);
	}

	@Override
	public boardDTO placeIn(int board_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".placeset", board_code);
	}

	@Override
	public void placeOn(int board_code) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".placeon", board_code);
	}

	@Override
	public void placeOff(String user_id) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".placeoff", user_id);
		
	}

	@Override
	public List<QnaBean> selectQNA(String log_text) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".selectQna", log_text);
	}

	@Override
	public List<QnaBean> selectQNADefault() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".selectQnaDefault");
	}

	@Override
	public TravelMemoPushData travelMemo(myPlaceDTO myplace) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".treavel_memo", myplace);
	}

	@Override
	public String travelIn(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".travel_inTitle", user_id);
	}

	@Override
	public void travelInPlace(int travel_Code) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".travel_inMemo", travel_Code);
	}

}
