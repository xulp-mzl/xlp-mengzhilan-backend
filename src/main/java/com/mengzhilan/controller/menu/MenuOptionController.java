package com.mengzhilan.controller.menu;

import com.mengzhilan.annotation.*;
import com.mengzhilan.aop.ExceptionHandlerImpl;
import com.mengzhilan.entity.menu.MenuItem;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.exception.EnableExceptionHandler;
import com.mengzhilan.exception.ExceptionHandler;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.menu.MenuItemService;
import org.xlp.json.JsonObject;
import org.xlp.utils.XLPCharsetUtil;
import org.xlp.utils.XLPStringUtil;

/**
 * Create by xlp on 2022/5/1
 *
 * 菜单操作控制类
 */
@EnableExceptionHandler
@ExceptionHandler(ExceptionHandlerImpl.class)
@Controller
@RequestMapping("/menus")
@ResponseCharset(XLPCharsetUtil.UTF8)
public class MenuOptionController {
    private MenuItemService menuItemService = CommonServiceHelper.getMenuItemService();

    /**
     * 获树结构菜单信息
     * @return
     */
    @RequestMapping(method = RequestMethodType.GET)
    public ResponseResult getTreeMenuItems(){
        return ResponseResult.success(menuItemService.getTreeMenuItems());
    }

    /**
     * 保存菜单信息
     * @param data
     * @return
     */
    @RequestMapping(method = RequestMethodType.POST)
    public ResponseResult saveMenuItem(@RequestBody String data){
        if (XLPStringUtil.isEmpty(data)){
            return ResponseResult.error(StatusCode.NOT_REQUEST_BODY, "要保存的数据不变为空！");
        }
        MenuItem menuItem = JsonObject.fromJsonString(data).toBean(MenuItem.class);
        if (XLPStringUtil.isEmpty(menuItem.getTitle())){
            return ResponseResult.error(StatusCode.MUST_DATA_LOSE, "名称不能为空！");
        }
        boolean success = menuItemService.saveMenuItem(menuItem);
        if(success){
            return ResponseResult.success();
        }
        return ResponseResult.error(StatusCode.HAS_EXISTS, "已存在名称或路由相同的菜单条目，操作失败！");
    }

    /**
     * 保存菜单信息
     * @param data
     * @return
     */
    @RequestMapping(method = RequestMethodType.PUT)
    public ResponseResult updateMenuItem(@RequestBody String data){
        if (XLPStringUtil.isEmpty(data)){
            return ResponseResult.error(StatusCode.NOT_REQUEST_BODY, "要更新的数据不变为空！");
        }
        MenuItem menuItem = JsonObject.fromJsonString(data).toBean(MenuItem.class);
        if (XLPStringUtil.isEmpty(menuItem.getTitle())){
            return ResponseResult.error(StatusCode.MUST_DATA_LOSE, "名称不能为空！");
        }
        boolean success = menuItemService.updateMenuItem(menuItem);
        if(success){
            return ResponseResult.success();
        }
        return ResponseResult.error(StatusCode.HAS_EXISTS, "已存在名称或路由相同的菜单条目，操作失败！");
    }

    /**
     * 获树结构菜单信息
     * @return
     */
    @RequestMapping(method = RequestMethodType.DELETE, value = "/{id}")
    public ResponseResult deleteMenuItems(@PathVariable("id") String id){
        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        if (menuItemService.checkHasChildren(menuItem)){
            return ResponseResult.error(StatusCode.HAS_CHILDREN_MENU, "该菜单下有子菜单，请先删除其子菜单！");
        }
        menuItemService.delete(menuItem);
        return ResponseResult.success();
    }
}
