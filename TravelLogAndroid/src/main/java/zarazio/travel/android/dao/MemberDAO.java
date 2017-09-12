package zarazio.travel.android.dao;

import zarazio.travel.android.bean.Friend;
import zarazio.travel.android.bean.Member;

public interface MemberDAO {
	public void insert(Member member) throws Exception;
	public Member loginCheck(Member member) throws Exception ;
	public int idCheck(Member member) throws Exception ;
	public String idFind(Member member) throws Exception;
	public Member passFind(Member member) throws Exception;
	public void lostpass(Member member) throws Exception;
	public void user_update(Member member) throws Exception;
	public void passUpdate(Member member) throws Exception;
	public void user_updateBasic(Member member) throws Exception;
	public void user_updateEamil(Member member) throws Exception;
	public void user_updatePhone(Member member) throws Exception;
	public void friendADD(Friend friend) throws Exception;
	public Friend friendState(Friend friend) throws Exception;
	public void friendDelete(Friend friend) throws Exception;
	public void friendUpdate(Friend friend) throws Exception;
}
