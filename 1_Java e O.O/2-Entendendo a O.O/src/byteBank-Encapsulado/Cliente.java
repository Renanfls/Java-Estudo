public class Cliente {
    private String nome;
    private String cpf;
    private String profissao;

    // Métodos nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    // Métodos cpf
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }
    // Métodos profissao
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
    public String getProfissao() {
        return profissao;
    }
}