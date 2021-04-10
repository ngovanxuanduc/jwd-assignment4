package fa.training.blog.controller;

import fa.training.blog.payload.request.PostContentReq;
import fa.training.blog.payload.response.HomeContentResp;
import fa.training.blog.service.HomeService;
import fa.training.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MainController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public HomeContentResp getHome(){
        return homeService.getHomPubliceContent();
    }

//    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createpost")
    public String createPost(@Valid @ModelAttribute PostContentReq postContentReq, BindingResult br){
        if (br.hasErrors()){
//            br.getAllErrors().forEach((error) -> {
//                String fieldName = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                System.out.println(fieldName+" : "+errorMessage);
//            });
            return "doan nay tra ve trang create Post Method: GET";
        }
        //TODO: lay du lieu luu vao DB
        System.out.println("Luu Xong: "+postService.createPost(postContentReq));

        return postContentReq.toString();

    }

}
