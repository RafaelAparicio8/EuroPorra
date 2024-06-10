function llamada() {
    fetch('ServAdminPartido')
        .then(response => response.json())
        .then(data => mostrar(data))
        .catch(error => console.error('Error:', error));
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
    var results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function mostrar(resultados) {
    let html = "<table>";
    html += "<tr><th>ID Partido</th><th>Equipo A</th><th>Resultado A</th><th>Equipo B</th><th>Resultado B</th></tr>";

    for (let i = 0; i < resultados.length; i++) {
        let partido = resultados[i];
        html += `<tr>
                    <td>${partido.idPartido}</td>
                    <td>${partido.nomEquipoA}</td>
                    <td>${partido.resultEquipoA}</td>
                    <td>${partido.nomEquipoB}</td>
                    <td>${partido.resultEquipoB}</td>
                    <td><button class="update-button" onclick="location.href='editarPartidos.html?id=${partido.idPartido}&op=2'">Actualizar</button></td>
                 </tr>`;
    }
    html += "</table>";
    document.getElementById("listadoPartidos").innerHTML = html;
}

window.onload = function() {
    llamada();
};

