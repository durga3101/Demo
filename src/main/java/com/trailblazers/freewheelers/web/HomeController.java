package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.UpdateDatabasePassword;
import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    private ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, @ModelAttribute("item") Item item, HttpServletRequest request) {
        if (request.getSession().getAttribute("itemForReserve") != null) {
            request.getSession().setAttribute("itemForReserve", null);
        }
        model.addAttribute("items", itemService.getItemsWithNonZeroQuantity());
        return "home";
    }

    @RequestMapping(value = "/encryption" ,method = RequestMethod.GET)
        public  void post(Model model){
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        new UpdateDatabasePassword(mapper, sqlSession).updateDatabaseToEncryptPassword();
        System.out.println("button clicked............................");
    }

}

