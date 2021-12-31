package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

/**
 * Spring Security public built-in providers are automatically configured.
 * - Google
 * - Facebook
 * - GitHub
 * <p>
 * Custom providers require an issuer URI but a default is provided.
 */
public enum OAuth2Provider {
  GOOGLE(true, false),
  FACEBOOK(true, false),
  GITHUB(true, false),
  OKTA(true, true, "https://your-okta-domain/oauth2/default"),
  KEYCLOAK(false, true, "http://localhost:9080/auth/realms/jhipster"),
  AUTHO0(false, true, "https://your-auth0-domain/"),
  OTHER(false, true, "https://your-issuer-uri");

  private final boolean builtIn;
  private final boolean custom;
  private final String defaultIssuerUri;

  OAuth2Provider(boolean builtIn, boolean custom) {
    this(builtIn, custom, null);
  }

  OAuth2Provider(boolean builtIn, boolean custom, String defaultIssuerUri) {
    this.builtIn = builtIn;
    this.custom = custom;
    this.defaultIssuerUri = defaultIssuerUri;
  }

  public boolean isBuiltIn() {
    return builtIn;
  }

  public boolean isCustom() {
    return custom;
  }

  public String getDefaultIssuerUri() {
    return defaultIssuerUri;
  }
}
