//se almacena la url de la api
let url="http://localhost:8080/api/v1/cliente/";
function listarCliente() {
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
            let curpoTablaCliente = document.getElementById("curpoTablaCliente");
            curpoTablaCliente.innerHTML="";
            for (let i = 0; i < result.length; i++) {
               //se crea una etiqueta tr por cada registro
                let trRegistro = document.createElement("tr");//fila por cada registro de la tabla
                let celdaId = document.createElement("td");
                let celdaTipoIdentificacion = document.createElement("td");
                let celdaIdentificacion = document.createElement("td");
                let celdaNombre = document.createElement("td");
                let celdaApellidos = document.createElement("td");
                let celdaTelefono = document.createElement("td");
                let celdaDireccion = document.createElement("td");
                let celdaCiudad = document.createElement("td");
                let celdaCorreo = document.createElement("td");
                let celdaEstado = document.createElement("td");
                // let celdaEditar = document.createElement("td");
                
                //almacenamos en valor
                
                celdaId.innerText = result[i]["id_cliente"];
                celdaTipoIdentificacion.innerText= result[i]["tipo_identificacion"];
                celdaIdentificacion.innerText = result[i]["identificacion"];
                celdaNombre.innerText = result[i]["nombres_cliente"];
                celdaApellidos.innerText = result[i]["apellidos_cliente"];
                celdaTelefono.innerText = result[i]["telefono"];
                celdaDireccion.innerText = result[i]["direccion"];
                celdaCiudad.innerText = result[i]["ciudad"];
                celdaCorreo.innerText = result[i]["correo_electronico"];
                celdaEstado.innerText = result[i]["estado"];
                
                //agregando a los td a su respectivo th y agregandolos a la fila

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaTipoIdentificacion);
                trRegistro.appendChild(celdaIdentificacion);
                trRegistro.appendChild(celdaNombre);
                trRegistro.appendChild(celdaApellidos);
                trRegistro.appendChild(celdaTelefono);
                trRegistro.appendChild(celdaDireccion);
                trRegistro.appendChild(celdaCiudad);
                trRegistro.appendChild(celdaCorreo);
                trRegistro.appendChild(celdaEstado);
                
                //boton editar 
                let celdaOpcion= document.createElement("td");
                let botonEditarCliente= document.createElement("button");
                botonEditarCliente.value=result[i]["id_cliente"];
                botonEditarCliente.innerHTML="Editar"; 

                botonEditarCliente.onclick=function(e){
                    $('#exampleModal').modal('show');
                    consultarClienteID(this.value); 
                }
                botonEditarCliente.className= "btn btn-primary"

                celdaOpcion.appendChild(botonEditarCliente); 
                trRegistro.appendChild(celdaOpcion);

                curpoTablaCliente.appendChild(trRegistro);//se traen todos los registros

                 //boton desahiblitar- la funcion de deshabilitar se encuentra abajo 
                 let botonDeshabilitarCliente= document.createElement("button");
                 botonDeshabilitarCliente.innerHTML="<i class='fa-solid fa-trash'></i>"; 
                 botonDeshabilitarCliente.className="btn btn-danger"; 
 
                 let clienteIdParaDeshabilitar= result[i]["id_cliente"]; 
                 botonDeshabilitarCliente.onclick=function(){
                   deshabilitarCliente(clienteIdParaDeshabilitar);
                 }
                 celdaOpcion.appendChild(botonDeshabilitarCliente); 
                 trRegistro.appendChild(celdaOpcion)
            }
        },
        error:function(error){
            alert("Error en la peticion ${error}");
        }
    })
 
}

//Paso para crear el registro de un cliente ****
function registrarCliente() {
    
    let tipo_identificacion = document.getElementById("tipo_identificacion").value;
    let identificacion =document.getElementById("identificacion").value;
    let nombres_cliente = document.getElementById("nombres_cliente").value;
    let apellidos_cliente = document.getElementById("apellidos_cliente").value;
    let telefono = document.getElementById("telefono").value;
    let direccion = document.getElementById("direccion").value;
    let ciudad = document.getElementById("ciudad").value;
    let correo_electronico = document.getElementById("correo_electronico").value;
    let estado = document.getElementById("estado").value;
  

    let formData = {
        
        "tipo_identificacion": tipo_identificacion,
        "identificacion": identificacion,
        "nombres_cliente": nombres_cliente,
        "apellidos_cliente": apellidos_cliente,
        "direccion": direccion,
        "telefono": telefono,
        "ciudad": ciudad,
        "correo_electronico": correo_electronico,
        "estado": estado
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
            // window.location.href= "http://127.0.0.1:5500/front_end/clienteRegistro.html";
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
    var identificacion = document.getElementById("identificacion");
    var nombres_cliente = document.getElementById("nombres_cliente"); 
    var apellidos_cliente = document.getElementById("apellidos_cliente"); 
    var direccion = document.getElementById("direccion"); 
    var telefono=document.getElementById("telefono");
    var ciudad = document.getElementById("ciudad");  

    return validarIdentificacion(identificacion) && validarNombresApellidos(nombres_cliente) 
         && validarNombresApellidos(apellidos_cliente) && validarTelefono(telefono) && validarDireccionCiudad(direccion)
         && validarDireccionCiudad(ciudad);
}

function validarIdentificacion(cuadroNumero) {
    if (!cuadroNumero || !cuadroNumero.value) {
        return false;
    }

    let valor = cuadroNumero.value;
    let valido = true;
    if (valor.length < 5 || valor.length > 10) {
        valido = false;
    }

    if (valido) {
        cuadroNumero.className = "form-control is-valid";
    } else {
        cuadroNumero.className = "form-control is-invalid";
    }
    return valido;
}


function validarNombresApellidos(campo){
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


function validarTelefono(Numero) {
    
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


//Cuando le damos click al boton de guardar, este llamara a la function UpdateCliente por medio del onclick******
function updateCliente() {
    var id_Cliente = document.getElementById("id_Cliente").value;

    let formData = {
        "tipo_documento": document.getElementById("tipo_documento").value,
        "identificacion": document.getElementById("identificacion").value,
        "nombres_cliente": document.getElementById("nombres_cliente").value,
        "apellidos_cliente": document.getElementById("apellidos_cliente").value,
        "direccion": document.getElementById("direccion").value,
        "telefono": document.getElementById("telefono").value,
        "ciudad": document.getElementById("ciudad").value,
        "correo_electronico": document.getElementById("correo_electronico").value,
        "estado": document.getElementById("estado").value
    };


    //Cuando estamos actualizando los datos, y lo hacemos correctamente Aparecerá la Alerta EXCELENTE *****
    if(validarCampos()){
    $.ajax({
        url: url + id_Cliente,
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
            
            listarCliente(); //Lista los médicos después de actualizar
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
//1.Crear petición que traiga la información del cliente por id
function consultarClienteID(id_Cliente){
    //alert(id);
    $.ajax({
        url:url+id_Cliente,
        type:"GET",
        success: function(result){
            
            document.getElementById("id_Cliente").value=result["id_Cliente"];
            document.getElementById("tipo_Identificacion").value=result["tipo_Identificacion"];
            document.getElementById("identificacion").value=result["identificacion"];
            document.getElementById("nombres_cliente").value=result["nombres_cliente"];
            document.getElementById("apellidos_cliente").value=result["apellidos_cliente"];
            document.getElementById("direccion").value=result["direccion"];
            document.getElementById("telefono").value=result["telefono"];
            document.getElementById("correo_electronico").value=result["correo_electronico"];
            document.getElementById("ciudad").value=result["ciudad"];
            document.getElementById("estado").value=result["estado"];
        }
    });
}
function limpiar(){
    // document.getElementById("tipo_Identificacion").value = "";
    document.getElementById("identificacion").className="form-control";
    document.getElementById("nombres_cliente").className="form-control";
    document.getElementById("apellidos_cliente").className="form-control";
    document.getElementById("direccion").className="form-control";
    document.getElementById("telefono").className="form-control";
    document.getElementById("correo_electronico").className="form-control";
    document.getElementById("ciudad").className="form-control";
    document.getElementById("estado").className="form-control";

    // document.getElementById("tipo_Identificacion").value = "";
    document.getElementById("identificacion").value = "";
    document.getElementById("nombres_cliente").value = "";
    document.getElementById("apellidos_cliente").value = "";
    document.getElementById("direccion").value = "";
    document.getElementById("telefono").value = "";
    document.getElementById("correo_electronico").value = "";
    document.getElementById("ciudad").value = "";
    document.getElementById("estado").value="";
}
// funcion  de deshabilitar cliente
function deshabilitarCliente(id_Cliente){
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
          url: url +id_Cliente,
          type: "DELETE",
          success: function(result){
            swal.fire(
              'Deshabilitado',
              'El registro ha sido deshabilitado ',
              'success'
            );
            listarCliente();//recarga la lista de clientes
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