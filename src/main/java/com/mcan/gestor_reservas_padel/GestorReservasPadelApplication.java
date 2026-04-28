package com.mcan.gestor_reservas_padel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mcan.gestor_reservas_padel", "com/mcan/gestor_reservas_padel/controllers", "com/mcan/gestor_reservas_padel/services", "com/mcan/gestor_reservas_padel/repositories"})
public class GestorReservasPadelApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorReservasPadelApplication.class, args);
	}

}
