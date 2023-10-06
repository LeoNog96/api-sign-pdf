package br.com.teste.signatureapi.services;

import br.com.teste.signatureapi.dtos.SignatureRequest;
import br.com.teste.signatureapi.dtos.SignatureWithImagesRequest;
import br.com.teste.signatureapi.dtos.SignatureResponse;
import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.pdf_signature.AssinaPdf;
import br.com.swconsultoria.pdf_signature.AssinaPdfImagem;
import br.com.swconsultoria.pdf_signature.dom.AssinaturaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SignatureService {

    private final Environment env;

    public SignatureService(Environment env) {
        this.env = env;
    }

    public SignatureResponse sign(SignatureRequest request, Path uploadsFolderPath) throws Exception {
        try {
            String pathAssinado = uploadsFolderPath.resolve("pdfSign-" + getCurrentTimeSpan() + ".pdf").toString();
            String certificatePath = saveFileTemp(request.getCertificate(), uploadsFolderPath);
            String archive = saveFileTemp(request.getArchive(), uploadsFolderPath);
            Certificado certificate = CertificadoService.certificadoPfx(certificatePath, request.getCertificatePassword());
            AssinaturaModel assinaturaModel = new AssinaturaModel();

            assinaturaModel.setCaminhoPdf(archive);
            assinaturaModel.setCaminhoPdfAssinado(pathAssinado);
            assinaturaModel.setCertificado(certificate);
            assinaturaModel.setNomeAssinatura(request.getPartSignatureName());
            assinaturaModel.setLocalAssinatura(request.getSignatureLocal());
            assinaturaModel.setMotivoAssinatura(request.getSignatureReason());
            assinaturaModel.setSenhaCertificado(request.getCertificatePassword().toCharArray());

            AssinaPdf assinaPdf = new AssinaPdf(assinaturaModel);
            assinaPdf.assina();
            byte[] arrayByte = Files.readAllBytes(Paths.get(pathAssinado));
            assinaPdf = null;
           // deleteFileTemp(certificatePath);
            //deleteFileTemp(archive);

            return new SignatureResponse(arrayByte);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public SignatureResponse signWithImages(SignatureWithImagesRequest request, Path uploadsFolderPath) throws Exception {
        String pathAssinado = uploadsFolderPath.resolve("pdfSignWithImage-" + getCurrentTimeSpan() + ".pdf").toString();
        try {
            String certificatePath = saveFileTemp(request.getCertificate(), uploadsFolderPath);
            String archive = saveFileTemp(request.getArchive(), uploadsFolderPath);
            String image = saveFileTemp(request.getImage(), uploadsFolderPath);
            Certificado certificate = CertificadoService.certificadoPfx(certificatePath, request.getCertificatePassword());

            AssinaturaModel assinaturaModel = new AssinaturaModel();
            assinaturaModel.setCaminhoPdf(archive);
            assinaturaModel.setCaminhoPdfAssinado(pathAssinado);
            assinaturaModel.setCertificado(certificate);
            assinaturaModel.setNomeAssinatura(request.getPartSignatureName());
            assinaturaModel.setLocalAssinatura(request.getSignatureLocal());
            assinaturaModel.setMotivoAssinatura(request.getSignatureReason());
            assinaturaModel.setSenhaCertificado(request.getCertificatePassword().toCharArray());
            assinaturaModel.setPagina(request.getPage());
            assinaturaModel.setCaminhoImagem(image);
            assinaturaModel.setPosicaoX(request.getPositionX());
            assinaturaModel.setPosicaoY(request.getPositionY());
            assinaturaModel.setZoomImagem(-50);

            AssinaPdfImagem assinaPdf = new AssinaPdfImagem(assinaturaModel);
            assinaPdf.assina();
            byte[] arrayByte = Files.readAllBytes(Paths.get(pathAssinado));
            assinaPdf = null;
            //deleteFileTemp(certificatePath);
            //deleteFileTemp(archive);
            //deleteFileTemp(pathAssinado);
            //deleteFileTemp(image);

            return new SignatureResponse(arrayByte);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String saveFileTemp(MultipartFile file, Path uploadsFolderPath) throws Exception {
        Path uploadedTargetFilePath = uploadsFolderPath.resolve(UUID.randomUUID().toString() + "-" + file.getOriginalFilename());
        Files.copy(file.getInputStream(), uploadedTargetFilePath);
        if (Files.isRegularFile(uploadedTargetFilePath)) {
            return uploadedTargetFilePath.toString();
        }
        return null;
    }

    private void deleteFileTemp(String path) throws Exception {
        Files.delete(Paths.get(path));
    }

    private Duration getCurrentTimeSpan() {
        LocalDateTime currentTime = LocalDateTime.now();
        return Duration.between(LocalDateTime.MIN, currentTime);
    }
}
