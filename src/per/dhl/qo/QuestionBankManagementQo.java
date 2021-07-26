package per.dhl.qo;

import lombok.Data;

import javax.swing.text.html.Option;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 题库管理Qo
 * @author: HongLi
 * @create: 2021-07-26 10:50
 */
@Data
public class QuestionBankManagementQo {
    private Integer fieldId;
    private String topicContent;
    private Integer topicId;
    private String optionContent;
    private String optionScore;
    private ArrayList<String> answer;
}
