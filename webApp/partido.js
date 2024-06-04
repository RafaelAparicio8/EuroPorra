function llamada() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    let resultados = JSON.parse(xhr.responseText);
                    mostrar(resultados); 
                } catch (e) {
                    console.error("Error parsing JSON: ", e);
                }
            }
        }
    };

    xhr.open("GET", "ServPartido", true); // Solicitud asíncrona
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
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
                 </tr>`;
    }
    html += "</table>";
    document.getElementById("listadoPartidos").innerHTML = html;
}

window.onload = function() {
    llamada();
}
