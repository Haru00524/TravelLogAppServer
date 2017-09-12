package zarazio.travel.android.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import zarazio.travel.android.bean.Material;
import zarazio.travel.android.bean.Member;
import zarazio.travel.android.bean.TravelGroup;
import zarazio.travel.android.bean.TravelPlace;
import zarazio.travel.android.bean.TravelStory;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.boardSearch;

@Repository // DAO객체 사용할수 있도록한다.
public class TravelStoryDAOImpl implements TravelStoryDAO {

	// Mapper찾아갈수 있도록 지정해준다.
	public static final String namespace = "travel.android.TravelStoryMapper";

	// sql쿼리문을 사용하겠다(CRUD구문).
	@Inject
	private SqlSession sqlSession;

	@Override
	public void expenseInsert(TravelStory travelstory) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".expenseInsert", travelstory);
	}

	@Override
	public List<TravelStory> selectExpense(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".selectExpense", group_Code);
	}

	@Override
	public void expenseInsertTravel(TravelStory travelstory) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".expenseInsertTravel", travelstory);
	}

	@Override
	public List<TravelStory> DivisionSearch(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".DivisionSearch", group_Code);
	}

	@Override
	public int fineMaxExpenseCode() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".fineMaxExpenseCode");
	}

	@Override
	public List<TravelStory> titleSearch(String user_id) throws Exception {
		// TODO Auto-generated method stub1
		return sqlSession.selectList(namespace + ".titleSearch", user_id); // 파라미터의
																			// 변수명
	}

	@Override
	public List<TravelPlace> TravelList(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".travel_place_list", group_Code);
	}

	@Override
	public TravelStory TravelDate(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".travel_date", group_Code);
	}

	@Override
	public List<TravelGroup> travelGroup(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".travel_groupList", group_Code);
	}

	@Override
	public List<Material> travelMaterial(String group_Code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".travel_material_check", group_Code);
	}

	@Override
	public void MaterialcheckOn(Material mate) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".material_check_on",mate);
	}

	@Override
	public void MaterialcheckOff(Material mate) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".material_check_off",mate);
	}

	@Override
	public List<boardLIstDTO> travelBoard(boardSearch board) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".Travel_board", board);
	}

}