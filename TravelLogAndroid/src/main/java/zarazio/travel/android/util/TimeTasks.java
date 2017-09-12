package zarazio.travel.android.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.TimerTask;

import org.json.simple.JSONObject;

public class TimeTasks extends TimerTask{
	
	public final String AUTH_KEY_FCM = "AAAA38W0sqQ:APA91bERMrpDlZlVLMxJWbrf1u2Q5Mm7QIRbQC942UD2sIBBavbgwyre0xozWYyR1AMe_R8Xqsm09jKWI6MRgPMpAZ49jmwsGVb0faUubr1M_gOJDyLpM9oQa-XBH2UpSiZ_uzcH3YmE";
	public final String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	private String userDeviceIdKey;
	
	public TimeTasks(String userDeviceIdKey){
		this.userDeviceIdKey = userDeviceIdKey;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM;
		
		URL url = null;
		try {
			url = new URL(FMCurl);
		} catch (MalformedURLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();

		info.put("body", "푸쉬 발송 테스트 입니다."); // Notification body

		json.put("notification", info);
		json.put("to", userDeviceIdKey.trim()); // deviceID

		try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
			// 혹시나 한글 깨짐이 발생하면
			// try(OutputStreamWriter wr = new
			// OutputStreamWriter(conn.getOutputStream(), "UTF-8")){
			// 인코딩을 변경해준다.

			wr.write(json.toString());
			wr.flush();
		} catch (Exception e) {
		}

		try {
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String output;
		System.out.println("Output from Server .... \n");
		try {
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.disconnect();
	}

}
