package vn.binh.Config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatJSPConfiguration_23110184 {
	@Bean

	public WebServerFactoryCustomizer<WebServerFactory> staticResourceCustomizer() {

		return factory -> {

			if (factory instanceof TomcatServletWebServerFactory tomcatFactory) {

				tomcatFactory.addContextCustomizers(
						context -> context.addLifecycleListener(new JSPStaticResourceConfigurer_23110184(context)));

			}

		};

	}

}
