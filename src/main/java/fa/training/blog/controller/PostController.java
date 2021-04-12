package fa.training.blog.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import fa.training.blog.payload.request.PostContentReq;
import fa.training.blog.service.HomeService;
import fa.training.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private PostService postService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/managepost")
    public String getManagePost(Model model) {
        model.addAttribute("content", homeService.getAllContentOfAuthor());
        return "manage-post";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/createpost")
    public String createPost(Model model) {
        model.addAttribute("post", new PostContentReq());
        return "create-post";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createpost")
    public String createPost(@Valid @ModelAttribute PostContentReq postContentReq, BindingResult br) {
        if (br.hasErrors()) {
//            br.getAllErrors().forEach((error) -> {
//                String fieldName = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                System.out.println(fieldName+" : "+errorMessage);
//            });
//            return "doan nay tra ve trang create Post Method: GET";
            return "redirect:/createpost";
        }
//        System.err.println(postContentReq);
        //TODO: lay du lieu luu vao DB
        System.out.println("Luu Xong: " + postService.createPost(postContentReq));

//        return postContentReq.toString();
        return "redirect:/home";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/delepost"})
    public String deletepost(@RequestParam(value = "id", required = false) Integer id) {
        System.err.println("-----------id" + id);
        if (id != null) {
            postService.deletePost(id);
        }
        return "redirect:/managepost";

    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/editpost"})
    public String edit(Model model , @RequestParam(value = "id",  required = false) Integer id , HttpSession session) {
    	//kiem tra id, kiem tra co phai bai post cua thang author hay ko
//    	System.err.println("-----------id" + id);
        if (id == null) {
            return "redirect:/managepost";
        }
//        System.err.println(postService.getPostContentReqByIdAndAuthorID(id));
        model.addAttribute("post", postService.getPostContentReqByIdAndAuthorID(id));
        return "create-post";
    }
}
