document.addEventListener('DOMContentLoaded', (event) => {
    const urlParams = new URLSearchParams(window.location.search);
    const idUsuario = urlParams.get('id');

    if (idUsuario) {
        fetch(`ServEditarUsuario?id=${idUsuario}`)
            .then(response => response.json())
            .then(data => cargarUsuario(data))
            .catch(error => console.error('Error:', error));
    }

    document.getElementById('formEditar').addEventListener('submit', function(event) {
        event.preventDefault();
        guardarCambios(); // Aquí se debería llamar a la función guardarCambios en lugar de submitForm
    });
});

function cargarUsuario(usuario) {
    document.getElementById('idUsuario').value = usuario.idUsuario;
    document.getElementById('nombre').value = usuario.nombre;
    document.getElementById('contrasena').value = usuario.contrasena;
    document.getElementById('puntuacion').value = usuario.puntuacion;
    document.getElementById('permiso').value = usuario.permiso;
}

function guardarCambios() { // Cambiar submitForm por guardarCambios
    const idUsuario = document.getElementById('idUsuario').value;
    const nombre = document.getElementById('nombre').value;
    const permiso = document.getElementById('permiso').value;
 
    const usuario = {
        idUsuario: idUsuario,
        nombre: nombre,
        permiso: permiso,
    };

    fetch('ServEditarUsuario', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Usuario actualizado:', data);
        alert('Usuario actualizado');
        window.location.href = 'adminUsuario.html';
    })
    .catch(error => console.error('Error:', error));
}

