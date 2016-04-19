package com.neurocienciasueb.web.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.neurocienciasueb.web.controllers.ClasificacionEntrenamientoController;
import com.neurocienciasueb.dto.ClasificacionEntrenamiento;

@FacesConverter("ClasificacionEntrenamientoConverter")
public class ClasificacionEntrenamientoConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		ClasificacionEntrenamientoController list = (ClasificacionEntrenamientoController) context.getApplication().getELResolver().getValue(context.getELContext(), null, "clasificacionEntrenamientoController");
		
		List<ClasificacionEntrenamiento> listLoaded = list.listarTodo(); 
														
		for(ClasificacionEntrenamiento  area : listLoaded){
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
