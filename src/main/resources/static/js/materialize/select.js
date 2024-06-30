'use strict'

document.addEventListener('DOMContentLoaded', function () {
  var elems = document.querySelectorAll('select')
  var instances = M.FormSelect.init(
    elems,
    document.querySelectorAll('select option')
  )
})
