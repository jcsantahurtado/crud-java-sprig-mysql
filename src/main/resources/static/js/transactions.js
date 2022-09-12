// Call the dataTables jQuery plugin
// Add advanced interaction controls to your HTML tables the free & easy way

loadTransactions();

async function loadTransactions() {

  const request = await fetch('api/transactionsList', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
  const transactions = await request.json();

  let listHtml = '';
  for (let transaction of transactions) {

    let btnDelete = '<a href="#" onclick="deleteTransaction(' + transaction.transactionId + ')" class="btn-delete"><i class="fa-solid fa-trash"></i></a>';
    let btnEdit = '<a href="#" onclick="editTransaction(' + transaction.transactionId + ')" class="btn-edit" id="btn-edit"><i class="fa-solid fa-pen"></i></a>';

    let transactionHtml = '<tr><td>'
                      + transaction.transactionId + '</td><td>'
                      + transaction.date + '</td><td>'
                      + transaction.description + '</td><td>'
                      + transaction.value + '</td><td>'
                      + btnDelete + btnEdit + '</td></tr>';

    listHtml += transactionHtml;
  }
  document.querySelector('#transactions tbody').outerHTML = listHtml;
}

async function deleteTransaction(id) {

    if (!confirm('¿Desea eliminar esta transacción?')) {
        return;
    }

    const request = await fetch('api/deleteTransaction/' + id, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
    });
    location.reload();
}

async function editTransaction(id) {
  window.location.href = 'edit-transaction.html?id=' + id;
}

document.getElementById("new").addEventListener("click", function (event) {
  event.preventDefault();
  window.location.href = 'form-transaction.html';
});
