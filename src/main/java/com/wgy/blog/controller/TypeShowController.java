package com.wgy.blog.controller;

import com.wgy.blog.entity.BlogQuery;
import com.wgy.blog.entity.Type;
import com.wgy.blog.service.BlogService;
import com.wgy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable("id") Long id, Model model){
        System.out.println(id);
        List<Type> types = typeService.listTypeTop(10000);
        if(id==-1){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId", id);
        //session.setAttribute("id",id);
        return "types";
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
