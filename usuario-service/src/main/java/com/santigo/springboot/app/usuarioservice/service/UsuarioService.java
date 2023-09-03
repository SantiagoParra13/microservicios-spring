package com.santigo.springboot.app.usuarioservice.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.santigo.springboot.app.usuarioservice.entities.Usuario;
import com.santigo.springboot.app.usuarioservice.feingclients.CarroFeingClient;
import com.santigo.springboot.app.usuarioservice.feingclients.MotoFeingClient;
import com.santigo.springboot.app.usuarioservice.models.Carro;
import com.santigo.springboot.app.usuarioservice.models.Moto;
import com.santigo.springboot.app.usuarioservice.repository.UserRepository;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarroFeingClient carroFeingClient;

    @Autowired
    private MotoFeingClient motoFeingClient;

    @Autowired
    private UserRepository userRepository;

    public List<Usuario> getAll() {
        return userRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        Usuario nuevoUsuario = userRepository.save(usuario);
        return nuevoUsuario;
    }

    public Carro saveCarro(int usuarioId, Carro carro) {
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeingClient.save(carro);

        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeingClient.save(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = userRepository.findById(usuarioId).orElse(null);

        if (usuario == null) {
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }
        resultado.put("usuario", usuario);
        List<Carro> carros = carroFeingClient.getCarros(usuarioId);
        if (carros.isEmpty()) {
            resultado.put("Carros", "El usuario no tiene Carros");
        } else {
            resultado.put("Carros", carros);
        }

        List<Moto> motos = motoFeingClient.getMotos(usuarioId);
        if (motos.isEmpty()) {
            resultado.put("Motos", "El usuario no tiene motos");
        } else {
            resultado.put("Motos", carros);
        }

        return resultado;

    }
    // esto es con RestTemplate
    public List<Carro> getCarros(int usuarioId) {
        try {
            // Intenta realizar una llamada al servicio web
            ResponseEntity<List<Carro>> response = restTemplate.exchange(
                    "http://localhost:8082/carro/usuario/" + usuarioId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Carro>>() {
                    });

            if (response.getStatusCode() == HttpStatus.OK) {
                // Si la llamada al servicio web es exitosa, devuelve la lista de carros
                return response.getBody();
            } else {
                // Maneja el caso en el que el servidor responde con un código de estado
                // diferente a OK
                // Puedes registrar el error o lanzar una excepción personalizada si es
                // necesario
                // En este caso, se devuelve una lista vacía como valor predeterminado
                return Collections.emptyList();
            }
        } catch (RestClientException e) {
            // Si ocurre una excepción (por ejemplo, si la llamada al servicio web falla),
            // maneja la excepción aquí

            // Puedes registrar el error o lanzar una excepción personalizada si es
            // necesario
            // En este caso, se devuelve una lista vacía como valor predeterminado en caso
            // de error
            return Collections.emptyList();
        }
    }

    public List<Moto> getMotos(int usuarioId) {
        try {
            // Intenta realizar una llamada al servicio web
            ResponseEntity<List<Moto>> response = restTemplate.exchange(
                    "http://localhost:8083/moto/usuario/" + usuarioId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Moto>>() {
                    });

            if (response.getStatusCode() == HttpStatus.OK) {
                // Si la llamada al servicio web es exitosa, devuelve la lista de carros
                return response.getBody();
            } else {
                // Maneja el caso en el que el servidor responde con un código de estado
                // diferente a OK
                // Puedes registrar el error o lanzar una excepción personalizada si es
                // necesario
                // En este caso, se devuelve una lista vacía como valor predeterminado
                return Collections.emptyList();
            }
        } catch (RestClientException e) {
            // Si ocurre una excepción (por ejemplo, si la llamada al servicio web falla),
            // maneja la excepción aquí

            // Puedes registrar el error o lanzar una excepción personalizada si es
            // necesario
            // En este caso, se devuelve una lista vacía como valor predeterminado en caso
            // de error
            return Collections.emptyList();
        }
    }

}
