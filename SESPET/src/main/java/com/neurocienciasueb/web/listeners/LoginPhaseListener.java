package com.neurocienciasueb.web.listeners;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component
public class LoginPhaseListener implements PhaseListener{
		
	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		Exception e = (Exception) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap()
				.get(WebAttributes.AUTHENTICATION_EXCEPTION);
				
		if (e instanceof BadCredentialsException) {
			FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap()
			.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Credenciales Invalidas Intente De Nuevo",
							null));
		}
                
                if (e instanceof UsernameNotFoundException) {
                    
			FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap()
			.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							e.getMessage(),
							null));
		}
	}

	@Override
	public PhaseId getPhaseId() {
		 return PhaseId.RENDER_RESPONSE;
	}
}
