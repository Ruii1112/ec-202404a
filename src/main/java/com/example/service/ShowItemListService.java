package com.example.service;

import com.example.common.SortType;
import com.example.domain.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品情報一覧を持ってくるサービス.
 *
 * @author krkrHotaru
 */

@Service
@Transactional
public class ShowItemListService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * あいまい検索結果の商品情報を12件持ってきます.
     *
     * @param searchWord 検索ワード
     * @param sortType 並び順指定番号
     * @param page ページ番号
     * @return 商品情報リスト
     */
    public List<Item> showItemsSearchedBySWord(String searchWord, Integer sortType, Integer page) {
        String sortStr = null;
        if (sortType != null) {
            sortStr = SortType.of(sortType).getValue();
        }
        Integer offset = null;
        if (page != 0) {
            offset = (page - 1) * 12;
        }
        return itemRepository.findItemsSearchByWordOrderBySortClipByOffset(searchWord, sortStr, offset);
    }

    /**
     * 全商品情報から10件持ってきます.
     *
     * @param sortType 並び順指定番号
     * @param page ページ番号
     * @return 商品情報リスト
     */
    public List<Item> showItemList(Integer sortType,Integer page) {
        String sortStr = null;
        if (sortType != null) {
            sortStr = SortType.of(sortType).getValue();
        }
        Integer offset = null;
        if (page != 0) {
            offset = (page - 1) * 12;
        }
        return itemRepository.findItemsSearchByWordOrderBySortClipByOffset(null, sortStr, offset);
    }

    /**
     * 全商品情報の行数を数えます.
     *
     * @return 全商品数、商品情報がnullの場合は0が返されます。
     */
    public int cntRowsAllItems() {
        return itemRepository.countItemRows(null);
    }

    /**
     * あいまい検索の結果該当した商品情報の行数を数えます.
     *
     * @param searchWord あいまい検索の検索ワード
     * @return 検索結果の商品数、検索結果がnullの場合は0が返されます。
     */
    public int cntRowsBySearchedItems(String searchWord) {
        return itemRepository.countItemRows(searchWord);
    }


}
