package ly.study.cy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;

@Configuration
public class MicrometerConfiguration {

	@Bean
	MeterRegistryCustomizer meterRegistryCustomizer(MeterRegistry meterRegistry) {
		return meterRegistry1 -> {
			meterRegistry.config().commonTags("application", "micrometer-gateway");
		};
	}
}
