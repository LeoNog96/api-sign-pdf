package br.com.teste.signatureapi.dtos;

import org.springframework.web.multipart.MultipartFile;

public class SignatureWithImagesRequest {

    private MultipartFile certificate;
    private MultipartFile archive;
    private String certificatePassword;
    private String partSignatureName;
    private String signatureLocal;
    private String signatureReason;
    private MultipartFile image;
    private int positionX;
    private int positionY;
    private int page;

    public SignatureWithImagesRequest(MultipartFile certificate, MultipartFile archive, String certificatePassword,
                                      String partSignatureName, String signatureLocal, String signatureReason,
                                      MultipartFile image, int positionX, int positionY, int page) {
        this.certificate = certificate;
        this.archive = archive;
        this.certificatePassword = certificatePassword;
        this.partSignatureName = partSignatureName;
        this.signatureLocal = signatureLocal;
        this.signatureReason = signatureReason;
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
        this.page = page;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}