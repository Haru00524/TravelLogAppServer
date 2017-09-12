package zarazio.travel.android;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import zarazio.travel.android.bean.QnaBean;
import zarazio.travel.android.bean.QnaResultBean;
import zarazio.travel.android.bean.TravelMemoPushData;
import zarazio.travel.android.bean.boardDTO;
import zarazio.travel.android.bean.myPlaceDTO;
import zarazio.travel.android.bean.selectTravel;
import zarazio.travel.android.service.QNAService;
import zarazio.travel.android.service.boardService;
import zarazio.travel.android.util.TimeTasks;
import zarazio.travel.android.util.TwitterTextAnalysis;

@Controller
public class FcmPushController {

	String qnaText;
	@Inject
	public QNAService service;
	@Inject
	private boardService service2;

	@RequestMapping("push_alram")
	public ResponseEntity<String> pushFCMNotification(String userDeviceIdKey, String longitude, String latitude,
			String user_id) throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		QnaResultBean result = new QnaResultBean();
		myPlaceDTO place = new myPlaceDTO();
		place.setLatitude(Double.parseDouble(latitude));
		place.setLongitude(Double.parseDouble(longitude));
		place.setUser_id(user_id);
		int board_code = 0;
		String data = null;
		board_code = service.boardserch(place);
		System.out.println(latitude + "/" + longitude);
		System.out.println(board_code);
		/*
		 * for(Object object : log_text) { String element = (String) object;
		 * System.out.println(element); }
		 */
		if (board_code == -1) {
			service.placeOff(user_id);
			System.out.println("아무것도 없음");
			return new ResponseEntity<String>("없음", resHeaders, HttpStatus.CREATED);
		} else {
			boardDTO board = service.placeIn(board_code);
			System.out.println(board.getPlace_in());
			if (board.getPlace_in() != 1) {
				service.placeOn(board_code);
				TwitterTextAnalysis twitter = new TwitterTextAnalysis();
				List<String> log_text = twitter.TextAnalysis(board.getBoard_content());

				List<String> qna = service.selectQNA(log_text);

				int random = (int) (Math.random() * qna.size());

				System.out.println(qna.get(random).toString());

				qnaText = qna.get(random).toString();

				result.setBoard_code(board_code);
				result.setQuestion_content(qnaText);

				Timer t = new Timer(true);
				TimerTask task1 = new TimeTasks(userDeviceIdKey);
				t.schedule(task1, 4000);
				Gson gson = new Gson();
				data = gson.toJson(result);
			} else {
				data = "";
			}
		}

		System.out.println(data);
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);

	}

	@RequestMapping("travel_push")
	public ResponseEntity<String> TravelPush(String userDeviceIdKey, String longitude, String latitude, String user_id)
			throws Exception {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		QnaResultBean result = new QnaResultBean();
		myPlaceDTO place = new myPlaceDTO();
		place.setLatitude(Double.parseDouble(latitude));
		place.setLongitude(Double.parseDouble(longitude));
		String data = "";
		TravelMemoPushData pushdata = new TravelMemoPushData();

		List<selectTravel> result2 = null;
		String ingroup_code = service.travelIn(user_id);

		if (ingroup_code == null) {
			ingroup_code = "없음";
		} else {
			place.setGroup_code(ingroup_code);
			System.out.println(ingroup_code);
			pushdata = service.travelMemo(place);
			if (pushdata != null) {
				data = pushdata.getTravel_Memo();
				service.travelInPlace(pushdata.getTravel_Code());
			}
		}
		if (data == null) {
			data = "없음";
			System.out.println(data + "는?");
		} else {
			if (pushdata != null) {
				if (pushdata.getTravel_in() == 0 && pushdata.getTravel_Code() != 0) {
					System.out.println("dsadasdasdsadsa" + pushdata.getTravel_in() + pushdata.getTravel_Code());
					Timer t = new Timer(true);
					TimerTask task1 = new TimeTasks(userDeviceIdKey);
					t.schedule(task1, 4000);
				}
			}
		}
		return new ResponseEntity<String>(data, resHeaders, HttpStatus.CREATED);

	}

}
