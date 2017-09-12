package zarazio.travel.android.dao;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import zarazio.travel.android.bean.Friend;
import zarazio.travel.android.bean.Member;


@Repository 
public class MemberDAOImpl implements MemberDAO{

	public static final String namespace = "travel.android.memberMapper" ;
	
	@Inject
	private SqlSession sqlSession ;
	
	
	@Override
	public void insert(Member member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".insert", member);
	}


	@Override
	public Member loginCheck(Member member) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".loginCheck", member);
	}


	@Override
	public int idCheck(Member member) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".idCheck",member) ;
	}


	@Override
	public String idFind(Member member) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".idfind",member);
	}


	@Override
	public Member passFind(Member member) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".passfind", member);
	}


	@Override
	public void lostpass(Member member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".lostpass", member);
	}


	@Override
	public void user_update(Member member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".profile_update", member);
	}


	@Override
	public void passUpdate(Member member) throws Exception {
		// TODO Auto-generated method stub
	
		sqlSession.update(namespace+".lostpass", member);
	}


	@Override
	public void user_updateEamil(Member member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".profile_update_email", member);
	}


	@Override
	public void user_updatePhone(Member member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".profile_update_phone", member);
	}


	@Override
	public void user_updateBasic(Member member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".profile_update_basic", member);
	}


	@Override
	public void friendADD(Friend friend) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".friendAdd",friend);
	}


	@Override
	public Friend friendState(Friend friend) throws Exception {
		// TODO Auto-generated method stub
		Friend result = new Friend();
		result = sqlSession.selectOne(namespace+".friend", friend);
		return result;
	}


	@Override
	public void friendDelete(Friend friend) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".friendDelete", friend);
	}


	@Override
	public void friendUpdate(Friend friend) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".friendUpdate", friend);
	}

	
}
