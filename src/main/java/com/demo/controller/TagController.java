package com.demo.controller;

import com.demo.entity.Blogger;
import com.demo.entity.Tag;
import com.demo.service.BloggerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class TagController {
    @Autowired()
    private BloggerService bloggerService;

    /*=====================About foreword tag web=======================*/
    @GetMapping("/admin/tag")
    public String forewordAdminTag() {
        return "admin/admin-tag";
    }

    /*==================About article page message====================*/
    @SneakyThrows
    @GetMapping(value="/admin/tag/getTagList", produces="application/json; charset=utf-8")
    @ResponseBody
    public String getTagList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Blogger blogger = (Blogger) session.getAttribute("blogger");
        HashMap<String, Object> message = new HashMap<String, Object>();

        // add admin self information
        message.put("account", blogger.getAccount());

        // add article other information
        List<Tag> tags = bloggerService.getTagByAccount(blogger.getAccount());
        message.put("total", tags.size());
//        Article reccent = bloggerService.getReccentPostArticle(blogger.getAccount());
//        message.put("linkCount", reccent.getPost_time().toString());
        int linkCount = 0;

        // add article content information
        List<HashMap<String, Object>> tagList = new ArrayList<HashMap<String, Object>>();
        for (Tag tag : tags) {
            HashMap<String, Object> tagJson = new HashMap<String, Object>();
            tagJson.put("name", tag.getName());
            int count = bloggerService.getTagLinkCount(tag);
            linkCount += count;
            tagJson.put("count", count);
            tagList.add(tagJson);
        }
        message.put("linkCount", linkCount);
        message.put("tags", tagList);

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }

    /*==================About tag page message====================*/
    @SneakyThrows
    @PostMapping("/admin/tag/addTag")
    @ResponseBody
    public String addTag(HttpServletRequest request, String tagName) {
        HttpSession session = request.getSession();
        Blogger blogger = (Blogger) session.getAttribute("blogger");
        HashMap<String, Object> message = new HashMap<String, Object>();

        Tag tag = new Tag(blogger.getAccount(), tagName);
        boolean flag = bloggerService.addTag(tag);

        message.put("result", flag);

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }
}
