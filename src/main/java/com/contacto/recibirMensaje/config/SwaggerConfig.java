package com.contacto.recibirMensaje.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de configuracion de la libreria Springfox
 *
 * @author camilocardenas - Everis Colombia - para Porvenir
 * @version 1.0
 * @date 12/11/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Metodo encargada de configurar los parametros de swagger 2.0
	 *
	 * @return Docket
	 * @version 1.0
	 */

	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.contacto.autenticacion.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}
