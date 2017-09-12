package zarazio.travel.android.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zarazio.travel.android.bean.Friend;
import zarazio.travel.android.bean.Member;
import zarazio.travel.android.dao.MemberDAO;


@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO dao ;
	
	@Override
	public void insert(Member member) throws Exception {
		// TODO Auto-generated method stub
		dao.insert(member);
	}

	@Override
	public Member loginCheck(Member member) throws Exception {
		// TODO Auto-generated method stub
		return dao.loginCheck(member);
	}

	@Override
	public int idCheck(Member member) throws Exception {
		// TODO Auto-generated method stub
		return dao.idCheck(member);
	}

	@Override
	public String idFind(Member member) throws Exception {
		// TODO Auto-generated method stub
		return dao.idFind(member);
	}

	@Override
	public Member passFind(Member member) throws Exception {
		// TODO Auto-generated method stub
		return dao.passFind(member);
	}

	@Override
	public void lostpass(Member member) throws Exception {
		// TODO Auto-generated method stub
		dao.lostpass(member);
	}

	@Override
	public void user_update(Member member) throws Exception {
		// TODO Auto-generated method stub
		if(member.getUser_email().equals("") && member.getUser_phone().equals("")){
			dao.user_updateBasic(member);
		}else if(!member.getUser_phone().equals("")){
			dao.user_updatePhone(member);
		}else if(!member.getUser_email().equals("")){
			dao.user_updateEamil(member);
		}else{
			dao.user_update(member);
		}
	}

	@Override
	public void passUpdate(Member member) throws Exception {
		// TODO Auto-generated method stub
		dao.passUpdate(member);
	}

	@Override
	public void friendADD(Friend friend) throws Exception {
		// TODO Auto-generated method stub
		dao.friendADD(friend);
	}

	@Override
	public Friend friendState(Friend friend) throws Exception {
		return dao.friendState(friend);
	}

	@Override
	public void friendDelete(Friend friend) throws Exception {
		// TODO Auto-generated method stub
		dao.friendDelete(friend);
	}

	@Override
	public void friendUpdate(Friend friend) throws Exception {
		// TODO Auto-generated method stub
		dao.friendUpdate(friend);
	}

}
