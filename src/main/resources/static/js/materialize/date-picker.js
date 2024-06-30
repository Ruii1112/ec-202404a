'use strict'

document.addEventListener('DOMContentLoaded', function () {
  var elems = document.querySelectorAll('.datepicker')
  const today = new Date()
  const tomorrow = new Date()
  const maxDate = new Date()
  tomorrow.setDate(today.getDate() + 1)
  maxDate.setDate(today.getDate() + 7)

  const options = {
    autoClose: true,
    format: 'yyyy-mm-dd',
    defaultDate: tomorrow,
    setDefaultDate: true,
    minDate: today,
    maxDate,
  }
  var instances = M.Datepicker.init(elems, options)
})
