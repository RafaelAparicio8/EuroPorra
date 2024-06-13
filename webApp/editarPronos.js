window.onload = function() {
    cargarPronosticos();
    
    let btnEnviarPronostico = document.getElementById("btnEnviarPronostico");
    if (btnEnviarPronostico) {
        btnEnviarPronostico.addEventListener("click", function(event) {
            event.preventDefault(); // Evitar el envío del formulario por defecto
            enviarPronosticos();
        });
    } else {
        console.error("Botón #btnEnviarPronostico no encontrado");
    }
};

function cargarPronosticos() {
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
            } else if (xhr.status === 401) {
                alert("No autorizado. Por favor, inicia sesión nuevamente.");
                window.location.href = 'login.html'; // Redirigir al usuario a la página de inicio de sesión
            } else {
                console.error("Error en la solicitud: ", xhr.status, xhr.statusText);
            }
        }
    };

    xhr.open("GET", "ServPartido", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}

function mostrar(resultados) {
    let html = "<table>";
    html += "<tr><th>Partido</th><th>Equipo Local</th><th>Resultado Local</th><th>Resultado Visitante</th><th>Equipo Visitante</th></tr>";
    
    for (let i = 0; i < resultados.length; i++) {
        let partido = resultados[i];
        html += `<tr>
                    <td>${partido.idPartido}</td>
                    <td>${partido.nomEquipoA}</td>
                    <td><input type="number" name="resultado_${partido.idPartido}_A" value="${partido.resultEquipoA}"></td>
                    <td><input type="number" name="resultado_${partido.idPartido}_B" value="${partido.resultEquipoB}"></td>
                    <td>${partido.nomEquipoB}</td>
                 </tr>`;
    }
    html += "</table>";
    
    let listadoPronosticos = document.getElementById("listadoPronosticos");
    if (listadoPronosticos) {
        listadoPronosticos.innerHTML = html;
    } else {
        console.error("Elemento #listadoPronosticos no encontrado");
    }
}

function enviarPronosticos() {
    let formPronosticos = document.getElementById("formPronosticos");
    if (formPronosticos) {
        const formData = new FormData(formPronosticos);
        const pronosticos = [];

        formData.forEach((value, key) => {
            const [prefix, idPartido, equipo] = key.split('_');
            let pronostico = pronosticos.find(p => p.idPartido === idPartido);
            if (!pronostico) {
                pronostico = { idPartido: idPartido, resultadoA: null, resultadoB: null };
                pronosticos.push(pronostico);
            }
            if (equipo === 'A') {
                pronostico.resultadoA = value;
            } else if (equipo === 'B') {
                pronostico.resultadoB = value;
            }
        });

        fetch('ServPronos', {
            method: 'POST',
            body: JSON.stringify(pronosticos),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (!response.ok) {
                if (response.status === 401) {
                    alert("No autorizado. Por favor, inicia sesión nuevamente.");
                    window.location.href = 'login.html'; // Redirigir al usuario a la página de inicio de sesión
                }
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data.status === 'success') {
                mostrarMensajePronosticoEnviado(pronosticos);
            } else {
                console.error('Error al enviar pronósticos:', data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    } else {
        console.error("Formulario #formPronosticos no encontrado");
    }
}

function mostrarMensajePronosticoEnviado() {
    const mensajePronostico = document.getElementById('mensajePronostico');
    if (mensajePronostico) {
        mensajePronostico.textContent = 'Pronóstico enviado!';
        setTimeout(function() {
            mensajePronostico.textContent = ''; // Limpiar mensaje después de unos segundos
        }, 3000); // Mostrar mensaje por 3 segundos (opcional)
    } else {
        console.error('Elemento #mensajePronostico no encontrado');
    }

}
