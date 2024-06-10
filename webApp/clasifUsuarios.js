/**
 * 
 */
function llamada() {
    fetch('ServClasifUsuarios')
        .then(response => response.json())
        .then(data => mostrar(data))
        .catch(error => console.error('Error:', error));
}

function mostrar(resultados) {
    let html = "<table>";
    html += "<tr><Posición</tr><th>Nombre de usuario</th><th>Puntuacion</th></tr>";

    for (let i = 0; i < resultados.length; i++) {
        let usuario = resultados[i];
        html += `<tr>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.puntuacion}</td>
                 </tr>`;
    }
    html += "</table>";
    document.getElementById("clasificacionUsuarios").innerHTML = html;
}

window.onload = function() {
    llamada();
};
