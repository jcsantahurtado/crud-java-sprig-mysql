
async function login() {

  let data = {};

  data.username = document.getElementById('username').value;
  data.password = document.getElementById('pwd').value;

  const request = await fetch('auth/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  });
  const response = await request.json();
  console.log(response);
  if (!response.error) {
    localStorage.token = response.token;
    localStorage.username = response.username;
    window.location.href = 'transactions.html';
  } else {
    alert('Las credencieles son incorrectas. Por favor intente de nuevo.')
  }
}

document.getElementById("login").addEventListener("click", function (event) {
  event.preventDefault()
  login();
});
