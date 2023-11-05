package br.com.fiap.bikecheck.domain.test;

import br.com.fiap.bikecheck.domain.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestClasses {

    public void testaClasses() {

        var cliente = new Cliente(
                1L,
                "Angelica",
                LocalDate.of(1993, 3, 14),
                "Endereço",
                1234567891L,
                11912345678L,
                11912345678L,
                "angelica@fiap.com.br"
        );

        var bicicleta = new Bicicleta(
                1L,
                "Caloi",
                LocalDate.now(),
                40000.0,
                "Vermelho",
                "Peças bonitas",
                new byte[2],
                new ArrayList<>(),
                cliente
        );

        var seguro = new Seguro(
                1L,
                LocalDate.now(),
                "Empresa",
                cliente,
                bicicleta
        );

        var empresa = new Empresa(
                1L,
                131232131232L,
                "Minha Empresa",
                "Endereço",
                "Tecnologia",
                11912345678L,
                "empresa@empresa.com.br"
        );

        var vistoria = new Vistoria(
                1L,
                new byte[2],
                bicicleta,
                seguro,
                cliente
        );

        System.out.println(cliente);
        System.out.println(bicicleta);
        System.out.println(seguro);
        System.out.println(empresa);
        System.out.println(vistoria);
    }
}
