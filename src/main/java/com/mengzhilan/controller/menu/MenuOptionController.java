package com.mengzhilan.controller.menu;

import com.mengzhilan.annotation.*;
import com.mengzhilan.constant.MessageConst;
import com.mengzhilan.entity.menu.MenuItem;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.menu.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.json.JsonObject;
import org.xlp.utils.XLPStringUtil;

/**
 * Create by xlp on 2022/5/1
 *
 * 菜单操作控制类
 */
@Controller
@RequestMapping("/menus/")
public class MenuOptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuOptionController.class);

    private MenuItemService menuItemService = CommonServiceHelper.getMenuItemService();

    /**
     * 获树结构菜单信息
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.GET)
    public ResponseResult getTreeMenuItems(){
        try {
            return ResponseResult.success(menuItemService.getTreeMenuItems());
        } catch (Exception e) {
            LOGGER.error("获树结构菜单信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }

    /**
     * 保存菜单信息
     * @param data
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.POST)
    public ResponseResult saveMenuItem(@RequestBody String data){
        if (XLPStringUtil.isEmpty(data)){
            return ResponseResult.error(StatusCode.NOT_REQUEST_BODY, "要保存的数据不变为空！");
        }
        try {
            MenuItem menuItem = JsonObject.fromJsonString(data).toBean(MenuItem.class);
            if (XLPStringUtil.isEmpty(menuItem.getTitle())){
                return ResponseResult.error(StatusCode.MUST_DATA_LOSE, "名称不能为空！");
            }
            boolean success = menuItemService.saveMenuItem(menuItem);
            if(success){
                return ResponseResult.success();
            }
            return ResponseResult.error(StatusCode.HAS_EXISTS, "已存在名称或路由相同的菜单条目，操作失败！");
        }  catch (Exception e){
            LOGGER.error("保存菜单信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }

    /**
     * 保存菜单信息
     * @param data
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.PUT)
    public ResponseResult updateMenuItem(@RequestBody String data){
        if (XLPStringUtil.isEmpty(data)){
            return ResponseResult.error(StatusCode.NOT_REQUEST_BODY, "要更新的数据不变为空！");
        }
        try {
            MenuItem menuItem = JsonObject.fromJsonString(data).toBean(MenuItem.class);
            if (XLPStringUtil.isEmpty(menuItem.getTitle())){
                return ResponseResult.error(StatusCode.MUST_DATA_LOSE, "名称不能为空！");
            }
            boolean success = menuItemService.updateMenuItem(menuItem);
            if(success){
                return ResponseResult.success();
            }
            return ResponseResult.error(StatusCode.HAS_EXISTS, "已存在名称或路由相同的菜单条目，操作失败！");
        }  catch (Exception e){
            LOGGER.error("更新菜单信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }

    /**
     * 获树结构菜单信息
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.DELETE, value = "{id}")
    public ResponseResult deleteMenuItems(@PathVariable("id") String id){
        try {
            MenuItem menuItem = new MenuItem();
            menuItem.setId(id);
            if (menuItemService.checkHasChildren(menuItem)){
                return ResponseResult.error(StatusCode.HAS_CHILDREN_MENU, "该菜单下有子菜单，请先删除其子菜单！");
            }
            menuItemService.delete(menuItem);
            return ResponseResult.success();
        } catch (Exception e) {
            LOGGER.error("获树结构菜单信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }
}
