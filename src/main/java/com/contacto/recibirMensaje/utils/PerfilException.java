package com.contacto.recibirMensaje.utils;

public class PerfilException extends RuntimeException{
	public PerfilException(){
		super("usuario y clave erronea");
	}
}
