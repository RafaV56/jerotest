package tk.jeroapp.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;

import tk.jeroapp.entitys.Rol;

@Component
public class UsuarioPropertyEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Rol rol=new Rol();
		rol.setNombre(text);
		super.setValue(rol);
	}
	

}
