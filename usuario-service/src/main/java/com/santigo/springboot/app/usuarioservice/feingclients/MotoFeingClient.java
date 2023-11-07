package com.santigo.springboot.app.usuarioservice.feingclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.santigo.springboot.app.usuarioservice.models.Moto;


@FeignClient(name = "moto-service", url = "http://localhost:8083")
@Service
public interface MotoFeingClient {

    @PostMapping("/moto") // Mueve la anotación @RequestMapping a nivel de método
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/moto/usuario/{usuarioId}")
    public List<Moto> getMotos(@PathVariable("usuarioId")int usuarioId); 

    

}
