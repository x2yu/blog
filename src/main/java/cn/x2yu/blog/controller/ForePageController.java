package cn.x2yu.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class ForePageController {

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }

    @GetMapping("/archive")
    public ModelAndView archive(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("archive.html");
        return mav;
    }

    @GetMapping("/blog-single")
    public ModelAndView blogsingle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("blogsingle.html");
        return mav;
    }

    @GetMapping("/contact")
    public ModelAndView contact(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("contact.html");
        return mav;
    }

    @GetMapping("/about")
    public ModelAndView about(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("about.html");
        return mav;
    }
}
