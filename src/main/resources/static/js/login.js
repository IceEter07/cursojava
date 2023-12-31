// Call the dataTables jQuery plugin
$(document).ready(function () {

});

async function iniciarSesion() {
    let datos = {};
    datos.email = document.getElementById('email').value
    datos.password = document.getElementById('password').value;
    console.log(datos);

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const respuesta = await request.text();
    if(respuesta != 'FAIL'){
        //Guardar el token en el servidor.j
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'usuarios.html'
    }
    else{
        alert('Las credenciales son incorrectas');
    }
}
