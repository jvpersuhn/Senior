package br.com.senior.varejo.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImportClienteEventPayload {
    
    public ImportEventStatus eventpl;
    
    public ImportClienteEventPayload() {
    }
    
    /** 
     * This constructor allows initialization of all fields, required and optional.
     */
    public ImportClienteEventPayload(ImportEventStatus eventpl) {
        this.eventpl = eventpl;
    }
    
    public void validate() {
        validate(true);
    }
    
    public void validate(boolean required) {
        validate(null, required);
    }
    
    public void validate(Map<String, Object> headers, boolean required) {
    	validate(headers, required, new ArrayList<>());
    }
    
    void validate(Map<String, Object> headers, boolean required, List<Object> validated) {
    	PedidosValidator.validate(this, headers, required, validated);
    }
    @Override
    public int hashCode() {
        int ret = 1;
        if (eventpl != null) {
            ret = 31 * ret + eventpl.hashCode();
        }
        return ret;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImportClienteEventPayload)) {
            return false;
        }
        ImportClienteEventPayload other = (ImportClienteEventPayload) obj;
        if ((eventpl == null) != (other.eventpl == null)) {
            return false;
        }
        if ((eventpl != null) && !eventpl.equals(other.eventpl)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	toString(sb, new ArrayList<>());
    	return sb.toString();
    }
    
    void toString(StringBuilder sb, List<Object> appended) {
    	sb.append(getClass().getSimpleName()).append(" [");
    	if (appended.contains(this)) {
    		sb.append("<Previously appended object>").append(']');
    		return;
    	}
    	appended.add(this);
    	sb.append("eventpl=<");
    	if (eventpl == null) {
    		sb.append("null");
    	} else {
    		eventpl.toString(sb, appended);
    	}
    	sb.append('>');
    	sb.append(']');
    }
    
}
