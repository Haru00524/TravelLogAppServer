package zarazio.travel.android.bean;

public class QnaBean {
	private int question_code;
	private String question_content;
	private String question_type;
	
	
	public int getQuestion_code() {
		return question_code;
	}
	public void setQuestion_code(int question_code) {
		this.question_code = question_code;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public String toString(){
		return question_content;
	}
}
