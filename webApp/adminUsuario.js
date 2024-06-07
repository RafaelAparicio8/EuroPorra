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
                    <td><a href='eliminarUsuario.html?id=${usuario.idUsuario}&op=2'>Eliminar</a></td> <!-- Corrige idUsario a idUsuario -->
                 </tr>`;
    }
    html += "</table>";
    document.getElementById("listadoUsuarios").innerHTML = html;
}

window.onload = function() {
    llamada();
};
