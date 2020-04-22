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
	
		 if($('#nombreDeUsuario').val().length>2 && $('#nombreDeUsuario').val().length<=60){
			$('#nombreDeUsuario').addClass('border border-success');
			$('#nombreDeUsuario').removeClass('border border-danger');
		 }else{
			$('#nombreDeUsuario').removeClass('border border-success');
			$('#nombreDeUsuario').addClass('border border-danger');	
		 }
}