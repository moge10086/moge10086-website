package com.moge10086.website.mapper;

import com.moge10086.website.domain.vo.user.BaseUserVO;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserQueryMapper {
    /**
     * 返回对应用户基本信息表
     * @param userIds
     * @return
     */
    @MapKey("userId")
    Map<String,BaseUserVO> getBaseUsers(List<Long> userIds);
}
