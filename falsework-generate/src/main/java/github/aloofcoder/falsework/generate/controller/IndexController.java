package github.aloofcoder.falsework.generate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 网站导航
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-12 23:37:00
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String toIndex(Model model) {
        model.addAttribute("name", "hanle");
        return "index";
    }
}
