package zarazio.travel.android.service;

import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zarazio.travel.android.bean.StepLogDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.dao.StepDAO;


@Service
public class StepServiceImpl implements StepService {

	@Inject
	private StepDAO dao;

	@Override
	public void StepInsert(String user_id) throws Exception {
		// TODO Auto-generated method stub
		dao.StepInsert(user_id);
	}

	@Override
	public int stepCodeSelect(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.stepCodeSelect(user_id);
	}

	@Override
	public void StepFileInsert(StepLogDTO step) throws Exception {
		// TODO Auto-generated method stub
		dao.StepFileInsert(step);
	}

	@Override
	public void StepDelete(int step_code) throws Exception {
		// TODO Auto-generated method stub
		dao.StepDelete(step_code);
	}

	@Override
	public List<boardLIstDTO> stepList(String step_log_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.stepList(step_log_code);
	}

	@Override
	public void StepLogDelete(int step_code) throws Exception {
		// TODO Auto-generated method stub
		dao.StepDelete(step_code);
	}
	
	

}
