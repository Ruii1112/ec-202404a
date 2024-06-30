"use strict";

$(function() {
    const $bookmarkIcon = $(".bookmark-icon");
    const bookmarkUrl = "http://localhost:8080/ec-202404a/bookmark";
    const bookmarkColor = "blue";

    if ($bookmarkIcon.data("flag")) {
        $bookmarkIcon.css("color", bookmarkColor);
    }

    $bookmarkIcon.on("click", () => {
        const isBookmarked = $bookmarkIcon.data("flag");

        $bookmarkIcon.css("color", isBookmarked ? "" : bookmarkColor);
        $bookmarkIcon.data("flag", !isBookmarked);

        $('#popup').text(isBookmarked ? "ブックマークが解除されました" : "ブックマークされました");
        $('#popup').fadeIn().delay(1000).fadeOut();

        $.ajax({
            url: bookmarkUrl,
            type: "post",
            dataType: "json",
            data: { itemId: $bookmarkIcon.data("itemid") },
            async: true
        })
        .done(data => {
            console.log(data);
        })
        .fail((xhr, textStatus, errorThrown) => {
            console.error(xhr, textStatus, errorThrown);
        });
    });

    $(".not-bookmark-icon").on("click", () => {
        window.location.href = 'http://localhost:8080/ec-202404a/login';
    })
});
