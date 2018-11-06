package com.jiazhe.youxiang.base.realm;

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
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author TU
 * @description
 * @date 2018/11/6.
 */
public class CustomerSessionDao extends AbstractSessionDAO {

    @Autowired
    private SimpleSessionPOMapper simpleSessionPOMapper;

    private static final Logger log = LoggerFactory.getLogger(CustomerSessionDao.class);
    private ConcurrentMap<Serializable, Session> sessions = new ConcurrentHashMap();

    public CustomerSessionDao() {
    }

    @Override
    protected Serializable doCreate(Session session) {
        log.info("---------doCreate-------------");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.storeSession(sessionId, session);
        SimpleSessionPO simpleSessionPO = new SimpleSessionPO();
        simpleSessionPO.setCookie(sessionId.toString());
        simpleSessionPO.setSession(SerializableUtils.serialize(session));
        simpleSessionPO.setExtInfo("");
        simpleSessionPO.setIsDeleted(Byte.valueOf("0"));
        simpleSessionPO.setAddTime(new Date());
        simpleSessionPO.setModTime(new Date());
        simpleSessionPO.setStatus(Byte.valueOf("1"));
        simpleSessionPOMapper.insert(simpleSessionPO);
        return sessionId;
    }

    protected Session storeSession(Serializable id, Session session) {
        log.info("---------storeSession-------------");
        if (id == null) {
            throw new NullPointerException("id argument cannot be null.");
        } else {
            return (Session) this.sessions.putIfAbsent(id, session);
        }
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("----------doReadSession-------------");
        Session session = null;
        try {
            session = super.readSession(sessionId);
        } catch (Exception e) {

        }
        if (null == session) {
            SimpleSessionPO simpleSessionPO = getSimpleSessionPOBySession(sessionId);
            if (simpleSessionPO != null) {
                session = (Session) SerializableUtils.deserialize(simpleSessionPO.getSession());
                Subject s = new Subject.Builder().session(session).buildSubject();
                if(s.getPrincipal() instanceof SysUserDTO){
                    SysUserDTO sysUserDTO = (SysUserDTO) s.getPrincipal();
                    if(null == sysUserDTO){
                        SecurityUtils.getSubject().login(new AuthToken("tujian","123", LoginType.USER.toString()));
                    }
                }
                if(s.getPrincipal() instanceof CustomerDTO){
                    CustomerDTO customerDTO = (CustomerDTO) s.getPrincipal();
                    if(null == customerDTO){
                        SecurityUtils.getSubject().login(new AuthToken("13051827155","", LoginType.CUSTOMER.toString()));
                    }
                }
            }
        }
        return session;
    }

    private SimpleSessionPO getSimpleSessionPOBySession(Serializable sessionId) {
        SimpleSessionPOExample example = new SimpleSessionPOExample();
        SimpleSessionPOExample.Criteria criteria = example.createCriteria();
        criteria.andCookieEqualTo(sessionId.toString());
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<SimpleSessionPO> simpleSessionPOList = simpleSessionPOMapper.selectByExample(example);
        return simpleSessionPOList.isEmpty() ? null : simpleSessionPOList.get(0);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.info("----------storeSession-------------");
        this.storeSession(session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        log.info("----------delete-------------");
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        } else {
            Serializable id = session.getId();
            if (id != null) {
                this.sessions.remove(id);
            }

        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.info("----------getActiveSession-------------");
        Collection<Session> values = this.sessions.values();
        return (Collection) (CollectionUtils.isEmpty(values) ? Collections.emptySet() : Collections.unmodifiableCollection(values));
    }
}
