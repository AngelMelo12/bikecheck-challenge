package br.com.fiap.bikecheck.domain.repository.impl;

import br.com.fiap.bikecheck.domain.entity.Empresa;
import br.com.fiap.bikecheck.domain.repository.Repository;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EmpresaRepository implements Repository<Empresa, Long> {
    private ConnectionFactory connectionFactory;

    private static final AtomicReference<EmpresaRepository> instance = new AtomicReference<>();

    private EmpresaRepository() {
        this.connectionFactory = br.com.fiap.infra.ConnectionFactory.build();
    }

    public static EmpresaRepository build() {
        instance.compareAndSet(null, new EmpresaRepository());
        return instance.get();
    }


    @Override
    public List<Empresa> findAll() {
        List<Empresa> list = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        var sql = "SELECT * FROM T_BC_EMPRESA";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long id = rs.getLong("ID_EMPRESA");
                    Long cnpj = rs.getLong("NR_CNPJ");
                    String nome = rs.getString("NM_EMPRESA");
                    String endereco = rs.getString("DS_ENDERECO");
                    String segmentos = rs.getString("DS_SEGMENTOS");
                    Long telefone = rs.getLong("NR_TELEFONE");
                    String email = rs.getString("DS_EMAIL");

                    list.add(new Empresa(
                            id,
                            cnpj,
                            nome,
                            endereco,
                            segmentos,
                            telefone,
                            email)
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar as empresas");
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }

    @Override
    public Empresa findById(Long id) {
        Empresa empresa = null;
        var sql = "SELECT * FROM T_BC_EMPRESA where ID_EMPRESA=?";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long cnpj = rs.getLong("NR_CNPJ");
                    String nome = rs.getString("NM_EMPRESA");
                    String endereco = rs.getString("DS_ENDERECO");
                    String segmentos = rs.getString("DS_SEGMENTOS");
                    Long telefone = rs.getLong("NR_TELEFONE");
                    String email = rs.getString("DS_EMAIL");

                    empresa = new Empresa(
                            id,
                            cnpj,
                            nome,
                            endereco,
                            segmentos,
                            telefone,
                            email);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possivel realizar a consulta: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, conn);
        }
        return empresa;
    }

    @Override
    public Empresa persist(Empresa empresa) {
        var sql = "INSERT INTO T_BC_EMPRESA " +
                " (ID_EMPRESA, NR_CNPJ, NM_EMPRESA, DS_ENDERECO, DS_SEGMENTOS, NR_TELEFONE, DS_EMAIL) " +
                " values (0, ?, ?, ?, ?, ?, ?)";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, empresa.getCnpj());
            ps.setString(2, empresa.getNome());
            ps.setString(3, empresa.getEndereco());
            ps.setString(4, empresa.getSegmentos());
            ps.setLong(5, empresa.getTelefone());
            ps.setString(6, empresa.getEmail());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                empresa.setId(id);
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel salvar a empresa no banco de dados:  " + e.getMessage());
        } finally {
            fecharObjetos(null, ps, conn);
        }

        return empresa;
    }
}
