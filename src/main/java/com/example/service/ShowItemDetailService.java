package com.example.service;

import com.example.domain.Item;
import com.example.domain.Review;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品の詳細表示処理のサービス.
 *
 * @author rui.inoue
 */
@Service
@Transactional
public class ShowItemDetailService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * idに基づく商品の表示処理.
     *
     * @param id 商品id
     * @return 商品情報
     */
    public Item showItem(Integer id){
        Item item = itemRepository.findById(id);
        List<Topping> toppingList = toppingRepository.findAll();
        List<Review> reviewList = reviewRepository.findByItemId(item.getId());
        item.setToppingList(toppingList);
        item.setReviewList(reviewList);

        return item;
    }
}
