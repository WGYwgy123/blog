package com.wgy.blog.controller;

import com.wgy.blog.entity.BlogQuery;
import com.wgy.blog.entity.Tag;
import com.wgy.blog.service.BlogService;
import com.wgy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable("id") Long id, Model model){
        //System.out.println(id);
        List<Tag> tags = tagService.listTagTop(10000);
        if(id==-1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        //session.setAttribute("id",id);
        return "tags";
    }

    /*@GetMapping("/types")
    public String types(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model, HttpSession session){
        List<Type> types = typeService.listTypeTop(10000);
        Long id = (Long) session.getAttribute("id");
        System.out.println(id);
        if(id==-1){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }*/
}
