package com.lxinet.jeesns.modules.cms.web.manage;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.core.utils.MemberUtil;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.cms.entity.ArticleCate;
import com.lxinet.jeesns.modules.cms.entity.Article;
import com.lxinet.jeesns.modules.cms.service.IArticleCateService;
import com.lxinet.jeesns.modules.cms.service.IArticleService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Controller("manageArticleController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class ArticleController extends BaseController {
    private final static String MANAGE_FTL_PATH = "/manage/cms/article/";
    @Resource
    private IArticleCateService articleCateService;
    @Resource
    private IArticleService articleService;

    @RequestMapping("${managePath}/cms/index")
    @Before(AdminLoginInterceptor.class)
    public String index(String key,Integer cateid,Model model) {
        if(cateid == null){
            cateid = 0;
        }
        List<ArticleCate> cateList = articleCateService.list();
        Page page = new Page(request);
        ResponseModel responseModel = articleService.listByPage(page,key,cateid,2);
        model.addAttribute("model",responseModel);
        model.addAttribute("cateList",cateList);
        model.addAttribute("key",key);
        model.addAttribute("cateid",cateid);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value="${managePath}/cms/article/add",method = RequestMethod.GET)
    public String add(Model model) {
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping(value="${managePath}/cms/article/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseModel(-1,getErrorMessages(bindingResult));
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel responseModel = articleService.save(loginMember,article);
        if(responseModel.getCode() == 0){
            responseModel.setCode(3);
        }
        return responseModel;
    }

    @RequestMapping(value="${managePath}/cms/article/list",method = RequestMethod.GET)
    public String list(String key,Integer cateid,Model model) {
        if(cateid == null){
            cateid = 0;
        }
        Page page = new Page(request);
        ResponseModel responseModel = articleService.listByPage(page,key,cateid,2);
        model.addAttribute("model",responseModel);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value="${managePath}/cms/article/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model){
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        Article article = articleService.findById(id);
        model.addAttribute("article",article);
        return MANAGE_FTL_PATH + "/edit";
    }

    @RequestMapping(value="${managePath}/cms/article/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@Valid Article article,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            new ResponseModel(-1,getErrorMessages(bindingResult));
        }
        if(article.getId() == null){
            return new ResponseModel(-2);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel responseModel = articleService.update(loginMember,article);
        if(responseModel.getCode() == 0){
            responseModel.setCode(3);
        }
        return responseModel;
    }


    @RequestMapping(value = "${managePath}/cms/article/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") Integer id){
        ResponseModel response = articleService.delete(id);
        return response;
    }

    @RequestMapping(value = "${managePath}/cms/article/audit/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object audit(@PathVariable("id") Integer id){
        ResponseModel response = articleService.audit(id);
        return response;
    }

}