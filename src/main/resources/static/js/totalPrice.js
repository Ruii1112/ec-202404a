'use strict'

$(function () {
  const updateTotalPrice = () => {
    $('#total-price').text(
      `小計：${new Intl.NumberFormat('ja-JP').format(calc())}円（税抜き）`
    )
  }

  const calc = () => {
    let totalVal = 0
    const size = $('input[name=size]:checked').data('price')
    $('input[name=toppingList]:checked').each((index, element) => {
      totalVal += $(element).data('price')
    })
    const num = $('select[name=quantity]').val()
    return (size + totalVal) * num
  }
  $('#total-price').text(
    `小計：${new Intl.NumberFormat('ja-JP').format(
      $('input[type=radio]:checked').data('price')
    )}円（税抜き）`
  )
  $('input[name=size], input[name=toppingList], select[name=quantity]').on(
    'change',
    updateTotalPrice
  )
})
