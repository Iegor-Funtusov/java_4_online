package ua.com.alevel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.PostFacade;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.data.PostData;

import java.util.List;

@Controller
@RequestMapping("/personal/post")
public class PostController {

    private final PostFacade postFacade;

    public PostController(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @GetMapping("/all")
    public String findAllPost(WebRequest request, Model model) {
        model.addAttribute("pageData", postFacade.findAll(request));
        return "pages/user/personal/post/post_all";
    }

    @GetMapping("/my")
    public String findMyPost(WebRequest request, Model model) {
        model.addAttribute("pageData", postFacade.findAll(request));
        return "pages/user/personal/post/post_my";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id, @RequestParam("reaction") boolean reaction) {
        model.addAttribute("postFullData", postFacade.findById(id));
        model.addAttribute("reaction", reaction);
        return "pages/user/personal/post/post_details";
    }

    @GetMapping("/new")
    public String redirectToNewPage(Model model) {
        model.addAttribute("postForm", new PostData());
        return "pages/user/personal/post/post_new";
    }

    @PostMapping("/new")
    public String newPost(@ModelAttribute("postForm") PostData data) {
        postFacade.create(data);
        return "redirect:/personal/post/my";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdatePage(@PathVariable Long id, Model model) {
        model.addAttribute("postForm", postFacade.findById(id));
        return "pages/user/personal/post/post_update";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute("postForm") PostData data, @RequestParam Long id) {
        postFacade.update(data, id);
        return "redirect:/personal/post/my";
    }

    @GetMapping("/like/{id}")
    public String likePost(@PathVariable Long id) {
        postFacade.like(id);
        return "redirect:/personal/post/details/" + id + "?reaction=true";
    }

    @GetMapping("/dislike/{id}")
    public String dislikePost(@PathVariable Long id) {
        postFacade.dislike(id);
        return "redirect:/personal/post/details/" + id + "?reaction=true";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        postFacade.delete(id);
        return "redirect:/personal/post/my";
    }

    @PostMapping("/upload/{id}")
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer id, RedirectAttributes attributes) {
        System.out.println("id = " + id);
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/personal/post/details/" + id + "?reaction=false";
        }
        postFacade.uploadFile(file, id);
        return "redirect:/personal/post/details/" + id + "?reaction=false";
    }

    @PostMapping("/search")
    private String searchBooks(@RequestParam String query, RedirectAttributes ra) {
        ra.addAttribute(WebRequestUtil.SEARCH_MESSAGE_PARAM, query);
        ra.addAttribute("owner", false);
        return "redirect:/personal/post/all";
    }

    @GetMapping("/suggestions")
    private @ResponseBody List<String> allSearchPosts(@RequestParam String query) {
        return postFacade.searchPostMessage(query);
    }
}
