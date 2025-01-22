package com.example.controller;

import com.example.domain.LoginUser;
import com.example.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ブックマーク機能の処理を制御するコントローラ.
 *
 * @author rui.inoue
 */
@RestController
@RequestMapping("/bookmark")
public class BookmarkApiController {

    @Autowired
    private BookmarkService bookmarkService;

    /**
     * ブックマーク.
     *
     * @param itemId 商品のid
     * @param loginUser ログインしているユーザ
     * @return 成功かどうか
     */
    @PostMapping()
    @Operation(
            summary = "ブックマーク",
            description = "ユーザが商品に対してブックマークボタンを押し、ブックマークを切り替える"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "ブックマークが成功しました",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            schema = @Schema(implementation = BookmarkResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "無効なリクエストデータ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<?> bookmark(
            @Parameter(description = "ブックマーク対象の商品ID", required = true)
            Integer itemId,
            @AuthenticationPrincipal
            @Parameter(hidden = true)
            LoginUser loginUser
    ){
        try {
            bookmarkService.bookmark(loginUser, itemId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new BookmarkResponse("success"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("無効なリクエストデータ：") + e.getMessage());
        }
    }

    static class BookmarkResponse {
        private String status;

        public BookmarkResponse(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
