package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.mapper.MenuMapper;
import com.guigu.pojo.Menu;
import com.guigu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouwenkai
 * @create 2021/4/19  14:16
 */
@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageResult findAll(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Menu> page = menuMapper.findAll(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void menuAdd(Menu menu) {
        menuMapper.menuAdd(menu);
    }

    /**
     * 回显
     *
     * @param id
     * @return
     */
    @Override
    public Menu menuUpdate(Integer id) {
        return menuMapper.menuUpdate(id);
    }

    /**
     * 更新
     *
     * @param menu
     */
    @Override
    public void menuEdit(Menu menu) {
        System.out.println(menu);
        menuMapper.menuEdit(menu);
    }

    @Override
    public void menuDelete(Integer id) throws Exception {
        //判断菜单与角色是否有关联
        Integer flag = menuMapper.menuDeletRole(id);
        if (flag > 0) {
            throw new Exception("要删除的数据被使用,无法删除");
        }
        menuMapper.menuDelete(id);
    }

    @Override
    public List<Menu> findByRoleId(int id) {
        return menuMapper.findByRoleId(id);
    }
}
