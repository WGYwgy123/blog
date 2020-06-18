package com.wgy.blog.controller;

import com.wgy.blog.entity.Blog;
import com.wgy.blog.service.BlogService;
import com.wgy.blog.service.TagService;
import com.wgy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        for (Blog b : blogService.listBlog(pageable)
                ) {
            //System.out.println(b.getDescription());
        }
        model.addAttribute("page",blogService.listBlog(pageable));
        //System.out.println("111111");
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam(value = "query") String query, Model model){
        model.addAttribute("page",blogService.listBlog(query,pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/search")
    public String search(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id, Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));

        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        System.out.println(11111);
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }

    @GetMapping("/admin/footer/newblog")
    public String adminNewblogs(Model model) {
        System.out.println(1111);
        model.addAttribute("adminnewblogs", blogService.listRecommendBlogTop(3));
        return "/admin/_fragments :: adminnewblogList";
    }
}
