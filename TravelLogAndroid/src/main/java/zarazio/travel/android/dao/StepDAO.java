package zarazio.travel.android.dao;

import java.util.List;

import zarazio.travel.android.bean.StepLogDTO;
import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;

public interface StepDAO {
	public void StepInsert(String user_id) throws Exception;
	public void StepDelete(int step_code) throws Exception;
	public void StepLogDelete(int step_code) throws Exception;
	public int stepCodeSelect(String user_id) throws Exception;
	public void StepFileInsert(StepLogDTO step) throws Exception;
	public List<boardLIstDTO> stepList(String step_log_code) throws Exception;
}
