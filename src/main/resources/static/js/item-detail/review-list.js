'use strict'

const $reviewListWrap = $('#review-content-wrap')

const $reviewContents = reviewList.map(
  ({ negativeValue, neutralValue, positiveValue, content }) => {
    const $reviewContent = $('<div></div>', { class: 'review-content' }).append(
      $('<div></div>', { class: 'review-content__text-wrap' }).append(
        $('<p></p>').append(content)
      )
    )
    const $numberWrap =
      positiveValue > negativeValue && positiveValue > neutralValue
        ? $('<div></div>', {
            class: 'review-number-wrap review-number-wrap--positive',
          }).append(
            $('<span></span>').append('ポジティブ'),
            $('<span></span>').append(Math.round(positiveValue * 10000) / 100)
          )
        : negativeValue > neutralValue
        ? $('<div></div>', {
            class: 'review-number-wrap review-number-wrap--negative',
          }).append(
            $('<span></span>').append('ネガティブ'),
            $('<span></span>').append(Math.round(negativeValue * 10000) / 100)
          )
        : $('<div></div>', {
            class: 'review-number-wrap review-number-wrap--neutral',
          }).append(
            $('<span></span>').append('ニュートラル'),
            $('<span></span>').append(Math.round(neutralValue * 10000) / 100)
          )
    $reviewContent.prepend($numberWrap)
    return $reviewContent
  }
)

$reviewListWrap.append(...$reviewContents)
