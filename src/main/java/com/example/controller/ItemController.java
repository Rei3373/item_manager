package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	private final ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	//商品一覧表示
	@GetMapping
	public String index(Model model) {
		List<Item> items = this.itemService.findAll();
		model.addAttribute("items", items);
		return "item/index";
	}
	
	//商品登録ページ表示用
	@GetMapping("/toroku")
	public String torokuPage(@ModelAttribute("itemForm") ItemForm itemForm) {
		
		return "item/torokuPage";
	}
	
	//商品登録の実行
	@PostMapping("/toroku")
	public String toroku(ItemForm itemForm) {
		return "redirect:/item";
	}
	
	//商品編集ページ
	@GetMapping("/henshu/{id}")
	public String henshuPage(@PathVariable("id")Integer id, Model model, @ModelAttribute("itemForm")ItemForm itemForm ) {
		
		return "redirect:/item";
	}
	
	//商品編集の実行
	@PostMapping("/henshu/{id}")
	public String henshu(@PathVariable("id")Integer id, @ModelAttribute("itemForm")ItemForm itemForm) {
		
		return "redirect:/item";
	}
	
	//商品削除の実行
	@PostMapping("/sakujo/{id}")
	public String sakujo(@PathVariable("id")Integer id) {
		
		return "redirect:/item";
	}
}
