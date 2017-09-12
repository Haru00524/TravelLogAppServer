package zarazio.travel.android.dao;

import java.util.List;

import zarazio.travel.android.bean.QnaBean;
import zarazio.travel.android.bean.StepLogDTO;
import zarazio.travel.android.bean.attachedFileDTO;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.hashTagDTO;
import zarazio.travel.android.bean.myPlaceDTO;

public interface hashtagDTO {
	public int boardserch(myPlaceDTO myplace) throws Exception;
	public boardDTO placeIn(int board_code) throws Exception;
	public void placeOn(int board_code) throws Exception;
	public void placeOff(String user_id) throws Exception;
	public List<QnaBean> selectQNA(String log_text) throws Exception;
	public List<QnaBean> selectQNADefault() throws Exception;
}
