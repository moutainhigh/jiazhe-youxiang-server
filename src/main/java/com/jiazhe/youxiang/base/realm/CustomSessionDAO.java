package com.jiazhe.youxiang.base.realm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.jiazhe.youxiang.base.util.SerializableUtils;
import com.jiazhe.youxiang.server.common.enums.LoginType;
import com.jiazhe.youxiang.server.dao.mapper.SimpleSessionPOMapper;
import com.jiazhe.youxiang.server.domain.po.SimpleSessionPO;
import com.jiazhe.youxiang.server.domain.po.SimpleSessionPOExample;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomSessionDAO extends AbstractSessionDAO {

    @Autowired
    private SimpleSessionPOMapper simpleSessionPOMapper;
    @Autowired
    private SessionDAO sessionDAO;

    private static final Logger log = LoggerFactory.getLogger(CustomSessionDAO.class);
    private ConcurrentMap<Serializable, Session> sessions = new ConcurrentHashMap();

    public CustomSessionDAO() {
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        log.info("-----------------doCreate"+sessionId);
        SimpleSessionPO simpleSessionPO = new SimpleSessionPO();
        simpleSessionPO.setCookie(sessionId.toString());
        simpleSessionPO.setSession(SerializableUtils.serialize(session));
//        simpleSessionPO.setExtInfo("");
//        simpleSessionPO.setIsDeleted(Byte.valueOf("0"));
        simpleSessionPO.setAddTime(new Date());
        simpleSessionPO.setModTime(new Date());
        simpleSessionPO.setStatus(Byte.valueOf("1"));
        simpleSessionPOMapper.insert(simpleSessionPO);
        this.assignSessionId(session, sessionId);
        this.storeSession(sessionId, session);
        return sessionId;
    }

    protected Session storeSession(Serializable id, Session session) {
        log.info("-------------------storeSession"+id);
        if(id == null) {
            throw new NullPointerException("id argument cannot be null.");
        } else {
            return (Session)this.sessions.putIfAbsent(id, session);
        }
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("-------------------doReadSession"+sessionId);
        Session session = (Session)this.sessions.get(sessionId);
        //是否有active的session
        if(null == session){
            SimpleSessionPO simpleSessionPO = getSimpleSessionPOBySession(sessionId);
            if(null != simpleSessionPO){
                session = SerializableUtils.deserialize(simpleSessionPO.getSession());
                this.assignSessionId(session, sessionId);
                this.storeSession(sessionId, session);
                ((SimpleSession)session).setLastAccessTime(new Date());
//                this.assignSessionId(session, sessionId);
//                this.storeSession(sessionId, session);
                log.info("-------------------模拟登录"+sessionId);
//                SecurityUtils.getSubject().login(new AuthToken("tujian","123", LoginType.USER.toString()));
            }else{

            }
//            if (simpleSessionPO != null) {
//                session = (Session) SerializableUtils.deserialize(simpleSessionPO.getSession());
//                Subject s = new Subject.Builder().session(session).buildSubject();
//                if(s.getPrincipal() instanceof SysUserDTO){
//                    SysUserDTO sysUserDTO = (SysUserDTO) s.getPrincipal();
//                    if(null == sysUserDTO){
//                        ((SimpleSession)session).setLastAccessTime(new Date());
//                        //SecurityUtils.getSubject().login(new AuthToken("tujian","123", LoginType.USER.toString()));
//                    }
//                }
//                if(s.getPrincipal() instanceof CustomerDTO){
//                    CustomerDTO customerDTO = (CustomerDTO) s.getPrincipal();
//                    if(null == customerDTO){
//                        ((SimpleSession)session).setLastAccessTime(new Date());
//                        //SecurityUtils.getSubject().login(new AuthToken("13051827155","", LoginType.CUSTOMER.toString()));
//                    }
//                }
//            }
        }
        return session;
    }

    private SimpleSessionPO getSimpleSessionPOBySession(Serializable sessionId) {
        SimpleSessionPOExample example = new SimpleSessionPOExample();
        SimpleSessionPOExample.Criteria criteria = example.createCriteria();
        criteria.andCookieEqualTo(sessionId.toString());
//        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<SimpleSessionPO> simpleSessionPOList = simpleSessionPOMapper.selectByExample(example);
        return simpleSessionPOList.isEmpty() ? null : simpleSessionPOList.get(0);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.info("-------------------update"+session.getId());
        this.storeSession(session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        log.info("-------------------delete"+session.getId());
        if(session == null) {
            throw new NullPointerException("session argument cannot be null.");
        } else {
            Serializable id = session.getId();
            if(id != null) {
                this.sessions.remove(id);
            }

        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Collection<Session> values = this.sessions.values();
        return (Collection)(CollectionUtils.isEmpty(values)?Collections.emptySet():Collections.unmodifiableCollection(values));
    }
}
