package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.web.forms.SurveyEntryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;

import static com.trailblazers.freewheelers.web.Session.PURCHASED_ITEMS;

@Controller
@RequestMapping(ReserveController.URL)
public class ReserveController {

    static final String URL = "/reserve";
    private Session session;

    @Autowired
    public ReserveController(Session session){
        this.session = session;
    }

    @ModelAttribute("survey")
    public SurveyEntryForm getSurveyForm() {
        return new SurveyEntryForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest, Model model, Principal principal, Session session) {
        HttpSession httpSession = httpServletRequest.getSession();
//        ItemServiceImpl itemService = new ItemServiceImpl();

        Long order_id= (Long) httpSession.getAttribute("order_id");
        HashMap<Item, Long> result = session.getItemHashMap(PURCHASED_ITEMS, httpSession);
        model.addAttribute("items", result);
        model.addAttribute("orderId",order_id);
    }

}
