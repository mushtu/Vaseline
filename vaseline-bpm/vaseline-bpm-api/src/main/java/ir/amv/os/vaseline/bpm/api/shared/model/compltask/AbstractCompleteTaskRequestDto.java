package ir.amv.os.vaseline.bpm.api.shared.model.compltask;

import ir.amv.os.vaseline.bpm.api.shared.model.BaseBpmRequestDto;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCompleteTaskRequestDto extends BaseBpmRequestDto {

    private static final long serialVersionUID = 1L;
    protected Map<String, Object> variablesMap = new HashMap<String, Object>();
    private String description;
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract Map<String, Object> getVariables();

    public void addVariable(String key, Object object) {
        variablesMap.put(key, object);
    }
}
