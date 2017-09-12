package zarazio.travel.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import zarazio.travel.android.bean.Friend;
import zarazio.travel.android.bean.Material;
import zarazio.travel.android.bean.Member;
import zarazio.travel.android.bean.TravelGroup;
import zarazio.travel.android.bean.TravelPlace;
import zarazio.travel.android.bean.TravelStory;
import zarazio.travel.android.bean.boardLIstDTO;
import zarazio.travel.android.bean.boardSearch;
import zarazio.travel.android.service.TravelStoryService;

@Controller
public class TravelStoryController {

	@Inject
	private TravelStoryService service;

	@RequestMapping("Travel_place")
	public ResponseEntity<String> travel_place(String group_code) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<TravelPlace> result = service.TravelList(group_code);
		
		Gson gson = new Gson();
		String data = gson.toJson(result);
		
		System.out.println(data);
		return new ResponseEntity<String>(data,resHeaders,HttpStatus.CREATED);
	}

	@RequestMapping("Travel_board")
	public ResponseEntity<String> Travel_board(String group_code, String user_id) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		boardSearch board = new boardSearch();
		board.setGroup_Code(group_code);
		board.setUser_id(user_id);
		
		List<boardLIstDTO> result = service.travelBoard(board);
		
		Gson gson = new Gson();
		String data = gson.toJson(result);
		
		System.out.println(data);
		return new ResponseEntity<String>(data,resHeaders,HttpStatus.CREATED);
	}
	
	@RequestMapping("Material_on")
	public ResponseEntity<String> Mterial_on(Material mate) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		System.out.println("on");
		service.MaterialcheckOn(mate);
		
		return new ResponseEntity<String>("success",resHeaders,HttpStatus.CREATED);
	}

	@RequestMapping("Material_off")
	public ResponseEntity<String> Mterial_off(Material mate) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		System.out.println("off");
		service.MaterialcheckOff(mate);
		
		return new ResponseEntity<String>("success",resHeaders,HttpStatus.CREATED);
	}
	
	@RequestMapping("Travel_Date")
	public ResponseEntity<String> travel_Date(String group_code) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		TravelStory travel = new TravelStory();
		travel = service.TravelDate(group_code);
		Gson gson = new Gson();
		String data = gson.toJson(travel);
		
		System.out.println(data);
		return new ResponseEntity<String>(data,resHeaders,HttpStatus.CREATED);
	}
	
	@RequestMapping("Travel_group")
	public ResponseEntity<String> Travel_group(String group_code) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<TravelGroup> result = service.travelGroup(group_code);
		
		Gson gson = new Gson();
		String data = gson.toJson(result);
		
		System.out.println(data);
		return new ResponseEntity<String>(data,resHeaders,HttpStatus.CREATED);
	}
	
	@RequestMapping("Travel_Material")
	public ResponseEntity<String> Travel_Material(String group_code) throws Exception {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		List<Material> result = service.travelMaterial(group_code);
		
		Gson gson = new Gson();
		String data = gson.toJson(result);
		
		System.out.println(data);
		return new ResponseEntity<String>(data,resHeaders,HttpStatus.CREATED);
	}

	

	@RequestMapping("titleSearch")
	@ResponseBody // 데이터만 보낼때 씀
	public List titleSearch(HttpServletRequest request, String user_id) throws Exception { // String
																							// user_id를
																							// 쓰려면
																							// 안드로이드에서
		List<TravelStory> list = service.titleSearch(user_id);

		List<HashMap> m_list = new ArrayList();
		Map<Object, String> map = null;

		for (int i = 0; i < list.size(); i++) {
			map = new HashMap();

			map.put("group_Code", list.get(i).getGroup_Code() + "");
			map.put("travel_title", list.get(i).getTravel_title());
			map.put("start_date", list.get(i).getStart_Date().toString());
			map.put("end_date", list.get(i).getEnd_Date().toString());
			m_list.add((HashMap) map);
		} // Map에 담아서 보낼때 key와 같게해야함

		System.out.println(m_list);
		return m_list; // 웹 -> 앱
	}

	@RequestMapping("scDivisionSearch")
	@ResponseBody
	public JSONObject scDivisionSearch(HttpServletRequest request, String group_Code) throws Exception {
		List<TravelStory> s_list = service.DivisionSearch(group_Code);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("coin_Limit", s_list.get(0).getCoin_Limit());
		jsonObj.put("sc_Division", s_list.get(0).getSc_Division());

		System.out.println("한도, 구분출력 : " + jsonObj.toString());
		return jsonObj;
	}

	@RequestMapping("selectExpense")
	@ResponseBody // 데이터만 보낼때 씀
	public List selectExpense(HttpServletRequest request, String group_Code) throws Exception { // String
																								// user_id를
																								// 쓰려면
																								// 안드로이드에서
		List<TravelStory> list = service.selectExpense(group_Code);

		List<HashMap> m_list = new ArrayList();
		Map<Object, String> map = null;

		for (int i = 0; i < list.size(); i++) {
			map = new HashMap();

			map.put("user_id", list.get(i).getUser_id() + "");
			map.put("expense_Content", list.get(i).getExpense_Content());
			map.put("expense_Cost", list.get(i).getExpense_Cost() + "");
			map.put("expense_Date", list.get(i).getExpense_Date().toString());
			m_list.add((HashMap) map);
		} // Map에 담아서 보낼때 key와 같게해야함

		System.out.println(m_list);
		return m_list; // 웹 -> 앱
	}
}