package teste.com.controlador.taskListener;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class AnalisarTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        String status = (String) delegateTask.getVariable("status");

        if ("APROVADO".equalsIgnoreCase(status)) {
            delegateTask.setVariable("resultado", "Aprovado");
        } else if ("REPROVADO".equalsIgnoreCase(status)) {
            delegateTask.setVariable("resultado", "Reprovado");
        }
    }
}
