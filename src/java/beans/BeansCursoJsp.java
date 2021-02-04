/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

public class BeansCursoJsp {

    private Long id;
    private String login;
    private String senha;
    private String nome;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String fotoBase64;
    private String contentType;
    private String tempFotoUser;
    private String curriculoBase64;
    private String contentTypeCurriculo;
    private String fotoBase64Miniatura;
    private boolean atualizarImage = true;
    private boolean atualizarPdf = true;
    private boolean ativo;
    private String sexo;
    private String perfil;

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String getTempFotoUser() {
        tempFotoUser = "data:"+contentType+";base64,"+fotoBase64;
        return tempFotoUser;
    }

    public boolean isAtualizarImage() {
        return atualizarImage;
    }

    public void setAtualizarImage(boolean atualizarImage) {
        this.atualizarImage = atualizarImage;
    }

    public boolean isAtualizarPdf() {
        return atualizarPdf;
    }

    public void setAtualizarPdf(boolean atualizarPdf) {
        this.atualizarPdf = atualizarPdf;
    }
    
    public String getCurriculoBase64() {
        return curriculoBase64;
    }

    public void setCurriculoBase64(String curriculoBase64) {
        this.curriculoBase64 = curriculoBase64;
    }

    public String getContentTypeCurriculo() {
        return contentTypeCurriculo;
    }

    public void setContentTypeCurriculo(String contentTypeCurriculo) {
        this.contentTypeCurriculo = contentTypeCurriculo;
    }
    
    
    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    private String ibge;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public BeansCursoJsp() {

    }

    public boolean validarUsuario(String login, String senha) {
        return this.login.equalsIgnoreCase(login)
                && this.senha.equalsIgnoreCase(login);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoBase64Miniatura() {
        return fotoBase64Miniatura;
    }

    public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
        this.fotoBase64Miniatura = fotoBase64Miniatura;
    }
    

}
