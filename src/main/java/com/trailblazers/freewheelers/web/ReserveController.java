package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.ReserveOrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(ReserveController.URL)
public class ReserveController {

    static final String URL = "/reserve";

    ItemService itemService = new ItemServiceImpl();
    AccountService accountService = new AccountServiceImpl();
    ReserveOrderService reserveOrderService = new ReserveOrderServiceImpl();

    @RequestMapping(method = RequestMethod.GET)
    public void get(Model model) {

        // TODO: get item information from session and show in view
    }

}
