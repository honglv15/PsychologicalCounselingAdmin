package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.qo.QuestionBankManagementQo;

import java.util.ArrayList;

public interface QuestionBankManagementMapper {
    ArrayList<QuestionBankManagementQo> InitQuestionBankManagementTable(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("selectValue") Integer selectValue);
}
