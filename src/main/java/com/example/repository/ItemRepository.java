package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	//DELETED_ATがnullのレコードを取得
	public List<Item> findByDeletedAtIsNull();
}
