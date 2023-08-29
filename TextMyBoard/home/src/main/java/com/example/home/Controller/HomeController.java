package com.example.home.Controller;

import com.example.home.Entity.Home;
import com.example.home.Form.HomeForm;
import com.example.home.Form.LoginForm;
import com.example.home.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService service;

//    form-backing bean의 초기화
    @ModelAttribute
    public HomeForm setUpForm(){
        HomeForm form = new HomeForm();
        return form;
    }
    @ModelAttribute
    public LoginForm setUpLoginForm(){
        LoginForm form = new LoginForm();
        return form;
    }
    @GetMapping
    public String blogPg(Model model,HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("msg","환영");
        return "blog";
    }
//    홈페이지
    @GetMapping("/login/register")
    public String registerPg(HomeForm homeForm,Model model){

        return "register";
    }
    @PostMapping("/login/register")
    public String register_Do(@Validated HomeForm homeForm,BindingResult result,Model model,RedirectAttributes redirectAttributes){
        Home home = new Home();
        home.setNum(homeForm.getNum());
        home.setId(homeForm.getId());
        home.setPsw(homeForm.getPsw());
        home.setUser_name(homeForm.getUser_name());
        home.setEmail(homeForm.getEmail());
        home.setUser_time(Timestamp.valueOf(LocalDateTime.now()));


        if(!result.hasErrors()){
            service.insertMember(home);
            redirectAttributes.addFlashAttribute("msg","가입이 완료 되었습니다!");
            return "redirect:/home/login";
        }else {
            redirectAttributes.addFlashAttribute("fail","가입 오류");
            return "redirect:/home/login";

        }

    }
//    회원 가입 완료.
    
    @GetMapping("/login")
    public String loginPg(LoginForm loginForm ,Model model){

        return "login";
    }
    @PostMapping("/login")
    public String login_Do(@Validated LoginForm loginForm,Model model, BindingResult result, RedirectAttributes redirectAttributes
    ,HttpServletRequest httpServletRequest, HomeForm homeForm) {
        Home home =new Home();
        home.setNum(loginForm.getNum());
        home.setId(loginForm.getId());
        home.setPsw(loginForm.getPsw());
        model.addAttribute("list",loginForm);




        Optional<Home> homeLogin = service.selectOneByHomeId(loginForm.getId(), loginForm.getPsw());


        if(!homeLogin.isEmpty()){

            HttpSession session =httpServletRequest.getSession();
            loginForm.setNum(homeLogin.get().getNum());
            System.out.println("test");
            System.out.println(loginForm);
            session.setAttribute("user",loginForm);

            model.addAttribute("msg","환영합니다! 썬블로그입니다");


            redirectAttributes.addFlashAttribute("loginComplete","로그인 성공");
            System.out.println("값이맞음");


            return "blogHome";

                }else {
                    model.addAttribute("msg","계정을 입력해주세요");
                    System.out.println("값이틀림");
                    redirectAttributes.addFlashAttribute("loginFail","아이디/패스워드 확인해주세요");
                    return "redirect:/home/login";


                }






    }
    @GetMapping("/login/logout")
    public String logout_Do(LoginForm loginForm,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("logout","로그아웃되었습니다");
        request.getSession().invalidate();


        return "blog";
    }
//    로그인  & 로그아웃
    @GetMapping("/login/members")
    public String membersPg(HomeForm homeForm ,RedirectAttributes redirectAttributes,HttpServletRequest httpServletRequest,Model model){
        Iterable<Home> list = service.selectAll();
        HttpSession session = httpServletRequest.getSession();

        model.addAttribute("list",list);
//        isADMIN 메소드 참인 애만 거짓은 일반서비스-
        return "members";
    }

    @PostMapping("/login/members")
    public String members_Do(HomeForm homeForm,HttpServletRequest httpServletRequest){
//


        return "members";
    }
//    멤버관리
    @GetMapping("/login/write_list")
    public String write_List(@Validated LoginForm loginForm, HttpServletRequest httpServletRequest, Model model,HomeForm homeForm){

        Home home = new Home();
        home.setNum(loginForm.getNum());
        home.setId(loginForm.getId());
        home.setPsw(loginForm.getPsw());
        model.addAttribute("user",loginForm);

        Optional<Home> homeLogin = service.selectOneByHomeId(loginForm.getId(), loginForm.getPsw());
        model.addAttribute("list",homeLogin);
        System.out.println("test2");

        HttpSession session =httpServletRequest.getSession();
        model.addAttribute("name","님");
        model.addAttribute("logout1","로그아웃");

        System.out.println(session.getAttribute("user"));
        System.out.println(" 자유게시판 =값이 맞음");
//

//원인을 모를때 깨끗하게

        return "boardPg";

    }
//    자유게시판 목록

    @GetMapping("/login/write")
    public String writePg(HomeForm homeForm,@Validated LoginForm loginForm, HttpServletRequest httpServletRequest,Model model){
        Home home = new Home();
        home.setNum(homeForm.getNum());
        home.setUser_name(homeForm.getUser_name());
        home.setTitle(homeForm.getTitle());
        home.setContent(homeForm.getContent());
        home.setUser_time(Timestamp.valueOf(LocalDateTime.now()));

        homeForm.setNewMember(true);

        Iterable<Home> list = service.selectAll();
        model.addAttribute("list",list);

        return "writePg";

    }
    @PostMapping("/login/write")
    public String write_Do(HomeForm homeForm, @Validated LoginForm loginForm, HttpServletRequest httpServletRequest, Model model
            ,BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Home home = new Home();
        home.setNum(homeForm.getNum());
        home.setTitle(homeForm.getTitle());
        home.setContent(homeForm.getContent());
        home.setUser_name(homeForm.getUser_name());
        home.setUser_time(Timestamp.valueOf(LocalDateTime.now()));
        home.setId(homeForm.getId());
        home.setPsw(homeForm.getPsw());
        home.setEmail(homeForm.getEmail());

        if(!bindingResult.hasErrors()){
            HttpSession session = httpServletRequest.getSession();
            Optional<Home> id = service.selectOneByHomeId(loginForm.getId(),loginForm.getPsw());
            loginForm.setNum(loginForm.getNum());
            session.setAttribute("user",loginForm);
            service.updateMember(home);
            System.out.println("작성했음");
        }else {
            redirectAttributes.addFlashAttribute("msg","오류");
            System.out.println("오류생김");
            return "redirect:boardPg";
        }

        return "boardPg";
    }
//글쓰기

}
