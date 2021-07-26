package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.AdminInfo;
import per.dhl.qo.AdmTableQo;

import java.util.ArrayList;

public interface AdminInfoMapper {
    AdminInfo SelectAdmInfoByNameAndPwd(@Param("adminAccount") String adminAccount, @Param("adminPassword") String adminPassword);

    ArrayList<AdmTableQo> selectAdms(@Param("lim") Integer limit, @Param("off") Integer offset);

    Integer countSelectAdms();

    Integer modifyAdmStatus(@Param("admAccount") String admAccount, @Param("admStatus") Integer admStatus);

    Integer delAdmStatus(@Param("admAccount") String admAccount);

    Integer resetPwd(@Param("admAccount") String admAccount, @Param("AdmPwd") String AdmPwd);

    ArrayList<AdmTableQo> searchAdmInfo(@Param("admNameSearch") String admNameSearch, @Param("admStatus") String admStatus, @Param("titleSelect") String titleSelect, @Param("lim") Integer limit, @Param("off") Integer offset);

    Integer searchCountAdm(@Param("admNameSearch") String admNameSearch, @Param("admStatus") String admStatus, @Param("titleSelect") String titleSelect);

    Integer admAccount(@Param("admAccount") String admAccount, @Param("admPwd") String admPwd, @Param("admRoleVal") Integer admRoleVal);

    Integer getAdmId(String admAccount);

}
