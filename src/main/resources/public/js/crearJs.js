/**
 * Código para js de Crear
 */

$(document).ready(function(e) {
	
	$('#peligro').empty().hide();
	
	/*ALIAS*/
	var idAlias = $('#alias').attr("id");
	var maxAlias = $('#alias').attr("maxlength");
	var minAlias = "4";
	
	
	/*APELLIDOS*/
	var idApellido = $('#apellidos').attr("id");
	var maxApellido = $('#apellidos').attr("maxlength");
	var minApellido = "3";
	
	/*NOMBRE_DE_USUARIO*/
	var idNombreDeUsuario = $('#nombreDeUsuario').attr("id");
	var maxNombreDeUsuario = $('#nombreDeUsuario').attr("maxlength");
	var minNombreDeUsuario = "3";
	
	/*PASSWORD O CONTRASEÑA*/
	var idPassword = $('#password').attr("id");
	var maxPassword = $('#password').attr("maxlength");
	var minPassword = "6";
	
	
	

	$("#repetirPassword").on({
		click : function() {
			passwordCorrecto();
		},
		keypress : function() {
			passwordCorrecto();
		},
		keydown : function() {
			passwordCorrecto();
		},
		keyup : function() {
			passwordCorrecto();
		}
	});
	
	$("#password").on({
		click : function() {
			tamano(idPassword,minPassword,maxPassword);
		},
		keypress : function() {
			tamano(idPassword,minPassword,maxPassword);
		},
		keydown : function() {
			tamano(idPassword,minPassword,maxPassword);
		},
		keyup : function() {
			tamano(idPassword,minPassword,maxPassword);
		}
	});
	
	
	$("#nombreDeUsuario").on({
		click : function() {
			tamano(idNombreDeUsuario,minNombreDeUsuario,maxNombreDeUsuario);
		},
		keypress : function() {
			tamano(idNombreDeUsuario,minNombreDeUsuario,maxNombreDeUsuario);
		},
		keydown : function() {
			tamano(idNombreDeUsuario,minNombreDeUsuario,maxNombreDeUsuario);
		},
		keyup : function() {
			tamano(idNombreDeUsuario,minNombreDeUsuario,maxNombreDeUsuario);
		}
	});
	
	$("#apellidos").on({
		click : function() {
			tamano(idApellido,minApellido,maxApellido);
		},
		keypress : function() {
			tamano(idApellido,minApellido,maxApellido);
		},
		keydown : function() {
			tamano(idApellido,minApellido,maxApellido);
		},
		keyup : function() {
			tamano(idApellido,minApellido,maxApellido);
		}
	});
	
		$("#alias").on({
		click : function() {
			tamano(idAlias,minAlias,maxAlias);
		},
		keypress : function() {
			tamano(idAlias,minAlias,maxAlias);
		},
		keydown : function() {
			tamano(idAlias,minAlias,maxAlias);
		},
		keyup : function() {
			tamano(idAlias,minAlias,maxAlias);
		}
	});
		
	$('#enviar').click(function(e) {//funcion para validar
		var bandera=true;
		$('#repetido,#peligro,#aliasJS,#passwordJS,#apellidosJS,#nombreDeUsuarioJS').empty();
		
		if(!tamano(idNombreDeUsuario,minNombreDeUsuario,maxNombreDeUsuario)){
			$('#nombreDeUsuarioJS').append('<small class="badge badge-warning">[ '+minNombreDeUsuario+' - '+maxNombreDeUsuario+' ]</small>');
			bandera=false;
		}
		
		if(!tamano(idApellido,minApellido,maxApellido)){
			$('#apellidosJS').append('<small class="badge badge-warning">[ '+minApellido+' - '+maxApellido+' ]</small>');
			bandera=false;
		}
		
		if(!tamano(idPassword,minPassword,maxPassword)){
			$('#passwordJS').append('<small class="badge badge-warning">[ '+minPassword+' - '+maxPassword+' ]</small>');
			bandera=false;
		}
		
		if(!tamano(idAlias,minAlias,maxAlias)){
			$('#aliasJS').append('<small class="badge badge-warning">[ '+minAlias+' - '+maxAlias+' ]</small>');
			bandera=false;
		}
		
		if(!passwordCorrecto()){
			$('#repetido').append('<small class="badge badge-warning">Warning - Error [They have to be the same - Tienen que ser iguales]</small>');
			bandera=false;
		}
			if(bandera){
				alert('Completado correctamente')
			}
			//para ir al principio de la página
			$('#principio').show(2000).focus();
			return bandera;
		});
	
 });




/**
 * Función para verificar los tamaños de string.
 * @param obj nombre del id
 * @param min mínimo tamaño permitido
 * @param max máximo tamaño permitido
 * @returns
 */
function tamano(obj,min,max){
	var ok=true;
	
		 if($('#'+obj).val().length>=min && $('#'+obj).val().length<=max){
			$('#'+obj).addClass('border border-success');
			$('#'+obj).removeClass('border border-danger');
		 }else{
			$('#'+obj).removeClass('border border-success');
			$('#'+obj).addClass('border border-danger');	
			ok=false;
		 }
		 
		 if(obj=='password'){
			 passwordCorrecto()
		 }
		 
		 return ok;
}

/**
 * Verifica que el password está bien escrito
 * @returns true si son iguales
 */
function passwordCorrecto() {
	var ps=$('#password').val();
	var rps=$('#repetirPassword').val();
	var boolean=ps==rps;
	

	 if(boolean){
		$('#repetirPassword').addClass('border border-success');
		$('#repetirPassword').removeClass('border border-danger');
	 }else{
		$('#repetirPassword').removeClass('border border-success');
		$('#repetirPassword').addClass('border border-danger');	
	 }
	 
	 return boolean;
}