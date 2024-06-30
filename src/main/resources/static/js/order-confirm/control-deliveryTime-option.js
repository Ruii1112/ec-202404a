'use strict'
;(() => {
  const initMaterializeSelect = () => {
    const elems = document.querySelectorAll('select')
    const instances = M.FormSelect.init(
      elems,
      document.querySelectorAll('select option')
    )
  }
  const updateActiveClassOnDeliveryTimeSelectInputField = () => {
    $('#deliveryTimeSelect').val() === null
      ? $('#deliveryTimeSelectInputField').removeClass('active')
      : $('#deliveryTimeSelectInputField').addClass('active')
  }

  const controlDisableOnOptions = ($options, boolean) => {
    $options.each((_, $option) => $($option).prop('disabled', boolean))
  }
  const today = new Date()
  const $deliveryTimeOptions = $('#deliveryTimeSelect option')

  const $trackingDeliveryTimeOptions = $deliveryTimeOptions.filter(
    (_, $deliveryTimeOption) =>
      $($deliveryTimeOption).val() < today.getHours() + 3
  )

  $('#deliveryTimeSelect').on('change', () => {
    updateActiveClassOnDeliveryTimeSelectInputField()
  })

  $('#deliveryDate').on('change', () => {
    const isSelectedToday =
      new Date($('#deliveryDate').val()).toDateString() === today.toDateString()
    controlDisableOnOptions($trackingDeliveryTimeOptions, isSelectedToday)

    if (
      isSelectedToday &&
      $('#deliveryTimeSelect').val() !== '' &&
      new Date($('#deliveryTimeSelect').val()).getHours() < today.getHours() + 3
    )
      $('#deliveryTimeSelect').val('')

    updateActiveClassOnDeliveryTimeSelectInputField()
    initMaterializeSelect()
  })
})()
