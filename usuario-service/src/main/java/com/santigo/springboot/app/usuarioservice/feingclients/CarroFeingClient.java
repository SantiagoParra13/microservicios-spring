package com.santigo.springboot.app.usuarioservice.feingclients;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.santigo.springboot.app.usuarioservice.models.Carro;

@FeignClient(name = "carro-service", url = "http://localhost:8082")
@Service
public interface CarroFeingClient {

    @PostMapping("/carro") // Mueve la anotación @RequestMapping a nivel de método
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/carro/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable("usuarioId")int usuarioId); 


}