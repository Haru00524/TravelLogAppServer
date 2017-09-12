package zarazio.travel.android.service;

import java.util.List;

import zarazio.travel.android.bean.Material;
import zarazio.travel.android.bean.TravelGroup;
import zarazio.travel.android.bean.TravelPlace;
import zarazio.travel.android.bean.TravelStory;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.boardSearch;

public interface TravelStoryService {
   public void expenseInsert(TravelStory travelstory) throws Exception; // 사용금액 입력
   public int fineMaxExpenseCode() throws Exception;
   public void expenseInsertTravel(TravelStory travelstory) throws Exception;
   public List<TravelStory> titleSearch(String user_id) throws Exception;
   public List<TravelStory> DivisionSearch(String group_Code) throws Exception; // 여비한도와 구분찾기
   public List<TravelStory> selectExpense(String group_Code) throws Exception;
   public List<TravelPlace> TravelList(String group_Code) throws Exception;
   public TravelStory TravelDate(String group_Code) throws Exception;
   public List<TravelGroup> travelGroup(String group_Code) throws Exception;
   public List<Material> travelMaterial(String group_Code) throws Exception;
   public void MaterialcheckOn(Material mate) throws Exception;
   public void MaterialcheckOff(Material mate) throws Exception;
   public List<boardLIstDTO> travelBoard(boardSearch board) throws Exception;
}