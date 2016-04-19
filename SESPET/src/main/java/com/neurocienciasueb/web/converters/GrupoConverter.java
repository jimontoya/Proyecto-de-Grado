package com.neurocienciasueb.web.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.neurocienciasueb.web.controllers.GrupoController;
import com.neurocienciasueb.dto.Grupo;

@FacesConverter("GrupoConverter")
public class GrupoConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		GrupoController list = (GrupoController) context.getApplication().getELResolver().getValue(context.getELContext(), null, "grupoController");
		
		List<Grupo> listLoaded = list.listarTodo(); 
														
		for(Grupo  area : listLoaded){
			String compare = area.toString();
			if(value.equals(compare)){
				return area;
			}
		}											
		return 	null;	
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return arg2.toString();
	}
}
