package br.com.teste.signatureapi.controllers;

import br.com.teste.signatureapi.dtos.SignatureRequest;
import br.com.teste.signatureapi.dtos.SignatureWithImagesRequest;
import br.com.teste.signatureapi.dtos.SignatureResponse;
import br.com.teste.signatureapi.services.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;

@RestController
@RequestMapping("v1/signatures")
public class SignatureController {

    private final SignatureService service;
    private final String path;

    @Autowired
    public SignatureController(Environment env) {
        this.service = new SignatureService(env);
        this.path =  System.getProperty("java.io.tmpdir");;
    }

    @PostMapping
    public ResponseEntity<SignatureResponse> sign(
            @RequestParam("certificate") MultipartFile certificate,
            @RequestParam("archive") MultipartFile archive,
            @RequestParam("certificatePassword") String certificatePassword,
            @RequestParam("partSignatureName") String partSignatureName,
            @RequestParam("signatureLocal") String signatureLocal,
            @RequestParam("signatureReason") String signatureReason) throws Exception {

        SignatureRequest request = new SignatureRequest(certificate, archive, certificatePassword, partSignatureName, signatureLocal, signatureReason);
        SignatureResponse result = service.sign(request, Paths.get(path));

        if (result != null) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(400).body(new SignatureResponse(null, "Falha ao assinar"));
        }
    }

    @PostMapping("with-images")
    public ResponseEntity<SignatureResponse> signWithImages(
            @RequestParam("certificate") MultipartFile certificate,
            @RequestParam("archive") MultipartFile archive,
            @RequestParam("certificatePassword") String certificatePassword,
            @RequestParam("partSignatureName") String partSignatureName,
            @RequestParam("signatureLocal") String signatureLocal,
            @RequestParam("signatureReason") String signatureReason,
            @RequestParam("image") MultipartFile image,
            @RequestParam("positionX") int positionX,
            @RequestParam("positionY") int positionY,
            @RequestParam("page") int page) throws Exception {

        SignatureWithImagesRequest request = new SignatureWithImagesRequest(certificate, archive, certificatePassword, partSignatureName, signatureLocal, signatureReason, image, positionX, positionY, page);
        SignatureResponse result = service.signWithImages(request, Paths.get(path));

        if (result != null) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(400).body(new SignatureResponse(null, "Falha ao assinar"));
        }
    }
}
