package com.example.controller;

import com.example.common.SortType;
import com.example.domain.Item;
import com.example.service.ShowItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

/**
 * 商品情報を操作するコントローラ.
 *
 * @author krkrHotaru
 */

@Controller
@RequestMapping("/")
public class ShowItemListController {

    @Autowired
    private ShowItemListService showItemListService;

    /**
     * 商品一覧画面を表示する.
     *
     * @param searchWord 検索ワード
     * @param sortType   並び順のタイプを指定する番号
     * @param page       ページ数
     * @param model      Requestスコープの準備
     * @return 商品一覧画面
     */
    @GetMapping("")
    public String toItemList(String searchWord, Integer sortType, Integer page, Model model) {
        searchWord = searchWord == null ? "" : searchWord;
        sortType = sortType == null ? 0 : sortType;
        page = page == null ? 0 : page;

        if (SortType.of(sortType) == null) {
            model.addAttribute("orderError", "存在しない並び替えが選択されました。初期画面を表示しています。");
            sortType = 0;
        }

        int cntRows = showItemListService.cntRowsBySearchedItems(searchWord);
        int maxPage = (int) (ceil((double) cntRows / 12));
        if (page != 0 && (maxPage < page || page < 0)) {
            model.addAttribute("pageError", "存在しないページへの遷移が行われました。初期画面を表示しています。");
            page = 1;
        }

        List<Item> itemList = showItemListService.showItemsSearchedBySWord(searchWord, sortType, page);
        if (itemList.isEmpty()) {
            model.addAttribute("searchError", "検索内容での該当結果は0件でした。初期画面を表示しています。");
        }
        if (model.getAttribute("orderError") != null || model.getAttribute("pageError") != null || model.getAttribute("searchError") != null) {
            itemList = showItemListService.showItemList(sortType, page);
            cntRows = showItemListService.cntRowsAllItems();
            maxPage = (int) (ceil((double) cntRows / 12));
            searchWord = "";
        }

        List<Integer> pages = new ArrayList<>();
        for (int i = 0; i < maxPage; i++) {
            pages.add(i + 1);
        }
        model.addAttribute("currentPage", page==0 ? 1 : page);
        model.addAttribute("pages", pages);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("sortType", sortType);
        model.addAttribute("itemList", itemList);
        return "item-list";
    }

}
