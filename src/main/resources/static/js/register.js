// Call the dataTables jQuery plugin
$(document).ready(function () {

});

async function registrarUsuario() {
  let datos = {};
  datos.nombre = document.getElementById('name').value
  datos.apellido = document.getElementById('lastName').value
  datos.email = document.getElementById('email').value
  datos.password = document.getElementById('password').value;
    console.log(datos)

  let repeatPassword = document.getElementById('repeatPassword').value

  if(repeatPassword != datos.password ){
    alert('Las contraseñas no coinciden')
    return;
  }

  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  const usuarios = await request.json();
}
