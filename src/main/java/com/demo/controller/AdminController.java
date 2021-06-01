package com.demo.controller;

import com.demo.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired()
    @Qualifier("bloggerServiceImpl")
    private BloggerService bloggerService;

    /*===============About admin to login this web site==============*/
    @GetMapping("/admin/login.html")
    public String redirectWebAdminLogin() {
        return "redirect:/admin/login";
    }
    @GetMapping("/admin/login")
    public String forewordWebAdminLogin() {
        return "admin/admin-login";
    }

    /*==============About a account check of the login===============*/
    @PostMapping("/admin/checkBloggerByForm")
    @ResponseBody
    public String checkBloggerByForm(HttpSession session, String account, String password) {
//        System.out.println(account + "\n" +  password);
        boolean flag = bloggerService.checkBlogger(account, password);
        if (flag) {
            session.setAttribute("blogger", bloggerService.getBloggerByAccount(account));
            return "true";
        } else {
            return "false";
        }
    }

    /*==================About foreword dashboard web====================*/
    @GetMapping("/admin/dashboard")
    public String forewordAdminDashboard() {
        return "admin/admin-dashboard";
    }


    /*===================About foreword classify web====================*/
    @GetMapping("/admin/category")
    public String forewordAdminClassify() {
        return "admin/admin-category";
    }

    /*===================About foreword comment web=====================*/
    @GetMapping("/admin/comment")
    public String forewordAdminComment() {
        return "admin/admin-comment";
    }

    /*===================About foreword message web=====================*/
    @GetMapping("/admin/message")
    public String forewordAdminMessage() {
        return "admin/admin-message";
    }


}
