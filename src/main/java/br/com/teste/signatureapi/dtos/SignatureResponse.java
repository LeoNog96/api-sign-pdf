package br.com.teste.signatureapi.dtos;

public class SignatureResponse {

    private byte[] arquivo;
    private String messagem;

    public SignatureResponse(byte[] arquivo, String messagem) {
        this.arquivo = arquivo;
        this.messagem = messagem;
    }

    public SignatureResponse(byte[] arquivo) {
        this.arquivo = arquivo;
        this.messagem = null;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}