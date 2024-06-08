/**
 * 
 */
function llamada() {
    fetch('ServEditarUsuario') // Cambia ServEditarUsuario por ServListarUsuarios
        .then(response => response.json())
        .then(data => mostrar(data))
        .catch(error => console.error('Error:', error));
}

function mostrar(resultados) {
    let html = "<table>";
    html += "<tr><th>ID</th><th>Nombre de usuario</th><th>Puntuacion</th><th>Permiso</th></tr>";

    for (let i = 0; i < resultados.length; i++) {
        let usuario = resultados[i];
        html += `<tr>
                    <td>${usuario.idUsuario}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.puntuacion}</td>
                    <td>${usuario.permiso}</td>
                    <td><a href='editarUsuario.html?id=${usuario.idUsuario}&op=2'>Editar</a></td>
                    <td><a href='#' onclick='eliminarUsuario(${usuario.idUsuario})'>Eliminar</a></td>
                 </tr>`;
    }
    html += "</table>";
    document.getElementById("listadoUsuarios").innerHTML = html;
}

function eliminarUsuario(idUsuario) {
    if (confirm("¿Seguro de que quieres eliminar este usuario? El usuario se eliminará de manera permanente.")) {
        fetch(`ServUsuario?action=delete&id=${idUsuario}`)
            .then(response => response.json())
            .then(data => {
                if (data.status === "success") {
                    llamada();
                } else {
                    alert("Error al eliminar el usuario");
                }
            })
            .catch(error => console.error('Error:', error));
    }
}


window.onload = function() {
    llamada();
};
