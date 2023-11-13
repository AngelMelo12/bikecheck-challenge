package br.com.fiap.bikecheck.domain.repository.impl;

import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.entity.Cliente;
import br.com.fiap.bikecheck.domain.service.impl.BicicletaService;
import br.com.fiap.bikecheck.domain.service.impl.ClienteService;
import br.com.fiap.bikecheck.infra.ConnectionFactory;
import br.com.fiap.bikecheck.domain.entity.Seguro;
import br.com.fiap.bikecheck.domain.repository.Repository;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SeguroRepository implements Repository<Seguro, Long> {

    private final ClienteService clienteService;
    private final BicicletaService bicicletaService;

    private final ConnectionFactory connectionFactory;

    private static final AtomicReference<SeguroRepository> instance = new AtomicReference<>();

    private SeguroRepository() {
        this.clienteService = new ClienteService();
        this.bicicletaService = new BicicletaService();
        this.connectionFactory = ConnectionFactory.build();
    }

    public static SeguroRepository build() {
        instance.compareAndSet(null, new SeguroRepository());
        return instance.get();
    }


    @Override
    public List<Seguro> findAll() {

        List<Seguro> list = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        var sql = "SELECT * FROM T_BC_SEGURO";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long id = rs.getLong("ID_SEGURO");
                    LocalDate dataContratacao = rs.getDate("DT_CONTRATACAO_SEGURO").toLocalDate();
                    String nome = rs.getString("NM_EMPRESA");
                    Long idCliente = rs.getLong("ID_CLIENTE");
                    Long idBicicleta = rs.getLong("ID_BICICLETA");
                    Cliente cliente = clienteService.findById(idCliente);
                    Bicicleta bicicleta = bicicletaService.findById(idBicicleta);

                    list.add(new Seguro(
                            id,
                            dataContratacao,
                            nome,
                            cliente,
                            bicicleta)
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os seguros");
            return List.of();
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }

    @Override
    public Seguro findById(Long id) {
        Seguro seguro = null;
        var sql = "SELECT * FROM T_BC_SEGURO where ID_SEGURO=?";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    LocalDate dataContratacao = rs.getDate("DT_CONTRATACAO_SEGURO").toLocalDate();
                    String nome = rs.getString("NM_EMPRESA");
                    Long idCliente = rs.getLong("ID_CLIENTE");
                    Long idBicicleta = rs.getLong("ID_BICICLETA");
                    Cliente cliente = clienteService.findById(idCliente);
                    Bicicleta bicicleta = bicicletaService.findById(idBicicleta);

                    seguro = new Seguro(
                            id,
                            dataContratacao,
                            nome,
                            cliente,
                            bicicleta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possivel realizar a consulta: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, conn);
        }
        return seguro;
    }

    public Seguro findByBicicleta(Long idBicicleta) {
        Seguro seguro = null;
        var sql = "SELECT * FROM T_BC_SEGURO where ID_BICICLETA=?";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, idBicicleta);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long id = rs.getLong("ID_SEGURO");
                    LocalDate dataContratacao = rs.getDate("DT_CONTRATACAO_SEGURO").toLocalDate();
                    String nome = rs.getString("NM_EMPRESA");
                    Long idCliente = rs.getLong("ID_CLIENTE");
                    Cliente cliente = clienteService.findById(idCliente);
                    Bicicleta bicicleta = bicicletaService.findById(idBicicleta);

                    seguro = new Seguro(
                            id,
                            dataContratacao,
                            nome,
                            cliente,
                            bicicleta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possivel realizar a consulta: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, conn);
        }
        return seguro;
    }

    @Override
    public Seguro persist(Seguro seguro) {
        var sql = "INSERT INTO T_BC_SEGURO " +
                " (DT_CONTRATACAO_SEGURO, NM_EMPRESA, ID_CLIENTE, ID_BICICLETA) " +
                " values (?, ?, ?, ?)";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(seguro.getDataContratacao()));
            ps.setString(2, seguro.getNomeEmpresa());
            ps.setLong(3, seguro.getCliente().getId());
            ps.setLong(4, seguro.getBicicleta().getId());
            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                seguro.setId(id);
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel salvar o seguro no banco de dados:  " + e.getMessage());
        } finally {
            fecharObjetos(null, ps, conn);
        }

        return seguro;
    }
}
