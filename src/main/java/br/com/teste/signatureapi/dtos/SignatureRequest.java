package br.com.teste.signatureapi.dtos;

import org.springframework.web.multipart.MultipartFile;

public class SignatureRequest {

    private MultipartFile certificate;
    private MultipartFile archive;
    private String certificatePassword;
    private String partSignatureName;
    private String signatureLocal;
    private String signatureReason;

    public SignatureRequest(MultipartFile certificate, MultipartFile archive, String certificatePassword,
                            String partSignatureName, String signatureLocal, String signatureReason) {
        this.certificate = certificate;
        this.archive = archive;
        this.certificatePassword = certificatePassword;
        this.partSignatureName = partSignatureName;
        this.signatureLocal = signatureLocal;
        this.signatureReason = signatureReason;
    }

    public MultipartFile getCertificate() {
        return certificate;
    }

    public void setCertificate(MultipartFile certificate) {
        this.certificate = certificate;
    }

    public MultipartFile getArchive() {
        return archive;
    }

    public void setArchive(MultipartFile archive) {
        this.archive = archive;
    }

    public String getCertificatePassword() {
        return certificatePassword;
    }

    public void setCertificatePassword(String certificatePassword) {
        this.certificatePassword = certificatePassword;
    }

    public String getPartSignatureName() {
        return partSignatureName;
    }

    public void setPartSignatureName(String partSignatureName) {
        this.partSignatureName = partSignatureName;
    }

    public String getSignatureLocal() {
        return signatureLocal;
    }

    public void setSignatureLocal(String signatureLocal) {
        this.signatureLocal = signatureLocal;
    }

    public String getSignatureReason() {
        return signatureReason;
    }

    public void setSignatureReason(String signatureReason) {
        this.signatureReason = signatureReason;
    }
}
