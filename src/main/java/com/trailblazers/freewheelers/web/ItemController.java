package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.model.ItemValidator;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(ItemController.ITEM_PAGE)
public class ItemController{

	static final String ITEM_PAGE = "/item";
	static final String ITEM_LIST_PAGE = "/itemList";

    ItemService itemService = new ItemServiceImpl();
	ItemValidator itemValidation = new ItemValidator();

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute Item item) {
        ItemGrid itemGrid = new ItemGrid(itemService.findAll());
		model.addAttribute("itemGrid", itemGrid);
        model.addAttribute("itemTypes", ItemType.values());
        return ITEM_LIST_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(Model model, @ModelAttribute Item item) {
		Map<String,String> errors = itemValidation.validate(item);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            ItemGrid itemGrid = new ItemGrid(itemService.findAll());
			model.addAttribute("itemGrid", itemGrid);
            model.addAttribute("itemTypes", ItemType.values());
			return ITEM_LIST_PAGE;
		} else {
			itemService.saveItem(item);
		}
		return "redirect:" + ITEM_PAGE;
	}

    @RequestMapping(method = RequestMethod.POST, params="update=Update all enabled items")
	public String updateItem(@ModelAttribute ItemGrid itemGrid) {
		itemService.saveAll(itemGrid.getItems());
		return "redirect:" + ITEM_PAGE;
	}

    @RequestMapping(method = RequestMethod.POST, params="delete=Delete all enabled items")
    public String deleteItem( @ModelAttribute ItemGrid itemGrid) {
        itemService.deleteItems(itemGrid.getItems());
        return "redirect:" + ITEM_PAGE;
    }
	
}
