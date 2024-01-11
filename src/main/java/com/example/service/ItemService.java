package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.repository.ItemRepository;

@Service
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	//データの全件取得
	public List<Item> findAll(){
		return this.itemRepository.findAll();
	}
	
	//データ保存用のメソッド
	public Item save(ItemForm itemForm) {
		//Entityクラスをインスタンス生成
		Item item = new Item();
		//フィールドのセット
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		// カテゴリIDをセットする
        item.setCategoryId(itemForm.getCategoryId());
        //新規登録時は在庫数に0をセットする
        item.setStock(0);
		//repository.saveメソッドを使ってデータの保存を行う
		return this.itemRepository.save(item);
	}
	
	//idカラムを利用してデータを検索する
	public Item findById(Integer id) {
	    Optional<Item> optionalItem = this.itemRepository.findById(id);
	    Item item  = optionalItem.get();
	    return item;
	}
	
	//データ更新用のメソッドです
	public Item update(Integer id, ItemForm itemForm) {
		Item item = this.findById(id);
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        // カテゴリIDをセットする
        item.setCategoryId(itemForm.getCategoryId());
        return this.itemRepository.save(item);
	}
	
	//データ削除
	public Item delete(Integer id) {
		Item item =this.findById(id);
		item.setDeletedAt(LocalDateTime.now());
		return this.itemRepository.save(item);
	}
	
	//findByDeletedAtIsNull()メソッドを呼び出す
	public List<Item> fingByDeletedAtIsNull(){
		return this.itemRepository.findByDeletedAtIsNull();
	}
	
	//入荷処理
	public Item nyuka(Integer id, Integer inputValue) {
		Item item = this.findById(id);
		item.setStock(item.getStock() + inputValue);
		return this.itemRepository.save(item);
	}
	//出荷処理
	public Item shukka(Integer id, Integer inputValue) {
		Item item = this.findById(id);
		if(inputValue <= item.getStock()) {
			item.setStock(item.getStock() - inputValue);
		}
		return this.itemRepository.save(item);
	}
}
