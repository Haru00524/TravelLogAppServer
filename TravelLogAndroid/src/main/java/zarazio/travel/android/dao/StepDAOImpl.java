package zarazio.travel.android.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import zarazio.travel.android.bean.StepLogDTO;
import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;

@Repository 
public class StepDAOImpl implements StepDAO {

public static final String namespace = "travel.android.StepMapper" ;
	
	@Inject
	private SqlSession sqlSession ;

	@Override
	public void StepInsert(String user_id) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".step_insert",user_id);
	}

	@Override
	public int stepCodeSelect(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".user_step", user_id);
	}

	@Override
	public void StepFileInsert(StepLogDTO step) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".update_step", step);
	}

	@Override
	public void StepDelete(int step_code) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".delete_step", step_code);
	}

	@Override
	public List<boardLIstDTO> stepList(String step_log_code) throws Exception {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList(namespace+".step_log_select", step_log_code);
	}

	@Override
	public void StepLogDelete(int step_code) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".delete_Log_step", step_code);
	}
	


}
