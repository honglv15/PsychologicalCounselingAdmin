package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.AdminInfoMapper;
import per.dhl.pojo.AdminInfo;
import per.dhl.qo.AdmTableQo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: AdminInfoService实现类
 * @author: HongLi
 * @create: 2021-07-09 23:09
 */
@Component
public class AdminInfoServiceImpl implements AdminInfoService {
    @Override
    public AdminInfo AdminLogin(String admName, String admPwd) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        AdminInfo admInfo = mapper.SelectAdmInfoByNameAndPwd(admName, admPwd);
        session.commit();
        session.close();
        return admInfo;
    }

    @Override
    public ArrayList<AdmTableQo> selectAdms(Integer limit, Integer offset) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        ArrayList<AdmTableQo> admInfos = mapper.selectAdms(limit, offset);
        session.commit();
        session.close();
        return admInfos;
    }

    @Override
    public Integer countSelectAdms() {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        Integer countSelectAdms = mapper.countSelectAdms();
        session.commit();
        session.close();
        return countSelectAdms;
    }

    @Override
    public Integer modifyAdmStatus(String admAccount, Integer admStatus) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        Integer countSelectAdms = mapper.modifyAdmStatus(admAccount, admStatus);
        session.commit();
        session.close();
        return countSelectAdms;
    }

    @Override
    public Integer delAdmStatus(String admAccount) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        Integer countDelAdmStatus= mapper.delAdmStatus(admAccount);
        session.commit();
        session.close();
        return countDelAdmStatus;
    }

    @Override
    public Integer resetPwd(String admAccount, String AdmPwd) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        Integer countResetPwd= mapper.resetPwd(admAccount,AdmPwd);
        session.commit();
        session.close();
        return countResetPwd;
    }

    @Override
    public ArrayList<AdmTableQo> searchAdmInfo(String admNameSearch, String admStatus, String titleSelect, Integer limit, Integer offset) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        ArrayList<AdmTableQo>SearchAdmInfo= mapper.searchAdmInfo(admNameSearch,admStatus,titleSelect,limit,offset);
        session.commit();
        session.close();
        return SearchAdmInfo;
    }

    @Override
    public Integer searchCountAdm(String admNameSearch, String admStatus, String titleSelect) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        Integer searchCountAdm= mapper.searchCountAdm(admNameSearch,admStatus,titleSelect);
        session.commit();
        session.close();
        return searchCountAdm;
    }

    @Override
    public Integer AddAdmInfo(String admAccount, String admPwd, Integer admRoleVal) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        Integer AddAdmInfoCount= mapper.admAccount(admAccount,admPwd,admRoleVal);
        session.commit();
        session.close();
        return AddAdmInfoCount;
    }

    @Override
    public Integer getAdmID(String admAccount) {
        SqlSession session = SqlSessionUtil.getSession();
        AdminInfoMapper mapper = session.getMapper(AdminInfoMapper.class);
        Integer AdmId= mapper.getAdmId(admAccount);
        session.commit();
        session.close();
        return AdmId;
    }




}
