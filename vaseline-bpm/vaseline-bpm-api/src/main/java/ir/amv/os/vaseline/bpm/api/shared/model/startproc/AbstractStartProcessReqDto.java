package ir.amv.os.vaseline.bpm.api.shared.model.startproc;


import ir.amv.os.vaseline.bpm.api.shared.model.BaseBpmRequestDto;

import java.util.Map;

/**
 * Abstract Start Process Request DTO to be implemented by subclasses.
 * Subclasses can customize the variables map and process definition key.
 */
public abstract class AbstractStartProcessReqDto extends BaseBpmRequestDto {

    private static final long serialVersionUID = 1L;

    public abstract String getProcessDefinitionKey();

    public abstract Map<String, Object> getVariablesMap();

}
