package com.noida.microservices.limitsservices;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class Configuration {

	int maximum;
	int minimum;
	public int getMaximum() {
		return maximum;
	}
	public int getMinimum() {
		return minimum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
}
