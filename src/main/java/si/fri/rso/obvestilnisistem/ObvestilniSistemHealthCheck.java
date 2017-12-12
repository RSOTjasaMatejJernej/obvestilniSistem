package si.fri.rso.obvestilnisistem;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class ObvestilniSistemHealthCheck implements HealthCheck {

        @Inject
        private RestProperties restProperties;

        @Override
        public HealthCheckResponse call() {

            if (restProperties.isHealthy()) {
                return HealthCheckResponse.named(ObvestilniSistemHealthCheck.class.getSimpleName()).up().build();
            } else {
                return HealthCheckResponse.named(ObvestilniSistemHealthCheck.class.getSimpleName()).down().build();
            }

        }


}
