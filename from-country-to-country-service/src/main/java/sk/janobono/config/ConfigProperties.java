package sk.janobono.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "app")
@ConstructorBinding
@Validated
public record ConfigProperties(
        @NotBlank String dataLink
) {
}
