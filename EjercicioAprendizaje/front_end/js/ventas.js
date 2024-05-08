



















//funcion para traer los clientes
function cargarVenta() {
    let urlpaciente = "http://localhost:8082/api/v1/venta/";
  
    $.ajax({
      url: urlpaciente,
      type: "GET",
      success: function (result) {
        let cliente = document.getElementById("cliente");
        cliente.innerHTML = "";
        for (let i = 0; i < result.length; i++) {
          let nombreventa = document.createElement("option");
          nombreCliente.value = result[i]["id_cliente"];
          nombreCliente.innerText = nombre_completo_cliente =
            result[i]["nombres_cliente"] +
            " " +
            result[i]["apellidos_cliente"];
            cliente.appendChild(nombreCliente);
        }
      },
    });
  }