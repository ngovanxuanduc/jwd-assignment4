package fa.training.blog.controller;

import fa.training.blog.service.HomeService;
import fa.training.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

//@RestController
@Controller
public class MainController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private PostService postService;

    @GetMapping(value = {"/home", "/"})
    public String getHome(Model model, HttpSession session, Principal principal) {
        homeService.getHomPublicContent().getPosts().forEach(System.out::println);
        model.addAttribute("content", homeService.getHomPublicContent());
        String name = null;
        if (principal != null) {
            name = principal.getName();
        }
        session.setAttribute("user", name);
        return "home";
    }



    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }


}
