package br.com.fiap.bikecheck.domain.repository.impl;

import br.com.fiap.bikecheck.domain.entity.Cliente;
import br.com.fiap.bikecheck.domain.repository.Repository;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ClienteRepository implements Repository<Cliente, Long> {
    private ConnectionFactory connectionFactory;

    private static final AtomicReference<ClienteRepository> instance = new AtomicReference<>();

    private ClienteRepository() {
        this.connectionFactory = ConnectionFactory.build();
    }

    public static ClienteRepository build() {
        instance.compareAndSet(null, new ClienteRepository());
        return instance.get();
    }


    @Override
    public List<Cliente> findAll() {
        List<Cliente> list = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        var sql = "SELECT * FROM T_BC_CLIENTE";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long id = rs.getLong("ID_CLIENTE");
                    String nome = rs.getString("NM_CLIENTE");
                    LocalDate dataNascimento = rs.getDate("DT_NASCIMENTO").toLocalDate();
                    String endereco = rs.getString("DS_ENDERECO");
                    Long cpf = rs.getLong("NR_CPF");
                    Long telefone = rs.getLong("NR_TELEFONE");
                    Long telefoneResidencial = rs.getLong("NR_TELEFONE_RESIDENCIAL");
                    String email = rs.getString("NR_TELEFONE_RESIDENCIAL");

                    list.add(new Cliente(
                            id,
                            nome,
                            dataNascimento,
                            endereco,
                            cpf,
                            telefone,
                            telefoneResidencial,
                            email)
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os clientes");
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }

    @Override
    public Cliente findById(Long id) {
        Cliente cliente = null;
        var sql = "SELECT * FROM T_BC_CLIENTE where ID_CLIENTE=?";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    String nome = rs.getString("NM_CLIENTE");
                    LocalDate dataNascimento = rs.getDate("DT_NASCIMENTO").toLocalDate();
                    String endereco = rs.getString("DS_ENDERECO");
                    Long cpf = rs.getLong("NR_CPF");
                    Long telefone = rs.getLong("NR_TELEFONE");
                    Long telefoneResidencial = rs.getLong("NR_TELEFONE_RESIDENCIAL");
                    String email = rs.getString("NR_TELEFONE_RESIDENCIAL");

                    cliente = new Cliente(
                            id,
                            nome,
                            dataNascimento,
                            endereco,
                            cpf,
                            telefone,
                            telefoneResidencial,
                            email);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possivel realizar a consulta: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, conn);
        }
        return cliente;
    }

    @Override
    public Cliente persist(Cliente cliente) {
        var sql = "INSERT INTO T_BC_CLIENTE " +
                " (ID_CLIENTE, NM_CLIENTE, DT_NASCIMENTO, DS_ENDERECO, NR_CPF, NR_TELEFONE, NR_TELEFONE_RESIDENCIAL, DS_EMAIL) " +
                " values (0, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNome());
            ps.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            ps.setString(3, cliente.getEndereco());
            ps.setLong(4, cliente.getCpf());
            ps.setLong(5, cliente.getTelefone());
            ps.setLong(6, cliente.getTelefoneResidencial());
            ps.setString(7, cliente.getEmail());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                cliente.setId(id);
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel salvar o cliente no banco de dados:  " + e.getMessage());
        } finally {
            fecharObjetos(null, ps, conn);
        }

        return cliente;
    }
}
