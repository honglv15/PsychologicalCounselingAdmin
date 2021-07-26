package per.dhl.service;

import per.dhl.pojo.AdminInfo;
import per.dhl.qo.AdmTableQo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 管理员信息service
 * @author: HongLi
 * @create: 2021-07-09 23:08
 */
public interface AdminInfoService {
    AdminInfo AdminLogin(String admName, String admPwd);

    ArrayList<AdmTableQo> selectAdms(Integer limit, Integer offset);

    Integer countSelectAdms();

    Integer modifyAdmStatus(String admAccount, Integer admStatus);

    Integer delAdmStatus(String admAccount);

    Integer resetPwd(String admAccount, String AdmPwd);

    ArrayList<AdmTableQo> searchAdmInfo(String admNameSearch, String admStatus, String titleSelect, Integer limit, Integer offset);

    Integer searchCountAdm(String admNameSearch, String admStatus, String titleSelect);

    Integer AddAdmInfo(String admAccount, String admPwd, Integer admRoleVal);

    Integer getAdmID(String admAccount);

}
