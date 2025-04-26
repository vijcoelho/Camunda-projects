package teste.com.controlador.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String idUsuario;
    private String nome;
    private Long idade;
    private String status;
    private String processId;

    public Usuario() {
    }

    @PersistenceConstructor
    public Usuario(String nome, Long idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return idUsuario;
    }

    public void setId(String id) {
        this.idUsuario = id;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
