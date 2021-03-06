package com.lxinet.jeesns.group.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.group.model.Group;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by zchuanzhao on 16/12/23.
 */
public interface IGroupDao extends IBaseDao<Group> {

    /**
     * 获取群组
     * @return
     */
    List<Group> listByPage(@Param("page") Page page, @Param("status") Integer status, @Param("key") String key);

    /**
     * 修改状态
     *
     * @param id
     * @return
     */
    Integer changeStatus(@Param("id") Integer id);

    List<Group> listByCustom(@Param("status") int status, @Param("num") int num, @Param("sort") String sort);

}