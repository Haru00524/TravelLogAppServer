package zarazio.travel.android.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zarazio.travel.android.bean.QnaBean;
import zarazio.travel.android.bean.StepLogDTO;
import zarazio.travel.android.bean.TravelMemoPushData;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.myPlaceDTO;
import zarazio.travel.android.dao.QNADAO;
import zarazio.travel.android.dao.StepDAO;

@Service
public class QNAServiceImpl implements QNAService {

	@Inject
	private QNADAO dao;

	@Override
	public int boardserch(myPlaceDTO myplace) throws Exception {
		// TODO Auto-generated method stub
		return dao.boardserch(myplace);
	}

	@Override
	public boardDTO placeIn(int board_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.placeIn(board_code);
	}

	@Override
	public void placeOn(int board_code) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("placeOn");
		dao.placeOn(board_code);
	}

	@Override
	public void placeOff(String user_id) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("placeOut");
		dao.placeOff(user_id);
	}

	@Override
	public List<String> selectQNA(List<String> log_text) throws Exception {
		// TODO Auto-generated method stub
		List<QnaBean> list = null;
		List<String> result = new ArrayList();
		for (Object object : log_text) {
			String element = (String) object;
			System.out.println(element);
			list = dao.selectQNA(element);
			System.out.println(list+ "list 출력");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					String qna = (String) list.get(i).toString();
					System.out.println(qna);
					result.add(qna);
				}
			}
		}
		if(result.size() == 0){
			System.out.println("ㅇㅇㅇ");
			list = dao.selectQNADefault();
			for (int i = 0; i < list.size(); i++) {
				String qna = (String) list.get(i).toString();
				System.out.println(qna);
				result.add(qna);
			}
		}
		System.out.println("결과값"+result);
		return result;
	}

	@Override
	public TravelMemoPushData travelMemo(myPlaceDTO myplace) throws Exception {
		// TODO Auto-generated method stub
		return dao.travelMemo(myplace);
	}

	@Override
	public String travelIn(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.travelIn(user_id);
	}

	@Override
	public void travelInPlace(int travel_Code) throws Exception {
		// TODO Auto-generated method stub
		dao.travelInPlace(travel_Code);
	}


}
