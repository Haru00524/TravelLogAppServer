package zarazio.travel.android.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import zarazio.travel.android.bean.Material;
import zarazio.travel.android.bean.TravelGroup;
import zarazio.travel.android.bean.TravelPlace;
import zarazio.travel.android.bean.TravelStory;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.boardSearch;

public interface TravelStoryDAO {
   public void expenseInsert(TravelStory travelstory) throws Exception; // ���ݾ� �Է�
   public int fineMaxExpenseCode() throws Exception; // ���� �ֱٿ� �߰��� �����ڵ� ã��
   public void expenseInsertTravel(TravelStory travelstory) throws Exception; // ��׷쿡�� ���� � �ݾ��ڵ带 ����ߴ��� �Է�
   public List<TravelStory> titleSearch(String user_id) throws Exception;
   public List<TravelStory> DivisionSearch(String group_Code) throws Exception; // �����ѵ��� ����ã��
   public List<TravelStory> selectExpense(String group_Code) throws Exception;
   public List<TravelPlace> TravelList(String group_Code) throws Exception;
   public TravelStory TravelDate(String group_Code) throws Exception;
   public List<TravelGroup> travelGroup(String group_Code) throws Exception;
   public List<Material> travelMaterial(String group_Code) throws Exception;
   public void MaterialcheckOn(Material mate) throws Exception;
   public void MaterialcheckOff(Material mate) throws Exception;
   public List<boardLIstDTO> travelBoard(boardSearch board) throws Exception;
}