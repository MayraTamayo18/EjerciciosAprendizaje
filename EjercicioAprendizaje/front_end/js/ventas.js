//se almacena la url de la api
let url="http://localhost:8080/api/v1/venta/";
function listarventa() {
    var busqueda = document.getElementById("buscar").value;
    var urlBusqueda = url;
    if (busqueda!=""){
        urlBusqueda+="busquedafiltro/"+busqueda;
    }   
    $.ajax({
        url:urlBusqueda,
        type: "GET",
        success: function(result){//success: funcion que se ejecuta cusndo la peticion tiene exito
            console.log(result);
            let curpoTablaVenta = document.getElementById("curpoTablaVenta");
            curpoTablaVenta.innerHTML="";
            for (let i = 0; i < result.length; i++) {
               //se crea una etiqueta tr por cada registro
                let trRegistro = document.createElement("tr");//fila por cada registro de la tabla
                let celdaId = document.createElement("td");
                let celdaTotal=  document.createElement("td");
                let celdaFecha = document.createElement("td");
                let celdaEstado = document.createElement("td");
                // let celdaEditar = document.createElement("td");
                
                //almacenamos en valor
                
                celdaId.innerText = result[i]["id_venta"];
                celdaTotal.innerText = result[i]["nombre_venta"];
                celdaFecha.innerText = result[i]["cantidad"];
                celdaEstado.innerText = result[i]["estado"];
                
                //agregando a los td a su respectivo th y agregandolos a la fila

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaTotal);
                trRegistro.appendChild(celdaFecha);
                trRegistro.appendChild(celdaEstado);
                
                //boton editar 
                let celdaOpcion= document.createElement("td");
                let botonEditarVenta= document.createElement("button");
                botonEditarVenta.value=result[i]["id_venta"];
                botonEditarVenta.innerHTML="Editar"; 

                botonEditarVenta.onclick=function(e){
                    $('#exampleModal').modal('show');
                    consultarventaID(this.value); 
                }
                botonEditarVenta.className= "btn btn-primary"

                celdaOpcion.appendChild(botonEditarVenta); 
                trRegistro.appendChild(celdaOpcion);

                curpoTablaVenta.appendChild(trRegistro);//se traen todos los registros

                 //boton desahiblitar- la funcion de deshabilitar se encuentra abajo 
                 let botonDeshabilitarVenta= document.createElement("button");
                 botonDeshabilitarVenta.innerHTML="<i class='fa-solid fa-trash'></i>"; 
                 botonDeshabilitarVenta.className="btn btn-danger"; 
 
                 let ventaIdParaDeshabilitar= result[i]["id_venta"]; 
                 botonDeshabilitarVenta.onclick=function(){
                   deshabilitarventa(ventaIdParaDeshabilitar);
                 }
                 celdaOpcion.appendChild(botonDeshabilitarVenta); 
                 trRegistro.appendChild(celdaOpcion)
            }
        },
        error:function(error){
            alert("Error en la peticion ${error}");
        }
    })
 
}

//Paso para crear el registro de un venta ****
function registrarVenta() {
    
    let total = document.getElementById("total").value;
    let estado = document.getElementById("estado").value;
    let fecha_venta =document.getElementById("fecha_venta").value;

  
    let formData = {
        
        "total": total,
        "estado": estado,
        "fecha_venta": fecha_venta
    };

    if(validarCampos()){

        $.ajax({
          url: url,
          type: "POST",
          data: formData,
          success: function(result){
            Swal.fire({
              title: "Excelente",
              text: "Su registro se guardó correctamente",
              icon: "success"
            });
            // window.location.href= "http://127.0.0.1:5500/front_end/medicoRegistro.html";
          },
          error: function(error){
            Swal.fire("Error", "Error al guardar "+error.responseText, "error");
          }
        });
      }else{
       // alert("llena los campos correctamente")
        Swal.fire({
          title: "Error!",
          text: "complete los campos correctamente",
          icon: "error"
        });
      }
}


//Paso para que el usuario se registre y llene todos los datos correctamente :D****
function validarCampos() {
    var total = document.getElementById("total");
    var fecha_venta = document.getElementById("fecha_venta");  

    return validarTotal(total) && fecha_venta(fecha_venta);
}


function validarTotal(campo){
    var valido=true;
    if(campo.value.length < 3 || campo.value.length > 45){
        valido=false;
    }

    if (valido) {
        campo.className = "form-control is-valid"
    }
    else{
        campo.className = "form-control is-invalid"
    }
    return valido;
}
function validarIvaDescuento(campo){
    var valido=true;
    if(campo.value.length < 2 || campo.value.length > 2){
        valido=false;
    }

    if (valido) {
        campo.className = "form-control is-valid"
    }
    else{
        campo.className = "form-control is-invalid"
    }
    return valido;
}

function validarPrecio(Numero) {
    
    let valor = Numero.value;
    let valido = true;
    if (valor.length < 10 || valor.length >13) {
        valido = false
    }

    if (valido) {
        Numero.className = "form-control is-valid"
    }
    else{
        Numero.className = "form-control is-invalid"
    }
    return valido;
}


function validarDireccionCiudad(Direccion){
    let valor = Direccion.value;
    let valido = true;
    if (valor.length <=0 || valor.length >45) {
        valido = false
    }
    if (valido) {
      Direccion.className = "form-control is-valid"
    }
    else{
      Direccion.className = "form-control is-invalid"
    }
    return valido;
}


//Cuando le damos click al boton de guardar, este llamara a la function updateMedico por medio del onclick******
function updateventa() {
    var id_venta = document.getElementById("id_venta").value;

    let formData = {
        "nombre_venta": document.getElementById("nombre_venta").value,
        "descripcion": document.getElementById("descripcion").value,
        "cantidad": document.getElementById("cantidad").value,
        "precio": document.getElementById("precio").value,
        "porcentaje_iva": document.getElementById("porcentaje_iva").value,
        "porcentaje_descuento": document.getElementById("porcentaje_descuento").value,
        "estado": document.getElementById("estado").value
    };


    //Cuando estamos actualizando los datos, y lo hacemos correctamente Aparecerá la Alerta EXCELENTE *****
    if(validarCampos()){
    $.ajax({
        url: url + id_venta,
        type: "PUT",
        data: formData,
        success: function(result) {
            Swal.fire({
                title: "Excelente",
                text: "Su registro se actualizó correctamente",
                icon: "success"
            });
            
            var modal = document.getElementById("exampleModal"); 
            modal.style.display = "hide";
            
            listarventa(); //Lista los venta después de actualizar
        },
        error: function(error) {
            Swal.fire("Error", "Error al guardar", "error");
        }  
    });
    }else{
        Swal.fire({
            title: "Error!",
            text: "Complete los campos correctamente",
            icon: "error"
        });
        }
}


/* metodo para obtener los datos en el modal de actualizar*/ 
//1.Crear petición que traiga la información del medico por id
function consultarventaID(id_venta){
    //alert(id);
    $.ajax({
        url:url+id_venta,
        type:"GET",
        success: function(result){
            
            document.getElementById("id_venta").value=result["id_venta"];
            document.getElementById("nombre_venta").value=result["nombre_venta"];
            document.getElementById("descripcion").value=result["descripcion"];
            document.getElementById("cantidad").value=result["cantidad"];
            document.getElementById("precio").value=result["precio"];
            document.getElementById("porcentaje_iva").value=result["porcentaje_iva"];
            document.getElementById("porcentaje_descuento").value=result["porcentaje_descuento"];
            document.getElementById("estado").value=result["estado"];
        }
    });
}
function limpiar(){

    document.getElementById("nombre_venta").className="form-control";
    document.getElementById("descripcion").className="form-control";
    document.getElementById("cantidad").className="form-control";
    document.getElementById("precio").className="form-control";
    document.getElementById("porcentaje_iva").className="form-control";
    document.getElementById("porcentaje_descuento").className="form-control";
    document.getElementById("estado").className="form-control";

    
    document.getElementById("nombre_venta").value = "";
    document.getElementById("descripcion").value = "";
    document.getElementById("cantidad").value = "";
    document.getElementById("precio").value = "";
    document.getElementById("porcentaje_iva").value = "";
    document.getElementById("porcentaje_descuento").value = "";
    document.getElementById("estado").value="";
}
// funcion  de deshabilitar medico
function deshabilitarventa(id_venta){
    swal.fire({
      title: '¿Estás seguro?',
      text: "Esta opción no tiene marcha atrás",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor:'#3085d6',
      cancelButtonText:'Cancelar',
      cancelButtonColor:'#d33',
      confirmButtonText:'Sí, deshabilitar!',
  
    }).then((result)=>{
      if (result.isConfirmed){
        $.ajax({
          url: url +id_venta,
          type: "DELETE",
          success: function(result){
            swal.fire(
              'Deshabilitado',
              'El registro ha sido deshabilitado ',
              'success'
            );
            listarventa();//recarga la lista de medicos
          },
          error: function(error){
            Swal.fire(
              'Error',
              'No se puede deshabilitar el registro ',
              'Error',
            );
          }
        });
      }
    });
  }

  function CargarFormulario() {
    cargarCliente();
  }
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