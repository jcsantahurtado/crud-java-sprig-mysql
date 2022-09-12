let url = new URL(window.location);
let id = url.searchParams.get('id');

async function getInfo() {

  if (id == null) {
    alert('Error! Será redirigido a la tabla Transacciones...');
    window.location.href = 'transactions.html';
  }

  const request = await fetch('api/transactionDetails/' + id, {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });

  const transaction = await request.json();

  const date = document.getElementById('date');
  const description = document.getElementById('description');
  const amount = document.getElementById('value');

  date.value = transaction.date;
  description.value = transaction.description;
  amount.value = transaction.value;
}

getInfo();

async function updateTransaction() {

  let data = {};

  data.date = document.getElementById('date').value;
  data.description = document.getElementById('description').value;
  data.value = document.getElementById('value').value;

  console.log(data);

  const request = await fetch('api/updateTransaction/' + id, {
    method: 'PUT',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  });
  alert('La transacción fue actualizada con exito!');
  window.location.href = 'transactions.html';
}

document.getElementById("save").addEventListener("click", function (event) {
  event.preventDefault();
  updateTransaction();
});
