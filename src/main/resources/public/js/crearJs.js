/**
 * Código para js de Crear
 */

$(document).ready(function(e) {
	/**
	 * Validamos el nombre, siempre que tenga entre 0-50 caracteres, cuando se presiones, y obtenga y pierda el foco
	 */
	$("#nombreDeUsuario").on({
		 	keydown: function(){
			   nombre();
			  },
			  keypress: function(){
				nombre();
			  },
			  blur: function(){
					nombre();
				  },
			  focus: function(){
						nombre();
	}});
 });

function nombre(){
	alert('hola');
	
		 if($('#nombreDeUsuario').val().length>0 && $('#nombre').val().length<50){
			$('#nombreDeUsuario').addClass('border border-success');
			$('#nombreDeUsuario').removeClass('border border-danger');
			$('#sNombre').removeClass('glyphicon glyphicon-remove form-control-feedback text-danger');
			$('#sNombre').addClass('glyphicon glyphicon-ok form-control-feedback text-success');
		 }else{
			$('#nombreDeUsuario').removeClass('border border-success');
			$('#nombreDeUsuario').addClass('border border-danger');
			$('#sNombre').removeClass('glyphicon glyphicon-ok form-control-feedback text-success');
			$('#sNombre').addClass('glyphicon glyphicon-remove form-control-feedback text-danger');	
		 }
}