package ca.logmein.pokergameapi.web.error;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BadRequestAlertException extends AbstractThrowableProblem {

    public static final URI DEFAULT_TYPE = URI.create("/problem-with-message");
    private static final long serialVersionUID = 1L;

    private static Map<String, Object> getAlertParameters(final String entityName, final String errorKey) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }

    private final String entityName;

    private final String errorKey;

    public BadRequestAlertException(final String defaultMessage, final String entityName, final String errorKey) {
        this(DEFAULT_TYPE, defaultMessage, entityName, errorKey);
    }

    public BadRequestAlertException(final URI type, final String defaultMessage, final String entityName, final String errorKey) {
        super(type, defaultMessage, Status.BAD_REQUEST, null, null, null, getAlertParameters(entityName, errorKey));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }
}
