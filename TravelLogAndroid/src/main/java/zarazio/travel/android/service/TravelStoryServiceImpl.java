package zarazio.travel.android.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zarazio.travel.android.bean.Material;
import zarazio.travel.android.bean.Member;
import zarazio.travel.android.bean.TravelGroup;
import zarazio.travel.android.bean.TravelPlace;
import zarazio.travel.android.bean.TravelStory;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.boardSearch;
import zarazio.travel.android.dao.TravelStoryDAO;

@Service
public class TravelStoryServiceImpl implements TravelStoryService {

	@Inject // 빈에등록, 사용
	private TravelStoryDAO dao; // interface가 객체가 아니기때문에 상속받는 하위객체를 선언

	@Override
	public void expenseInsert(TravelStory travelstory) throws Exception {
		// TODO Auto-generated method stub
		dao.expenseInsert(travelstory);
	}

	@Override
	public int fineMaxExpenseCode() throws Exception {
		// TODO Auto-generated method stub
		return dao.fineMaxExpenseCode();
	}

	@Override
	public List<TravelStory> selectExpense(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectExpense(group_Code);
	}

	@Override
	public void expenseInsertTravel(TravelStory travelstory) throws Exception {
		// TODO Auto-generated method stub
		dao.expenseInsertTravel(travelstory);
	}

	@Override
	public List<TravelStory> titleSearch(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.titleSearch(user_id); // 하위객체의 클래스를 사용
	}

	@Override
	public List<TravelStory> DivisionSearch(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return dao.DivisionSearch(group_Code);
	}

	@Override
	public List<TravelPlace> TravelList(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return dao.TravelList(group_Code);
	}

	@Override
	public TravelStory TravelDate(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return dao.TravelDate(group_Code);
	}

	@Override
	public List<TravelGroup> travelGroup(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return dao.travelGroup(group_Code);
	}

	@Override
	public List<Material> travelMaterial(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return dao.travelMaterial(group_Code);
	}

	@Override
	public void MaterialcheckOn(Material mate) throws Exception {
		// TODO Auto-generated method stub
		dao.MaterialcheckOn(mate);
	}

	@Override
	public void MaterialcheckOff(Material mate) throws Exception {
		// TODO Auto-generated method stub
		dao.MaterialcheckOff(mate);
	}

	@Override
	public List<boardLIstDTO> travelBoard(boardSearch board) throws Exception {
		// TODO Auto-generated method stub
		return dao.travelBoard(board);
	}
}