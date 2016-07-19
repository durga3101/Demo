package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.web.forms.SurveyEntryForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(ReserveController.URL)
public class ReserveController {

    static final String URL = "/reserve";

    @ModelAttribute("survey")
    public SurveyEntryForm getSurveyForm() {
        return new SurveyEntryForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest, Model model, Principal principal ) {
        HttpSession session = httpServletRequest.getSession();
        ItemServiceImpl itemService = new ItemServiceImpl();

        HashMap<Long, Long> map = (HashMap) session.getAttribute("purchasedItems");

        HashMap<Item, Long> result = new HashMap<>();

        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            Long id = entry.getKey();
            Long quantity = entry.getValue();
            Item item = itemService.get(id);
            result.put(item, quantity);
        }

        model.addAttribute("items", result);
    }

}
