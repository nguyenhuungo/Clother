// VirtualSelect.init({
//   ele: "select",
// });

function showConfirmModalDeleteDialog(id, name) {
  $("#productName").text(name);
  $("#yesOptionDeleteModalId").attr("href", "/admin/product/delete/" + id);
  $("#deleteModalId").modal("show");
}

function changeIsShowProductDetail(url, idHTML, idItem, isCheck) {
  $("#yesOptionChangeIsShow").attr(
    "href",
    "/admin/product/" + url + "/" + idItem + "/" + !isCheck
  );
  $("#isShowModalId").modal("show");
  $("#notChangeIsShow").attr("onclick", notChangeIsShow(idHTML, isCheck));
}

function notChangeIsShow(idHTML, isCheck) {
  document.getElementById(idHTML).checked = isCheck;
}

function showConfirmModalDialogDeleteAllbyId() {
  $("#confirmationDeleteIds").modal("show");
}

function flexUrlSubmit(url, method, formName) {
  $("#flexUrlTableForm" + formName).attr("action", "/admin/product/" + url);
  $("#flexUrlTableForm" + formName).attr("method", method);
  document.getElementById("flexUrlTableForm" + formName).submit();
}

function toggleProductIds(source) {
  checkboxes = document.getElementsByName("productIds");
  for (var i = 0, n = checkboxes.length; i < n; i++) {
    checkboxes[i].checked = source.checked;
  }
}

// function openBoxCreateImgs(id) {
//   if (document.getElementById(id).isCheck === true) {
//   }
// }

function openPopupIsShowSpeedAddProduct(tenThuocTinh, tenField) {
  document.getElementById("tenThuocTinh").innerHTML = tenThuocTinh;
  document.getElementById("fieldthuocTinhInput").value = tenField;
  if (tenField === "mauSac") {
    var htmlObj = document.getElementById("maMauSacDiv");
    html =
      "<label for='maMauSacInput'>Mã màu sắc:</label><input type='text' class='form-control' name='maMauSac' id='maMauSacInput' />";
    htmlObj.innerHTML = html;
  }
  $("#iShowSpeedModalId").modal("show");
}

// $("#multiple-select-field").select2({
//   theme: "bootstrap-5",
//   width: $(this).data("width")
//     ? $(this).data("width")
//     : $(this).hasClass("w-100")
//     ? "100%"
//     : "style",
//   placeholder: $(this).data("placeholder"),
//   closeOnSelect: false,
// });

function openPopupChangeIsShowFormAddProduct() {
  $("#isShowModalId").modal("show");
}

function changeIsShowFormAddProduct() {
  flexUrlSubmit("changeIsShowFormAddProduct", "post", "AddProduct");
}
