package com.hamitmizrak.innova_springboot.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/*
public interface IAuditLogRepository extends JpaRepository<AuditLogEntity, Long> {
}
*/

@Repository
public class IAuditLogRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // AuditLogEntity ekleme metodu
    public int save(AuditLogEntity auditLogEntity) {
        String sql = "INSERT INTO audit_logs (message, timestamp) VALUES (?, ?)";
        return jdbcTemplate.update(sql, auditLogEntity.getMessage(), auditLogEntity.getTimestamp());
    }

    // Birden fazla AuditLogEntity kaydetme metodu
    public int[] saveAll(List<AuditLogEntity> auditLogEntities) {
        String sql = "INSERT INTO audit_logs (entity_name, action, performed_by, timestamp, message) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.batchUpdate(
                sql,
                new org.springframework.jdbc.core.BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement ps, int i) throws SQLException {
                        AuditLogEntity auditLogEntity = auditLogEntities.get(i);
                        ps.setString(1, auditLogEntity.getEntityName());
                        ps.setString(2, auditLogEntity.getAction());
                        ps.setString(3, auditLogEntity.getPerformedBy());
                        ps.setTimestamp(4, new java.sql.Timestamp(auditLogEntity.getTimestamp().getTime()));
                        ps.setString(5, auditLogEntity.getMessage());
                        //ps.setObject(6, auditLogEntity.getTimestamp());
                    }

                    @Override
                    public int getBatchSize() {
                        return auditLogEntities.size();
                    }
                }
        );
    }



    // AuditLogEntity listeleme metodu
    public List<AuditLogEntity> getAllAuditLogs() {
        String sql = "SELECT * FROM audit_logs";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            AuditLogEntity auditLogEntity = new AuditLogEntity();
            auditLogEntity.setId(rs.getLong("id"));
            auditLogEntity.setMessage(rs.getString("message"));
            auditLogEntity.setTimestamp(rs.getTimestamp("timestamp"));
            return auditLogEntity;
        });
    }

    // ID'ye göre AuditLogEntity getirme
    public AuditLogEntity getAuditLogById(Long id) {
        String sql = "SELECT * FROM audit_logs WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            AuditLogEntity auditLogEntity = new AuditLogEntity();
            auditLogEntity.setId(rs.getLong("id"));
            auditLogEntity.setMessage(rs.getString("message"));
            auditLogEntity.setTimestamp(rs.getTimestamp("timestamp"));
            return auditLogEntity;
        });
    }

    // ID'ye göre AuditLogEntity silme
    public int deleteAuditLogById(Long id) {
        String sql = "DELETE FROM audit_logs WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // AuditLogEntity güncelleme metodu
    public int updateAuditLog(AuditLogEntity auditLogEntity) {
        String sql = "UPDATE audit_logs SET message = ?, timestamp = ? WHERE id = ?";
        return jdbcTemplate.update(sql, auditLogEntity.getMessage(), auditLogEntity.getTimestamp(), auditLogEntity.getId());
    }
}
