/**
 * 
 */
package rm.tabou2.facade.spring.security;

import javax.servlet.ServletContext;

import org.ocpsoft.logging.Logger.Level;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.ocpsoft.rewrite.servlet.config.rule.JoinPath;

/**
 * @author FNI18300
 *
 */
public class RewriteHttpConfigurationProvider extends HttpConfigurationProvider {

	private static final String TABOU2_TIERS_PATH = "/tiers/{path}";
	private static final String TABOU2_TIERS = "/tiers";

	private static final String TABOU2_OPERATION_PATH = "/operations/{path}";
	private static final String TABOU2_OPERATION = "/operations";

	public RewriteHttpConfigurationProvider() {
		super();
	}

	@Override
	public Configuration getConfiguration(ServletContext context) {
		return ConfigurationBuilder.begin()
				.addRule().when(Direction.isInbound().and(Path.matches("/v2/api-docs"))).perform(Log.message(Level.INFO,"Request swagger").and(Forward.to("/v2/api-docs")))
				// V2 -> c'est l'officiel
				.addRule().when(Direction.isInbound().and(Path.matches("/v2/{path}"))).perform(Log.message(Level.INFO,"Request {path}").and(Forward.to("/{path}")))
				// V1/tiers** -> c'est l'officiel pour les tiers
				.addRule(Join.path("/v1/" + TABOU2_TIERS).to("/v1" +  TABOU2_TIERS))
				.addRule(Join.path("/v1/" + TABOU2_TIERS_PATH).to("/v1" +  TABOU2_TIERS_PATH))
				//Gestion de la redirection des opérations
				.addRule(Join.path("/v1/" + TABOU2_OPERATION).to("/v1" + TABOU2_OPERATION))
				.addRule(Join.path("/v1/" + TABOU2_OPERATION_PATH).to("/v1" + TABOU2_OPERATION_PATH))
				// V1/** -> c'est l'officiel 
				.addRule(Join.path("/v1/{path}").to("{path}"))
				// /tiers -> l'officiel c'est V1
				.addRule(Join.path(TABOU2_TIERS).to("/v1/" + TABOU2_TIERS))
				.addRule(Join.path(TABOU2_TIERS_PATH).to("/v1/" + TABOU2_TIERS_PATH))
				// Redirection des opérations actuelles vers la v1
				.addRule(Join.path(TABOU2_OPERATION).to("/v1" + TABOU2_OPERATION))
				.addRule(Join.path(TABOU2_OPERATION_PATH).to("/v1" + TABOU2_OPERATION_PATH));
	}

	@Override
	public int priority() {
		return 10;
	}

}