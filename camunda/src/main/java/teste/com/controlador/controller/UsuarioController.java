package teste.com.controlador.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/form")
    public String mostrarForms() {
        return "custom-forms";
    }

    @PostMapping("/start-process")
    public String startProcess(
            @RequestParam("nome") String nome,
            @RequestParam("idade") Long idade,
            Model model
    ) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                "controller-process",
                Variables
                        .putValue("nome", nome)
                        .putValue("idade", idade)
                        .putValue("status", "ANALISE")
        );
        model.addAttribute("mensagem", instance.getId());
        return "sucesso";
    }

    @GetMapping("/atualizar-status")
    public String mostrarAtualizarStatusForm() {
        return "atualizar-status";
    }

    @PostMapping("/atualizar-status")
    public String atualizarStatus(
            @RequestParam("processoId") String processoId,
            @RequestParam("status") String status,
            Model model
    ) {
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processoId)
                .singleResult();
        if (instance != null) {
            runtimeService.setVariable(processoId, "status", status);
            taskService.createTaskQuery()
                    .processInstanceId(processoId)
                    .list()
                    .forEach(task -> taskService.complete(task.getId()));
            model.addAttribute("mensagem", "Status atualizado com sucesso para: " + status);
        } else {
            model.addAttribute("mensagem", "Processo não encontrado.");
        }

        return "sucesso";
    }
}
