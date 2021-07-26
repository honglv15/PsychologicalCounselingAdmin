package per.dhl.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JF21030630
 * @description:
 * @author: HongLi
 * @create: 2021-06-30 21:44
 */
@Data
public class JsonDto {
    private Integer id; //是否成功或者失败
    private String message;//返回的消息
    private String location;//返回页面
    private Map<String, Object> datas = new HashMap<>();//数据
}
