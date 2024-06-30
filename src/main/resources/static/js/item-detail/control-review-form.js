'use strict'
;(() => {
  const $openReviewFormButton = $('#openReviewFormButton')
  const $reviewForm = $('#reviewForm')

  const openReviewForm = () => {
    $openReviewFormButton.hide(500)
    $reviewForm.show(500)
  }

  isOpenReviewForm && openReviewForm()
  $openReviewFormButton.on('click', openReviewForm)
})()
