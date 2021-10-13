package br.com.zupproject.Mercado.Livre.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import static org.springframework.boot.actuate.health.Status.*;

@Component
public class HealthCheck implements HealthIndicator {

	@Override
	public Health health() {
		Map<String, Object> detalhes = new HashMap<>();
		detalhes.put("versao", "1.0.0");
		detalhes.put("descricao", "Health Check para o projeto do mercado livre");
		return Health.status(UP).withDetails(detalhes).build();
	}

}
