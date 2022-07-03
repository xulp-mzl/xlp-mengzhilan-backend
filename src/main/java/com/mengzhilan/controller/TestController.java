package com.mengzhilan.controller;

import com.mengzhilan.annotation.*;
import com.mengzhilan.entity.model.ModelFormAndTableBaseConfigInfo;
import com.mengzhilan.enumeration.RequestMethodType;
import org.xlp.db.ddl.MYSqlTableCreator;
import org.xlp.db.ddl.TableCreator;
import org.xlp.db.utils.XLPDBUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Create by xlp on 2021/12/11
 */
@Controller
public class TestController {
    @ResponseCharset("utf-8")
    @RequestMapping(value = "/test", method = RequestMethodType.GET)
    public String test(HttpServletResponse response, HttpServletRequest request,
                       @RequestParam(required = true) String id){
        return "{\"id\":" + id + "}";
    }

    @ResponseCharset("utf-8")
    @RequestMapping(value = "/test1", method = RequestMethodType.GET)
    public void test1(HttpServletResponse response, @RequestParam("id") String id1) throws IOException {
        System.out.println(response);
        response.getWriter().write(id1+"1");
    }


    @ResponseCharset("utf-8")
    @RequestMapping(value = "/test123{id}{name}", method = RequestMethodType.GET)
    public void test3(HttpServletResponse response, @PathVariable("id") String id2,
                      @PathVariable("name") String name) throws IOException {
        System.out.println(response);
        response.getWriter().write(id2+"4" + name);
    }

    @ResponseCharset("utf-8")
    @RequestMapping(value = "/test123{id}23{name}", method = RequestMethodType.GET)
    public void test2(HttpServletResponse response, @PathVariable("id") String id2,
                      @PathVariable("name") String name) throws IOException {
        System.out.println(response);
        response.getWriter().write(id2+"2" + name);
    }

    @ResponseCharset("utf-8")
    @RequestMapping(value = "/createTable", method = RequestMethodType.GET)
    public String createTable(HttpServletResponse response) throws IOException, SQLException {
       TableCreator tableCreator = new MYSqlTableCreator(XLPDBUtil.getConnection());
       tableCreator.createTableByEntityClass(ModelFormAndTableBaseConfigInfo.class);
       return "表创建成功";
    }
}
