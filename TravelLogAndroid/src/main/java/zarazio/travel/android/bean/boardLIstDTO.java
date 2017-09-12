package zarazio.travel.android.bean;

import java.sql.Timestamp;

public class boardLIstDTO {
	private int board_code;
    private String board_title;
	private String board_content;
	private double log_longtitude;
	private double log_latitude;
	private int share_type;
	private Timestamp board_date;
	private int board_type_code;
	private String user_id;
	private int place_score;
	private int step_log_code;
	private int randomViewY;
	private String file_type;
	private String file_content;
	private int write_type;
	private int reply_code;
	private int place_in;
	private String user_profile;
	
	public int getBoard_code() {
		return board_code;
	}
	public void setBoard_code(int board_code) {
		this.board_code = board_code;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public double getLog_longtitude() {
		return log_longtitude;
	}
	public void setLog_longtitude(double log_longtitude) {
		this.log_longtitude = log_longtitude;
	}
	public double getLog_latitude() {
		return log_latitude;
	}
	public void setLog_latitude(double log_latitude) {
		this.log_latitude = log_latitude;
	}
	public int getShare_type() {
		return share_type;
	}
	public void setShare_type(int share_type) {
		this.share_type = share_type;
	}
	public Timestamp getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Timestamp board_date) {
		this.board_date = board_date;
	}
	public int getBoard_type_code() {
		return board_type_code;
	}
	public void setBoard_type_code(int board_type_code) {
		this.board_type_code = board_type_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getPlace_score() {
		return place_score;
	}
	public void setPlace_score(int place_score) {
		this.place_score = place_score;
	}
	public int getStep_log_code() {
		return step_log_code;
	}
	public void setStep_log_code(int step_log_code) {
		this.step_log_code = step_log_code;
	}
	public int getRandomViewY() {
		return randomViewY;
	}
	public void setRandomViewY(int randomViewY) {
		this.randomViewY = randomViewY;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getFile_content() {
		return file_content;
	}
	public void setFile_content(String file_content) {
		this.file_content = file_content;
	}
	public int getWrite_type() {
		return write_type;
	}
	public void setWrite_type(int write_type) {
		this.write_type = write_type;
	}
	public int getReply_code() {
		return reply_code;
	}
	public void setReply_code(int reply_code) {
		this.reply_code = reply_code;
	}
	public int getPlace_in() {
		return place_in;
	}
	public void setPlace_in(int place_in) {
		this.place_in = place_in;
	}
	public String getUser_profile() {
		return user_profile;
	}
	public void setUser_profile(String user_profile) {
		this.user_profile = user_profile;
	}
	
	
	
}