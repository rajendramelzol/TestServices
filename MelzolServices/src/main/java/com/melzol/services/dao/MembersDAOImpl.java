package com.melzol.services.dao;

import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.beans.MemberRowMapper;
import com.melzol.services.model.Members;
import com.melzol.services.rowmappers.MemberViewRowMapper;
import com.melzol.services.rowmappers.PopularMembersRowMapper;

@Repository("membersDAO")
@Transactional
public class MembersDAOImpl extends HibernateDaoSupport implements MembersDAO {
	private static final Log log = LogFactory.getLog(MembersDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public MembersDAOImpl() {
        super();
     }
    @Autowired
    public MembersDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#getJdbcTemplate()
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#setJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate)
	 */
	protected void initDao() {
		// do nothing
	}
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#init(org.hibernate.SessionFactory)
	 */
	@Autowired
	public void init(SessionFactory sessionFactory) {
	    setSessionFactory(sessionFactory);
	    this.sessionFactory=sessionFactory;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}
	public void save(Members persistentInstance){
		try {
			sessionFactory.getCurrentSession().save(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Members findById(Integer memberId) {
		log.debug("getting Event instance with id: " + memberId);
		try {
			Members instance = (Members) sessionFactory.getCurrentSession().get("com.melzol.services.model.Members", memberId);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Members> findAllMembers(Double lat,Double lon,int memId,int page) {
		List<Members> list = null;
		//String sql="CALL get_memberslocations(?,?)";
		String sql="SELECT Ng.member_id, Ng.user_name,Ng.gcm_key,Ng.profile_pic,Ng.latitude,Ng.longitude,"
				+ " (((acos(sin((?*pi()/180)) * sin((LN.latitude*pi()/180))+cos((?*pi()/180)) * "
				+ "cos((LN.latitude*pi()/180)) * cos(((? - LN.longitude)*pi()/180))))*180/pi())*60*1.1515) "
				+ "AS `distance` FROM latitude_longitude LN, members Ng where Ng.address_refid = LN.id HAVING "
				+ "`distance` <= 1.5 ORDER BY Ng.member_id=? DESC,`distance` ASC LIMIT 20 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{lat,lat,lon,memId,page},new MemberRowMapper());
		
		System.out.println("the result in InviteMembers xooo838"+list);
		/*}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}*/
		return  list;
	}
	
	public Members findByMobile(java.lang.String mobile) {
		Query query = currentSession().createQuery("from Members where mobile=:mobile and status=1");
		query.setString("mobile", mobile);
		Members qresult=(Members) query.uniqueResult();
		return qresult;
	}
	
	public Members findBylat(Double latitude,Double longitude) {
		Query query = currentSession().createQuery("from Members where latitude=:latitude and longitude=:longitude");
		query.setDouble("latitude", latitude);
		query.setDouble("longitude",longitude);
		Members qresult=(Members) query.uniqueResult();
		return qresult;
	}


	public void updateMember(Members m) {
		Session s=sessionFactory.getCurrentSession();

		Members member=(Members) s.load(Members.class, m.getMemberId());
		if(m.getUserName()!=null)
		member.setUserName(m.getUserName());
		if(m.getFullName()!=null)
		member.setFullName(m.getFullName());
		if(m.getAge()!=null)
		member.setAge(m.getAge());
		if(m.getCompany()!=null)
		member.setCompany(m.getCompany());
		if(m.getSex()!=null)
		member.setSex(m.getSex());
		if(m.getAddressRefid()!=null)
		member.setAddressRefid(m.getAddressRefid());
		if(m.getProfession()!=null)
		member.setProfession(m.getProfession());
		if(m.getInterestHobbies()!=null)
		member.setInterestHobbies(m.getInterestHobbies());
		if(m.getAbout()!=null)
		member.setAbout(m.getAbout());
		if(m.getStatusMessage()!=null)
		member.setStatusMessage(m.getStatusMessage());
		if(m.getProfilePic()!=null)
		member.setProfilePic(m.getProfilePic());
		if(m.getGcmKey()!=null)
		member.setGcmKey(m.getGcmKey());
		if(m.getUpdatedTs()!=null)
		member.setUpdatedTs(m.getUpdatedTs());
		if(m.getLatitude()!=null)
		member.setLatitude(m.getLatitude());
		if(m.getLongitude()!=null)
		member.setLongitude(m.getLongitude());
		if(m.getPlace()!=null)
		member.setPlace(m.getPlace());
		if(m.getCity()!=null)
		member.setCity(m.getCity());
		if(m.getState()!=null)
		member.setState(m.getState());
		if(m.getCountry()!=null)
		member.setCountry(m.getCountry());
		if(m.getPincode()!=null)
		member.setPincode(m.getPincode());
		s.update(member);
		
	}

	public List<Members> findInviteMembers(Double lat,Double lon,int memId) {
		List<Members> list = null;
		String sql="SELECT Ng.member_id,Ng.user_name,Ng.profile_pic,Ng.gcm_key,Ng.latitude,Ng.longitude, "
				+ "(((acos(sin((?*pi()/180)) * sin((LN.latitude*pi()/180))+cos((?*pi()/180)) * "
				+ "cos((LN.latitude*pi()/180)) * cos(((? - LN.longitude)*pi()/180))))*180/pi())*60*1.1515)  "
				+ " AS `distance` FROM latitude_longitude LN, members Ng where Ng.address_refid = LN.id and Ng.member_id!=? "
				+ "and Ng.member_id NOT IN (select T.tag_melzol_id from tagging T where T.melzol_id=?) and "
				+ "Ng.member_id NOT IN (select T.melzol_id from tagging T where T.tag_melzol_id=?) HAVING `distance` <= 1.5 "
				+ "ORDER BY `distance` ASC";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{lat,lat,lon,memId,memId,memId},new MemberRowMapper());
		/*System.out.println("the result in InviteMembers xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}*/
		return  list;
	}
	public Members findUserName(String userName) {
		Query query = currentSession().createQuery("from Members where userName=:userName");
		query.setString("userName", userName);
		Members qresult=(Members) query.uniqueResult();
		return qresult;
	}
	
	public void updateProfilePic( String profilePic,String memberId) {
		Query query = currentSession().createQuery("UPDATE Members SET profilePic =:profilePic where  memberId=:memberId");
		query.setString("profilePic", profilePic);
		query.setString("memberId", memberId);
		query.executeUpdate();
		
		
	}

	public List<Members> findTaggedMembers(int memId) {
		List<Members> list = null;
		String sql="SELECT M.member_id,M.user_name,M.profile_pic,M.gcm_key,M.latitude,M.longitude "
				+ "FROM members M,tagging T WHERE T.melzol_id=? AND M.member_id=T.tag_melzol_id AND T.status=1";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId},new MemberRowMapper());
		System.out.println("the result in tagged members xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}
		return  list;
	}
	
	public int findNeighbourCount(int memId) {
		int TotNeighCnt = 0;
		String sql="SELECT count(NM.member_id) as TotNeigh_Cnt FROM members NM, members M where M.member_id = ? and"
				+ " (((acos(sin((M.latitude*pi()/180)) * sin((NM.latitude*pi()/180))+cos((M.latitude*pi()/180)) * "
				+ "cos((NM.latitude*pi()/180)) * cos(((M.longitude - NM.longitude)*pi()/180))))*180/pi())*60*1.1515) <= 1.5 ";
		try{
		TotNeighCnt=jdbcTemplate.queryForObject(sql,new Object[]{memId},Integer.class);
		System.out.println("the result in tagged members xooo838"+TotNeighCnt);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}
		return  TotNeighCnt;
		
	}

	public int findTaggedCount(Double lat, Double lon,int memId) {
		int TagCnt = 0;
		
		String sql="SELECT count(NM.member_id)  FROM tagging t,members NM where NM.member_id=t.tag_melzol_id "
				+ "and t.melzol_id =? and t.status != 3 and t.status!=2 and"
				+ "(((acos(sin((?*pi()/180)) * sin((NM.latitude*pi()/180))+cos((?*pi()/180)) * "
				+ " cos((NM.latitude*pi()/180)) * cos(((? - NM.longitude)*pi()/180))))*180/pi())*60*1.1515) <= 1.5";
		try{
			TagCnt=jdbcTemplate.queryForObject(sql,new Object[]{memId,lat,lat,lon},Integer.class);
		System.out.println("the result in tagged members xooo838"+TagCnt);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}
		return  TagCnt;
	}

	public MemberDTO findMemberView(int memId,int id) {
		MemberDTO list = null;
		String sql="select mem.*,(select count(G.group_id) from groups G where G.created_by = mem.member_id  and G.status=1) as group_count,"
				+ "(select count(B.blog_id) from blogs B where B.created_by = mem.member_id and B.status=1) as blog_count, "
				+ "(select count(gs.gossip_id) from gossips gs where gs.created_by = mem.member_id  and gs.status=1) as gossip_Count, "
				+"(select count(t.tag_melzol_id) from tagging t where t.melzol_id = mem.member_id and t.status=1) as TotTagged_Cnt,"
				+ "(SELECT count(NM.member_id)  FROM tagging t,members NM where NM.member_id=t.tag_melzol_id and t.melzol_id =? and t.status=1 and"
				+ " (((acos(sin((mem.latitude*pi()/180)) * sin((NM.latitude*pi()/180))+cos((mem.latitude*pi()/180)) * cos((NM.latitude*pi()/180)) * cos(((mem.longitude - NM.longitude)*pi()/180))))*180/pi())*60*1.1515) <= 1.5) as TaggedNeigh_Cnt, "
				+ "(select T.status  from tagging T where T.melzol_id=? and T.tag_melzol_id=?) as tagstatus,"
				+ "(select T.status  from tagging T where T.tag_melzol_id=? and T.melzol_id=?) as requeststatus,"
				+ "(SELECT count(NM.member_id) FROM members NM, members M where M.member_id = mem.member_id and (((acos(sin((M.latitude*pi()/180)) * sin((NM.latitude*pi()/180))+cos((M.latitude*pi()/180)) * cos((NM.latitude*pi()/180)) * "
				+ "cos(((M.longitude - NM.longitude)*pi()/180))))*180/pi())*60*1.1515) <= 1.5 ) TotNeighCnt from members mem where mem.member_id = ?";
		//try{
		list=jdbcTemplate.queryForObject(sql,new Object[]{memId,id,memId,id,memId,memId},new MemberViewRowMapper());
		/*System.out.println("the result in tagged members xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}*/
		return  list;
	}
	public List<Members> findTagRequest(Double lat,Double lon,int memId) {
		List<Members> list = null;
		String sql="SELECT Ng.member_id,Ng.user_name,Ng.profile_pic,Ng.gcm_key,Ng.latitude,Ng.longitude,"
				+ "(((acos(sin((?*pi()/180)) * sin((LN.latitude*pi()/180))+cos((?*pi()/180)) * "
				+ "cos((LN.latitude*pi()/180)) * cos(((? - LN.longitude)*pi()/180))))*180/pi())*60*1.1515) AS `distance` "
				+ " FROM latitude_longitude LN, members Ng where Ng.address_refid = LN.id and Ng.member_id!=? and Ng.member_id  IN "
				+ "(select T.melzol_id from tagging T where T.tag_melzol_id=? and T.status=2) HAVING `distance` <= 1.5 ORDER BY "
				+ "`distance` ASC";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{lat,lat,lon,memId,memId},new MemberRowMapper());
		/*System.out.println("the result in InviteMembers xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}*/
		return  list;
		
	}
	public List<Members> findGroupMembers(int id,int refType,int start) {
		List<Members> list = null;
		String sql="SELECT M.member_id,M.user_name,M.profile_pic,M.gcm_key,M.latitude,M.longitude "
				+ " FROM members M  LEFT OUTER JOIN joinings_opinions GM ON M.member_id = GM.member_id where "
				+ "GM.ref_id=? and GM.ref_type=? and GM.activity_type=1  order by GM.join_date desc  LIMIT 10 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{id,refType,start},new MemberRowMapper());
		/*System.out.println("the result in InviteMembers xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}*/
		return  list;
	}  
	public List<MemberDTO> findPopularMembers(Double lat, Double lon, int memId,int start) {
		List<MemberDTO> list = null;
		String sql="SELECT  Ng.member_id,Ng.user_name,Ng.profile_pic,Ng.gcm_key,Ng.latitude,Ng.longitude, "
				+ "(SELECT count(NM.member_id)  FROM tagging t,members NM where NM.member_id=t.tag_melzol_id and t.melzol_id =Ng.member_id and t.status=1 and "
				+ " (((acos(sin((?*pi()/180)) * sin((NM.latitude*pi()/180))+cos((?*pi()/180)) * cos((NM.latitude*pi()/180)) * cos(((? - NM.longitude)*pi()/180))))*180/pi())*60*1.1515) <= 1.5) as tagcnt,"
				+ "(SELECT count(NM.member_id)  FROM members NM, members M where M.member_id = ? and(((acos(sin((M.latitude*pi()/180)) * sin((NM.latitude*pi()/180))+cos((M.latitude*pi()/180)) * cos((NM.latitude*pi()/180)) * "
				+ " cos(((M.longitude - NM.longitude)*pi()/180))))*180/pi())*60*1.1515) <= 1.5) as nt, "
				+ "(select T.status  from tagging T where T.melzol_id=? and T.tag_melzol_id=Ng.member_id) as tagstatus,"
				+ "(((acos(sin((?*pi()/180)) * sin((LN.latitude*pi()/180))+cos((?*pi()/180)) * cos((LN.latitude*pi()/180)) * cos(((? - LN.longitude)*pi()/180))))*180/pi())*60*1.1515)  AS `distance`,"
				+ "(select tagcnt*100/(nt-1) ) as popularity FROM latitude_longitude LN, members Ng where Ng.address_refid = LN.id HAVING `distance` <= 1.5 ORDER BY popularity desc LIMIT 10 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{lat,lat,lon,memId,memId,lat,lat,lon,start},new PopularMembersRowMapper());
	
		/*System.out.println("the result in InviteMembers xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}*/
		return  list;
	}
	public List<Members> findMemberOpinions(int id, int refType,int opinion, int start) {
		List<Members> list = null;
		String sql="SELECT M.member_id,M.user_name,M.profile_pic,M.gcm_key,M.latitude,M.longitude "
				+ " FROM members M  LEFT OUTER JOIN joinings_opinions GM ON M.member_id = GM.member_id where "
				+ "GM.ref_id=? and GM.ref_type=? and GM.opinion=?  order by GM.join_date desc  LIMIT 30 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql,new Object[]{id,refType,opinion,start},new MemberRowMapper());
		/*System.out.println("the result in InviteMembers xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}*/
		return  list;
	}

}
