async function registerProducer() {

  let data = {};

  data.name = document.getElementById('fname').value;
  data.username = document.getElementById('username').value;
  data.email = document.getElementById('email').value;
  data.password = document.getElementById('pwd').value;
  data.roles = [document.getElementById('sel1').value.toLowerCase()];

  console.log(data.roles);

  let repeatPasswd = document.getElementById('rpwd').value;

  if (repeatPasswd != data.password) {
    alert('La contraseña que escribiste es diferente');
    return;
  }

  const request = await fetch('auth/newUser', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  });
  const response = await request.json();

  alert(response.message);
  window.location.href = 'login.html';
}

document.getElementById("save").addEventListener("click", function (event) {
  event.preventDefault()
  registerProducer()
});
