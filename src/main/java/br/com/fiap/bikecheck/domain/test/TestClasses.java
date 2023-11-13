package br.com.fiap.bikecheck.domain.test;

import br.com.fiap.bikecheck.domain.entity.*;
import br.com.fiap.bikecheck.domain.service.impl.*;

import java.time.LocalDate;

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

        var empresa = new Empresa(
                1L,
                131232131232L,
                "Minha Empresa",
                "Endereço",
                "Tecnologia",
                11912345678L,
                "empresa@empresa.com.br"
        );

        var bicicleta = new Bicicleta(
                1L,
                "Bicicleta",
                LocalDate.now(),
                42700.0,
                "Azul",
                "Aço",
                new byte[2]
        );

        var seguro = new Seguro(
                1L,
                LocalDate.now(),
                "Empresa",
                cliente,
                bicicleta
        );
        var vistoria = new Vistoria(
                1L,
                new byte[2],
                bicicleta,
                seguro
        );

        System.out.println(cliente);
        System.out.println(bicicleta);
        System.out.println(seguro);
        System.out.println(empresa);
        System.out.println(vistoria);

        BicicletaService bicicletaService = new BicicletaService();
        ClienteService clienteService = new ClienteService();
        EmpresaService empresaService = new EmpresaService();
        SeguroService seguroService = new SeguroService();
        VistoriaService vistoriaService = new VistoriaService();

//        System.out.println(bicicletaService.persist(bicicleta));
//        System.out.println(clienteService.persist(cliente));

        System.out.println(bicicletaService.findAll());
        System.out.println(clienteService.findAll());
        System.out.println(empresaService.findAll());
        System.out.println(seguroService.findAll());
        System.out.println(vistoriaService.findAll());

    }
}
