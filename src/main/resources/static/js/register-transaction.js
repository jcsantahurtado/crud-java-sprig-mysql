
async function registerTransaction() {

  let data = {};

  data.date = document.getElementById('date').value;
  data.description = document.getElementById('description').value;
  data.value = document.getElementById('value').value;

  console.log(data);

  const request = await fetch('api/createTransaction', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.token}`
    },
    body: JSON.stringify(data)
  });
  alert('La transacción fue registrada con exito!');
  window.location.href = 'transactions.html';
}

document.getElementById("save").addEventListener("click", function (event) {
  event.preventDefault();
  registerTransaction();
});
